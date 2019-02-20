class Concesionarias extends Module {

    constructor(config) {
        super(config);
    }

    /* AJAX CALLS */
    testConfigHandler(evt) {
        evt.preventDefault();
        console.log("changeUpdateConfigHandler, [EVENT] = %o", evt);
        console.log("Testing Config %o", evt.target.id);

        const configTecno = $("#"+Id.UPDATE_CONFIG_SELECT).children("option:selected").val();

        const paramsToData = (params) => params
                .map(param => `${param.name}=${param.value}`)
                .reduce((acc, elem) => {
                    if(acc === "") { acc += elem; }
                    else { acc += `&${elem}` }
                    return acc;
                }, "");

        const params = $("#"+Id.UPDATE_CONFIG_FORM).serializeArray();
        const data = paramsToData(params);
        console.log("Testing data %o", data);

        ConcesionariasService.POST_TEST_CONFIG(data);
    }

    changeUpdateConfigHandler(evt) {
        evt.preventDefault();
        console.log("changeUpdateConfigHandler, [EVENT] = %o", evt);

        const currentConfigTecno = GlobalState.getLastConfigConsulted().configTecno;
        const newConfigTecno = evt.target.value;
        console.log("Changing Config From %o To %o", currentConfigTecno, newConfigTecno);

        if(currentConfigTecno != newConfigTecno) {
            const newHtml = 
                ConcesionariasHelpers.getNewUpdateForm(newConfigTecno, GlobalState.getLastConfigConsulted().concesionariaId)
                                    .showForm();
            console.log("newHtml = %o", newHtml);
            jUtils.showing("modal_content", newHtml);
        } else {
            const oldHtml = GlobalState.getLastConfigConsulted().showForm();
            console.log("oldHtml = %o", oldHtml);
            jUtils.showing("modal_content", oldHtml);
        }
    }

    configurarHandler(evt) {
        evt.preventDefault();
        console.log("configurarHandler, [EVENT] = %o", evt);
        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];
        console.log("Configurando Concesionaria %o", idConcesionaria);

        const target = $(evt.target);
        const tr = $(target).closest('tr');
        const className = $(tr).prop('className');
        if(className.includes('concesionaria_no_aprobada')) {
            console.error("Configurando Concesionaria %o NO aprobada", idConcesionaria);
            return;
        }

        ConcesionariasService.POST_CONSULTAR_CONFIG_PARAM(idConcesionaria, ConcesionariasHelpers.formConsultarConfig);
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
        const data = $("#"+Id.UPDATE_CONFIG_FORM).serializeArray();
        console.log("ConcesionariasService.POST_CONFIG_CONCESIONARIA with DATA [%o]", data);
        ConcesionariasService.POST_CONFIG_CONCESIONARIA(data);
    }

    registrarConcesionaria(evt) {
        evt.preventDefault();
        console.log("registrarConcesionaria, [EVENT] = %o", evt);
        const _formRegistrarConcesionaria = $('#form_registrar_concesionaria');
        const _email = $("#email");
        const isFormValid =
            jUtils.inputsAreOk(_formRegistrarConcesionaria) && 
            jUtils.isValidEmail(_email.val());    

        if(!isFormValid) {
            console.log("Form is Invalid");
            return false;
        }

        var url = Action.REGISTRAR_CONCESIONARIA_ENDPOINT;

        _formRegistrarConcesionaria.attr('action', url).submit();
    }
};

$(() => {
    /* Initialization Code */
    const concesionarias = new Concesionarias(ConcesionariasConfig);
    jUtils.loadModule(concesionarias);
    console.log("Concesionarias View Loaded Modules: [CONFIG] = %o, [CONCESIONARIAS] = %o",
        ConcesionariasConfig, concesionarias);
});