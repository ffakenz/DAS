#!/usr/bin/env bash

function run_docker() {
    echo "Running mssql_gobierno"
    docker run -p 1433:1433 --name mssql_gobierno -d mssql_gobierno:1.0
}

echo "Running Docker container"
run_docker