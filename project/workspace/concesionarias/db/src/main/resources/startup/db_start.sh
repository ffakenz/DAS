#!/usr/bin/env bash

echo $pwd

function dbstart () {
    SCHEMA=$1
    echo "Run liquibase ${SCHEMA}"
    #mvn -f ../../../.. clean -Denv=$SCHEMA
    mvn -f ../../../.. liquibase:update -Denv=$SCHEMA -X
    echo "Finish liquibase ${SCHEMA}"
}

dbstart "cxf_one"
dbstart "axis_one"
dbstart "rest_one"