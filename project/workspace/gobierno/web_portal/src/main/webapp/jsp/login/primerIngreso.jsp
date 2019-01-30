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

            <div id="login-form">

                <h1 style="text-align: center">
                    Primer Ingreso
                </h1>

                <%@include file="formLoginPrimerIngreso.jsp"%>

            </div>


            <%@include file="../commons/resultado.jsp"%>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>
    <%@include file="../js_imports/home.jsp"%>
    <%@include file="../js_imports/login.jsp"%>

</html>