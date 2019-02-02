<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=loginbox" />
            <div class="loginbox">
                <img src="/web_portal/img/login/avatar.png" class="avatar"/>
                <h1><fmt:message key="login_cabecera" bundle="${etq}" /></h1>
                <form id="login_form" action="javascript:void(0)" method="post">
                    <p><fmt:message key="login_usuario" bundle="${etq}" /></p>
                    <input type="text" name="username" id="username" value="irocca" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />"/>
                    <p><fmt:message key="login_clave" bundle="${etq}" /></p>
                    <input type="password" name="password" id="password" value="lam" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />"/>
                    <input id="login_btn" type="submit" value="<fmt:message key="login_ingresar" bundle="${etq}"/>" />
                    <a id="first_login_btn" href="#">Primer ingreso?</a>
                </form>
            </div>
            <%@include file="../commons/resultado.jsp"%>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
</html>