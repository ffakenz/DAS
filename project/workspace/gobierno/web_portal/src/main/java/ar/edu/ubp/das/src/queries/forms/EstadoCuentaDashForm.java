package ar.edu.ubp.das.src.queries.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import utils.JsonUtils;

import java.sql.Timestamp;

@Entity
public class EstadoCuentaDashForm extends DynaActionForm {

    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "documento_cliente")
    private Long documentoCliente;
    @Column(name = "estado_plan")
    private String estadoPlan;
    @Column(name = "nombre_concesionaria")
    private String nombreConcesionaria;
    @Column(name = "nro_plan_concesionaria")
    private Long nroPlanConcesionaria;
    @Column(name = "vehiculo_nombre")
    private String vehiculoNombre;
    @Column(name = "email_cliente")
    private String emailCliente;
    @Column(name = "concesionaria_email")
    private String concesionariaEmail;
    @Column(name = "fecha_ultima_actualizacion_plan")
    private Timestamp fechaUltimaActualizacionPlan;
    @Column(name = "fecha_ultima_actualizacion_cuota")
    private Timestamp fechaUltimaActualizacionCuota;
    @Column(name = "total_cuotas_pagas")
    private Integer totalCuotasPagas;
    @Column(name = "total_cuotas_por_pagar")
    private Integer totalCuotasPorPagar;
    @Column(name = "minima_cuota_paga")
    private Double minimaCuotaPaga;
    @Column(name = "max_cuota_paga")
    private Double maxCuotaPaga;
    @Column(name = "avg_cuota_paga")
    private Double avgCuotaPaga;
    @Column(name = "total_monto_pagado")
    private Double totalMontoPagado;
    @Column(name = "total_monto_por_pagar")
    private Double totalMontoPorPagar;
    @Column(name = "vehiculo_precio")
    private Double vehiculoPrecio;


    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(final String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(final Long documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getEstadoPlan() {
        return estadoPlan;
    }

    public void setEstadoPlan(final String estadoPlan) {
        this.estadoPlan = estadoPlan;
    }

    public String getNombreConcesionaria() {
        return nombreConcesionaria;
    }

    public void setNombreConcesionaria(final String nombreConcesionaria) {
        this.nombreConcesionaria = nombreConcesionaria;
    }

    public Long getNroPlanConcesionaria() {
        return nroPlanConcesionaria;
    }

    public void setNroPlanConcesionaria(final Long nroPlanConcesionaria) {
        this.nroPlanConcesionaria = nroPlanConcesionaria;
    }

    public String getVehiculoNombre() {
        return vehiculoNombre;
    }

    public void setVehiculoNombre(final String vehiculoNombre) {
        this.vehiculoNombre = vehiculoNombre;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(final String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getConcesionariaEmail() {
        return concesionariaEmail;
    }

    public void setConcesionariaEmail(final String concesionariaEmail) {
        this.concesionariaEmail = concesionariaEmail;
    }

    public Timestamp getFechaUltimaActualizacionPlan() {
        return fechaUltimaActualizacionPlan;
    }

    public void setFechaUltimaActualizacionPlan(final Timestamp fechaUltimaActualizacionPlan) {
        this.fechaUltimaActualizacionPlan = fechaUltimaActualizacionPlan;
    }

    public Timestamp getFechaUltimaActualizacionCuota() {
        return fechaUltimaActualizacionCuota;
    }

    public void setFechaUltimaActualizacionCuota(final Timestamp fechaUltimaActualizacionCuota) {
        this.fechaUltimaActualizacionCuota = fechaUltimaActualizacionCuota;
    }

    public Integer getTotalCuotasPagas() {
        return totalCuotasPagas;
    }

    public void setTotalCuotasPagas(final Integer totalCuotasPagas) {
        this.totalCuotasPagas = totalCuotasPagas;
    }

    public Integer getTotalCuotasPorPagar() {
        return totalCuotasPorPagar;
    }

    public void setTotalCuotasPorPagar(final Integer totalCuotasPorPagar) {
        this.totalCuotasPorPagar = totalCuotasPorPagar;
    }

    public Double getMinimaCuotaPaga() {
        return minimaCuotaPaga;
    }

    public void setMinimaCuotaPaga(final Double minimaCuotaPaga) {
        this.minimaCuotaPaga = minimaCuotaPaga;
    }

    public Double getMaxCuotaPaga() {
        return maxCuotaPaga;
    }

    public void setMaxCuotaPaga(final Double maxCuotaPaga) {
        this.maxCuotaPaga = maxCuotaPaga;
    }

    public Double getAvgCuotaPaga() {
        return avgCuotaPaga;
    }

    public void setAvgCuotaPaga(final Double avgCuotaPaga) {
        this.avgCuotaPaga = avgCuotaPaga;
    }

    public Double getTotalMontoPagado() {
        return totalMontoPagado;
    }

    public void setTotalMontoPagado(final Double totalMontoPagado) {
        this.totalMontoPagado = totalMontoPagado;
    }

    public Double getTotalMontoPorPagar() {
        return totalMontoPorPagar;
    }

    public void setTotalMontoPorPagar(final Double totalMontoPorPagar) {
        this.totalMontoPorPagar = totalMontoPorPagar;
    }

    public Double getVehiculoPrecio() {
        return vehiculoPrecio;
    }

    public void setVehiculoPrecio(final Double vehiculoPrecio) {
        this.vehiculoPrecio = vehiculoPrecio;
    }
}
