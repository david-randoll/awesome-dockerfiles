# https://github.com/lipanski/docker-static-website/tree/master
FROM lipanski/docker-static-website:latest
WORKDIR /var/www/html
COPY src .
EXPOSE 3000
CMD ["/busybox-httpd", "-f", "-v", "-p", "3000"]