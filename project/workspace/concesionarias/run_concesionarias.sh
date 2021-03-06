#!/usr/bin/env bash

echo "Installing Core"
# ./concesionarias_core/install_core.sh
#mvn -f ./concesionarias_core/ clean install
echo "Running Axis Client"
# screen -dmS axis_one ./concesionarias_axis/concesionaria_axis_one/start_up_axis.sh
screen -dmS axis_one mvn -f ./concesionarias_axis/concesionaria_axis_one clean compile -Denv="axis_one" tomcat7:run
echo "Running Cxf Client"
# screen -dmS cxf_one ./concesionarias_cxf/concesionaria_cxf_one/start_up_cxf.sh
screen -dmS cxf_one mvn -f ./concesionarias_cxf/concesionaria_cxf_one clean compile -Denv="cxf_one" tomcat7:run
echo "Running Cxf Client"
# screen -dmS cxf_one ./concesionarias_cxf/concesionaria_cxf_two/start_up_cxf.sh
screen -dmS cxf_two mvn -f ./concesionarias_cxf/concesionaria_cxf_two clean compile -Denv="cxf_two" tomcat7:run
echo "Running Rest Client"
# screen -dmS rest_one ./concesionarias_rest/concesionaria_rest_one/start_up_rest.sh
screen -dmS rest_one mvn -f ./concesionarias_rest/concesionaria_rest_one clean compile -Denv="rest_one" tomcat7:run
echo "Running Rest Client"
# screen -dmS rest_two ./concesionarias_rest/concesionaria_rest_two/start_up_rest.sh
screen -dmS rest_two mvn -f ./concesionarias_rest/concesionaria_rest_two clean compile -Denv="rest_two" tomcat7:run


# check available containers using: docker -ps
# check available sessions using: screen -ls