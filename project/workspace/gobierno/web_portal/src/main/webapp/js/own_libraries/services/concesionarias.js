const ConcesionariasService = {

    GET_CONSULTAR_TODAS() {
        $.ajax({
            url: Action.CONCESIONARIA_CONSULTAR_TODAS,
            type: "get",
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr.responseText);
                jUtils.showing("concesionariasDiv", hr);
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                jUtils.showing("tableConcesionarias", html);
            }
        });
    },

    POST_TEST_CONFIG(data) {
        $.ajax({
            url: Action.TEST_CONFIG,
            type: "post",
            data: data,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                jUtils.showing(Id.TEST_CONFIG_LABEL, hr);
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                jUtils.showing(Id.TEST_CONFIG_LABEL, html);
                $("#modal_generic").modal("show");
            }
        });
    },

    /*todo: change to get*/
    POST_CONSULTAR_CONFIG_PARAM(idConcesionaria) {
        $.ajax({
            url: Action.CONCESIONARIA_CONSULTAR_CONFIG_PARAM,
            type: "post",
            data: "id=" + idConcesionaria,
            dataType: "json",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr.responseText);

                jUtils.showing("modal_content", hr);
                $("#modal_generic").modal("show");
            },
            success: function(jsonArray) {
                console.log("AJAX RESULT SUCCESS %o", jsonArray);
                console.log("MODULE %o", jsonArray);
                /* EACH SUCCESS CHANGES THE STATE { LAST_CONFIGS_CONSULTED_ST } USING formConsultarConfings */
                const html = ConcesionariasUtils.formConsultarConfig(jsonArray, idConcesionaria);
                jUtils.showing("modal_content", html);
                $("#modal_generic").modal("show");
            }
        });
    },

    POST_APROBAR_CONCESIONARIA(idConcesionaria) {
        $.ajax({
            url: Action.APROBAR_CONCESIONARIA_ENDPOINT,
            type: "post",
            data: "id=" + idConcesionaria,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                alert("AJAX RESULT ERROR " + hr);
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                $(`#concesionaria_row-${idConcesionaria}`).replaceWith(html);
            }
        });
    },

    POST_DESAPROBAR_CONCESIONARIA(idConcesionaria) {
        $.ajax({
            url: Action.DESAPROBAR_CONCESIONARIA_ENDPOINT,
            type: "post",
            data: "id=" + idConcesionaria,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                alert("AJAX RESULT ERROR " + hr);
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                $(`#concesionaria_row-${idConcesionaria}`).replaceWith(html);
            }
        });
    },

    POST_CONFIG_CONCESIONARIA(data) {
        $.ajax({
            url: Action.CONFIG_CONCESIONARIA,
            type: "post",
            data: data,
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                jUtils.showing(Id.UPDATE_CONFIG_LABEL, hr);
            },
            success: function(json) {
                console.log("AJAX RESULT SUCCESS %o", json.toString());
                jUtils.showing(Id.UPDATE_CONFIG_LABEL, "Configurada exitosamente");
            }
        });
    }
};