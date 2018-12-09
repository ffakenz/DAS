package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

@Entity
public class ConfigTecnoXConcesionariaForm {

    @Column(name = "concesionaria_id")
    Long concesionariaId;
    @Column(name = "config_tecnologica")
    String configTecnologica;

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public String getConfigTecnologica() {
        return configTecnologica;
    }

    public void setConfigTecnologica(final String configTecnologica) {
        this.configTecnologica = configTecnologica;
    }

    @Override
    public String toString() {
        return "ConfigTecnoXConcesionariaForm{" +
                "concesionariaId=" + concesionariaId +
                ", configTecnologica='" + configTecnologica + '\'' +
                '}';
    }
}
