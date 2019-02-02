<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <!-- concesionarias aprobadas -->
            <div class="jumbotron">
                <h1 class="display-3">Concesionarias disponibles</h1>
                <hr class="my-4">
                <div id="conc_aprobadas_div"></div>
                <br><hr class="my-4"><br>
                <p class="lead">
                    Si querés formar parte de nuestro programa, puedes hacerlo desde
                    <a href="/web_portal/home/ShowRegistrarConcesionaria.do" name="registrarConcesionaria">
                        aquí
                    </a>
                </p>
            </div>
            <!-- carousel -->
            <%@include file="./carousel_home.jsp"%>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
</html>
