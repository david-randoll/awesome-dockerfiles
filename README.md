# Dockerfiles Repository

Welcome to the Dockerfiles repository! This repo is dedicated to housing optimized Dockerfiles for various platforms,
making it easier to create lightweight and efficient Docker images for your projects.

## Why Use These Dockerfiles?

The Dockerfiles in this repository are optimized to significantly reduce image sizes while maintaining functionality.
Here are some stats for the current platforms:

| Platform       | Original Size | Optimized Size     |
| -------------- | ------------- | ------------------ |
| Static Website |               |                    |
| Next.js        | 1GB           | 67MB               |
| React Vite     | 1.4GB         | 232KB (Kilobytes!) |
| Node.js        | 940MB         | 62MB               |
| Spring Boot    | 459MB         | 77MB               |
| Dotnet Core    | 219MB         | 53MB               |

## Current Platforms

This repository currently includes Dockerfiles for the following platforms:

-   [**Next.js**](nextjs)
-   [**React Vite**](react_vite)
-   [**Node.js**](nodejs)
-   [**Spring Boot**](spring_boot)
-   [**Dotnet Core**](dotnet)

More platforms will be added over time as the repository evolves (PR Welcome).

## How to Use

1. Navigate to the folder for your [desired platform](#current-platforms).

2. Follow the instructions in the README file for that specific platform to build and run your Docker image.

## Contributing

Contributions are welcome! If you have an optimized Dockerfile for a platform not yet included in this repository, feel
free to open a pull request. Ensure your Dockerfile is well-documented and includes before-and-after stats for image
size.

## License

This repository is licensed under the [MIT License](LICENSE). Feel free to use and modify the Dockerfiles for your own
projects.

---

Let's build lean and fast Docker images together!
