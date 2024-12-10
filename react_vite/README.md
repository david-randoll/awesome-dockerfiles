# React Vite with Docker

This directory provides Dockerfiles for deploying a React Vite application. You can choose between three different
Dockerfiles depending on your needs:

1. [**Dockerfile**](Dockerfile): General-purpose Dockerfile. Should provide the smallest image size.
2. [**Dockerfile_alpine**](Dockerfile_alpine): This is the same as the [Dockerfile](Dockerfile) without the user
   creation.
3. [**Dockerfile_nginx**](Dockerfile_nginx): Uses `nginx:alpine` for serving the React Vite app.

## Setup Instructions

### 1. Add a `.dockerignore` File

Create a [`.dockerignore`](.dockerignore) file in the root of your project.

### 2. Add an `nginx.conf` File

Create an [`nginx.conf`](nginx.conf) file in the root of your project. This file is used by the Dockerfile to configure
the server.

### 3. Build the Docker Image

#### Using the General Dockerfile

```bash
docker build -t react-vite .
```

#### Using the Alpine Dockerfile

```bash
docker build -f Dockerfile_alpine -t react-vite:alpine .
```

#### Using the NGINX Dockerfile

```bash
docker build -f Dockerfile_nginx -t react-vite:nginx .
```

### 4. Using Docker Slim

We can use Docker Slim to reduce the size further.

By running the below command, we are telling Docker Slim to build an image with the target `react-vite` and include the
paths `/var/www/html`, `/var/log/nginx`, `/var/lib/nginx`, and `/run/nginx`. We are instructing Docker Slim to include
these paths because they are required for nginx to run properly.

```bash
docker run --rm -it -v /var/run/docker.sock:/var/run/docker.sock dslim/slim build --target react-vite --include-path /var/www/html --include-path /var/log/nginx --include-path /var/lib/nginx --include-path /run/nginx
```

After successfully running the above command, you will see a new image with name `react-vite.slim` in your Docker

## Running the Container

After building the image, run the container using the following command:

```bash
docker run -p 3000:80 react-vite
```

## Contributing

If you have improvements or additional configurations for the React Vite setup, feel free to open a pull request!

---

Enjoy deploying your React Vite application with Docker!

## References

-   https://www.geeksforgeeks.org/how-to-run-nginx-for-root-non-root/
-   https://medium.com/@pierre.fourny/optimized-docker-setup-for-vite-powered-react-apps-e7b7f5a82bb4
-   https://youtu.be/t779DVjCKCs?si=xSdHkSH6YaZ7h54i
