const EstadoCuentaService = {
    GET_CONSULTAR_TODOS() {
        jUtils.executing( "loadingDiv");
        $.ajax({
                url: Action.ESTADO_CUENTA_CONSULTAR_TODOS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_CONSULTAR_TODOS ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                jUtils.hiding("loadingDiv", false);
            },
            success: function(html) {
                jUtils.hiding("loadingDiv", true);
                console.log("AJAX RESULT GET_CONSULTAR_TODOS SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};