
package ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.2
 * Sun Apr 08 19:59:51 ART 2018
 * Generated source version: 3.2.2
 */

@XmlRootElement(name = "consultarPlan", namespace = "http://ws.das.edu.ubp.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarPlan", namespace = "http://ws.das.edu.ubp.ar/")

public class ConsultarPlan {

    @XmlElement(name = "planId")
    private java.lang.Long planId;

    public java.lang.Long getPlanId() {
        return this.planId;
    }

    public void setPlanId(java.lang.Long newPlanId)  {
        this.planId = newPlanId;
    }

}

