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

    <fieldset>

        <form id="loginForm" action="javascript:login.validarUsuario();" method="post">

            <div class="md-form">
                <input type="text" name="username" id="username" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />">
            </div>

            <div class="md-form">
                <input type="password" name="password" id="password" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />">
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-default" > <fmt:message key="login_ingresar" bundle="${etq}" /> </button>
            </div>

        </form>

    </fieldset>

    <button id="login" name="login" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>

</div>

</body>

<%@include file="../footer/footer.jsp"%>

</html>