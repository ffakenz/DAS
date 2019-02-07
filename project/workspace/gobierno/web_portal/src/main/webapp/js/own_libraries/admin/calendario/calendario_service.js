const CalendarioService = {

    GET_SORTEOS() {
        $.ajax({
            url: Action.GET_SORTEOS,
            type: "get",
            dataType: "html",
            error: function (hr) {
                console.log("AJAX RESULT GET_SORTEOS ERROR %o", hr.responseText);
                jUtils.showing("content_admin_page_div", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT GET_SORTEOS SUCCESS %o", html);
                jUtils.showing("content_admin_page_div", html);
            }
        });
    },

    CREAR_SORTEO(fechaEjecucion) {
        $.ajax({
            url: Action.CREAR_SORTEO,
            type: "post",
            dataType: "html",
            data: "fecha_ejecucion=" + fechaEjecucion,
            error: function (hr) {
                console.log("AJAX RESULT CREAR_SORTEO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT CREAR_SORTEO SUCCESS %o", html);
                jUtils.showing("resultado", html);
            }
        });
    },

    EJECUTAR_SORTEO(idSorteo) {
        $.ajax({
            url: Action.EJECUTAR_SORTEO,
            type: "post",
            dataType: "html",
            data: "id=" + idSorteo,
            error: function (hr) {
                console.log("AJAX RESULT EJECUTAR_SORTEO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT EJECUTAR_SORTEO SUCCESS %o", html);
                jUtils.showing("resultado", html);
            }
        });
    },

    ACTUALIZAR_FECHA_SORTEO(idSorteo, fecha) {
        $.ajax({
            url: Action.ACTUALIZAR_FECHA_SORTEO,
            type: "post",
            dataType: "html",
            data: {"id": idSorteo, "fecha": fecha},
            error: function (hr) {
                console.log("AJAX RESULT ACTUALIZAR_FECHA_SORTEO ERROR %o", hr.responseText);
                jUtils.showing("resultado", hr);
            },
            success: function (html) {
                console.log("AJAX RESULT ACTUALIZAR_FECHA_SORTEO SUCCESS %o", html);
                jUtils.showing("resultado", html);
            }
        });
    }
};