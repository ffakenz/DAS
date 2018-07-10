package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ConfigParamForm extends DynaActionForm {
    private Long concesionariaId;
    private String configTecno;
    private String configParam;
    private String value;


    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public String getConfigTecno() {
        return configTecno;
    }

    public void setConfigTecno(final String configTecno) {
        this.configTecno = configTecno;
    }

    public String getConfigParam() {
        return configParam;
    }

    public void setConfigParam(final String configParam) {
        this.configParam = configParam;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConfigParamForm{" +
                "concesionariaId=" + concesionariaId +
                ", configTecno='" + configTecno + '\'' +
                ", configParam='" + configParam + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
