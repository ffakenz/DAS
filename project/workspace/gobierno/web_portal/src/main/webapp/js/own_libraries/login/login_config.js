const LoginConfig = (module) => [
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
];