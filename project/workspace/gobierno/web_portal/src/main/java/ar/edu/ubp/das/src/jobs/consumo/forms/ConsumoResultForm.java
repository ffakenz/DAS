package ar.edu.ubp.das.src.jobs.consumo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import utils.JsonUtils;

@Entity
public class ConsumoResultForm extends DynaActionForm {

    @Column(name = "id_concesionaria")
    private Long idConcesionaria;
    @Column(name = "id_consumo")
    private Long idConsumo;
    @Column(name = "nro")
    private Long nro;
    @Column(name = "description")
    private String description;
    @Column(name = "result")
    private String result;

    public Long getIdConcesionaria() {
        return idConcesionaria;
    }

    public void setIdConcesionaria(final Long idConcesionaria) {
        this.idConcesionaria = idConcesionaria;
    }

    public Long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(final Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Long getNro() {
        return nro;
    }

    public void setNro(final Long nro) {
        this.nro = nro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
