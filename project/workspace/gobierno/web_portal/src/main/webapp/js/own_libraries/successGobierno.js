"use strict";

$(() => {

    $("#tableConcesionarias").DataTable();

    /* HELPERS */
    const buildConfigurarConcesionariasForm = (jsonArray) => {
        if(jsonArray.length == 0) {
            return updateConfigForm.showForm(ConfigTecno.REST);
        }
        else {
            const head = jsonArray[0];
            const configTecno = head["configTecno"];
            const configParams = jsonArray.map( param => {
                let obj = {};
                obj["name"] = param.configParam;
                obj["value"] = param.value;
                return obj;
            });
            return updateConfigForm.showForm(
                {"configTecno": configTecno, "configParams": configParams}
            );
        }
    };

    /* AJAX CALLS */
    const aprobarHandler = (evt) => {
        console.log("Aprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];

        $.ajax({
            url: Globals.APROBAR_CONCESIONARIA_ENDPOINT,
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
    };

    const desAprobarHandler = (evt) => {
        console.log("DesAprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];

        $.ajax({
            url: Globals.DESAPROBAR_CONCESIONARIA_ENDPOINT,
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
    };

    const configurarHandler = (evt) => {
        console.log("Configurando Concesionaria %o", evt.target.id);

        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];

        $.ajax({
            url: Globals.CONCESIONARIA_CONSULTAR_CONFIG_PARAM,
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

                const html = buildConfigurarConcesionariasForm(jsonArray);
                jUtils.showing("modal_content", html);
                $("#modal_generic").modal("show");
            }
        });
    };

    const updateConfigHandler = (evt) => {

        console.log("Actualizando Concesionaria %o", evt.target.id);
        evt.preventDefault();

        $.ajax({
            url: Globals.CONFIG_CONCESIONARIA,
            type: "post",
            data: $("#update_config_form").serializeArray(),
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                jUtils.showing("update_config_label", hr);
            },
            success: function(json) {
                console.log("AJAX RESULT SUCCESS %o", json.toString());
                jUtils.showing("update_config_label", "Configurada exitosamente");
            }
        });
    };

    const testConfigHandler = (evt) => {
        console.log("Testing Config %o", evt.target.id);
        evt.preventDefault();

        const configTecno = $("#update_config_select").children("option:selected").val();

        let data;

        if (configTecno === "REST") {
            data = "tecno=REST&";
            data += "url=" + $("#url").val();
        } else if (configTecno === "AXIS") {
            data = "tecno=AXIS&";
            data += "endpointUrl=" + $("#endpointUrl").val() + "&";
            data += "targetNameSpace=" + $("#targetNameSpace").val();
        } else if (configTecno === "CXF") {
            data = "tecno=CXF&";
            data += "wsdlUrl=" + $("#wsdlUrl").val();
        }

        $.ajax({
            url: Globals.TEST_CONFIG,
            type: "post",
            data: data,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                jUtils.showing("test_config_label", hr);
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                jUtils.showing("test_config_label", html);
                $("#modal_generic").modal("show");
            }
        });

        const data2 = $("#update_config_form").serializeArray();
        console.log(data2)

    };

    const changeUpdateConfigHandler = (evt) => {
        console.log("Changing Config %o", evt.target.id);
        console.log("EVT ID = %o", evt.target.id);
        evt.preventDefault();

        const innerHtml = $("#inner_update_config_form_div").html;
        console.log(innerHtml);

    };

    const testConsumo = (evt) => {
        console.log("Test consumo %o", evt.target.id);
        evt.preventDefault();

    };

    $("#tableConcesionarias").delegate(".aprobar_btn", "click", aprobarHandler);
    $("#tableConcesionarias").delegate(".desaprobar_btn", "click", desAprobarHandler);
    $("#tableConcesionarias").delegate(".config_btn", "click", configurarHandler);
    $("#modal_content").delegate("#update_config_btn", "click", updateConfigHandler);
    $("#modal_content").delegate("#test_config_btn", "click", testConfigHandler);
    $("#modal_content").delegate("#update_config_select", "change", changeUpdateConfigHandler);
    $("#test_consumo_div").delegate("#test_consumo_div", "click", testConsumo);

});