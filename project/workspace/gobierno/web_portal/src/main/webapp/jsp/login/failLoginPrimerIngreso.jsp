<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <%@include file="../commons/head.jsp" %>

    <body>
        <div class="content-body">

            <header>
                <button name="login" class="btn btn-primary float-right" onclick="javascript:home.goToHome();">Volver al home</button>
            </header>

            <div class="page-header text-center">
                <h2>USUARIO INCORRECTO ... si quiere puede volver a probar</h2>
            </div>

            <%@include file="formLogin.jsp"%>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>

</html>