var login = {

    validarUsuario : function() {

        var url = "/web_portal/login/Login.do";

        $('#loginForm')
            .attr('action', url)
            .submit();
    }
};
