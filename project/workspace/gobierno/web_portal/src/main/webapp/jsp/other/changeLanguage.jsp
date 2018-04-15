<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${idioma}" scope="session" />

<script>
    jUtils.changeLang("Etiquetas_js", "${idioma}");
    window.location.href = window.location.href.split("?")[0] + "?idioma=${idioma}";
</script>