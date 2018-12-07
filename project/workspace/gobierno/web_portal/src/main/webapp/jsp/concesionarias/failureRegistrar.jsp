<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <head>

        <%@include file="../header/header.jsp" %>

    </head>

    <body>
        <H2>LOS DATOS QUE HAS INGRESADO NO SON VALIDOS . VUELVE A INTENTARLO O PONTE EN CONTACTO CON NOSOTROS</H2>
        <button name="goToRegistrar" class="btn btn-primary" onclick="javascript:home.showRegistrarConcesionaria();">Volver a intentarlo</button>
        <button name="goToHome" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>
    </body>

    <%@include file="../footer/footer.jsp"%>


</html>