INSERT INTO concesionaria_config_params (concesionaria_id, config_tecno, config_param, value)
VALUES
    (1, 'REST', 'url', 'http://localhost:8001/concesionaria_rest_one/concesionariaRestOne')
    , (2, 'CXF', 'wsdlUrl', 'http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')
    , (3, 'AXIS', 'endpointUrl', 'http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/')
    , (3, 'AXIS', 'targetNameSpace', 'http://ws.ConcesionariaAxisOne/')
    -- , (4, 'REST', 'url', 'http://localhost:8004/concesionarias_rest_four/concesionariaRestFour')
    -- , (5, 'CXF', 'wsdlUrl', 'http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')