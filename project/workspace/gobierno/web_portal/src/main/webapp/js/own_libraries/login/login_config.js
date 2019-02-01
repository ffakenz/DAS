const LoginConfig = (module) => [
    { 
        ctx: Id.HEADER_HOME,
        cnfg: [
            { delegate: Id.LOGOUT_BTN, handler: module.closeSession }
        ]
    },
    { 
        ctx: Id.LOGIN_FORM,
        cnfg: [
            { delegate: Id.LOGIN_BTN, handler: module.validarUsuario }
        ]
    },
    { 
        ctx: Id.FIRST_LOGIN_DIV,
        cnfg: [
            { delegate: Id.FIRST_LOGIN_BTN, handler: module.goToPrimerIngreso }
        ]
    },
    { 
        ctx: Id.LOGIN_PRIMER_INGRESO_FORM,
        cnfg: [
            { delegate: Id.LOGIN_REGISTRAR_BTN, handler: module.registrarUsuario }
        ]
    }
];