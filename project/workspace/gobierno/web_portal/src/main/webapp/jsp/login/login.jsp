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

            <%@include file="header_login.jsp"%>

            <div id="login-form">

                <h1 style="text-align: center">
                    <fmt:message key="login_cabecera" bundle="${etq}" />
                </h1>

                <%@include file="formLogin.jsp"%>

                <%@include file="../home/go_to_home.jsp" %>

            </div>


            <div id="resultado">

                <div id="mensaje"></div>

                <div id="error"></div>

            </div>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>
    <%@include file="../js_imports/home.jsp"%>
    <%@include file="../js_imports/login.jsp"%>

</html>