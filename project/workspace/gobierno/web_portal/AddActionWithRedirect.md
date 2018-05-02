# Agregar un Action que redirija

###### Qué es un `Action`
Supongamos que tenemos un botón HTML en un archivo JSP.
Al hacer click dispara un método javascript almacenado en un objeto javascript, en un archivo externo alojado en el directorio WebContent/js/

Éste método realiza una llamada ajax al backend, y `Action` va a representar a la lógica del backend que queremos ejecutar.

###### Ejemplo
Supongamos que queremos setear el idioma.
Debería ser tan fácil como escribir el siguiente método:

```javascript

setIdioma : function(idioma) {

    jUtils.executing( "mensaje" );
    $.ajax({
        url: "/web_portal/admin/SetearIdioma.do",
        type: "post",
        data: "idioma=" + idioma,
        dataType: "html",
        error: function(hr){
            jUtils.hiding("resultIdioma");
            jUtils.showing("error", hr.responseText);
        },
        success: function(html) {
            jUtils.hiding("error");
            jUtils.showing("resultIdioma", html);
        }
    });
},
```

> (en éste caso la url necesita el prefijo /webportal/ porque no hemos modificado aún a WebContent/urlrewrite.xml)






Con éso dicho, podemos proceder a explicar los pasos necesarios para agregar un `Action`:

Agregar archivo /src/main/java/ar.edu.etc.actions/SetearIdiomaAction.java

``` java
package ar.edu.ubp.das.src.admin.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetearIdiomaAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RuntimeException {
		// TODO Auto-generated method stub

		request.setAttribute("idioma", form.getItem("idioma"));

		return mapping.getForwardByName("success");
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

        <action type="SetearIdiomaAction" path="/SetearIdioma.do">
            <forward name="success" path="/admin/setearIdioma.jsp" />
        </action>

    </action-mappings>
</actions>
```
