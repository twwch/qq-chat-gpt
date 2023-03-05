FROM openjdk:17-oracle
ADD target/qq-robot-1.0-SNAPSHOT.jar /app/app.jar
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
VOLUME ["/app/bots"]
ENTRYPOINT 9543
ENTRYPOINT ["java", "-Dfile.encoding=utf-8", "-Djava.security.edg=file:/dev/./urandom", "-Duser.timezone=Asia/Shanghai", "-Xmx128m", "-Xms64m", "--config-dir", "/app/bots", "-jar", "/app/app.jar"]
