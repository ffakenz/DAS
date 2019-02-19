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
                jUtils.showing("content_admin_page_div", html);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_JOB_RESULTS_REPORT SUCCESS %o", html);
                jUtils.showing("content_admin_page_div", html);
                $("#table_admin_result_title").html(jUtils.addTitle("Calendario Sorteo"));
            }
        });
    }
};