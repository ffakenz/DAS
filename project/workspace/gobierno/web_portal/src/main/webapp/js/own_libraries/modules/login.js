class Login {
    constructor(){}

    closeSession() {
        Utils.moveLocationTo(Action.LOGOUT_ENDPOINT);
    }
}