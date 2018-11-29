#!/usr/bin/env bash

function test_db(){
    PORT=$1
    echo "Connecting to container mssql_concesionarias_${PORT}"
    docker exec -ti mssql_concesionarias_${PORT} /bin/bash -c "./sqlcmd -S localhost -U SA -P Das12345"
}

# Example of usages
# test_db 1434