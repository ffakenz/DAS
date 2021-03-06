const ConcesionariasHelpers = {

    /* HELPERS */
    getNewUpdateForm(configTecno, concesionariaId) {
        if(configTecno === ConfigTecno.REST) {
            return new ConfigParam(
                ConfigTecno.REST,
                [{name: "url", value: ""}],
                concesionariaId
            );
        }
        if(configTecno === ConfigTecno.AXIS) {
            return new ConfigParam(
                ConfigTecno.AXIS,
                [{name: "endpointUrl", value: ""}, {name: "targetNameSpace", value: ""}],
                concesionariaId
            );
        }
        if(configTecno === ConfigTecno.CXF) {
            return new ConfigParam(
                ConfigTecno.CXF,
                [{name: "wsdlUrl", value: ""}],
                concesionariaId
            );
        }
    },

    /* the jsonArray is the result from calling CONCESIONARIA_CONSULTAR_CONFIG_PARAM Action */
    formConsultarConfig(jsonArray, concesionariaId) {
        console.log("Executing formConsultarConfig for [CONCESIONARIA_ID] = %o with [ARRAY] = %o", 
            concesionariaId, jsonArray);
        if(jsonArray.length === 0) {
            const newState = ConcesionariasHelpers.getNewUpdateForm(ConfigTecno.REST, concesionariaId);
            GlobalState.setLasConfigConsulted(newState);
            console.log("formConsultarConfig for [CONCESIONARIA_ID] = %o [RETURN] = %o", newState);
            return newState.showForm();
        }
        else {
            const head = jsonArray[0];
            const configTecno = head["configTecno"];
            const configParams = jsonArray.map( param => {
                let obj = {};
                obj["name"] = param.configParam;
                obj["value"] = param.value;
                console.log("formConsultarConfig for [CONCESIONARIA_ID] = %o [RETURN] = %o", obj);
                return obj;
            });
            const newState = new ConfigParam(configTecno, configParams, concesionariaId);
            GlobalState.setLasConfigConsulted(newState);
            console.log("formConsultarConfig for [CONCESIONARIA_ID] = %o [RETURN] = %o", newState);
            return newState.showForm();
        }
    }
};