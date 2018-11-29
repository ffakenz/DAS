# Environmet setup

1 - Execute in /db/../startup/build     : build_docker.sh
2 - Execute in /db/../startup           : run_docker.sh
3 - Execute in /db/../startup           : db_start.sh 


# Startup
 
1 - Execute in /concesionarias          : mvn clean
2 - Execute in /concesionarias_core     : install_core.sh
3 - Execute in each /concesionaria_xxxx : start_up_xxx.sh


# Stop all

- Execute in /db/../startup             : stop.sh


# Is db up ?

- Execute the function define in /db/../startup/test_docker_db.sh on the terminal
- Example of usages: `test_db 1434`
                     `use db_concesionarias` 


# URLs para consumir los servicios:

- AXIS : http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne?wsdl

- REST : http://localhost:8001/concesionaria_rest_one/concesionariaRestOne/consultarPlanes

- CXF  : http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one/consultarPlanes


# Cosas a tener en cuenta

- Si un servicio no logra conectarse a la db, es posible que tengas una sesion abierta (ejemplo terminal) 


# Como deberia quedar el entorno configurado ?

|CONCESIONARIAS  |PORTS_DB  |NAMES                     |PORTS_WEB|
|----------------|----------|--------------------------|---------|       
|axis_one        |1434      |mssql_concesionarias_1434 |8000     |
|rest_one        |1435      |mssql_concesionarias_1435 |8001     |
|cxf_one         |1436      |mssql_concesionarias_1436 |8002     |
