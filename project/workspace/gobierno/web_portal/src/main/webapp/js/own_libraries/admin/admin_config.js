const AdminConfig = (module) => [
    { 
        ctx: Id.TEST_CONSUMO_DIV,
        cnfg: [
            { delegate: Id.TEST_CONSUMO_BTN, handler: module.testConsumo }
        ]
    },
    {
        ctx: Id.ADMIN_SIDE_BAR,
        cnfg: [
            { delegate: Id.SHOW_CONFIG_CONCESIONARIAS_BTN, handler: module.showConcesionarias },
            { delegate: Id.SHOW_JOB_RESULTS_REPORT_BTN, handler: module.showJobResultsReport },
            { delegate: Id.SHOW_SORTEOS_BTN, handler: module.showSorteos },
            { delegate: Id.SHOW_PLANES_BTN, handler: module.showPlanes },
            { delegate: Id.EXECUTE_CONSUMO_BTN, handler: module.executeConsumo },
            { delegate: Id.EXECUTE_SORTEO_BTN, handler: module.executeSorteo }
        ]
    }

];