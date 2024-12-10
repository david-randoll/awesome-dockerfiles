# Build stage
FROM node:23-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM alpine:3.20
ARG APP_USER=vite
RUN apk add --no-cache nginx && \
    addgroup --system --gid 1001 "$APP_USER" && \
    adduser --system --uid 1001 --ingroup "$APP_USER" "$APP_USER" && \
    mkdir -p /var/www/html /var/lib/nginx/logs /run/nginx && \
    chown -R "$APP_USER":"$APP_USER" /var/lib/nginx /var/www/html && \
    chmod -R 777 /var/lib/nginx/logs /run/nginx
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY --from=builder --chown=$APP_USER:$APP_USER /app/dist /var/www/html/
USER $APP_USER
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]