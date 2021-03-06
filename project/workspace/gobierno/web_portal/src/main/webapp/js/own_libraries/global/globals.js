'use strict';

const Action = {
    HOME_ENDPOINT : "/web_portal/home/Home.do",
    SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/home/ShowRegistrarConcesionaria.do",
    /* LOGIN */
    SHOW_LOGIN_ENDPOINT : "/web_portal/home/ShowLogin.do",
    LOGIN_ENDPOINT : "/web_portal/login/Login.do",
    LOGOUT_ENDPOINT : "/web_portal/login/Logout.do",
    LOGIN_PRIMER_INGRESO : "/web_portal/usuarios/RegistrarConsumer.do",
    SHOW_LOGIN_PRIMER_INGRESO : "/web_portal/usuarios/ShowPrimerIngreso.do",
    /* CONCESIONARIA */
    REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/RegistrarConcesionaria.do",
    APROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/AprobarConcesionaria.do",
    DESAPROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/DesAprobarConcesionaria.do",
    CONCESIONARIA_CONSULTAR_CONFIG_PARAM :  "/web_portal/concesionarias/ConsultarConcesionariaConfigParam.do",
    CONCESIONARIA_CONSULTAR_TODAS :  "/web_portal/concesionarias/ConsultarConcesionarias.do",
    CONCESIONARIA_CONSULTAR_APROBADAS :  "/web_portal/concesionarias/ConsultarAprobadas.do",
    ESTADO_CUENTA_CONSULTAR_TODOS :  "/web_portal/estado_cuentas/ConsultarEstadoCuentas.do",
    TEST_CONFIG :  "/web_portal/concesionarias/TestConfig.do",
    CONFIG_CONCESIONARIA : "/web_portal/concesionarias/Configurar.do",
    /* LANGUAGE */
    CHANGE_LANGUAGE_ENDPOINT : "/web_portal/other/ChangeLanguage.do",
    /* SORTEO  */
    GET_SORTEOS :  "/web_portal/sorteo/ConsultarSorteos.do",
    CREAR_SORTEO :  "/web_portal/sorteo/CrearSorteo.do",
    ACTUALIZAR_SORTEO :  "/web_portal/sorteo/ActualizarSorteo.do",
    GET_CALENDARIO: "/web_portal/sorteo/CalendarioAdmin.do",
    /* REPORTS */
    GET_JOB_RESULTS_REPORT: "/web_portal/jobs/ViewConsumoJobResults.do",
    /* CALENDAR */
    GET_CALENDAR_CELL_MODAL: "/web_portal/sorteo/CalendarCellModal.do",
    /* QUERIES */
    ESTADO_CUENTA_DASH :  "/web_portal/queries/EstadoCuentaDash.do",
    UPDATE_ESTADO_CUENTA :  "/web_portal/queries/UpdateEstadoCuentaDash.do"
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
    MODAL_CONTENT: "modal_content",
    REGISTRAR_CONCESIONARIA_DIV: "registrar_concesionaria_div",
    HEADER_HOME: "header_home",
    SHOW_LOGIN_BTN: "show_login_btn",
    GO_TO_PROFILE_BTN: "go_to_profile_btn",
    REGISTRAR_CONCESIONARIA_BTN: "registrar_concesionaria_btn",
    GO_TO_HOME_DIV: "go_to_home_div",
    GO_TO_HOME_BTN: "go_to_home_btn",
    LOGOUT_BTN: "logout_btn",
    LOGOUT_DIV: "logout_div",
    LOGIN_FORM: "login_form",
    LOGIN_BTN: "login_btn",
    FIRST_LOGIN_DIV: "first_login_div",
    FIRST_LOGIN_BTN: "first_login_btn",
    LOGIN_PRIMER_INGRESO_FORM: "login_primer_ingreso_form",
    CALENDARIO: "calendario",
    SHOW_CONFIG_CONCESIONARIAS_BTN: "show_config_concesionarias_btn",
    SHOW_SORTEOS_BTN: "show_sorteos_btn",
    SHOW_PLANES_BTN: "show_planes_btn",
    ADMIN_SIDE_BAR: "admin_side_bar",
    SHOW_JOB_RESULTS_REPORT_BTN: "show_job_results_report_btn",
    CONTENT_ADMIN_PAGE_DIV: "content_admin_page_div",
    CALENDAR_MAIN_DIV: "calendar_main_div",
    CALENDAR_NEXT: "calendar_next",
    CALENDAR_PREV: "calendar_prev",
    CALENDAR_TABLE: "calendar_table",
    CREATE_CELL_MODAL_FOOTER: "update_cell_modal_footer",
    UPDATE_CELL_MODAL_FOOTER: "create_cell_modal_footer",
    CREATE_SORTEO_BTN: "create_sorteo_btn",
    UPDATE_SORTEO_BTN: "update_sorteo_btn",
    LANG_DIV: "lang_div",
    LANG_SELECT: "lang_select",
    TABLE_ADMIN_RESULT: "table_admin_result"
};

const Class = {
    UPDATE_CONFIG_PARAM: "update_config_param",
    UPDATE_CONFIG_BTN: "update_config_btn",
    TEST_CONFIG_BTN: "test_config_btn",
    APROBAR_BTN: "aprobar_btn",
    DESAPROBAR_BTN: "desaprobar_btn",
    CONFIG_BTN: "config_btn",
    LOGINBOX: "loginbox",
    ACTUALIZAR_FECHA_SORTEO_BTN: "actualizar_fecha_sorteo_btn",
    EMPTY_CELL: "empty_cell",
    SORTEO_CELL: "sorteo_cell",
    CALENDAR_CELL: "calendar_cell",
    LANG_BTN: "lang_btn",
    ESTADO_CUENTA_UPDATE_BTN: "estado_cuenta_update_btn"
};