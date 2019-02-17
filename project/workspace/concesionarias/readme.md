# Environmet setup

1 - Execute in /db/../startup/build     : build_docker.sh
2 - Execute in /db/../startup           : run_docker.sh
3 - Execute in /db/../startup           : db_start.sh 
    * if db_start fails it means run_docker failed installing dbs. 
    * Execut db_install.sh in this case

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

- AXIS WSDL: http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne?wsdl 
- AXIS Endpoints: http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint
    - /consultarPlanes
    - /consultarPlan?planId=1
    - /cancelarPlan?planId=1
    
- REST : http://localhost:8001/concesionaria_rest_one/concesionariaRestOne
    - /consultarPlanes
    - /consultarPlan?planId=1
    - /cancelarPlan?planId=1
         
- CXF WSDL: http://localhost:8002/concesionaria_cxf_one/services/
- CXF Endpoints: * Postman
    [POST] http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl
    * Body = raw (text/xml)
    
    - /consultarPlanes
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pm="http://ws.das.edu.ubp.ar/">
         <soapenv:Header></soapenv:Header>
         <soapenv:Body><pm:consultarPlanes></pm:consultarPlanes></soapenv:Body>
        </soapenv:Envelope>
    
    - /consultarPlan
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pm="http://ws.das.edu.ubp.ar/">
         <soapenv:Header></soapenv:Header>
         <soapenv:Body><pm:consultarPlan><planId>1</planId></pm:consultarPlan></soapenv:Body>
        </soapenv:Envelope>
    
    - /cancelarPlan
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pm="http://ws.das.edu.ubp.ar/">
         <soapenv:Header></soapenv:Header>
         <soapenv:Body><pm:cancelarPlan><planId>1</planId></pm:cancelarPlan></soapenv:Body>
        </soapenv:Envelope>

# Cosas a tener en cuenta

- Si un servicio no logra conectarse a la db, es posible que tengas una sesion abierta (ejemplo terminal) 
- Si un servicio no actualiza su comportamiento, luego de un start_up_xxxx.sh, seguramente falta de builder concesionarias_core  

# Como deberia quedar el entorno configurado ?

|CONCESIONARIAS  |PORTS_DB  |NAMES                     |PORTS_WEB|PORTS_TOMCAT|
|----------------|----------|--------------------------|---------|------------|
|axis_one        |1434      |mssql_concesionarias_1434 |8000     |8080        |
|rest_one        |1435      |mssql_concesionarias_1435 |8001     |8081        |
|cxf_one         |1436      |mssql_concesionarias_1436 |8002     |8082        |
|cxf_two         |1438      |mssql_concesionarias_1438 |8004     |8084        |


# Be careful with the ports
sudo lsof -i TCP:{PORT}
kill {PID}