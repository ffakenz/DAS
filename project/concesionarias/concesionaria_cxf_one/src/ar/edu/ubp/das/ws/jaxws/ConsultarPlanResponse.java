
package ar.edu.ubp.das.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.6
 * Fri Feb 16 12:41:57 ART 2018
 * Generated source version: 3.1.6
 */

@XmlRootElement(name = "consultarPlanResponse", namespace = "http://ws.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarPlanResponse", namespace = "http://ws.das.ubp.edu.ar/")

public class ConsultarPlanResponse {

    @XmlElement(name = "return")
    private beans.PlanBean _return;

    public beans.PlanBean getReturn() {
        return this._return;
    }

    public void setReturn(beans.PlanBean new_return)  {
        this._return = new_return;
    }

}
