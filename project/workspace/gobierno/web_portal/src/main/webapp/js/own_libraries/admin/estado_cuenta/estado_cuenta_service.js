const EstadoCuentaService = {
    GET_CONSULTAR_TODOS() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
                url: Action.ESTADO_CUENTA_CONSULTAR_TODOS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_CONSULTAR_TODOS ERROR %o", hr.responseText);
                jUtils.showing("content_admin_page_div", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function(html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_CONSULTAR_TODOS SUCCESS %o", html);
                jUtils.showing("content_admin_page_div", html);
            }
        });
    },

    GET_ESTADO_CUENTA_DASH() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
                url: Action.ESTADO_CUENTA_DASH,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_ESTADO_CUENTA_DASH ERROR %o", hr.responseText);
                jUtils.showing("content_admin_page_div", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function(html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_ESTADO_CUENTA_DASH SUCCESS %o", html);
                jUtils.showing("content_admin_page_div", html);
            }
        });
    }
};