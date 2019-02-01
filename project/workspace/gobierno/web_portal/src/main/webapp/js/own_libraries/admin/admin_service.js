const AdminService = {

    GET_TEST_CONSUMO() {
        $.ajax({
            url: Action.CONSUMO_TEST,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_TEST_CONSUMO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_TEST_CONSUMO SUCCESS %o", html);
                jUtils.showing("resultado", html);
            }
        });
    },
}