const ConcesionariasService = {

    GET_CONSULTAR_TODAS() {

        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.CONCESIONARIA_CONSULTAR_TODAS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT GET_CONSULTAR_TODAS ERROR %o", hr.responseText);
                jUtils.showing("table_admin_result", hr);
                jUtils.hiding("loadingDiv",false);
            },
            success: function(html) {
                console.log("AJAX RESULT GET_CONSULTAR_TODAS SUCCESS %o", html);
                jUtils.showing("table_admin_result", html);
                jUtils.hiding("loadingDiv",false);
            }
        });
    },

    POST_TEST_CONFIG(data) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.TEST_CONFIG,
            type: "post",
            data: data,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT POST_TEST_CONFIG ERROR %o", hr);
                jUtils.showing(Id.TEST_CONFIG_LABEL, hr);
            },
            success: function(html) {
                console.log("AJAX RESULT POST_TEST_CONFIG SUCCESS %o", html);
                jUtils.showing(Id.TEST_CONFIG_LABEL, html);
            }
        });
    },

    POST_CONSULTAR_CONFIG_PARAM(idConcesionaria, formConsultarConfig) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.CONCESIONARIA_CONSULTAR_CONFIG_PARAM,
            type: "post",
            data: "id=" + idConcesionaria,
            dataType: "json",
            error: function(hr){
                console.log("AJAX RESULT POST_CONSULTAR_CONFIG_PARAM ERROR %o", hr.responseText);
                jUtils.showing("modal_content", hr);
                $("#config_concesionaria_modal").modal("show");
            },
            success: function(jsonArray) {
                console.log("AJAX RESULT POST_CONSULTAR_CONFIG_PARAM SUCCESS %o", jsonArray);
                /* EACH SUCCESS CHANGES THE STATE { LAST_CONFIGS_CONSULTED_ST } USING formConsultarConfings */
                const html = formConsultarConfig(jsonArray, idConcesionaria);
                console.log("CONSULTAR HTML = %o", html);
                jUtils.showing("modal_content", html);
                $("#config_concesionaria_modal").modal("show");
            }
        });
    },

    POST_APROBAR_CONCESIONARIA(idConcesionaria) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.APROBAR_CONCESIONARIA_ENDPOINT,
            type: "post",
            data: "id=" + idConcesionaria,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT POST_APROBAR_CONCESIONARIA ERROR %o", hr);
                alert("AJAX RESULT ERROR " + hr);
            },
            success: function(html) {
                console.log("AJAX RESULT POST_APROBAR_CONCESIONARIA SUCCESS %o", html);
                $(`#concesionaria_row-${idConcesionaria}`).replaceWith(html);
            }
        });
    },

    POST_DESAPROBAR_CONCESIONARIA(idConcesionaria) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.DESAPROBAR_CONCESIONARIA_ENDPOINT,
            type: "post",
            data: "id=" + idConcesionaria,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT POST_DESAPROBAR_CONCESIONARIA ERROR %o", hr);
                alert("AJAX RESULT ERROR " + hr);
            },
            success: function(html) {
                console.log("AJAX RESULT POST_DESAPROBAR_CONCESIONARIA SUCCESS %o", html);
                $(`#concesionaria_row-${idConcesionaria}`).replaceWith(html);
            }
        });
    },

    POST_CONFIG_CONCESIONARIA(data) {
        jUtils.executing( "loadingDiv");
        $.ajax({
            url: Action.CONFIG_CONCESIONARIA,
            type: "post",
            data: data,
            error: function(hr){
                console.log("AJAX RESULT POST_CONFIG_CONCESIONARIA ERROR %o", hr);
                jUtils.showing(Id.UPDATE_CONFIG_LABEL, hr);
            },
            success: function(json) {
                console.log("AJAX RESULT POST_CONFIG_CONCESIONARIA SUCCESS %o", json.toString());
                jUtils.showing(Id.UPDATE_CONFIG_LABEL, "Configurada exitosamente");
            }
        });
    }
};