const translator = {

    setIdioma : function(evt) {
        jUtils.executing( "mensaje" );
        $.ajax({
            url: Action.CHANGE_LANGUAGE_ENDPOINT,
            type: "post",
            data: "idioma=" + evt.target.value,
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
