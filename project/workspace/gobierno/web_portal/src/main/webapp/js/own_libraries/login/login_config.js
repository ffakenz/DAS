const LoginConfig = (module) => [
    { 
        ctx: Id.HEADER_HOME,
        cnfg: [
            { delegate: Id.LOGOUT_BTN, handler: module.closeSession }
        ]
    },
    { 
        ctx: Class.LOGINBOX,
        cnfg: [
            { delegate: Id.FIRST_LOGIN_BTN, handler: module.goToPrimerIngreso },
            { delegate: Id.LOGIN_REGISTRAR_BTN, handler: module.registrarUsuario },
            { delegate: Id.LOGIN_BTN, handler: module.validarUsuario }
        ]
    }
];