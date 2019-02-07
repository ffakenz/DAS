const EstadoCuentaService = {
    GET_CONSULTAR_TODOS() {
        $.ajax({
                url: Action.ESTADO_CUENTA_CONSULTAR_TODOS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_CONSULTAR_TODOS ERROR %o", hr.responseText);
                jUtils.showing("content_admin_page_div", hr);
            },
            success: function(html) {
                console.log("AJAX RESULT GET_CONSULTAR_TODOS SUCCESS %o", html);
                jUtils.showing("content_admin_page_div", html);
            }
        });
    }
};