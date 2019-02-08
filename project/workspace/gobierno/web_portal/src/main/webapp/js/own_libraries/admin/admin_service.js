const AdminService = {

    GET_TEST_CONSUMO() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.CONSUMO_TEST,
            type: "get",
            dataType: "html",
            onLine: function () {},
            error: function (hr) {
                console.log("AJAX RESULT GET_TEST_CONSUMO ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_TEST_CONSUMO SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
            }
        });
    },


    EXECUTE_SORTEO() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.CONSUMO_TEST,
            type: "get",
            dataType: "html",
            onLine: function () {},
            error: function (hr) {
                console.log("AJAX RESULT GET_TEST_CONSUMO ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_TEST_CONSUMO SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
            }
        });
    },

    EXECUTE_CONSUMO() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.CONSUMO_TEST,
            type: "get",
            dataType: "html",
            onLine: function () {},
            error: function (hr) {
                console.log("AJAX RESULT GET_TEST_CONSUMO ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_TEST_CONSUMO SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};