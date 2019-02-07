const CalendarioService = {

    GET_SORTEOS() {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.GET_SORTEOS,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_SORTEOS ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("table_admin_result", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_SORTEOS SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("table_admin_result", html);
            }
        });
    },

    GET_CALENDARIO(data) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.GET_CALENDARIO,
            type: "POST",
            data: data,
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_CALENDARIO ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("calendar_main_div", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_CALENDARIO SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("calendar_main_div", html);
            }
        });
    },
    
    GET_CALENDAR_CELL_MODAL(data) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.GET_CALENDAR_CELL_MODAL,
            type: "POST",
            data: data,
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_CALENDAR_CELL_MODAL ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("modal_content", hr);
                $("#config_concesionaria_modal").modal("show");
            },
            success: function (html) {
                console.log("AJAX RESULT GET_CALENDAR_CELL_MODAL SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("modal_content", html);
                $("#config_concesionaria_modal").modal("show");
            }
        });
    },

    CREAR_SORTEO(fechaEjecucion) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.CREAR_SORTEO,
            type: "post",
            dataType: "html",
            data: "fecha_ejecucion=" + fechaEjecucion,
            error: function (hr) {
                console.log("AJAX RESULT CREAR_SORTEO ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT CREAR_SORTEO SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("resultado", html);
            }
        });
    },

    EJECUTAR_SORTEO(idSorteo) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.EJECUTAR_SORTEO,
            type: "post",
            dataType: "html",
            data: "id=" + idSorteo,
            error: function (hr) {
                console.log("AJAX RESULT EJECUTAR_SORTEO ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT EJECUTAR_SORTEO SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("resultado", html);
            }
        });
    },

    ACTUALIZAR_FECHA_SORTEO(idSorteo, fecha) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.ACTUALIZAR_FECHA_SORTEO,
            type: "post",
            dataType: "html",
            data: {"id": idSorteo, "fecha": fecha},
            error: function (hr) {
                console.log("AJAX RESULT ACTUALIZAR_FECHA_SORTEO ERROR %o", hr.responseText);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT ACTUALIZAR_FECHA_SORTEO SUCCESS %o", html);
                jUtils.hiding( "loadingDiv");
                jUtils.showing("resultado", html);
            }
        });
    }
};