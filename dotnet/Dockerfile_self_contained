FROM mcr.microsoft.com/dotnet/sdk:8.0-alpine AS builder
WORKDIR /app
COPY . ./
RUN dotnet publish \
	-c Release \
	-o /app/publish \
	--self-contained true \
	-p:PublishSingleFile=true \
    # uncomment the following line to enable trimming. you may run into issues with trimming, so be sure to test your app after enabling it.
    # -p:PublishTrimmed=true \
    -p:AssemblyName=dotnet-app

FROM mcr.microsoft.com/dotnet/runtime-deps:8.0-alpine
USER $APP_UID
WORKDIR /app
COPY --from=builder --chown=$APP_UID:$APP_UID /app/publish .
ENV ASPNETCORE_HTTP_PORTS=5001
EXPOSE 5001
ENTRYPOINT ["./dotnet-app"]