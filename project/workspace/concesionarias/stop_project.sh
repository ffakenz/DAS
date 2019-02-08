#!/usr/bin/env bash

echo "Stopping Axis Client"
screen -S axis_one -X quit
echo "Stopping Cxf Client"
screen -S cxf_one -X quit
echo "Stopping Cxf Client"
screen -S cxf_two -X quit
echo "Stopping Rest Client"
screen -S rest_one -X quit
echo "Stopping Containers"
./db/src/main/resources/startup/stop.sh