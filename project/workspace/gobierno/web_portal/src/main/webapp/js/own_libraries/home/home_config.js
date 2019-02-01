const HomeConfig = (module) => [
    { 
        ctx: Id.HEADER_HOME,
        cnfg: [
            { delegate: Id.SHOW_LOGIN_BTN, handler: module.showLogin }
            , { delegate: Id.GO_TO_PROFILE_BTN, handler: module.goToProfile }
        ]
    },
    { 
        ctx: Id.REGISTRAR_CONCESIONARIA_DIV,
        cnfg: [
            { delegate: Id.REGISTRAR_CONCESIONARIA_BTN, handler: module.showRegistrarConcesionaria }
        ]
    },
    { 
        ctx: Id.GO_TO_HOME_DIV,
        cnfg: [
            { delegate: Id.GO_TO_HOME_BTN, handler: module.goToHome }
        ]
    }
];