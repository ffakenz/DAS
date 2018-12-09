$(() => {

    const Globals = {
        HOME_ENDPOINT : "/web_portal/home/Home.do",
        SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/home/ShowRegistrarConcesionaria.do",
        SHOW_LOGIN_ENDPOINT : "/web_portal/home/ShowLogin.do",
        LOGIN_ENDPOINT : "/web_portal/login/Login.do",
        LOGOUT_ENDPOINT : "/web_portal/login/Logout.do",
        CHANGE_LANGUAGE_ENDPOINT : "/web_portal/other/ChangeLanguage.do",
        REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/RegistrarConcesionaria.do",
        APROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/AprobarConcesionaria.do",
        DESAPROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/DesAprobarConcesionaria.do",
        CONCESIONARIA_CONSULTAR_CONFIG_PARAM :  "/web_portal/concesionarias/ConsultarConcesionariaConfigParam.do"
    };

    $("#tableConcesionarias").DataTable();

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
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr);
                alert("AJAX RESULT ERROR " + hr);
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                $("#showModalConfig").replaceWith(html);
            }
        });
    };

    $("#tableConcesionarias").delegate(".aprobar_btn", "click", aprobarHandler);
    $("#tableConcesionarias").delegate(".desaprobar_btn", "click", desAprobarHandler);
    $("#tableConcesionarias").delegate(".config_btn", "click", configurarHandler);
});