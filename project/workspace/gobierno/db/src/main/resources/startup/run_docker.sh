#!/usr/bin/env bash

function run_docker() {
    PORT=$1
    echo "Running mssql_gobierno"
    docker run -p 1433:1433 --name mssql_gobierno -d mssql_gobierno:1.0
}
function db_install() {
    PORT=$1
    echo "Installing DB in mssql_gobierno"
    docker exec -d mssql_gobierno sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
}

echo "Running Docker container"
run_docker 1433
echo "Awaiting"
sleep 15
echo "Installing DB"
db_install 1433