const ConsumoService = {

    GET_JOB_RESULTS_REPORT() {
        $.ajax({
            url: Action.GET_JOB_RESULTS_REPORT,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT ERROR %o", hr.responseText);
                jUtils.showing("consumo_results_table", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT SUCCESS %o", html);
                jUtils.showing("consumo_results_table", html);
            }
        });
    }
};