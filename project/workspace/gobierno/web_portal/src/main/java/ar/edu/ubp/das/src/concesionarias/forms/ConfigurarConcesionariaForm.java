package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

@Entity
public class ConfigurarConcesionariaForm extends DynaActionForm {

    @Column(name = "concesionaria_id")
    Long concesionariaId;
    @Column(name = "config_tecno")
    String configTecno;
    @Column(name = "config_param")
    String configParam;
    @Column(name = "value")
    String value;

    public ConfigurarConcesionariaForm() {}

    public ConfigurarConcesionariaForm(String configTecno, String configParam, String value) {
        this.configTecno = configTecno;
        this.configParam = configParam;
        this.value = value;
    }

    public ConfigurarConcesionariaForm(Long concesionariaId, String configTecno, String configParam, String value) {
        this.concesionariaId = concesionariaId;
        this.configTecno = configTecno;
        this.configParam = configParam;
        this.value = value;
    }

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
        return "ConfigurarConcesionariaForm{" +
                "concesionariaId=" + concesionariaId +
                ", configTecno='" + configTecno + '\'' +
                ", configParam='" + configParam + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
