#!/usr/bin/env bash

function db_install() {
    echo "Installing DB in mssql_gobierno"
    docker exec -d mssql_gobierno sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
}
echo "Installing DB"
db_install