class Home extends Module {
    constructor(config){
        super(config);
    }

    showRegistrarConcesionaria(evt) {
        jUtils.moveLocationTo(Action.SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT);
    }

    goToHome(evt) {
        jUtils.moveLocationTo(Action.HOME_ENDPOINT);
    }

 
};

$(() => {
    const home = new Home(HomeConfig);
    jUtils.loadModule(home);
    console.log("Home View Loaded Modules: [HomeConfig] = %o, [Home] = %o", HomeConfig, home);
});