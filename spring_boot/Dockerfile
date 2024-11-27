# https://medium.com/@RoussiAbdelghani/optimizing-java-base-docker-images-size-from-674mb-to-58mb-c1b7c911f622
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
RUN addgroup --system --gid 1001 spring && \
    adduser --system --uid 1001 spring && \
    mkdir -p /app && \
    chown -R spring:spring /app
WORKDIR /app
COPY --from=build --chown=spring:spring /app/target/*.jar app.jar
USER spring
EXPOSE 8080
ENV server.port=8080
CMD ["java", "-jar", "app.jar"]