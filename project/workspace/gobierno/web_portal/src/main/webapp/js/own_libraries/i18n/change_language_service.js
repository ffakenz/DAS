const ChangeLanguageService = {
    SET_IDIOMA(lang) {
        $.ajax({
            url: Action.CHANGE_LANGUAGE_ENDPOINT,
            type: "post",
            dataType: "html",
            data: lang,
            error: function(hr){
                console.log("AJAX RESULT SET_IDIOMA [%o] ERROR %o", lang, hr.responseText);
            },
            success: function(html) {
                console.log("AJAX RESULT SET_IDIOMA [%o] SUCCESS %o", lang, html);
                location.reload();
            }
        });
    }
};

