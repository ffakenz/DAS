const ConcesionariasConfig = (module) => [
    { 
        ctx: Id.CONCESIONARIAS_TABLE,
        cnfg: [
            { delegate: Class.APROBAR_BTN, handler: module.aprobarHandler }
            , { delegate: Class.DESAPROBAR_BTN, handler: module.desAprobarHandler }
            , { delegate: Class.CONFIG_BTN, handler: module.configurarHandler }
        ]
    },
    { 
        ctx: Id.MODAL_CONTENT,
        cnfg: [
            { delegate: Class.BTN_UPDATE_CONFIG, handler: module.updateConfigHandler }
            , { delegate: Class.BTN_TEST_CONFIG, handler: module.testConfigHandler }
            , { delegate: Id.UPDATE_CONFIG_SELECT, handler: module.changeUpdateConfigHandler ,
                event_type: "change" }
        ]
    },
    { 
        ctx: Id.TEST_CONSUMO_DIV,
        cnfg: [
            { delegate: Id.TEST_CONSUMO_BTN, handler: module.testConsumo }
        ]
    },
    {
        ctx: Id.REGISTRAR_CONCESIONARIA_DIV,
        cnfg: [
            { delegate: Id.REGISTRAR_CONCESIONARIA_FORM_BTN, handler: module.registrarConcesionaria}
        ]
    }
];