$(() => {

    const login = {

        validarUsuario : (evt) => {

            evt.preventDefault();

            console.log("%o", evt);

            const url = Action.LOGIN_ENDPOINT;

            $('#loginForm')
                .attr('action', url)
                .submit();
        },

        goToPrimerIngreso : () => {

            window.location.href = Action.SHOW_LOGIN_PRIMER_INGRESO;
        }
    };

    const registro = {

        registrarUsuario : (evt) => {

            evt.preventDefault();

            console.log("%o", evt);

            const url = Action.LOGIN_PRIMER_INGRESO;

            $('#loginPrimerIngresoForm')
                .attr('action', url)
                .submit();
        }
    };

    $('#login_btn').on('click', login.validarUsuario);
    $('#first_login_btn').on('click', login.goToPrimerIngreso);
    $('#login_registrar_btn').on('click', registro.registrarUsuario);
});