
package ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.2
 * Fri Feb 15 20:05:50 ART 2019
 * Generated source version: 3.2.2
 */

@XmlRootElement(name = "consultarPlanes", namespace = "http://ws.das.edu.ubp.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarPlanes", namespace = "http://ws.das.edu.ubp.ar/", propOrder = {"identificador", "offset"})

public class ConsultarPlanes {

    @XmlElement(name = "identificador")
    private java.lang.String identificador;
    @XmlElement(name = "offset")
    private java.lang.String offset;

    public java.lang.String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(java.lang.String newIdentificador)  {
        this.identificador = newIdentificador;
    }

    public java.lang.String getOffset() {
        return this.offset;
    }

    public void setOffset(java.lang.String newOffset)  {
        this.offset = newOffset;
    }

}

