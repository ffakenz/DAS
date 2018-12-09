$(() => {

    const login = {

        validarUsuario : (evt) => {

            evt.preventDefault();

            console.log("%o", evt);

            const url = "/web_portal/login/Login.do";

            $('#loginForm')
                .attr('action', url)
                .submit();
        }
    };

    $('#login_btn').on('click', login.validarUsuario);

});


