class Login extends Module {
    constructor(config){
        super(config);
    }

    closeSession(evt) {
        console.log("closeSession = %o", evt);
        jUtils.moveLocationTo(Action.LOGOUT_ENDPOINT);
    }

    validarUsuario(evt) {
        evt.preventDefault();
        console.log("validarUsuario = %o", evt);
        const url = Action.LOGIN_ENDPOINT;
        $(evt.target.form).attr('action', url).submit();
    }
};