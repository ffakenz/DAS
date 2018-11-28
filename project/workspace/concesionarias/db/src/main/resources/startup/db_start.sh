#!/usr/bin/env bash

function dbstart () {
    SCHEMA=$1
    echo "Run liquibase ${SCHEMA}"
    mvn -f ../../../.. clean install -Denv=$SCHEMA
    echo "Finish liquibase ${SCHEMA}"
}

dbstart "cxf_one"
dbstart "axis_one"
dbstart "rest_one"