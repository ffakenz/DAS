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

    <%@include file="../header/header.jsp" %>

    <body>

        <c:choose>
            <c:when test="${sessionScope.ssid != null}">
                <button id="logout" name="logout" class="btn btn-primary" onclick="javascript:logout.closeSession();">Cerrar Sesion</button>
            </c:when>
            <c:otherwise>
                <button id="login" name="login" class="btn btn-primary" onclick="javascript:home.showLogin();">Login</button>
            </c:otherwise>
        </c:choose>

        <button id="registrarConcesionaria" name="registrarConcesionaria" class="btn btn-primary" onclick="javascript:home.showRegistrarConcesionaria();">Registrar Concesionaria</button>


    </body>

    <%@include file="../footer/footer.jsp"%>

</html>
