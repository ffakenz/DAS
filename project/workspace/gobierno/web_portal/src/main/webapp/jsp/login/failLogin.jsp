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

            <div class="page-header text-center">
                <h2>USUARIO INCORRECTO ... si quiere puede volver a probar</h2>
            </div>

            <%@include file="formLogin.jsp"%>

            <button name="login" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>

</html>