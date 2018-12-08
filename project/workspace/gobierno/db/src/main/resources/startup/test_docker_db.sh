#!/usr/bin/env bash

function test_db(){
    echo "Connecting to container mssql_gobierno"
    docker exec -ti mssql_gobierno /bin/bash -c "./sqlcmd -S localhost -U SA -P Das12345"
}

# Example of usages
# test_db # 1433
# use db_gobierno