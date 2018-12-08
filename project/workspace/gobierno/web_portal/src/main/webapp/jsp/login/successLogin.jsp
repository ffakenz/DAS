<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

<head>
    <%@include file="../header/header.jsp" %>
</head>

<body>
<H2>USUARIO CORRECTO ${sessionScope.ssid}!!!!</H2>
<br>

<c:choose>
    <c:when test="${requestScope.userType == 'gobierno'}">
        <label>Admin</label>
    </c:when>
    <c:when test="${requestScope.userType == 'consumer'}">
        <label>Consumer</label>
    </c:when>
    <c:otherwise>
        <label>Tipo de usuario invalido</label>
    </c:otherwise>
</c:choose>

<br>

<button id="login" name="login" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>

</body>

<%@include file="../footer/scripts_import.jsp"%>

</html>