FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/beer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/app.jar"]