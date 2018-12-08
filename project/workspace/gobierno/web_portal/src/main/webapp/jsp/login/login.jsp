<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<%@include file="../header/header.jsp" %>

<body>

<div id="login-form">

    <h1><fmt:message key="login_cabecera" bundle="${etq}" /></h1>

    <%@include file="formLogin.jsp"%>

    <button id="login" name="login" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>

</div>

<div id="resultado">

    <div id="mensaje"></div>

    <div id="error"></div>

</div>


</body>

<%@include file="../footer/footer.jsp"%>

</html>