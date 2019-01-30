const LoginConfig = (module) => [
    { 
        ctx: "#header_home", 
        cnfg: [
            { delegate: "#logout", handler: module.closeSession }
        ]
    },
    { 
        ctx: "#login_form", 
        cnfg: [
            { delegate: "#login_btn", handler: module.validarUsuario }
        ]
    },
    { 
        ctx: "#first_login_div", 
        cnfg: [
            { delegate: "#first_login_btn", handler: module.goToPrimerIngreso }
        ]
    },
    { 
        ctx: "#login_primer_ingreso_form", 
        cnfg: [
            { delegate: "#login_registrar_btn", handler: module.registrarUsuario }
        ]
    }
];