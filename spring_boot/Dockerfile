# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENV server.port=8080
CMD ["java", "-jar", "app.jar"]