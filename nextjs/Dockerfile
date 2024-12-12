FROM node:23-alpine3.20 AS builder
WORKDIR /app
RUN apk add --no-cache libc6-compat
ENV NEXT_TELEMETRY_DISABLED=1
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run standalone

FROM alpine:3.20
ARG app_user=nextjs
RUN apk add --no-cache nodejs && \
    addgroup --system --gid 1001 "$app_user" && \
    adduser --system --uid 1001 "$app_user" && \
    mkdir -p /app && \
    chown -R "$app_user":"$app_user" /app
USER $app_user
WORKDIR /app
ENV NEXT_TELEMETRY_DISABLED=1
ENV NODE_ENV=production
COPY --from=builder --chown="$app_user":"$app_user" /app/.next/standalone .
EXPOSE 3000
ENV PORT=3000
CMD [ "node", "server.js" ]