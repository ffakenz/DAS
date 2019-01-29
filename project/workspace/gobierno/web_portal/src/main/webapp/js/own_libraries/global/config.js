const Config = {
    home: (module) => [
        { 
            ctx: "#header_home", 
            cnfg: [
                { delegate: "#login", handler: module.showLogin }
                , { delegate: "#goToProfile", handler: module.goToProfile }
            ]
        },
        { 
            ctx: "#registrar_concesionaria_div", 
            cnfg: [
                { delegate: "#registrar_concesionaria", handler: module.showRegistrarConcesionaria }
            ]
        },
        { 
            ctx: "#goToHomeDiv", 
            cnfg: [
                { delegate: "#goToHome", handler: module.goToHome }
            ]
        }
    ],
    login: (module) => [
        { 
            ctx: "#header_home", 
            cnfg: [
                { delegate: "#logout", handler: module.closeSession }
            ]
        },
        { 
            ctx: "#loginForm", 
            cnfg: [
                { delegate: "#login_btn", handler: module.validarUsuario }
            ]
        }
    ],
    concesionarias: (module) => [
        { 
            ctx: "#tableConcesionarias", 
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
                    event_type: "onchange" }
            ]
        },
        { 
            ctx: "#test_consumo_div", 
            cnfg: [
                { delegate: "#test_consumo_div", handler: module.testConsumo }
            ]
        }
    ]
};