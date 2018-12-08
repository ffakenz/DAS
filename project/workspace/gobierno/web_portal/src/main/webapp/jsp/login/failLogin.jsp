<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <head>
        <%@include file="../header/header.jsp" %>
    </head>

    <body>
    <H2>USUARIO INCORRECTO ... si quiere puede volver a probar</H2>
    <%@include file="formLogin.jsp"%>

    <button name="login" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>
    </body>

    <%@include file="../footer/scripts_import.jsp"%>

</html>