<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />
<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=loginbox" />
            <div class="loginbox loginbox-login">
                <img src="/web_portal/img/login/avatar.png" class="avatar"/>
                <%
                    String cabecera = (String) request.getAttribute("cabecera"); // in case of failure
                    String title = (cabecera != null) ? cabecera : "primer_ingreso_cabecera"; // in case of first call
                %>
                <h1><fmt:message key="<%= title %>" bundle="${etq}" /></h1>
                <form id="login_primer_ingreso_form" method="post">
                    <p><fmt:message key="login_documento" bundle="${etq}" /></p>
                    <input type="text" name="documento" id="documento" required placeholder="<fmt:message key="login_documento" bundle="${etq}" />">
                    <p><fmt:message key="login_usuario" bundle="${etq}" /></p>
                    <input type="text" name="username" id="username" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />">
                    <p><fmt:message key="login_clave" bundle="${etq}" /></p>
                    <input type="password" name="password" id="password" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />">
                    <input id="login_registrar_btn" type="submit" value="<fmt:message key="login_registrar" bundle="${etq}" />" />
                </form>
            </div>
            <%@include file="../commons/resultado.jsp"%>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
</html>