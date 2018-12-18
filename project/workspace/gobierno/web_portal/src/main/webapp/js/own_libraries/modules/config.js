const Config = {
    home: (self) => [
        { 
            ctx: "#header_home", 
            cnfg: [
                { delegate: "#login", handler: self.showLogin}
                , { delegate: "#goToProfile", handler: self.goToProfile}
            ]
        },
        { 
            ctx: "#registrar_concesionaria_div", 
            cnfg: [
                { delegate: "#registrar_concesionaria", handler: self.showRegistrarConcesionaria}
            ]
        },
        { 
            ctx: "#goToHomeDiv", 
            cnfg: [
                { delegate: "#goToHome", handler: self.goToHome}
            ]
        }
    ],
    login: (self) => [
        { 
            ctx: "#header_home", 
            cnfg: [
                { delegate: "#logout", handler: self.closeSession}
            ]
        },
        { 
            ctx: "#loginForm", 
            cnfg: [
                { delegate: "#login_btn", handler: self.validarUsuario}
            ]
        }
    ]
};