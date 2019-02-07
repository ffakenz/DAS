const EstadoCuentaService = {
    GET_CONSULTAR_TODOS() {
        jUtils.executing( "loadingDiv");
        $.ajax({
                url: Action.ESTADO_CUENTA_CONSULTAR_TODOS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_CONSULTAR_TODOS ERROR %o", hr.responseText);
                jUtils.hiding("loadingDiv", true);
                jUtils.showing("table_admin_result", hr);
            },
            success: function(html) {
                console.log("AJAX RESULT GET_CONSULTAR_TODOS SUCCESS %o", html);
                jUtils.hiding("loadingDiv", true);
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};