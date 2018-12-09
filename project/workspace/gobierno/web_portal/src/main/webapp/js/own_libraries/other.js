var translator = {

    setIdioma : function(idioma) {

        jUtils.executing( "mensaje" );
        $.ajax({
            url: Globals.CHANGE_LANGUAGE_ENDPOINT,
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
