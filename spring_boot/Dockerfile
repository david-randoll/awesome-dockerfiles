# https://medium.com/@RoussiAbdelghani/optimizing-java-base-docker-images-size-from-674mb-to-58mb-c1b7c911f622
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine AS jre-builder
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN apk add --no-cache binutils && \
    # Determine the modules required by the application
    jar -xf app.jar && \
    jdeps --ignore-missing-deps -q  \
        --recursive  \
        --multi-release 21  \
        --print-module-deps  \
        --class-path 'BOOT-INF/lib/*'  \
        app.jar > modules.txt && \
    # Create a custom JRE
    "$JAVA_HOME/bin/jlink" \
        --verbose \
        --add-modules $(cat modules.txt) \
        --strip-debug \
        --no-man-pages \
        --no-header-files \
        --compress=2 \
        --output /javaruntime && \
    rm -rf BOOT-INF META-INF app.jar modules.txt

# Create a final image
FROM alpine:3.20
ENV JAVA_HOME=/opt/jdk/jdk-21
ENV PATH="${JAVA_HOME}/bin:${PATH}"
ARG APP_USER=spring
COPY --from=jre-builder /javaruntime $JAVA_HOME
RUN addgroup --system --gid 1001 "$APP_USER" && \
    adduser --system --uid 1001 --home /app --ingroup "$APP_USER" "$APP_USER" && \
    chown -R "$APP_USER":"$APP_USER" /app
WORKDIR /app
COPY --from=build --chown=$APP_USER:$APP_USER /app/target/*.jar app.jar
USER $APP_USER
EXPOSE 8080
ENV server.port=8080
CMD ["java", "-jar", "app.jar"]