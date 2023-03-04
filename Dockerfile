FROM openjdk:17-oracle
ADD target/qq-robot-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
ENTRYPOINT 9543
ENTRYPOINT ["java", "-Dfile.encoding=utf-8", "-Djava.security.edg=file:/dev/./urandom", "-Duser.timezone=Asia/Shanghai", "-Xmx128m", "-Xms64m", "-jar", "/app/app.jar"]
