#!/usr/bin/env bash

function dbstart () {
    SCHEMA=$1
    echo "Refreshing ${SCHEMA}"
    mvn -f ../../../.. liquibase:update -Denv=$SCHEMA
}

echo "Refreshing Dbs"
dbstart "cxf_one"
dbstart "axis_one"
dbstart "rest_one"