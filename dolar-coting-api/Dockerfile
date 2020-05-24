FROM openjdk:8-jdk-alpine
LABEL maintainer="leonardofa@gmail.com"
COPY target/*.jar app.jar
CMD java -XX:+PrintFlagsFinal -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -jar /app.jar
EXPOSE 8080
