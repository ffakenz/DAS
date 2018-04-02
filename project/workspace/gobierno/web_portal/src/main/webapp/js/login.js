var administradores = {

    setIdioma : function(idioma) {

        jUtils.executing( "mensaje" );
        $.ajax({
            url: "/web_portal/admin/SetearIdioma.do",
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
    }
};
