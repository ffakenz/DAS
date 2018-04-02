<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="ar.edu.ubp.das.bundle.etiquetas.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${idioma}" scope="session" />

<script>

    //location.reload();
    
    jUtils.changeLang("Etiquetas_js", "${idioma}");

    //window.location.href = window.location.href + "?idioma=${idioma}";
    window.location = "/admin/Home.do?idioma=${idioma}";
    
</script>