package ar.edu.ubp.das.src.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

public final class Constants {
    //************ DAOS ****************************
    public static final String CONCESIONARIAS_DAO_PACKAGE = "concesionarias";
    public static final String CONCESIONARIAS_DAO_NAME = "Concesionarias";
    public static final String CONFI_CONCESIONARIAS_DAO_NAME = "ConfigurarConcesionaria";

    //************ CONSUMERS ROLES ****************************
    public static final String ROL_GOBIERNO = "gobierno";
    public static final String ROL_CONSUMER = "consumer";

    //************ PARAMS SERVLETS ****************************
    public static final String USER_TYPE = "userType";
    public static final String SSID = "ssid";
    public static final String FORWARD_NAME = "forwardName";

    //************ SERVLET REQUEST ATTRIBUTES ****************************
    public static final String RESULT_RQST_ATTRIBUTE = "result";
    public static final String APROBADA_RQST_ATTRIBUTE = "aprobada";
    public static final String APROBADAS_RQST_ATTRIBUTE = "aprobadas";
    public static final String DESAPROBADA_RQST_ATTRIBUTE = "desaprobada";
    public static final String CONFIG_SUCCEDD_RQST_ATTRIBUTE = "configurationSucceeded";

    //************ CSS {CLASSES && ID} IDENTIFIERS ****************************
    public static final String CONCESIONARIA_ROW = "concesionaria_row";
    public static final String CONCESIONARIA_APROBADA = "concesionaria_aprobada";
    public static final String CONCESIONARIA_NO_APROBADA = "concesionaria_no_aprobada";
    public static final String BTN_APROBAR = "aprobar_btn";
    public static final String BTN_DESAPROBAR = "desaprobar_btn";
    public static final String BTN_CONFIGURAR = "config_btn";
}