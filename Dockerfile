## First stage: Build the project using Maven with JDK 17
#FROM maven:3.8.6-openjdk-17 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
## Second stage: Create the actual image using JRE 17
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=build /app/target/*.jar /app/sport-app-api.jar
#ENTRYPOINT ["java", "-jar", "/app/sport-app-api.jar"]
FROM openjdk:17-alpine

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]