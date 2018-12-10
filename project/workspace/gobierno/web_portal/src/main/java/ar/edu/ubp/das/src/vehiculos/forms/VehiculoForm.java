package ar.edu.ubp.das.src.vehiculos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class VehiculoForm extends DynaActionForm {
    @Column(name = "id")
    private Long id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "marca")
    private String marca;
    @Column(name = "fecha_de_alta")
    private Timestamp fechaDeAlta;
    @Column(name = "precio")
    private Long precio;
    @Column(name = "color")
    private String color;
    @Column(name = "modelo")
    private String modelo;

    @Override
    public String toString() {
        return "VehiculoForm{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", fechaDeAlta=" + fechaDeAlta +
                ", precio=" + precio +
                ", color='" + color + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(final String marca) {
        this.marca = marca;
    }

    public Timestamp getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(final Timestamp fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(final Long precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(final String modelo) {
        this.modelo = modelo;
    }
}
