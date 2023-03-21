# Hi, there 👋

`recorder-lark` 为飞书机器人开发者提供统计飞书群聊信息。请注意，在使用前请确保让您的群聊用户知晓群聊信息的使用情况。

**!!! 注意：目前仓库还在测试阶段，所有数据格式暂不稳定。!!!**

# 必要条件

- redis 数据库，用于存储聊天数据集
- 飞书企业账户（允许非认证）
- 在 [开发者后台](https://open.feishu.cn/app) 创建飞书机器人

# 启动方式1：docker

在 docker run 之前，请先配置好如下参数。另外注意，如果您是使用 windows docker，请将 \ 替换成 ` 
- `LARK_TOKEN` 飞书机器人 token
- `LARK_ENCRYPT_KEY` 飞书机器人消息事件加密密钥
- `LARK_APP_ID` 飞书应用 ID
- `LARK_APP_SECRET` 飞书应用密钥
- `REDIS_HOST` redis 数据库地址
- `REDIS_PORT` redis 数据库端口号
- `REDIS_PASS` redis 数据库密码
```bash
docker run -itd \
  --name=recorder-lark \
  --restart unless-stopped \
  -e TZ=Asia/Shanghai \
  -e LARK_TOKEN=larktoken \
  -e LARK_ENCRYPT_KEY=larkEncryptkey \
  -e LARK_APP_ID=larkappid \
  -e LARK_APP_SECRET=larkappsecret \
  -e REDIS_HOST=your.example.com \
  -e REDIS_PORT=99999 \
  -e REDIS_PASS=password \
  -p "19091:19091" \
  ghcr.io/penfriendhub/recorder-lark:latest
```
如果您更喜欢使用 `docker compose up -d` 来启动，可参考如下 `docker-compose.yml` 文件内容
```yml
version: "3.9"
services:
  recorder-lark:
    image: ghcr.io/penfriendhub/recorder-lark:latest
    container_name: recorder-lark
    restart: unless-stopped
    environment:
      - TZ=Asia/Shanghai
      - LARK_TOKEN=larktoken
      - LARK_ENCRYPT_KEY=larkEncryptkey
      - LARK_APP_ID=larkappid
      - LARK_APP_SECRET=larkappsecret
      - REDIS_HOST=your.example.com
      - REDIS_PORT=99999
      - REDIS_PASS=password
    ports:
      - "19091:19091"
```

# 启动方式2：jar 包

在执行下述命令之前，请在环境变量中提前配置好如下变量信息
- `LARK_TOKEN` 飞书机器人 token
- `LARK_ENCRYPT_KEY` 飞书机器人消息事件加密密钥
- `LARK_APP_ID` 飞书应用 ID
- `LARK_APP_SECRET` 飞书应用密钥
- `REDIS_HOST` redis 数据库地址
- `REDIS_PORT` redis 数据库端口号
- `REDIS_PASS` redis 数据库密码
```bash
# 下载源码
git clone https://github.com/penfriendhub/recorder-lark.git
cd recorder-lark
# 下载依赖和打包
mvn dependency:go-offline && mvn package
# 将 jar 包移动到根目录, 并重命名
mv target/recorder-lark-*.jar recorder-lark.jar
# 启动 jar 包
java -jar recorder-lark.jar --spring.profiles.active=prod
```