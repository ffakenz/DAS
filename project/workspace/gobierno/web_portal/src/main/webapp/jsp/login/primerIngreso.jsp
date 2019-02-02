<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=loginbox" />
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <div class="loginbox">
                <img src="/web_portal/img/login/avatar.png" class="avatar"/>
                <h1 style="text-align: center"> Primer Ingreso</h1>
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