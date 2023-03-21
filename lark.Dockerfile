# step 1: build jar file
FROM maven:3.8-openjdk-11-slim AS builder
WORKDIR /app
COPY . ./
RUN mvn dependency:go-offline && mvn package

# step 2: build base image
FROM openjdk:11-jre-slim AS starter
#RUN sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list &&\
#    sed -i 's|security.debian.org/debian-security|mirrors.ustc.edu.cn/debian-security|g' /etc/apt/sources.list
RUN apt-get update &&\
    apt-get install -yq --no-install-recommends curl &&\
    rm -rf /var/lib/apt/lists/*

# step 3: build recorder-lark image
FROM starter
LABEL MAINTAINER="unickcheng" GitHUb="https://github.com/penfriendhub/recorder-lark"
WORKDIR /app
COPY --from=builder /app/target/recorder-lark-*.jar ./recorder-lark.jar
ENTRYPOINT ["java", "-jar", "recorder-lark.jar", "--spring.profiles.active=prod"]
HEALTHCHECK --interval=15s --timeout=2s CMD curl -f http://localhost:${SERVER_PORT:-"19091"}/ping || exit 1
