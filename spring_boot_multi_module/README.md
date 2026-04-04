# Spring Boot Multi-Module with Docker

This directory provides Dockerfiles for containerizing a multi-module Spring Boot application. It demonstrates how to
structure and build a project with a shared library module (`common`) and a web application module (`web`).

## Project Structure

```
spring_boot_multi_module/
├── pom.xml              # Parent POM (aggregator)
├── common/              # Shared library module
│   ├── pom.xml
│   └── src/main/java/com/davidr/common/
│       ├── model/Greeting.java
│       └── service/GreetingService.java
└── web/                 # Spring Boot web application
    ├── pom.xml
    └── src/main/java/com/davidr/web/
        ├── Application.java
        └── HelloController.java
```

-   **common** — A plain JAR library providing shared models and services. Does not include the Spring Boot Maven plugin.
-   **web** — The Spring Boot application that depends on `common`. Produces the executable fat JAR.

## Files Included

#### 1. [Dockerfile](./Dockerfile)

-   Builds a lightweight Docker image for your multi-module Spring Boot application.
-   Utilizes `jdeps` to analyze dependencies and include only the required modules in the JRE.

#### 2. [Dockerfile_EclipseTemurin](./Dockerfile_EclipseTemurin)

-   Uses the `eclipse-temurin` base image to build and run your Spring Boot application.
-   Ideal for environments requiring the Eclipse Temurin JDK.

## Key Differences from Single-Module Builds

1.  **POM copying** — Each module's `pom.xml` is copied separately to leverage Docker layer caching.
2.  **Build command** — Uses `mvn clean package -DskipTests -pl web -am` to build the web module and its dependencies.
3.  **JAR path** — The executable JAR is at `web/target/*.jar`, not `target/*.jar`.

## Setup Instructions

### 1. Add a [.dockerignore](./.dockerignore) File

Copy the [`.dockerignore`](.dockerignore) file in the root of your project.

### 2. Dockerfile

Create a [Dockerfile](Dockerfile) in the root of your project. This file contains the necessary commands to build the
Docker image.

### 3. Building the Docker Image

#### Using the Regular Dockerfile

```bash
docker build -t spring-boot-multi-module-app .
```

#### Using Dockerfile_EclipseTemurin

```bash
docker build -f Dockerfile_EclipseTemurin -t spring-boot-multi-module-app:temurin .
```

### 4. Using Docker Slim (Optional)

We can use Docker Slim to reduce the size further.

**Note**: docker slim can remove some files that are required for your application to run. So, it is recommended to test
the application after using Docker Slim.

```bash
docker run --rm -it -v /var/run/docker.sock:/var/run/docker.sock dslim/slim build --target spring-boot-multi-module-app
```

## Running the Docker Container

After building the image, run the container with:

```bash
docker run -p 8080:8080 spring-boot-multi-module-app
```

Replace `spring-boot-multi-module-app` with the appropriate tag if using the `EclipseTemurin` version.

### Endpoints

-   `GET /` — Returns "Hello, World!"
-   `GET /greet?name=David` — Returns a JSON greeting from the shared `common` module

## Contributing

If you have additional configurations or optimizations for Spring Boot Dockerfiles, feel free to submit a pull request.

---

## References

-   https://snyk.io/blog/jlink-create-docker-images-spring-boot-java/
-   https://medium.com/@RoussiAbdelghani/optimizing-java-base-docker-images-size-from-674mb-to-58mb-c1b7c911f622
