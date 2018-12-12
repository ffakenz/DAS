"use strict";

$(() => {

    $("#tableConcesionarias").DataTable();

    /* HELPERS */

    const getNewUpdateForm = (configTecno, concesionariaId) => {
        if(configTecno == ConfigTecno.REST) {
            return new ConfigParam(
                ConfigTecno.REST,
                [{name: "url", value: ""}],
                concesionariaId
            ).showForm();
        }
        if(configTecno == ConfigTecno.AXIS) {
            return new ConfigParam(
                ConfigTecno.AXIS,
                [{name: "endpointUrl", value: ""}, {name: "targetNameSpace", value: ""}],
                concesionariaId
            ).showForm();
        }
        if(configTecno == ConfigTecno.CXF) {
            return new ConfigParam(
                ConfigTecno.CXF,
                [{name: "wsdlUrl", value: ""}],
                concesionariaId
            ).showForm();
        }
    };

    /* the jsonArray is the result from calling CONCESIONARIA_CONSULTAR_CONFIG_PARAM Action */
    const formConsultarConfings = (jsonArray, concesionariaId) => {
        if(jsonArray.length == 0) {
            const newState = getNewUpdateForm(ConfigTecno.REST, concesionariaId);
            LAST_CONFIGS_CONSULTED_ST = newState;
            return newState.showForm();
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
            const newState = new ConfigParam(configTecno, configParams, concesionariaId);
            LAST_CONFIGS_CONSULTED_ST = newState;
            return newState.showForm();
        }
    };

    /* AJAX CALLS */
    const changeUpdateConfigHandler = (evt) => {
        evt.preventDefault();

        const currentConfigTecno = LAST_CONFIGS_CONSULTED_ST.configTecno;
        const newConfigTecno = evt.target.value;
        console.log("Changing Config From %o To %o", currentConfigTecno, newConfigTecno);

        if(currentConfigTecno != newConfigTecno) {
            const newHtml = getNewUpdateForm(newConfigTecno, LAST_CONFIGS_CONSULTED_ST.concesionariaId);
            console.log("newHtml = %o", newHtml);
            jUtils.showing("modal_content", newHtml);
        } else {
            const oldHtml = LAST_CONFIGS_CONSULTED_ST.showForm();
            console.log("oldHtml = %o", oldHtml);
            jUtils.showing("modal_content", oldHtml);
        }

    };

    const configurarHandler = (evt) => {
        console.log("Configurando Concesionaria %o", evt.target.id);

        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];

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

                const html = formConsultarConfings(jsonArray, idConcesionaria);
                jUtils.showing("modal_content", html);
                $("#modal_generic").modal("show");
            }
        });
    };

    const aprobarHandler = (evt) => {
        console.log("Aprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];

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
    };

    const desAprobarHandler = (evt) => {
        console.log("DesAprobando Concesionaria %o", evt.target.id);
        const idButton = evt.target.id;
        const idConcesionaria  = idButton.split("-")[1];

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
    };

    const updateConfigHandler = (evt) => {

        console.log("Actualizando Concesionaria %o", evt.target.id);
        evt.preventDefault();
        const idButton = evt.target.id;
        const idConcesionaria = idButton.split("-")[1];
        console.log("Actualizando Concesionaria _ %o", idConcesionaria);

        $.ajax({
            url: Action.CONFIG_CONCESIONARIA,
            type: "post",
            data: $(`#${Id.UPDATE_CONFIG_FORM}`).serializeArray(),
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                jUtils.showing(Id.UPDATE_CONFIG_LABEL, hr);
            },
            success: function(json) {
                console.log("AJAX RESULT SUCCESS %o", json.toString());
                jUtils.showing(Id.UPDATE_CONFIG_LABEL, "Configurada exitosamente");
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

        const data2 = $("#update_config_form").serializeArray();
        console.log(data2)

    };

    const testConsumo = (evt) => {
        console.log("Test consumo %o", evt.target.id);
        evt.preventDefault();

    };

    $("#tableConcesionarias").delegate(".aprobar_btn", "click", aprobarHandler);
    $("#tableConcesionarias").delegate(".desaprobar_btn", "click", desAprobarHandler);
    $("#tableConcesionarias").delegate(".config_btn", "click", configurarHandler);
    $("#modal_content").delegate(`.${Class.BTN_UPDATE_CONFIG}`, "click", updateConfigHandler);
    $("#modal_content").delegate(`.${Class.BTN_TEST_CONFIG}`, "click", testConfigHandler);
    $("#modal_content").delegate(`#${Id.UPDATE_CONFIG_SELECT}`, "change", changeUpdateConfigHandler);
    $("#test_consumo_div").delegate("#test_consumo_div", "click", testConsumo);
});