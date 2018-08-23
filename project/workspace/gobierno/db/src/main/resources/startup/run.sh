#!/usr/bin/env bash
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Das12345' -p 1433:1433 --name mssql -d microsoft/mssql-server-linux:2017-latest
docker exec -d mssql sh -c "/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P Das12345 -Q \"CREATE DATABASE db_gobierno;\""