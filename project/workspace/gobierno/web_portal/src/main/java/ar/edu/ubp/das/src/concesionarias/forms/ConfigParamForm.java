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

    public void setConcesionariaId(Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public String getConfigTecno() {
        return configTecno;
    }

    public void setConfigTecno(String configTecno) {
        this.configTecno = configTecno;
    }

    public String getConfigParam() {
        return configParam;
    }

    public void setConfigParam(String configParam) {
        this.configParam = configParam;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
