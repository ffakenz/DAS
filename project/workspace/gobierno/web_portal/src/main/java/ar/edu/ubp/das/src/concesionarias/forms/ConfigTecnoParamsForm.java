package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.util.StringJoiner;

public class ConfigTecnoParamsForm extends DynaActionForm {

    String configTecno;
    String configParam;
    String value;

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
        return new StringJoiner(", ", ConfigTecnoParamsForm.class.getSimpleName() + "[", "]")
                .add("configTecno='" + configTecno + "'")
                .add("configParam='" + configParam + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
