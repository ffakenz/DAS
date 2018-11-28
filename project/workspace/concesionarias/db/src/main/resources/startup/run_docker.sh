#!/usr/bin/env bash
echo "running docker container"
docker run -p 1434:1433 --name mssql_concesionarias -d mssql_concesionarias:1.0
echo "creating db_concesionarias"
docker exec -d mssql_concesionarias sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
echo "db_concesionarias created"