class Login extends Module {
    constructor(config){
        super(config);
    }

    closeSession(evt) {
        Utils.moveLocationTo(Action.LOGOUT_ENDPOINT);
    }
};