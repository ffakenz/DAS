package ar.edu.ubp.das.mvc.config;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class AliasConfig {

    private String name;
    private Map<String, FormBeanConfig> formBeans;
    private List<ResourceBundleConfig> resources;
    private Map<String, ActionConfig> actions;

    public AliasConfig(final String name, final ServletContext context) throws RuntimeException {
        this.name = name;
        this.formBeans = new HashMap<>();
        this.resources = new LinkedList<>();
        this.actions = new HashMap<>();

        try {
            final String filename = "/WEB-INF/actions/" + name + "-actions.xml";
            final InputStream input = context.getResourceAsStream(filename);
            if (input == null) {
                throw new RuntimeException("El archivo " + filename + " no existe");
            }

            final SchemaFactory schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            factory.setSchema(schema.newSchema(new Source[]{new StreamSource(context.getResourceAsStream("/WEB-INF/schema/actions.xsd"))}));
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(input);
            final XPath xPath = XPathFactory.newInstance().newXPath();

            final NodeList formBeans = NodeList.class.cast(xPath.compile("/actions/form-beans/form-bean").evaluate(document, XPathConstants.NODESET));
            for (int i = 0, len = formBeans.getLength(); i < len; i++) {
                final FormBeanConfig formBeanConfig = new FormBeanConfig();
                formBeanConfig.setName(String.valueOf(xPath.compile("@name").evaluate(formBeans.item(i), XPathConstants.STRING)));
                formBeanConfig.setType(String.valueOf(xPath.compile("@type").evaluate(formBeans.item(i), XPathConstants.STRING)));
                this.formBeans.put(formBeanConfig.getName(), formBeanConfig);
            }

            final ResourceBundleConfig mvcBundleConfig = new ResourceBundleConfig();
            mvcBundleConfig.setBasename(ModuleConfigImpl.getFwkPackage() + "properties.messages");
            this.resources.add(mvcBundleConfig);

            final NodeList bundles = NodeList.class.cast(xPath.compile("/actions/resources-bundles/bundle").evaluate(document, XPathConstants.NODESET));
            for (int i = 0, len = bundles.getLength(); i < len; i++) {
                final ResourceBundleConfig bundleConfig = new ResourceBundleConfig();
                bundleConfig.setBasename(ModuleConfigImpl.getSrcPackage() + this.name + ".properties." + String.valueOf(xPath.compile("@basename").evaluate(bundles.item(i), XPathConstants.STRING)));
                this.resources.add(bundleConfig);
            }

            final NodeList actions = NodeList.class.cast(xPath.compile("/actions/action-mappings/action").evaluate(document, XPathConstants.NODESET));
            for (int i = 0, len = actions.getLength(); i < len; i++) {
                final ActionConfig actionConfig = new ActionConfig();
                actionConfig.setPath(String.valueOf(xPath.compile("@path").evaluate(actions.item(i), XPathConstants.STRING)));
                actionConfig.setType(String.valueOf(xPath.compile("@type").evaluate(actions.item(i), XPathConstants.STRING)));
                actionConfig.setForm(String.valueOf(xPath.compile("@form").evaluate(actions.item(i), XPathConstants.STRING)));
                actionConfig.setValidate(String.valueOf(xPath.compile("@validate").evaluate(actions.item(i), XPathConstants.STRING)));
                actionConfig.setNoForward(String.valueOf(xPath.compile("@noforward").evaluate(actions.item(i), XPathConstants.STRING)));
                actionConfig.setJson(String.valueOf(xPath.compile("@json").evaluate(actions.item(i), XPathConstants.STRING)));

                final NodeList parameters = NodeList.class.cast(xPath.compile("./parameter").evaluate(actions.item(i), XPathConstants.NODESET));
                for (int j = 0, jlen = parameters.getLength(); j < jlen; j++) {
                    final ParameterConfig paramConfig = new ParameterConfig();
                    paramConfig.setName(String.valueOf(xPath.compile("@name").evaluate(parameters.item(j), XPathConstants.STRING)));
                    paramConfig.setValue(String.valueOf(xPath.compile("@value").evaluate(parameters.item(j), XPathConstants.STRING)));
                    actionConfig.addParameter(paramConfig);
                }

                final NodeList forwards = NodeList.class.cast(xPath.compile("./forward").evaluate(actions.item(i), XPathConstants.NODESET));
                for (int j = 0, jlen = forwards.getLength(); j < jlen; j++) {
                    final ForwardConfig forwardConfig = new ForwardConfig();
                    forwardConfig.setName(String.valueOf(xPath.compile("@name").evaluate(forwards.item(j), XPathConstants.STRING)));
                    forwardConfig.setPath(String.valueOf(xPath.compile("@path").evaluate(forwards.item(j), XPathConstants.STRING)));
                    forwardConfig.setRedirect(String.valueOf(xPath.compile("@redirect").evaluate(forwards.item(j), XPathConstants.STRING)));
                    actionConfig.addForward(forwardConfig);
                }
                this.actions.put(actionConfig.getPath(), actionConfig);
            }
        } catch (final ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException | MissingResourceException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getName() {
        return name;
    }

    public FormBeanConfig getFormBeanByName(final String name) {
        if (this.formBeans.containsKey(name)) {
            return this.formBeans.get(name);
        }
        return null;
    }

    public String getResourceByName(final String name) {
        final Iterator<ResourceBundleConfig> iterator = this.resources.iterator();
        while (iterator.hasNext()) {
            final ResourceBundle bundle = ResourceBundle.getBundle(iterator.next().getBasename());
            if (bundle.containsKey(name)) {
                return bundle.getString(name);
            }
        }
        return name;
    }

    public ActionConfig getActionByPath(final String path) {
        if (this.actions.containsKey(path)) {
            return this.actions.get(path);
        }
        return null;
    }

    @Override
    public String toString() {
        return "AliasConfig [name=" + name + ", formBeans=" + formBeans
                + ", resources=" + resources + ", actions=" + actions + "]";
    }

}
