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
        <div class="page-header text-center">
            <H2>FELICITACIONES TE HAS REGISTRADO .. AHORA DEBES ESPERAR A SER APROBADO !!!!</H2>
        </div>

        <button id="login" name="login" class="btn btn-primary" onclick="home.IDENTIFICADORHome();">Volver al home</button>
    </body>

    <%@include file="../commons/footer.jsp"%>


</html>