const ConsumoService = {

    GET_JOB_RESULTS_REPORT() {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.GET_JOB_RESULTS_REPORT,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("table_admin_result", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("table_admin_result", html);
            }
        });
    }
};