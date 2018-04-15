var administradores = {

    setIdioma : function(idioma) {

        jUtils.executing( "mensaje" );
        $.ajax({
            url: "/web_portal/other/ChangeLanguage.do",
            type: "post",
            data: "idioma=" + idioma,
            dataType: "html",
            error: function(hr){
                jUtils.hiding("resultIdioma");
                jUtils.showing("error", hr.responseText);
            },
            success: function(html) {
                jUtils.hiding("error");
                jUtils.showing("resultIdioma", html);
            }
        });
    },

    validarAdmin : function() {

        jUtils.executing( "mensaje" );
        $.ajax({
            url: "/web_portal/login/ValidarAdmin.do",
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
