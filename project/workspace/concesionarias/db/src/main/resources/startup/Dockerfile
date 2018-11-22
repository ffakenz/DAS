FROM microsoft/mssql-server-linux:2017-latest
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=Das12345
EXPOSE 1433
WORKDIR /opt/mssql-tools/bin
RUN mkdir scripts
COPY ./script.sql ./scripts

# Only run this at the first time
# docker build --no-cache -t mssql_concesionarias:1.0 .

# Commands when docker is run
# docker exec -ti mssql_concesionarias /bin/bash
# ./sqlcmd -S localhost -U SA -P Das12345