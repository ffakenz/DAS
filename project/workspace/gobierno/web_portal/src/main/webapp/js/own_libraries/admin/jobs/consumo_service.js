const ConsumoService = {

    GET_JOB_RESULTS_REPORT() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.GET_JOB_RESULTS_REPORT,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};