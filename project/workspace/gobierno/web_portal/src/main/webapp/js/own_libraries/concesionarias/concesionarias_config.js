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
            { delegate: Class.UPDATE_CONFIG_BTN, handler: module.updateConfigHandler }
            , { delegate: Class.TEST_CONFIG_BTN, handler: module.testConfigHandler }
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
            { delegate: Id.CONCESIONARIA_REGISTRAR_SEND_FORM_BTN, handler: module.registrarConcesionaria}
        ]
    }
];