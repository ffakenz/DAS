const ConcesionariasUtils = {

    /* HELPERS */
    getNewUpdateForm(configTecno, concesionariaId) {
        if(configTecno === ConfigTecno.REST) {
            return new ConfigParam(
                ConfigTecno.REST,
                [{name: "url", value: ""}],
                concesionariaId
            ).showForm();
        }
        if(configTecno === ConfigTecno.AXIS) {
            return new ConfigParam(
                ConfigTecno.AXIS,
                [{name: "endpointUrl", value: ""}, {name: "targetNameSpace", value: ""}],
                concesionariaId
            ).showForm();
        }
        if(configTecno === ConfigTecno.CXF) {
            return new ConfigParam(
                ConfigTecno.CXF,
                [{name: "wsdlUrl", value: ""}],
                concesionariaId
            ).showForm();
        }
    },

    /* the jsonArray is the result from calling CONCESIONARIA_CONSULTAR_CONFIG_PARAM Action */
    formConsultarConfig(jsonArray, concesionariaId) {
        if(jsonArray.length === 0) {
            const newState = this.getNewUpdateForm(ConfigTecno.REST, concesionariaId);
            LAST_CONFIGS_CONSULTED_ST = newState;
            return newState.showForm();
        }
        else {
            const head = jsonArray[0];
            const configTecno = head["configTecno"];
            const configParams = jsonArray.map( param => {
                let obj = {};
                obj["name"] = param.configParam;
                obj["value"] = param.value;
                return obj;
            });
            const newState = new ConfigParam(configTecno, configParams, concesionariaId);
            LAST_CONFIGS_CONSULTED_ST = newState;
            return newState.showForm();
        }
    }
};