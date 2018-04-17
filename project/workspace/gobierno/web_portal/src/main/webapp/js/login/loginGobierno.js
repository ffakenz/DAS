var loginGobierno = {
    validarUsuarioGobierno : function() {

        jUtils.executing( "mensaje" );
        $.ajax({
            url: "/web_portal/login/ValidarUsuarioGobierno.do",
            type: "post",
            data: $( "#formulario" ).serialize(),
            dataType: "html",
            error: function(hr){
                jUtils.hiding("mensaje");
                jUtils.showing("error", hr.responseText);
            },
            success: function(html) {
                jUtils.hiding("error");
                jUtils.showing("mensaje", html);
            }
        });
    }
};
