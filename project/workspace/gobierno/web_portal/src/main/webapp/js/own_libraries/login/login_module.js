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
    
    validarUsuario(evt) {
        evt.preventDefault();
        console.log("%o", evt);
        const url = Action.LOGIN_ENDPOINT;
        $('#login_form')
            .attr('action', url)
            .submit();
    }

    goToPrimerIngreso(evt) {
        evt.preventDefault();
        window.location.href = Action.SHOW_LOGIN_PRIMER_INGRESO;
    }

    registrarUsuario(evt) {
        evt.preventDefault();
        
        console.log("%o", evt);
        const url = Action.LOGIN_PRIMER_INGRESO;
        $('#login_primer_ingreso_form')
            .attr('action', url)
            .submit();
    }
};