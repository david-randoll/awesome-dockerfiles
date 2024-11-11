FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json .
RUN npm install
COPY . .
RUN npm run standalone

FROM alpine:3.20
RUN apk add --no-cache nodejs
WORKDIR /app
COPY --from=builder /app/.next/standalone .

EXPOSE 3000
CMD ["node", "server.js"]