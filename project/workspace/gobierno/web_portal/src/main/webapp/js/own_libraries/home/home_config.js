const HomeConfig = (module) => [
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
    },
    {
        ctx: Id.LANG_DIV,
        cnfg: [
            { delegate: Id.ES_LANG_BTN, handler: translator.setIdioma },
            { delegate: Id.EN_LANG_BTN, handler: translator.setIdioma }
        ]
    }
];