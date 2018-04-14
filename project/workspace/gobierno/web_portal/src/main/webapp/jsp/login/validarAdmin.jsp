<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!-- JSTL -->

<c:if test="${requestScope.respuesta eq 'i'}">
    <fmt:message key="validar_inexistente" bundle="${etq}" />
</c:if>

<c:if test="${requestScope.respuesta eq 'b'}">
    <fmt:message key="validar_bloqueado" bundle="${etq}" />
</c:if>

<c:if test="${requestScope.respuesta eq 'e'}">
    <fmt:message key="validar_incorrecta" bundle="${etq}" />
</c:if>

<c:if test="${requestScope.respuesta eq 'c'}">
    <script> window.location="/admin/Home.do" </script>
</c:if>

<!-- JSTL -->