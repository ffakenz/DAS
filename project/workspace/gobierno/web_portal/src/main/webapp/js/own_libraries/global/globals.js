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
    CONCESIONARIA_CONSULTAR_APROBADAS :  "/web_portal/concesionarias/ConsultarAprobadas.do",
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
    TEST_CONFIG_LABEL: "test_config_label",
    CONCESIONARIA_REGISTRAR_SEND_FORM_BTN: "concesionaria_registrar_send_form_btn",
    TEST_CONSUMO_BTN: "test_consumo_btn",
    TEST_CONSUMO_DIV: "test_consumo_div",
    CONCESIONARIAS_TABLE: "concesionarias_table",
    MODAL_CONTENT: "modal_content",
    REGISTRAR_CONCESIONARIA_DIV: "registrar_concesionaria_div",
    HEADER_HOME: "header_home",
    SHOW_LOGIN_BTN: "show_login_btn",
    GO_TO_PROFILE_BTN: "go_to_profile_btn",
    REGISTRAR_CONCESIONARIA_BTN: "registrar_concesionaria_btn",
    GO_TO_HOME_DIV: "go_to_home_div",
    GO_TO_HOME_BTN: "go_to_home_btn",
    LOGOUT_BTN: "logout_btn",
    LOGIN_FORM: "login_form",
    LOGIN_BTN: "login_btn",
    FIRST_LOGIN_DIV: "first_login_div",
    FIRST_LOGIN_BTN: "first_login_btn",
    LOGIN_PRIMER_INGRESO_FORM: "login_primer_ingreso_form",
    LOGIN_REGISTRAR_BTN: "login_registrar_btn"
};

const Class = {
    UPDATE_CONFIG_PARAM: "update_config_param",
    UPDATE_CONFIG_BTN: "update_config_btn",
    TEST_CONFIG_BTN: "test_config_btn",
    APROBAR_BTN: "aprobar_btn",
    DESAPROBAR_BTN: "desaprobar_btn",
    CONFIG_BTN: "config_btn",
    LOGINBOX: "loginbox"
};