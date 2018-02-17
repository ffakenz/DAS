
package ar.ubp.edu.das.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.ubp.edu.das.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CancelarPlan_QNAME = new QName("http://ws.das.edu.ubp.ar/", "cancelarPlan");
    private final static QName _CancelarPlanResponse_QNAME = new QName("http://ws.das.edu.ubp.ar/", "cancelarPlanResponse");
    private final static QName _ConsultarPlan_QNAME = new QName("http://ws.das.edu.ubp.ar/", "consultarPlan");
    private final static QName _ConsultarPlanResponse_QNAME = new QName("http://ws.das.edu.ubp.ar/", "consultarPlanResponse");
    private final static QName _ConsultarPlanes_QNAME = new QName("http://ws.das.edu.ubp.ar/", "consultarPlanes");
    private final static QName _ConsultarPlanesResponse_QNAME = new QName("http://ws.das.edu.ubp.ar/", "consultarPlanesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.ubp.edu.das.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelarPlan }
     * 
     */
    public CancelarPlan createCancelarPlan() {
        return new CancelarPlan();
    }

    /**
     * Create an instance of {@link CancelarPlanResponse }
     * 
     */
    public CancelarPlanResponse createCancelarPlanResponse() {
        return new CancelarPlanResponse();
    }

    /**
     * Create an instance of {@link ConsultarPlan }
     * 
     */
    public ConsultarPlan createConsultarPlan() {
        return new ConsultarPlan();
    }

    /**
     * Create an instance of {@link ConsultarPlanResponse }
     * 
     */
    public ConsultarPlanResponse createConsultarPlanResponse() {
        return new ConsultarPlanResponse();
    }

    /**
     * Create an instance of {@link ConsultarPlanes }
     * 
     */
    public ConsultarPlanes createConsultarPlanes() {
        return new ConsultarPlanes();
    }

    /**
     * Create an instance of {@link ConsultarPlanesResponse }
     * 
     */
    public ConsultarPlanesResponse createConsultarPlanesResponse() {
        return new ConsultarPlanesResponse();
    }

    /**
     * Create an instance of {@link PlanBean }
     * 
     */
    public PlanBean createPlanBean() {
        return new PlanBean();
    }

    /**
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.edu.ubp.ar/", name = "cancelarPlan")
    public JAXBElement<CancelarPlan> createCancelarPlan(CancelarPlan value) {
        return new JAXBElement<CancelarPlan>(_CancelarPlan_QNAME, CancelarPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarPlanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.edu.ubp.ar/", name = "cancelarPlanResponse")
    public JAXBElement<CancelarPlanResponse> createCancelarPlanResponse(CancelarPlanResponse value) {
        return new JAXBElement<CancelarPlanResponse>(_CancelarPlanResponse_QNAME, CancelarPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.edu.ubp.ar/", name = "consultarPlan")
    public JAXBElement<ConsultarPlan> createConsultarPlan(ConsultarPlan value) {
        return new JAXBElement<ConsultarPlan>(_ConsultarPlan_QNAME, ConsultarPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPlanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.edu.ubp.ar/", name = "consultarPlanResponse")
    public JAXBElement<ConsultarPlanResponse> createConsultarPlanResponse(ConsultarPlanResponse value) {
        return new JAXBElement<ConsultarPlanResponse>(_ConsultarPlanResponse_QNAME, ConsultarPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPlanes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.edu.ubp.ar/", name = "consultarPlanes")
    public JAXBElement<ConsultarPlanes> createConsultarPlanes(ConsultarPlanes value) {
        return new JAXBElement<ConsultarPlanes>(_ConsultarPlanes_QNAME, ConsultarPlanes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPlanesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.edu.ubp.ar/", name = "consultarPlanesResponse")
    public JAXBElement<ConsultarPlanesResponse> createConsultarPlanesResponse(ConsultarPlanesResponse value) {
        return new JAXBElement<ConsultarPlanesResponse>(_ConsultarPlanesResponse_QNAME, ConsultarPlanesResponse.class, null, value);
    }

}
