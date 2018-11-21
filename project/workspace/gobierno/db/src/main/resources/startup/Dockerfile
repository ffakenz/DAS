FROM microsoft/mssql-server-linux:2017-latest
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=Das12345
EXPOSE 1433
WORKDIR /opt/mssql-tools/bin
RUN mkdir scripts
COPY ./script.sql ./scripts

# docker build -t mssql_concesionarias:1.0 .
# docker run -p 1433:1433 --name mssql_concesionarias -d mssql_concesionarias:1.0
# docker exec -d mssql_concesionarias sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
# docker exec -ti mssql_concesionarias /bin/bash
# ./sqlcmd -S localhost -U SA -P Das12345
