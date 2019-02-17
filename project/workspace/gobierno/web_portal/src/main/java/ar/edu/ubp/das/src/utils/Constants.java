package ar.edu.ubp.das.src.utils;

public final class Constants {

    /************ DAOS ****************************/
    public static final String CONCESIONARIAS_DAO_NAME = "Concesionarias";
    public static final String CONFIG_CONCESIONARIAS_DAO_NAME = "ConfigurarConcesionaria";
    public static final String CONFIG_TECNO_PARAM_DAO_NAME = "ConfigTecnoParam";
    public static final String USUARIOS_DAO_NAME = "Usuarios";
    public static final String CONSUMER_DAO_NAME = "Consumer";
    public static final String ESTADO_CUENTAS_DAO_NAME = "EstadoCuentas";
    public static final String SORTEO_DAO_NAME = "Sorteo";
    public static final String CONSUMO_DAO_NAME = "Consumo";
    public static final String VIEW_CONSUMO_RESULTS__DAO_NAME = "ViewConsumoResults";

    /************ DAOS PACKAGES**********************************************/
    public static final String CONCESIONARIAS_DAO_PACKAGE = "concesionarias";
    public static final String USUARIOS_DAO_PACKAGE = "usuarios";
    public static final String CONSUMERS_DAO_PACKAGE = "consumers";
    public static final String ESTAD_CUENTAS_DAO_PACKAGE = "estado_cuentas";
    public static final String SORTEO_DAO_PACKAGE = "jobs.sorteo";
    public static final String CONSUMO_DAO_PACKAGE = "jobs.consumo";

    /************ CONSUMERS ROLES ****************************/
    public static final String ROL_GOBIERNO = "gobierno";
    public static final String ROL_CONSUMER = "consumer";

    /************ IDENTIFICADOR ****************************/
    public static final String IDENTIFICADOR = "GOB";

    /************ PARAMS SERVLETS ****************************/
    public static final String USER_TYPE = "userType";
    public static final String SSID = "ssid";
    public static final String FORWARD_NAME = "forwardName";

    /************ SERVLET REQUEST ATTRIBUTES ****************************/
    public static final String RESULT_RQST_ATTRIBUTE = "result";
    public static final String APROBADA_RQST_ATTRIBUTE = "aprobada";
    public static final String APROBADAS_RQST_ATTRIBUTE = "aprobadas";
    public static final String DESAPROBADA_RQST_ATTRIBUTE = "desaprobada";
    public static final String CONFIG_SUCCEDD_RQST_ATTRIBUTE = "configurationSucceeded";
    public static final String CONFIG_PARAMS_LIST_RQST_ATTRIBUTE = "configParams";
    public static final String CONCESIONARIAS_LIST_RQST_ATTRIBUTE = "concesionariasList";
    public static final String ESTADO_CUENTAS_LIST_RQST_ATTRIBUTE = "estadoCuentasList";
    public static final String SORTEOS_LIST_RQST_ATTRIBUTE = "sorteosList";
    public static final String JOB_RESULTS_REPORT_RQST_ATTRIBUTE = "sorteosList";

    /************ CSS {CLASSES && ID} IDENTIFIERS ****************************/
    public static final String CONCESIONARIA_ROW = "concesionaria_row";
    public static final String ESTADO_CUENTA_ROW = "estado_cuenta_row";
    public static final String SORTEO_ROW = "sorteo_row";
    public static final String VIEW_CONSUMO_RESULTS_ROW = "view_consumo_results_row";
    public static final String CONCESIONARIA_APROBADA = "concesionaria_aprobada";
    public static final String CONCESIONARIA_NO_APROBADA = "concesionaria_no_aprobada";
    public static final String APROBAR_BTN = "aprobar_btn";
    public static final String DESAPROBAR_BTN = "desaprobar_btn";
    public static final String CONFIG_BTN = "config_btn";

    /************ SORTEO ****************************/
    public static Integer CUOTAS_MIN = 0;
    public static Integer CUOTAS_MAX = 2;

    /************ CONSUMO ****************************/
    public static Integer FROM_DAYS = 15;
    public static Integer TO_DAYS = 0;
}