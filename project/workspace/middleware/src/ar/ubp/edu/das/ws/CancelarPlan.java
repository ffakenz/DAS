
package ar.ubp.edu.das.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancelarPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelarPlan"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="planGanador" type="{http://ws.das.edu.ubp.ar/}planBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelarPlan", propOrder = {
    "planGanador"
})
public class CancelarPlan {

    protected PlanBean planGanador;

    /**
     * Gets the value of the planGanador property.
     * 
     * @return
     *     possible object is
     *     {@link PlanBean }
     *     
     */
    public PlanBean getPlanGanador() {
        return planGanador;
    }

    /**
     * Sets the value of the planGanador property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanBean }
     *     
     */
    public void setPlanGanador(PlanBean value) {
        this.planGanador = value;
    }

}
