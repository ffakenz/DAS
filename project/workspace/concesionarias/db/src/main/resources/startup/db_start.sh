#!/usr/bin/env bash

function dbstart () {
    SCHEMA=$1
    echo "Refreshing ${SCHEMA}"
    mvn -f ../../../.. clean install -Denv=$SCHEMA
}

echo "Refreshing Dbs"
<<<<<<< Updated upstream
dbstart "axis_one" # 1434
dbstart "rest_one" # 1435
dbstart "cxf_one"  # 1436
dbstart "rest_two"  # 1437
=======
#dbstart "axis_one" # 1434
#dbstart "rest_one" # 1435
#dbstart "cxf_one"  # 1436
#dbstart "rest_two"  # 1437
>>>>>>> Stashed changes
dbstart "cxf_two"  # 1438