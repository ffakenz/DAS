'use strict';
class Module {
    constructor(eventHandlers) {
        this.eventHandlers = eventHandlers(this);
    }
    getEventHandlers(){
        return this.eventHandlers;
    }
};

class Home extends Module {
    constructor(config){
        super(config);
    }

    showRegistrarConcesionaria(evt) {
        Utils.moveLocationTo(Action.SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT);
    }

    showLogin(evt) {
        Utils.moveLocationTo(Action.SHOW_LOGIN_ENDPOINT);
    }

    goToHome(evt) {
        Utils.moveLocationTo(Action.HOME_ENDPOINT);
    }

    goToProfile(evt) {
        Utils.moveLocationTo(Action.LOGIN_ENDPOINT);
    }
};