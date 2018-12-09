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

            <%@ include file="../commons/header.jsp" %>
            <!-- Form Name -->
            <div class="page-header text-center">
                <h1>LOS DATOS QUE HAS INGRESADO NO SON VALIDOS</h1>
                <br>
                <h2>Puedes volver a intentarlo o ponerte en contacto con nosotros a través de nuestra página de contacto</h2>
            </div>

            <button name="goToRegistrar" class="btn btn-outline-primary btn-lg" onclick="home.showRegistrarConcesionaria();">Volver a intentarlo</button>

            <button name="goToHome" class="btn btn-outline-secondary" onclick="home.goToHome();">Volver al home</button>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>

</html>