
package ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.2
 * Mon Feb 18 23:01:34 ART 2019
 * Generated source version: 3.2.2
 */

@XmlRootElement(name = "cancelarPlan", namespace = "http://ws.das.edu.ubp.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelarPlan", namespace = "http://ws.das.edu.ubp.ar/", propOrder = {"identificador", "planId"})

public class CancelarPlan {

    @XmlElement(name = "identificador")
    private java.lang.String identificador;
    @XmlElement(name = "planId")
    private java.lang.Long planId;

    public java.lang.String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(java.lang.String newIdentificador)  {
        this.identificador = newIdentificador;
    }

    public java.lang.Long getPlanId() {
        return this.planId;
    }

    public void setPlanId(java.lang.Long newPlanId)  {
        this.planId = newPlanId;
    }

}

