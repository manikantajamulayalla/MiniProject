FROM ubuntu:latest
LABEL authors="manik"

ENTRYPOINT ["top", "-b"]
FROM maven:3.8.5-openjdk-17 AS build
COPY src .
# RUN mvn clean package-DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/MiniProject-0.0.1-SNAPSHOT.jar MiniProject.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","MiniProject.jar"]
