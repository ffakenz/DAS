'use strict';

const Action = {

    HOME_ENDPOINT : "/web_portal/home/Home.do",
    SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/home/ShowRegistrarConcesionaria.do",
    SHOW_LOGIN_ENDPOINT : "/web_portal/home/ShowLogin.do",

    LOGIN_ENDPOINT : "/web_portal/login/Login.do",
    LOGOUT_ENDPOINT : "/web_portal/login/Logout.do",
    LOGIN_PRIMER_INGRESO : "/web_portal/usuarios/RegistrarConsumer.do",
    SHOW_LOGIN_PRIMER_INGRESO : "/web_portal/usuarios/ShowPrimerIngreso.do",

    REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/RegistrarConcesionaria.do",
    APROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/AprobarConcesionaria.do",
    DESAPROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/DesAprobarConcesionaria.do",
    CONCESIONARIA_CONSULTAR_CONFIG_PARAM :  "/web_portal/concesionarias/ConsultarConcesionariaConfigParam.do",
    CONCESIONARIA_CONSULTAR_TODAS :  "/web_portal/concesionarias/ConsultarConcesionarias.do",
    TEST_CONFIG :  "/web_portal/concesionarias/TestConfig.do",
    CONFIG_CONCESIONARIA : "/web_portal/concesionarias/Configurar.do",

    CHANGE_LANGUAGE_ENDPOINT : "/web_portal/other/ChangeLanguage.do",

    CONSUMO_TEST :  "/web_portal/consumo/Consumo.do"
};

const ConfigTecno = {
    REST: "REST",
    AXIS: "AXIS",
    CXF: "CXF"
};

const Id = {
    UPDATE_CONFIG_FORM: "update_config_form",
    INNER_UPDATE_CONFIG_FORM_DIV: "inner_update_config_form_div",
    UPDATE_CONFIG_SELECT: "update_config_select",
    UPDATE_CONFIG_LABEL: "update_config_label",
    TEST_CONFIG_LABEL: "test_config_label"
};

const Class = {
    UPDATE_CONFIG_PARAM: "config_param",
    BTN_UPDATE_CONFIG: "update_config_btn",
    BTN_TEST_CONFIG: "test_config_btn"
};