FROM node:23-alpine3.20 AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci --production
COPY . .

FROM alpine:3.20
RUN apk add --no-cache nodejs npm && \
    addgroup --system --gid 1001 node && \
    adduser --system --uid 1001 node && \
    mkdir -p /app && \
    chown -R node:node /app
WORKDIR /app
COPY --from=builder --chown=node:node /app .
USER node
EXPOSE 9012
ENV PORT=3000
CMD [ "npm", "start" ]