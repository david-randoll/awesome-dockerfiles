# .NET with Docker

This directory contains Dockerfiles for building and deploying .NET applications. You can choose the appropriate Dockerfile based on your needs.

## Files Included

### 1. [Dockerfile](./Dockerfile)

-   Produces the smallest possible image for your .NET application.

### 2. [Dockerfile_chiseled](./Dockerfile_chiseled)

-   Uses the `mcr.microsoft.com/dotnet/runtime-deps:8.0-jammy-chiseled` base image.
-   Ideal for highly secure and minimal environments.

### 3. [Dockerfile_mcr](./Dockerfile_mcr)

-   Based on `mcr.microsoft.com/dotnet/aspnet:8.0-alpine`.
-   Balances image size and compatibility.

### 4. Add a `.dockerignore` File

Copy the [.dockerignore](.dockerignore) file in the root of your project.

## Building the Docker Image

### Using the Regular Dockerfile

```bash
docker build -t dotnet-app .
```

### Using Dockerfile_chiseled

```bash
docker build -f Dockerfile_chiseled -t dotnet-app:chiseled .
```

### Using Dockerfile_mcr

```bash
docker build -f Dockerfile_mcr -t dotnet-app:mcr .
```

## Running the Docker Container

Once the image is built, run the container using:

```bash
docker run -p 5000:5001 dotnet-app
```

Replace `dotnet-app` with the appropriate tag if using `chiseled` or `mcr` versions.

## Contributing

If you have ideas for improving or adding new Docker configurations for .NET, feel free to contribute by opening a pull request.

---

## References

-   https://www.mytechramblings.com/posts/testing-chiseled-ubuntu-containers-with-dotnet/
-   https://andrewlock.net/exploring-the-dotnet-8-preview-updates-to-docker-images-in-dotnet-8/
-   https://medium.com/@jeroenverhaeghe/creating-smaller-and-more-secure-docker-images-for-net-core-8c74101e9027
