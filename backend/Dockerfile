FROM maven:3.8.5-openjdk-17-slim AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/beer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/app.jar"]