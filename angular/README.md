# Angular with Docker

This directory provides Dockerfiles for deploying an Angular application. You can choose between several
configurations tailored to your needs.

> **Note**: This implementation uses a pre-built Angular application approach for ultra-fast builds and extremely 
> small image sizes. The main Dockerfile produces an image that's only ~98KB in size!

---

## Files Included

### 1. [Dockerfile](Dockerfile)

-   Uses a custom-built BusyBox image as the base image for ultra-lightweight deployment (~98KB).

### 2. [Dockerfile_alpine](Dockerfile_alpine)

-   Uses `alpine:3.20` as the base image. The application runs as the root user. For a non-root version, refer to the
    [Dockerfile_alpine_nonroot](Dockerfile_alpine_nonroot) file.

### 3. [Dockerfile_alpine_nonroot](Dockerfile_alpine_nonroot)

-   Also based on `alpine:3.20`, but configured to run the application as a non-root user for enhanced security.

### 4. [Dockerfile_busybox](Dockerfile_busybox)

-   Uses `busybox` as the base image, optimized for extremely small image sizes.

### 5. [Dockerfile_nginx](Dockerfile_nginx)

-   Uses `nginx:alpine` as the base image for serving the Angular application with NGINX (~53MB).

---

## Image Size Comparison

Here's a comparison of the resulting image sizes. The `.slim` images are created using Docker Slim for further
optimization.

The `Dockerfile` creates an image measured in kilobytes, making it perfect for production deployments!

---

## Setup Instructions

### 1. Add a `.dockerignore` File

Copy the [`.dockerignore`](.dockerignore) file to the root of your project to exclude unnecessary files from your image.

### 2. Add an `nginx.conf` File (Optional)

If you're using one of the NGINX-based Dockerfiles, create an [`nginx.conf`](nginx.conf) file in the root of your
project. This configuration includes:

- Angular routing support (fallback to index.html)
- Static asset caching
- Security headers
- Gzip compression

**Note**: Only use this file if you are using one of the NGINX Dockerfiles.

### 3. Build the Docker Image

#### General Dockerfile

```bash
docker build -t angular .
```

#### Alpine Dockerfile

```bash
docker build -f Dockerfile_alpine -t angular .
```

#### Alpine Non-Root Dockerfile

```bash
docker build -f Dockerfile_alpine_nonroot -t angular .
```

#### Busybox Dockerfile

```bash
docker build -f Dockerfile_busybox -t angular .
```

#### NGINX Dockerfile

```bash
docker build -f Dockerfile_nginx -t angular .
```

---

## Optimizing with Docker Slim (Optional)

Use Docker Slim to further reduce the image size.

**Note**: Docker Slim might remove essential files. Test your application thoroughly after slimming down the image.

#### For `Dockerfile_alpine_nonroot`

```bash
docker run --rm -it -v /var/run/docker.sock:/var/run/docker.sock dslim/slim build --target angular --include-path /var/www/html --include-path /var/log/nginx --include-path /var/lib/nginx --include-path /run/nginx
```

#### For Other Dockerfiles (excluding BusyBox)

```bash
docker run --rm -it -v /var/run/docker.sock:/var/run/docker.sock dslim/slim build --target angular --include-path /var/www/html
```

After successfully running the above commands, you will see a new image named `angular.slim` in your Docker images.

---

## Running the Container

After building the image, you can run the container with the following command:

**For BusyBox-based images (Dockerfile, Dockerfile_busybox):**
```bash
docker run -p 3000:3000 angular
```

**For NGINX-based images (Dockerfile_alpine, Dockerfile_alpine_nonroot, Dockerfile_nginx):**
```bash
docker run -p 3000:80 angular
```

Open your browser and navigate to `http://localhost:3000` to see your Angular application running in Docker!

---

## Features

This Angular Docker setup includes:

- **Multi-stage builds** for optimized image sizes
- **Angular CLI** for building the application
- **TypeScript support** out of the box
- **Production optimization** with Angular build optimizations
- **Routing support** for single-page application navigation
- **Security headers** in NGINX configurations
- **Gzip compression** for faster loading
- **Static asset caching** for improved performance
- **Non-root user options** for enhanced security

---

## Customization

### Environment Variables

You can customize the build by modifying the Angular configuration or using build-time arguments:

```bash
docker build --build-arg NODE_ENV=production -t angular .
```

### Angular Configuration

The application is built using the production configuration by default. To customize:

1. Modify `angular.json` for build configurations
2. Update `package.json` scripts as needed
3. Add environment files in `src/environments/`

### NGINX Configuration

Customize the `nginx.conf` file to:
- Add custom headers
- Modify caching policies
- Configure SSL/TLS (for HTTPS)
- Add custom location blocks

---

## Contributing

If you have improvements or additional configurations for the Angular setup, feel free to open a pull request!

---

## References

-   [Angular CLI Documentation](https://angular.io/cli)
-   [Docker Multi-stage Builds](https://docs.docker.com/develop/dev-best-practices/dockerfile_best-practices/#use-multi-stage-builds)
-   [NGINX Docker Image](https://hub.docker.com/_/nginx)
-   [Smallest Docker Image for Static Websites](https://lipanski.com/posts/smallest-docker-image-static-website)
-   [Docker Static Website Repository](https://github.com/lipanski/docker-static-website/tree/master)
-   [Angular Deployment Guide](https://angular.io/guide/deployment)