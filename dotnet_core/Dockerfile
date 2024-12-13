# FROM mcr.microsoft.com/dotnet/sdk:8.0 AS builder
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS builder
WORKDIR /app
COPY *.csproj ./
RUN dotnet restore
COPY . ./
RUN dotnet publish -c Release -o out -p:AssemblyName=dotnet-app

# FROM mcr.microsoft.com/dotnet/aspnet:8.0
FROM mcr.microsoft.com/dotnet/aspnet:8.0
USER $APP_UID
WORKDIR /app
COPY --from=builder --chown=$APP_UID:$APP_UID /app/out .
ENV ASPNETCORE_HTTP_PORTS=5001
EXPOSE 5001
ENTRYPOINT ["dotnet", "dotnet-app.dll"]