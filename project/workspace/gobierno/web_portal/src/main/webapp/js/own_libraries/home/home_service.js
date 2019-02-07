const HomeService = {

    GET_CONSULTAR_APROBADAS() {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.CONCESIONARIA_CONSULTAR_APROBADAS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_CONSULTAR_APROBADAS ERROR %o", hr.responseText);
                jUtils.showing("conc_aprobadas_div", "En este momento no se encuentra disponible la lista de concesionarias aprobadas ... Disculpe los inconvenients !");
            },
            success: function(html) {
                console.log("AJAX RESULT GET_CONSULTAR_APROBADAS SUCCESS %o", html);
                jUtils.showing("conc_aprobadas_div", html);
            }
        });
    }
};