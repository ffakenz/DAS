var login = {

    validarUsuario : function() {

        console.log("entro al validarUsuario");

        jUtils.executing( "mensaje" );
        $.ajax({
            url: "/web_portal/login/Login.do",
            type: "post",
            data: $( "#formulario" ).serialize(),
            dataType: "html",
            error: function(hr){
                window.location.href = "/web_portal/login/FailLogin.do";
            },
            success: function(html) {
                window.location.href = "/web_portal/login/SuccessLogin.do";
            }
        });
    }
};
