# Build stage
FROM node:23-alpine3.20 AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# https://github.com/lipanski/docker-static-website/tree/master
FROM lipanski/docker-static-website:latest
WORKDIR /var/www/html
COPY --from=builder /app/dist ./
EXPOSE 3000
CMD ["/busybox-httpd", "-f", "-v", "-p", "3000"]