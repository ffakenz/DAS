#!/usr/bin/env bash

## DEPRECATED ##

function run_docker() {
    PORT=$1
    echo "Running mssql_concesionarias_${PORT}"
    docker run -p ${PORT}:1433 --name mssql_concesionarias_${PORT} -d mssql_concesionarias:1.0
}
function db_install() {
    PORT=$1
    echo "Installing DB in mssql_concesionarias_${PORT}"
    docker exec -d mssql_concesionarias_${PORT} sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
}

echo "Running Docker containers"
run_docker 1434 # axis_one
run_docker 1435 # rest_one
run_docker 1436 # cxf_one
run_docker 1437 # rest_two
run_docker 1438 # cxf_two
echo "Awaiting"
sleep 15
echo "Installing DBs"
db_install 1434
db_install 1435
db_install 1436
db_install 1437
db_install 1438