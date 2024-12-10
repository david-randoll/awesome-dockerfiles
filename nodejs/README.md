# Node.js with Docker

This directory provides a Dockerfile to containerize your Node.js application for development and production use. This example uses an express server, but you can modify it to fit your needs.

## Setup Instructions

### 1. Add a `.dockerignore` File

Create a [`.dockerignore`](.dockerignore) file in the root of your project.

### 2. Dockerfile

Create a [Dockerfile](Dockerfile) in the root of your project. This file contains the necessary commands to build the Docker image.

### 3. Build the Docker Image

Run the following command to build the Docker image:

```bash
docker build -t nodejs-app .
```

### 4. Run the Docker Container

Once the image is built, run the container with:

```bash
docker run -p 3000:3000 nodejs-app
```

Replace `nodejs-app` with your desired image name.

## Contributing

If you have suggestions or enhancements for this Docker setup, feel free to open a pull request.

---

## References

-   https://github.com/vercel/ncc
-   https://webbylab.com/blog/minimal-size-docker-image-for-your-nodejs-app/
