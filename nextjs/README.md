# Next.js App with Docker Support

This repository includes a Dockerfile to build and run your Next.js application. Follow the steps below to configure
your project for Docker support.

## Setup Instructions

### 1. Add a Standalone Script to `package.json`

Add the following script to your `package.json` file under the `scripts` section:

```json
"standalone": "next build && cp -r public/ .next/standalone && cp -r .next/static .next/standalone/.next",
```

We'll be using nextjs's `standalone` script to prepare the necessary files for a production-ready application. This
script will copy the `public` directory and the `static` directory to the `.next/standalone` directory so that we don't
need to copy them manually in the final Docker image. Read more about
[nextjs standalone here](https://nextjs.org/docs/pages/api-reference/next-config-js/output).

### 2. Update `next.config.ts`

Create or update your `next.config.ts` file and add the output property with the value `standalone`:

```typescript
import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    output: "standalone",
};

export default nextConfig;
```

### 3. Create a `.dockerignore` File

Create a `.dockerignore` file in the root of your project and add the following:

```
# .dockerignore file
.DS_Store
.next
node_modules
.gitignore
README.md
.dockerignore
LICENSE
.docker
.gitlab
```

### 4. Build the Docker Image

Run the following command to build the Docker image for your Next.js application:

```bash
docker build -t nextjs .
```

Replace `your-app-name` with the desired name for your Docker image.

## Additional Notes

-   Ensure you have Docker installed and running on your system.
-   The `standalone` script prepares the necessary files for a production-ready Docker build.
-   Customize the Dockerfile as needed for your specific deployment requirements.

You're now ready to run your Next.js app using Docker!
