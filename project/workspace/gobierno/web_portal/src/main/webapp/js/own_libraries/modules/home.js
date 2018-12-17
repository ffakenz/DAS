'use strict';
class Home {
    constructor(){
    }

    showRegistrarConcesionaria() {
        Utils.moveLocationTo(Action.SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT);
    }

    showLogin() {
        Utils.moveLocationTo(Action.SHOW_LOGIN_ENDPOINT);
    }

    goToHome() {
        Utils.moveLocationTo(Action.HOME_ENDPOINT);
    }

    goToProfile() {
        Utils.moveLocationTo(Action.LOGIN_ENDPOINT);
    }
};