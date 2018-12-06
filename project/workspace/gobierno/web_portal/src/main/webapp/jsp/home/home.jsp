<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <head>
        <%@include file="./header.jsp" %>
    </head>

    <body>
        <button id="registrarConcesionaria" name="registrarConcesionaria" class="btn btn-primary" onclick="javascript:home.showRegistrarConcesionaria();">Registrar Concesionaria</button>

        <button id="login" name="login" class="btn btn-primary" onclick="javascript:home.showLogin();">Login</button>
    </body>

    <%@include file="../footer/footer.jsp"%>
    <script src="/web_portal/util/Javascript.do/load=own_libraries/home/home"></script>

</html>
