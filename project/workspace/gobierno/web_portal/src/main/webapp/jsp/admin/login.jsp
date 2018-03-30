<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        
        <title><fmt:message key="login_titulo" bundle="${etq}" /></title>
       
        <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=page,messages" />
        
        <script type="text/javascript" src="/web_portal/util/Javascript.do/load=jquery,jquery.i18n.properties,utils"> </script>
                
    </head>
    
    <body>
    
    <input type="button" onclick="javascript:administradores.setIdioma('es');" value="<fmt:message key="espanol" bundle="${etq}" />"/>
    <input type="button" onclick="javascript:administradores.setIdioma('en');" value="<fmt:message key="ingles" bundle="${etq}" />"/>
    <div id="resultIdioma"></div>
                
        <div id="login-form">
    
            <h1><fmt:message key="login_cabecera" bundle="${etq}" /></h1>
    
            <fieldset>
    
                <form id="formulario" action="javascript:administradores.validarAdmin();" method="post">
    
                    <input type="text" name="usuario" id="usuario" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />"> 
    
                    <input type="password" name="clave" id="clave" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />"> 
    
                    <input type="submit" value="<fmt:message key="login_ingresar" bundle="${etq}" />">
    
                    <!-- 
    
                    <footer class="clearfix">
    
                        <p> <span class="info"> ? </span>
                        
                        <a href="#"> ¿Olvidó su contraseña? </a> </p>
    
                    </footer>
                    
                    -->
    
                </form>
    
            </fieldset>
        
        </div>
        
        <div id="resultado">
        
	        <div id="mensaje"></div>
	        
	        <div id="error"></div>
        
        </div>
        
    </body>

</html>