#!/bin/bash

docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=0304Euge' -p 1433:1433 --name mssql -d microsoft/mssql-server-linux:2017-latest
# docker exec -it mssql "bash"
# /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 0304Euge
# CREATE DATABASE db_gobierno;