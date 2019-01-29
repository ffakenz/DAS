class Concesionarias extends Module {

    constructor(config) {
        super(config);
        this.concesionariasService = new ConcesionariasService();
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

        console.log("Event %o", evt);

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
            console.lzog("oldHtml = %o", oldHtml);
            jUtils.showing("modal_content", oldHtml);
        }

    }

    configurarHandler(evt) {
        console.log("Configurando Concesionaria %o", evt.target.id);

        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];

        ConcesionariasService.POST_CONSULTAR_CONFIG_PARAM(idConcesionaria);
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