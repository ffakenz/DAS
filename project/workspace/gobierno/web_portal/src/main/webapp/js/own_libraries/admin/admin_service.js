const AdminService = {

    GET_TEST_CONSUMO() {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.CONSUMO_TEST,
            type: "get",
            dataType: "html",
            onLine: function () {},
            error: function (hr) {
                console.log("AJAX RESULT GET_TEST_CONSUMO ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("table_admin_result", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_TEST_CONSUMO SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};