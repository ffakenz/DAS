const HomeConfig = (module) => [
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
];