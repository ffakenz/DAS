const AdminService = {
    UPDATE_ESTADO_CUENTA(data) {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.UPDATE_ESTADO_CUENTA,
            type: "post",
            data: data,
            error: function(hr){
                console.log("AJAX RESULT UPDATE_ESTADO_CUENTA ERROR %o", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function(json) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT UPDATE_ESTADO_CUENTA SUCCESS %o", json.toString());
                EstadoCuentaService.GET_ESTADO_CUENTA_DASH();
            }
        });
    }
};