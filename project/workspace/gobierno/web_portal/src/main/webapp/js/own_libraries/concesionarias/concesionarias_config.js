const ConcesionariasConfig = (module) => [
    { 
        ctx: "#concesionarias_table",
        cnfg: [
            { delegate: ".aprobar_btn", handler: module.aprobarHandler }
            , { delegate: ".desaprobar_btn", handler: module.desAprobarHandler }
            , { delegate: ".config_btn", handler: module.configurarHandler }
        ]
    },
    { 
        ctx: "#modal_content", 
        cnfg: [
            { delegate: `.${Class.BTN_UPDATE_CONFIG}`, handler: module.updateConfigHandler }
            , { delegate: `.${Class.BTN_TEST_CONFIG}`, handler: module.testConfigHandler }
            , { delegate: `#${Id.UPDATE_CONFIG_SELECT}`, handler: module.changeUpdateConfigHandler ,
                event_type: "change" }
        ]
    },
    { 
        ctx: "#test_consumo_div", 
        cnfg: [
            { delegate: "#test_consumo_div", handler: module.testConsumo }
        ]
    },
    {
        ctx: "#registrar_concesionaria",
        cnfg: [
            { delegate: `#${Id.REGISTRAR_CONCESIONARIA}`, handler: module.registrarConcesionaria}
        ]
    }
];