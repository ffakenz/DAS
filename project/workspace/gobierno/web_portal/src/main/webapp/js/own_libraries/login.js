$(() => {

    const login = {

        validarUsuario : (evt) => {

            evt.preventDefault();

            console.log("%o", evt);

            const url = Globals.LOGIN_ENDPOINT;

            $('#loginForm')
                .attr('action', url)
                .submit();
        }
    };

    $('#login_btn').on('click', login.validarUsuario);

});