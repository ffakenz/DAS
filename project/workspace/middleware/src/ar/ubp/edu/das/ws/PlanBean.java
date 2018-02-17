
package ar.ubp.edu.das.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for planBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="planBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="concesionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="concesionariaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cuotasPagadas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="fechaAlta" type="{http://ws.das.edu.ubp.ar/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="fechaUltimoUpdate" type="{http://ws.das.edu.ubp.ar/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="vehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planBean", propOrder = {
    "clientId",
    "concesionaria",
    "concesionariaId",
    "cuotasPagadas",
    "documento",
    "fechaAlta",
    "fechaUltimoUpdate",
    "id",
    "vehiculo"
})
public class PlanBean {

    protected String clientId;
    protected String concesionaria;
    protected Integer concesionariaId;
    protected Integer cuotasPagadas;
    protected Long documento;
    protected Timestamp fechaAlta;
    protected Timestamp fechaUltimoUpdate;
    protected Integer id;
    protected String vehiculo;

    /**
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the concesionaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcesionaria() {
        return concesionaria;
    }

    /**
     * Sets the value of the concesionaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcesionaria(String value) {
        this.concesionaria = value;
    }

    /**
     * Gets the value of the concesionariaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConcesionariaId() {
        return concesionariaId;
    }

    /**
     * Sets the value of the concesionariaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConcesionariaId(Integer value) {
        this.concesionariaId = value;
    }

    /**
     * Gets the value of the cuotasPagadas property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCuotasPagadas() {
        return cuotasPagadas;
    }

    /**
     * Sets the value of the cuotasPagadas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCuotasPagadas(Integer value) {
        this.cuotasPagadas = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDocumento(Long value) {
        this.documento = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setFechaAlta(Timestamp value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the fechaUltimoUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getFechaUltimoUpdate() {
        return fechaUltimoUpdate;
    }

    /**
     * Sets the value of the fechaUltimoUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setFechaUltimoUpdate(Timestamp value) {
        this.fechaUltimoUpdate = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the vehiculo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehiculo() {
        return vehiculo;
    }

    /**
     * Sets the value of the vehiculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehiculo(String value) {
        this.vehiculo = value;
    }

}
