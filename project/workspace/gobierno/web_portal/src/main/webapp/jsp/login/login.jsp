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
            <div id="header_home">
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <div class="collapse navbar-collapse" id="navbarColor01">
                    <%@include file="../commons/go_to_home.jsp"%>
                </div>
            </nav>
            <div class="loginbox">
                <img src="/web_portal/img/login/avatar.png" class="avatar"/>
                <h1><fmt:message key="login_cabecera" bundle="${etq}" /></h1>
                <form id="login_form" action="javascript:void(0)" method="post">
                    <p><fmt:message key="login_usuario" bundle="${etq}" /></p>
                    <input type="text" name="username" id="username" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />"/>
                    <p><fmt:message key="login_clave" bundle="${etq}" /></p>
                    <input type="password" name="password" id="password" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />"/>
                    <input id="login_btn" type="submit" value="<fmt:message key="login_ingresar" bundle="${etq}"/>" />
                    <a id="first_login_btn" href="#">Primer ingreso?</a>
                </form>
            </div>
            <%@include file="../commons/resultado.jsp"%>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
    <%@include file="../js_imports/home.jsp"%>
    <%@include file="../js_imports/login.jsp"%>
</html>