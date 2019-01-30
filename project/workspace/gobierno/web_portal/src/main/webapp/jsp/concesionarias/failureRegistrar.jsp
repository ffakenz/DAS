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

            <div id="registrar_concesionaria_div">
                <button id="registrar_concesionaria_btn" class="btn btn-outline-primary btn-lg">Volver a intentarlo</button>
            </div>


            <%@include file="../commons/go_to_home.jsp" %>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>
    <%@include file="../js_imports/home.jsp"%>
    <%@include file="../js_imports/login.jsp"%>
</html>