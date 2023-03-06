
# 部署方式
1. 修改 `src/resources/simbot-bots/bot.json`
2. 修改 `src/resources/application.yml` 中 `spring.profiles.active` 为 `prod`
    ```yaml
    spring:
      profiles:
        active: prod
    ```
3. 本地打包得到jar文件
4. 服务器上制作docker镜像

   Dockerfile
   ```Dockerfile
   FROM openjdk:17-oracle
   ADD qq-robot-1.0-SNAPSHOT.jar /app.jar
   RUN bash -c 'touch /app.jar'
   WORKDIR /app
   ENTRYPOINT 9543
   ENTRYPOINT ["java", "-Dfile.encoding=utf-8", "-Djava.security.edg=file:/dev/./urandom", "-Duser.timezone=Asia/Shanghai", "-Xmx128m", "-Xms64m", "-jar", "/app.jar"]
   ``` 
   
   将jar和Dockerfile放在同一个目录下执行一下语句
   ```shell
   docker build . --file Dockerfile --tag twwch/qq-chat-gpt:master
   ```
   
   因为需要和终端做交互(输入验证码)，因此前台启动
   ```shell
   docker run -it -m 1800m  --restart=always --net=host --name qq-robot-chatgpt \
      -v /etc/hosts:/etc/hosts \
      -v /etc/localtime:/etc/localtime \
      twwch/qq-chat-gpt:master
   ```

   启动完成后会出现一个链接，点击去验证获取授权码，拿到ticket后，输入即可，所有授权完成后，退出程序，然后重启程序即可
   ```shell
   docker restart qq-robot-chatgpt
   ```