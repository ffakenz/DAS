class Concesionarias extends Module {

    constructor(config) {
        super(config);
        /* this.concesionariasService = new ConcesionariasService();*/
    }

    /* AJAX CALLS */
    testConfigHandler(evt) {
        evt.preventDefault();
        console.log("changeUpdateConfigHandler, [EVENT] = %o", evt);
        console.log("Testing Config %o", evt.target.id);

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
        console.log("changeUpdateConfigHandler, [EVENT] = %o", evt);

        const currentConfigTecno = LAST_CONFIGS_CONSULTED_ST.configTecno;
        const newConfigTecno = evt.target.value;
        console.log("Changing Config From %o To %o", currentConfigTecno, newConfigTecno);

        if(currentConfigTecno != newConfigTecno) {
            const newHtml = ConcesionariasUtils.getNewUpdateForm(newConfigTecno, LAST_CONFIGS_CONSULTED_ST.concesionariaId);
            console.log("newHtml = %o", newHtml);
            jUtils.showing("modal_content", newHtml);
        } else {
            const oldHtml = LAST_CONFIGS_CONSULTED_ST.showForm();
            console.log("oldHtml = %o", oldHtml);
            jUtils.showing("modal_content", oldHtml);
        }

    }

    configurarHandler(evt) {
        evt.preventDefault();
        console.log("configurarHandler, [EVENT] = %o", evt);
        console.log("Configurando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];
        ConcesionariasService.POST_CONSULTAR_CONFIG_PARAM(idConcesionaria);
    }

    aprobarHandler(evt) {
        evt.preventDefault();
        console.log("aprobarHandler, [EVENT] = %o", evt);
        console.log("Aprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];
        ConcesionariasService.POST_APROBAR_CONCESIONARIA(idConcesionaria);
    }

    desAprobarHandler(evt) {
        evt.preventDefault();
        console.log("desAprobarHandler, [EVENT] = %o", evt);
        console.log("DesAprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];
        ConcesionariasService.POST_DESAPROBAR_CONCESIONARIA(idConcesionaria);
    }

    updateConfigHandler(evt) {
        evt.preventDefault();
        console.log("updateConfigHandler, [EVENT] = %o", evt);
        console.log("Actualizando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];
        const data = $(`#${Id.UPDATE_CONFIG_FORM}`).serializeArray();
        ConcesionariasService.POST_CONFIG_CONCESIONARIA(data);
    }

    testConsumo(evt) {
        evt.preventDefault();
        console.log("testConsumo, [EVENT] = %o", evt);
        console.log("Test consumo %o", evt.target.id);
    }
};