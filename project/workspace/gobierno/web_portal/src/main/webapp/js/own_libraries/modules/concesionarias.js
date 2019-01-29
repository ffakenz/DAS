class Concesionarias extends Module {
    
    constructor(config) {
        super(config);
    }

    /* HELPERS */
    getNewUpdateForm(configTecno, concesionariaId) {
        if(configTecno == ConfigTecno.REST) {
            return new ConfigParam(
                ConfigTecno.REST,
                [{name: "url", value: ""}],
                concesionariaId
            ).showForm();
        }
        if(configTecno == ConfigTecno.AXIS) {
            return new ConfigParam(
                ConfigTecno.AXIS,
                [{name: "endpointUrl", value: ""}, {name: "targetNameSpace", value: ""}],
                concesionariaId
            ).showForm();
        }
        if(configTecno == ConfigTecno.CXF) {
            return new ConfigParam(
                ConfigTecno.CXF,
                [{name: "wsdlUrl", value: ""}],
                concesionariaId
            ).showForm();
        }
    };

    /* the jsonArray is the result from calling CONCESIONARIA_CONSULTAR_CONFIG_PARAM Action */
    formConsultarConfig(jsonArray, concesionariaId) {
        if(jsonArray.length == 0) {
            const newState = getNewUpdateForm(ConfigTecno.REST, concesionariaId);
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

    /* AJAX CALLS */
    testConfigHandler(evt) {
        console.log("Testing Config %o", evt.target.id);
        evt.preventDefault();

        const configTecno = $(`#${Id.UPDATE_CONFIG_SELECT}`).children("option:selected").val();

        const paramsToData = (params) => params
                .map(param => `${param.name}=${param.value}`)
                .reduce((acc, elem) => {
                    if(acc == "") { acc += elem; }
                    else { acc += `&${elem}` }
                    return acc;
                }, "");

        const params = $(`#${Id.UPDATE_CONFIG_FORM}`).serializeArray();
        const data = paramsToData(params);
        console.log("Testing data %o", data);

        ConcesionariasService.POST_TEST_CONFIG(data);
    }

    changeUpdateConfigHandler(evt) {
        evt.preventDefault();

        const currentConfigTecno = LAST_CONFIGS_CONSULTED_ST.configTecno;
        const newConfigTecno = evt.target.value;
        console.log("Changing Config From %o To %o", currentConfigTecno, newConfigTecno);

        if(currentConfigTecno != newConfigTecno) {
            const newHtml = getNewUpdateForm(newConfigTecno, LAST_CONFIGS_CONSULTED_ST.concesionariaId);
            console.log("newHtml = %o", newHtml);
            jUtils.showing("modal_content", newHtml);
        } else {
            const oldHtml = LAST_CONFIGS_CONSULTED_ST.showForm();
            console.log("oldHtml = %o", oldHtml);
            jUtils.showing("modal_content", oldHtml);
        }

    }

    configurarHandler(evt) {
        console.log("Configurando Concesionaria %o", evt.target.id);

        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];

        ConcesionariasService.POST_CONSULTAR_CONFIG_PARAM(idConcesionaria, this.formConsultarConfig.bind(this));
    }

    aprobarHandler(evt) {

        console.log("Aprobando Concesionaria %o", evt.target.id);

        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];

        ConcesionariasService.POST_APROBAR_CONCESIONARIA(idConcesionaria);
    }

    desAprobarHandler(evt) {
        console.log("DesAprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];

        ConcesionariasService.POST_DESAPROBAR_CONCESIONARIA(idConcesionaria);
    }

    updateConfigHandler(evt) {

        console.log("Actualizando Concesionaria %o", evt.target.id);
        evt.preventDefault();
        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];
        console.log("Actualizando Concesionaria _ %o", idConcesionaria);

        const data = $(`#${Id.UPDATE_CONFIG_FORM}`).serializeArray();
        ConcesionariasService.POST_CONFIG_CONCESIONARIA(data);
    }

    testConsumo(evt) {
        console.log("Test consumo %o", evt.target.id);
        evt.preventDefault();

    }
};