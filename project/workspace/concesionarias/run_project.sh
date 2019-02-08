#!/usr/bin/env bash

function dbstart () {
    SCHEMA=$1
    echo "Refreshing ${SCHEMA}"
    mvn -f ./db/ clean install -Denv=$SCHEMA
}

chmod +x ./give_permissions.sh
echo "Giving Persmissions"
./give_permissions.sh
echo "Runnin Containers"
./db/src/main/resources/startup/run_docker.sh
echo "Awaiting"
sleep 15
echo "Installing DBs"
./db/src/main/resources/startup/db_install.sh
echo "Setting up general DB"
# ./db/src/main/resources/startup/db_start.sh
dbstart "axis_one" # 1434
dbstart "rest_one" # 1435
dbstart "cxf_one"  # 1436
dbstart "rest_two"  # 1437
echo "Installing Core"
# ./concesionarias_core/install_core.sh
mvn -f ./concesionarias_core/ clean install
echo "Running Axis Client"
# screen -dmS axis_one ./concesionarias_axis/concesionaria_axis_one/start_up_axis.sh
screen -dmS axis_one mvn -f ./concesionarias_axis/concesionaria_axis_one clean compile -Denv="axis_one" tomcat7:run
echo "Running Cxf Client"
# screen -dmS cxf_one ./concesionarias_cxf/concesionaria_cxf_one/start_up_cxf.sh
screen -dmS cxf_one mvn -f ./concesionarias_cxf/concesionaria_cxf_one clean compile -Denv="cxf_one" tomcat7:run
echo "Running Rest Client"
# screen -dmS rest_one ./concesionarias_rest/concesionaria_rest_one/start_up_rest.sh
screen -dmS rest_one mvn -f ./concesionarias_rest/concesionaria_rest_one clean compile -Denv="rest_one" tomcat7:run
echo "Running Rest Client"
# screen -dmS rest_two ./concesionarias_rest/concesionaria_rest_two/start_up_rest.sh
screen -dmS rest_two mvn -f ./concesionarias_rest/concesionaria_rest_two clean compile -Denv="rest_two" tomcat7:run


# check available containers using: docker -ps
# check available sessions using: screen -ls