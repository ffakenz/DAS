class Admin extends Module {
    constructor(config){
        super(config);
    }

    showConcesionarias(evt) {
        evt.preventDefault();
        jUtils.hiding("calendar_main_div",true);
        console.log("showConfigConcesionarias, [EVENT] = %o", evt);
        ConcesionariasService.GET_CONSULTAR_TODAS();
    }

    showSorteos(evt) {
        evt.preventDefault();
        console.log("showSorteos, [EVENT] = %o", evt);
        setTimeout(function(){
                CalendarioService.GET_SORTEOS();
        }, 200);
        setTimeout(function(){
            CalendarioService.GET_CALENDARIO({"year": '2019', "month": '02'});
        }, 400);
    }

    showPlanes(evt) {
        evt.preventDefault();
        jUtils.hiding("calendar_main_div",true);
        console.log("showPlanes, [EVENT] = %o", evt);
        EstadoCuentaService.GET_ESTADO_CUENTA_DASH();
    }

    showJobResultsReport(evt) {
        evt.preventDefault();
        jUtils.hiding("calendar_main_div",true);
        console.log("showJobResultsReport, [EVENT] = %o", evt);
        ConsumoService.GET_JOB_RESULTS_REPORT();
    }
};

$(() => {
    /* Initialization Code */
    const admin = new Admin(AdminConfig);
    jUtils.loadModule(admin);
    console.log("Admin View Loaded Modules: [AdminConfig] = %o, [Admin] = %o",
        AdminConfig, admin);
});
