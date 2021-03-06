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
                jUtils.showing("content_admin_page_div", html);
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT GET_SORTEOS SUCCESS %o", html);
                jUtils.showing("content_admin_page_div", html);
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
                $("#loadingDiv").modal("hide");
                $("#config_concesionaria_modal").modal("hide");
            },
            success: function (html) {
                console.log("AJAX RESULT CREAR_SORTEO SUCCESS %o", html);
                $("#loadingDiv").modal("hide");
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
                CalendarioService.GET_SORTEOS();
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
                $("#loadingDiv").modal("hide");
            },
            success: function (html) {
                $("#loadingDiv").modal("hide");
                console.log("AJAX RESULT ACTUALIZAR_SORTEO SUCCESS %o", html);
            }
        });
    }
};