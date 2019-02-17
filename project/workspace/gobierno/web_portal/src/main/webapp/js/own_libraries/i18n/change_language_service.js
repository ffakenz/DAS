const ChangeLanguageService = {
    SET_IDIOMA(idioma) {
        $.ajax({
            url: Action.CHANGE_LANGUAGE_ENDPOINT,
            type: "post",
            dataType: "html",
            data: "idioma=" + idioma,
            error: function(hr){
                console.log("AJAX RESULT SET_IDIOMA [%o] ERROR %o", idioma, hr.responseText);
            },
            success: function(html) {
                console.log("AJAX RESULT SET_IDIOMA [%o] SUCCESS %o", idioma, html);
                location.reload();
            }
        });
    }
};

