class Admin extends Module {
    constructor(config){
        super(config);
    }

    testConsumo(evt) {
        evt.preventDefault();
        console.log("testConsumo, [EVENT] = %o", evt);
        AdminService.GET_TEST_CONSUMO();
    }

    showConcesionarias(evt) {
        evt.preventDefault();
        console.log("showConfigConcesionarias, [EVENT] = %o", evt);
        ConcesionariasService.GET_CONSULTAR_TODAS();
    }

    showSorteos(evt) {
        evt.preventDefault();
        console.log("showSorteos, [EVENT] = %o", evt);
        CalendarioService.GET_SORTEOS();
    }

    showPlanes(evt) {
        evt.preventDefault();
        console.log("showPlanes, [EVENT] = %o", evt);
        EstadoCuentaService.GET_CONSULTAR_TODOS();
    }

    showJobResultsReport(evt) {
        evt.preventDefault();
        console.log("showJobResultsReport, [EVENT] = %o", evt);
        ConsumoService.GET_JOB_RESULTS_REPORT();
    }

    executeConsumo(evt) {
        evt.preventDefault();
        console.log("executeConsumo, [EVENT] = %o", evt);
        AdminService.EXECUTE_CONSUMO();
    }

    executeSorteo(evt) {
        evt.preventDefault();
        console.log("executeSorteo, [EVENT] = %o", evt);
        AdminService.EXECUTE_SORTEO();
    }
};

$(() => {
    /* Initialization Code */
    const admin = new Admin(AdminConfig);
    jUtils.loadModule(admin);
    console.log("Admin View Loaded Modules: [AdminConfig] = %o, [Admin] = %o",
        AdminConfig, admin);
});
