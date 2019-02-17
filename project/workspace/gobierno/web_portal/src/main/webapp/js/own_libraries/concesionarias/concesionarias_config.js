const ConcesionariasConfig = (module) => [
    { 
        ctx: Id.TABLE_ADMIN_RESULT,
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
        ctx: Class.LOGINBOX,
        cnfg: [
            { delegate: Id.CONCESIONARIA_REGISTRAR_SEND_FORM_BTN, handler: module.registrarConcesionaria}
        ]
    }
];