#!/usr/bin/env bash
echo "running docker container"
docker run -p 1433:1433 --name mssql_gobierno -d mssql_gobierno:1.0
echo "creating db_gobierno" &&
docker exec -d mssql_gobierno sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
echo "db_gobierno created"