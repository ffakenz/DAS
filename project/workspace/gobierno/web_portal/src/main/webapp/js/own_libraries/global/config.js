const Config = {
    home: (self) => [
        { 
            ctx: "#header_home", 
            cnfg: [
                { delegate: "#login", handler: self.showLogin }
                , { delegate: "#goToProfile", handler: self.goToProfile }
            ]
        },
        { 
            ctx: "#registrar_concesionaria_div", 
            cnfg: [
                { delegate: "#registrar_concesionaria", handler: self.showRegistrarConcesionaria }
            ]
        },
        { 
            ctx: "#goToHomeDiv", 
            cnfg: [
                { delegate: "#goToHome", handler: self.goToHome }
            ]
        }
    ],
    login: (self) => [
        { 
            ctx: "#header_home", 
            cnfg: [
                { delegate: "#logout", handler: self.closeSession }
            ]
        },
        { 
            ctx: "#loginForm", 
            cnfg: [
                { delegate: "#login_btn", handler: self.validarUsuario }
            ]
        }
    ],
    concesionarias: (self) => [
        { 
            ctx: "#tableConcesionarias", 
            cnfg: [
                { delegate: ".aprobar_btn", handler: self.aprobarHandler }
                , { delegate: ".desaprobar_btn", handler: self.desAprobarHandler }
                , { delegate: ".config_btn", handler: self.configurarHandler }
            ]
        },
        { 
            ctx: "#modal_content", 
            cnfg: [
                { delegate: `.${Class.BTN_UPDATE_CONFIG}`, handler: self.updateConfigHandler }
                , { delegate: `.${Class.BTN_TEST_CONFIG}`, handler: self.testConfigHandler }
                , { delegate: `#${Id.UPDATE_CONFIG_SELECT}`, handler: self.changeUpdateConfigHandler ,
                    event_type: "onchange" }
            ]
        },
        { 
            ctx: "#test_consumo_div", 
            cnfg: [
                { delegate: "#test_consumo_div", handler: self.testConsumo }
            ]
        }
    ]
};