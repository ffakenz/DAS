const CalendarioService = {

    GET_SORTEOS() {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.GET_SORTEOS,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_SORTEOS ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_SORTEOS SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
                $("#table_admin_result_title").html(jUtils.addTitle("Sorteos Registrados"));
            }
        });
    },

    GET_CALENDARIO(data) {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.GET_CALENDARIO,
            type: "POST",
            data: data,
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_CALENDARIO ERROR %o", hr.responseText);
                jUtils.showing("calendar_main_div", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_CALENDARIO SUCCESS %o", html);
                jUtils.showing("calendar_main_div", html);
            }
        });
    },
    
    GET_CALENDAR_CELL_MODAL(data) {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.GET_CALENDAR_CELL_MODAL,
            type: "POST",
            data: data,
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_CALENDAR_CELL_MODAL ERROR %o", hr.responseText);
                jUtils.showing("modal_content", hr);
                $("#config_concesionaria_modal").modal("show");
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_CALENDAR_CELL_MODAL SUCCESS %o", html);
                jUtils.showing("modal_content", html);
                $("#config_concesionaria_modal").modal("show");
            }
        });
    },

    EJECUTAR_SORTEO(idSorteo) {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.EJECUTAR_SORTEO,
            type: "post",
            dataType: "html",
            data: "id=" + idSorteo,
            error: function (hr) {
                console.log("AJAX RESULT EJECUTAR_SORTEO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT EJECUTAR_SORTEO SUCCESS %o", html);
                jUtils.showing("resultado", html);
            }
        });
    },

    CREAR_SORTEO(data) {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.CREAR_SORTEO,
            type: "post",
            dataType: "html",
            data: data,
            error: function (hr) {
                console.log("AJAX RESULT CREAR_SORTEO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
                $("#loadingDiv").modal("hide");
                $("#config_concesionaria_modal").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                jUtils.showing("resultado", html);
                const value = jUtils.dataToJson(data);
                const cell = $(`#cell_day-${value["cell_day"]}`);
                $(cell).removeClass("empty_cell");
                $(cell).addClass("sorteo_cell");
                const form = $(cell).find("form");
                console.log("HTML %o", html);
                const json = JSON.parse(html);
                console.log("HTML ID %o", json);
                const htmlToInsert = `<input type="hidden" name="cell_id" value="${json.id}"/>`;
                $(form).append(htmlToInsert);
                $("#config_concesionaria_modal").modal("hide");
            }
        });
    },
    
    ACTUALIZAR_SORTEO(data) {
        $("#loadingDiv").modal("show");
        jUtils.executing("loadingDiv");
        $.ajax({
            url: Action.ACTUALIZAR_SORTEO,
            type: "post",
            dataType: "html",
            data: data,
            error: function (hr) {
                console.log("AJAX RESULT ACTUALIZAR_SORTEO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT ACTUALIZAR_SORTEO SUCCESS %o", html);
                jUtils.showing("resultado", html);
            }
        });
    }
};