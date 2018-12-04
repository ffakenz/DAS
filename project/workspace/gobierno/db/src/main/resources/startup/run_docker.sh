#!/usr/bin/env bash

function run_docker() {
    echo "Running mssql_gobierno"
    docker run -p 1433:1433 --name mssql_gobierno -d mssql_gobierno:1.0
}
function db_install() {
    echo "Installing DB in mssql_gobierno"
    docker exec -d mssql_gobierno sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
}

echo "Running Docker container"
run_docker
echo "Awaiting"
sleep 15
echo "Installing DB"
db_install