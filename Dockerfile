FROM openjdk:11-ea-17-jre
ADD target/qq-robot-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT 9915
ENTRYPOINT ["java", "-Dfile.encoding=utf-8", "-Djava.security.edg=file:/dev/./urandom", "-Duser.timezone=Asia/Shanghai", "-Xmx128m", "-Xms64m", "-jar", "/app.jar"]
