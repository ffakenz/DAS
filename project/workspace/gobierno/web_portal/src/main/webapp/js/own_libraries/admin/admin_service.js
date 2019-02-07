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
                jUtils.showing("table_admin_result", hr);
                jUtils.hiding("loadingDiv",false);
            },
            success: function (html) {
                jUtils.hiding("loadingDiv",true);
                console.log("AJAX RESULT GET_TEST_CONSUMO SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};