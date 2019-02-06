const LoginConfig = (module) => [
    { 
        ctx: Id.HEADER_HOME,
        cnfg: [
            { delegate: Id.LOGOUT_BTN, handler: module.closeSession }
            , { delegate: Id.SHOW_LOGIN_BTN, handler: module.showLogin }
            , { delegate: Id.GO_TO_PROFILE_BTN, handler: module.goToProfile }
        ]
    },
    { 
        ctx: Class.LOGINBOX,
        cnfg: [
            { delegate: Id.FIRST_LOGIN_BTN, handler: module.goToPrimerIngreso },
            { delegate: Id.LOGIN_BTN, handler: module.validarUsuario }
        ]
    }
];