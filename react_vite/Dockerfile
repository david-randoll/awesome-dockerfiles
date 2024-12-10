# Build stage
FROM node:23-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM alpine:3.20
ARG app_user=vite
RUN apk add --no-cache nginx && \
    addgroup --system --gid 1001 "$app_user" && \
    adduser --system --uid 1001 --ingroup "$app_user" "$app_user" && \
    mkdir -p /var/www/html /var/log/nginx /var/lib/nginx /run/nginx && \
    chown -R "$app_user":"$app_user" /var/www/html /var/log/nginx /var/lib/nginx /run/nginx
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY --from=builder --chown=$app_user:$app_user /app/dist /var/www/html/
USER $app_user
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]