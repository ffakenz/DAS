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

            <div id="header_home">
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <div class="collapse navbar-collapse" id="navbarColor01">
                    <%@include file="../commons/go_to_home.jsp"%>
                </div>
            </nav>

            <div id="login-form">

                <h1 style="text-align: center">
                    <fmt:message key="login_cabecera" bundle="${etq}" />
                </h1>

                <%@include file="formLogin.jsp"%>

            </div>

            <%@include file="../commons/resultado.jsp"%>


        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>
    <%@include file="../js_imports/home.jsp"%>
    <%@include file="../js_imports/login.jsp"%>

</html>