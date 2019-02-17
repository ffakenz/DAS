#!/usr/bin/env bash

http http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne/health?identificador=GOB
http http://localhost:8001/concesionaria_rest_one/concesionariaRestOne/health?identificador=GOB
http http://localhost:8003/concesionaria_rest_two/concesionariaRestTwo/health?identificador=GOB
echo "
<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pm=\"http://ws.das.edu.ubp.ar/\">
    <soapenv:Header></soapenv:Header>
    <soapenv:Body>
    	<pm:health>
    		<identificador>GOB</identificador>
		</pm:health>
	</soapenv:Body>
</soapenv:Envelope>
" | http POST http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one
echo "
<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pm=\"http://ws.das.edu.ubp.ar/\">
    <soapenv:Header></soapenv:Header>
    <soapenv:Body>
    	<pm:health>
    		<identificador>GOB</identificador>
		</pm:health>
	</soapenv:Body>
</soapenv:Envelope>
" | http POST http://localhost:8004/concesionaria_cxf_two/services/concesionaria_cxf_two
