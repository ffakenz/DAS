package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.util.List;
import java.util.StringJoiner;

@Entity
public class GeneralConfigConcesionariaForm extends DynaActionForm {

    Long concesionariaId;
    List<ConfigTecnoParamsForm> params;

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public List<ConfigTecnoParamsForm> getParams() {
        return params;
    }

    public void setParams(List<ConfigTecnoParamsForm> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GeneralConfigConcesionariaForm.class.getSimpleName() + "[", "]")
                .add("concesionariaId=" + concesionariaId)
                .add("params=" + params)
                .toString();
    }
}
