
package ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.2
 * Sun Feb 17 19:56:11 ART 2019
 * Generated source version: 3.2.2
 */

@XmlRootElement(name = "consultarPlanes", namespace = "http://ws.das.edu.ubp.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarPlanes", namespace = "http://ws.das.edu.ubp.ar/", propOrder = {"identificador", "from", "to"})

public class ConsultarPlanes {

    @XmlElement(name = "identificador")
    private java.lang.String identificador;
    @XmlElement(name = "from")
    private java.lang.String from;
    @XmlElement(name = "to")
    private java.lang.String to;

    public java.lang.String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(java.lang.String newIdentificador)  {
        this.identificador = newIdentificador;
    }

    public java.lang.String getFrom() {
        return this.from;
    }

    public void setFrom(java.lang.String newFrom)  {
        this.from = newFrom;
    }

    public java.lang.String getTo() {
        return this.to;
    }

    public void setTo(java.lang.String newTo)  {
        this.to = newTo;
    }

}

