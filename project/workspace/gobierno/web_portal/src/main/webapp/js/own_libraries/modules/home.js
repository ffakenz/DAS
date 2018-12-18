class Home extends Module {
    constructor(config){
        super(config);
    }

    showRegistrarConcesionaria(evt) {
        jUtils.moveLocationTo(Action.SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT);
    }

    showLogin(evt) {
        jUtils.moveLocationTo(Action.SHOW_LOGIN_ENDPOINT);
    }

    goToHome(evt) {
        jUtils.moveLocationTo(Action.HOME_ENDPOINT);
    }

    goToProfile(evt) {
        jUtils.moveLocationTo(Action.LOGIN_ENDPOINT);
    }    
};