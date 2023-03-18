# step 1: build jar file
FROM maven:3.8-openjdk-11-slim AS builder
WORKDIR /app
COPY . ./
RUN mvn dependency:go-offline && mvn package

# step 2: build recorder-lark image
FROM openjdk:11-jre-slim
LABEL MAINTAINER="unickcheng" GitHUb="https://github.com/penfriendhub/recorder-lark"
WORKDIR /app
COPY --from=builder /app/target/recorder-lark-*.jar ./recorder-lark.jar
ENTRYPOINT ["java", "-jar", "recorder-lark.jar", "--spring.profiles.active=prod"]
HEALTHCHECK --interval=15s --timeout=2s CMD curl -f http://localhost:${SERVER_PORT:-"19091"}/lark/ping || exit 1
