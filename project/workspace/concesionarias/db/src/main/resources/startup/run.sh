#!/usr/bin/env bash
ASD="asdasdasd"

echo "running docker container"
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Das12345' -p 1434:1433 --name mssql_concesionarias -d microsoft/mssql-server-linux:2017-latest &&
echo "creating db_concesionarias" &&
docker exec -d mssql_concesionarias sh -c "/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P Das12345 -Q \"CREATE DATABASE db_concesionarias;\""
echo "db_concesionarias created"
echo "calling create schemas"
#./create_schemas.sh