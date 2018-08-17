#!/usr/bin/env bash

#start SQL Server, start the script to create the DB
/opt/mssql/bin/sqlservr & /usr/src/app/db/createdb.sh