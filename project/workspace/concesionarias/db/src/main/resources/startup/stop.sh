#!/usr/bin/env bash
function stop_docker() {
    PORT=$1
    echo "Stopping mssql_concesionarias_${PORT}"
    docker stop mssql_concesionarias_$PORT
    docker rm mssql_concesionarias_$PORT
}

echo "Stopping containers"
#stop_docker 1434 # "axis_one"
#stop_docker 1435 # "rest_one"
#stop_docker 1436 # "cxf_one"
stop_docker 1438 # "cxf_two"