class Login extends Module {
    constructor(config){
        super(config);
    }

    goToProfile(evt) {
        evt.preventDefault();
        console.log("goToProfile = %o", evt);
        jUtils.moveLocationTo(Action.LOGIN_ENDPOINT);
    }

    showLogin(evt) {    
        evt.preventDefault();
        console.log("showLogin = %o", evt);
        jUtils.moveLocationTo(Action.SHOW_LOGIN_ENDPOINT);
    }

    closeSession(evt) {
        evt.preventDefault();
        console.log("closeSession = %o", evt);
        jUtils.moveLocationTo(Action.LOGOUT_ENDPOINT);
    }

    validarUsuario(evt) {
        evt.preventDefault();
        console.log("validarUsuario = %o", evt);
        const url = Action.LOGIN_ENDPOINT;
        $(evt.target.form).attr('action', url).submit();
    }

    goToPrimerIngreso(evt) {
        evt.preventDefault();
        console.log("goToPrimerIngreso = %o", evt);
        jUtils.moveLocationTo(Action.SHOW_LOGIN_PRIMER_INGRESO);
    }

    registrarUsuario(evt) {
        evt.preventDefault();
        console.log("registrarUsuario = %o", evt);
        const url = Action.LOGIN_PRIMER_INGRESO;
        $('#login_primer_ingreso_form')
            .attr('action', url)
            .submit();
    }
};

$(() => {
    /* Initialization Code */
    const login = new Login(LoginConfig);
    jUtils.loadModule(login);
    console.log("Login View Loaded Modules: [LoginConfig] = %o, [Login] = %o",
        LoginConfig, login);
});