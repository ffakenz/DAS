# Agregar un Action que no redirija

###### Qué es un `Action`
Supongamos que queremos conseguir texto css. Un archivo JSP quiere llamar a el backend para conseguirlo. Y quiere enviar parámetros.

En el JSP llamaremos al backend, y `Action` va a representar a la lógica del backend que queremos ejecutar.

###### Ejemplo
Supongamos que queremos conseguir el css de múltiples archivos.
Debería ser tan fácil como escribir en el JSP:

```javascript
<link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=page,messages" />
```

> (en éste caso la url necesita el prefijo /webportal/ porque no hemos modificado aún a WebContent/urlrewrite.xml)






Con éso dicho, podemos proceder a explicar los pasos necesarios para agregar un `Action`:

Agregar archivo /src/main/java/ar.edu.etc.actions/StyleSheet.java

``` java
package ar.edu.ubp.das.src.util.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;

public class StyleSheetAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/css;charset=ISO-8859-1");
        try {
			PrintWriter out = response.getWriter();
	        try {
	        	if(request.getParameter("load") != null) {
	        		String root      = ModuleConfigImpl.getContext().getRealPath("/css/") + "/";
	        		String scripts[] = request.getParameter("load").split(",");
		        	for(String script : scripts) {
		        		try {
		        			List<String>  file  = Files.readAllLines(Paths.get(root + script + ".css"), Charset.defaultCharset());
			        		StringBuilder lines = new StringBuilder();
			        		for(String line : file) {
			        			lines.append(line);
			        		}
			        		out.println(lines);
		        		}
		        		catch(NoSuchFileException ex) { }
		        	}
	        	}
			}
	        finally {
	            out.close();
	        }
		}
		catch (IOException e) { }
		return null;
	}

}

```


Agregar entrada a ```xml <action-mappings>```
```xml

<?xml version="1.0" encoding="UTF-8"?>
<actions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	     xsi:noNamespaceSchemaLocation="../schema/actions.xsd">
    <form-beans>
    </form-beans>
    <action-mappings>
        <action path="/StyleSheet.do" type="StyleSheetAction" validate="false" noforward="true" />
    </action-mappings>
</actions>


```
