#!/usr/bin/env bash

function db_install() {
    PORT=$1
    echo "Installing DB in mssql_concesionarias_${PORT}"
    docker exec -d mssql_concesionarias_${PORT} sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
}

echo "Installing DBs"
#db_install 1434
#db_install 1435
#db_install 1436
db_install 1437