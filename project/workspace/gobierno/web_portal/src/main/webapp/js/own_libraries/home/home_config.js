const HomeConfig = (module) => [
    { 
        ctx: "#header_home", 
        cnfg: [
            { delegate: "#show_login_btn", handler: module.showLogin }
            , { delegate: "#go_to_profile_btn", handler: module.goToProfile }
        ]
    },
    { 
        ctx: "#registrar_concesionaria_div", 
        cnfg: [
            { delegate: "#registrar_concesionaria_btn", handler: module.showRegistrarConcesionaria }
        ]
    },
    { 
        ctx: "#go_to_home_div",
        cnfg: [
            { delegate: "#go_to_home_btn", handler: module.goToHome }
        ]
    }
];