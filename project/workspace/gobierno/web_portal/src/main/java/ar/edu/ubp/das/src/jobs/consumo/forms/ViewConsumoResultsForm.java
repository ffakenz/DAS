package ar.edu.ubp.das.src.jobs.consumo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class ViewConsumoResultsForm extends DynaActionForm {
    @Column(name = "job_id")
    private Long jobId;
    @Column(name = "job_fecha_ejecucion")
    private Timestamp jobFechaEjecucion;
    @Column(name = "consumo_id")
    private Long consumoId;
    @Column(name = "concesionaria_id")
    private Long concesionariaId;
    @Column(name = "estado_consumo")
    private String estadoConsumo;
    @Column(name = "offset_consumo")
    private Timestamp offsetConsumo;
    @Column(name = "id_request_resp_consumo")
    private String idRequestRespConsumo;
    @Column(name = "estado_description")
    private String estadoDescription;
    @Column(name = "consumo_result_id")
    private Long consumoResultId;
    @Column(name = "consumo_result")
    private String consumoResult;
    @Column(name = "consumo_result_description")
    private String consumoResultDescription;

    @Override
    public String toString() {
        return "ViewConsumoResultsForm{" +
                "jobId=" + jobId +
                ", jobFechaEjecucion=" + jobFechaEjecucion +
                ", consumoId=" + consumoId +
                ", concesionariaId=" + concesionariaId +
                ", estadoConsumo='" + estadoConsumo + '\'' +
                ", offsetConsumo=" + offsetConsumo +
                ", idRequestRespConsumo='" + idRequestRespConsumo + '\'' +
                ", estadoDescription='" + estadoDescription + '\'' +
                ", consumoResultId=" + consumoResultId +
                ", consumoResult='" + consumoResult + '\'' +
                ", consumoResultDescription='" + consumoResultDescription + '\'' +
                '}';
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(final Long jobId) {
        this.jobId = jobId;
    }

    public Timestamp getJobFechaEjecucion() {
        return jobFechaEjecucion;
    }

    public void setJobFechaEjecucion(final Timestamp jobFechaEjecucion) {
        this.jobFechaEjecucion = jobFechaEjecucion;
    }

    public Long getConsumoId() {
        return consumoId;
    }

    public void setConsumoId(final Long consumoId) {
        this.consumoId = consumoId;
    }

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public String getEstadoConsumo() {
        return estadoConsumo;
    }

    public void setEstadoConsumo(final String estadoConsumo) {
        this.estadoConsumo = estadoConsumo;
    }

    public Timestamp getOffsetConsumo() {
        return offsetConsumo;
    }

    public void setOffsetConsumo(final Timestamp offsetConsumo) {
        this.offsetConsumo = offsetConsumo;
    }

    public String getIdRequestRespConsumo() {
        return idRequestRespConsumo;
    }

    public void setIdRequestRespConsumo(final String idRequestRespConsumo) {
        this.idRequestRespConsumo = idRequestRespConsumo;
    }

    public String getEstadoDescription() {
        return estadoDescription;
    }

    public void setEstadoDescription(final String estadoDescription) {
        this.estadoDescription = estadoDescription;
    }

    public Long getConsumoResultId() {
        return consumoResultId;
    }

    public void setConsumoResultId(final Long consumoResultId) {
        this.consumoResultId = consumoResultId;
    }

    public String getConsumoResult() {
        return consumoResult;
    }

    public void setConsumoResult(final String consumoResult) {
        this.consumoResult = consumoResult;
    }

    public String getConsumoResultDescription() {
        return consumoResultDescription;
    }

    public void setConsumoResultDescription(final String consumoResultDescription) {
        this.consumoResultDescription = consumoResultDescription;
    }
}
