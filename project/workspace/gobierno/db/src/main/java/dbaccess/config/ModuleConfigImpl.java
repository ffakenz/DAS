package dbaccess.config;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
import java.util.HashMap;
import java.util.Map;

public final class ModuleConfigImpl {

	private static ModuleConfigImpl       instance;
	private Map<String, DatasourceConfig> datasources;
	
	private ModuleConfigImpl() {
		this.datasources = new HashMap<String, DatasourceConfig>();
	}

	public static void load(ClassLoader classLoader) throws RuntimeException {
		if(ModuleConfigImpl.instance == null) {
			ModuleConfigImpl.instance = new ModuleConfigImpl();
			ModuleConfigImpl.instance.loadDatasources(classLoader);
		}
	}
	
	private void loadDatasources(ClassLoader classLoader) throws RuntimeException {
		try {
			String schemaFileName = "./dao/schema/datasources.xsd";
			InputStream schemaInputStream = classLoader.getResourceAsStream(schemaFileName);

			String      filename = "./dao/datasources.xml";
			InputStream input    = classLoader.getResourceAsStream(filename);
			if(input == null) {
				throw new RuntimeException("El archivo " + filename + " no existe");
			}



			SchemaFactory          schema   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(schemaInputStream)}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(input);
			
			NodeList datasources = NodeList.class.cast(xPath.compile("/datasources/datasource").evaluate(document, XPathConstants.NODESET));
			for (int i = 0, len = datasources.getLength(); i < len; i++) {
			    DatasourceConfig datasourceConfig = new DatasourceConfig();
			                     datasourceConfig.setId(String.valueOf(xPath.compile("@id").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setDriver(String.valueOf(xPath.compile("@driver").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setUrl(String.valueOf(xPath.compile("@url").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setUsername(String.valueOf(xPath.compile("@username").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setPassword(String.valueOf(xPath.compile("@password").evaluate(datasources.item(i), XPathConstants.STRING)));
                this.datasources.put(datasourceConfig.getId(), datasourceConfig);		
			}
		} 
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
		    throw new RuntimeException(ex); 
		}	
	}


	private DatasourceConfig getDatasource(String id) {
		if(this.datasources.containsKey(id)) {
			return this.datasources.get(id);
		}
		return null;
	}

	public static DatasourceConfig getDatasourceById(String id) {
		return ModuleConfigImpl.instance.getDatasource(id);
	}

}
