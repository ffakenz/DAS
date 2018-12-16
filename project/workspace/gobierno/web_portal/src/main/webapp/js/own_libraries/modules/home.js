'use strict';
class Home {
    constructor(){
        this.utils = new Utils();
    }

    showRegistrarConcesionaria() {
        this.utils.moveLocationTo(Action.SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT);
    }

    showLogin() {
        this.utils.moveLocationTo(Action.SHOW_LOGIN_ENDPOINT);
    }

    goToHome() {
        this.utils.moveLocationTo(Action.HOME_ENDPOINT);
    }

    goToProfile() {
        this.utils.moveLocationTo(Action.LOGIN_ENDPOINT);
    }
}

const home = new Home();