<%@ page

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

            <div class="page-header text-center">
                <h2>USUARIO CORRECTO !!!!</h2>
                <br>
                <h3> Session de login nº:  ${sessionScope.ssid}</h3>
            </div>

            <div class="container">
                <c:choose>
                    <c:when test="${requestScope.userType == 'gobierno'}">
                        <label>Admin</label>
                    </c:when>
                    <c:when test="${requestScope.userType == 'consumer'}">
                        <label>Consumer</label>
                    </c:when>
                    <c:otherwise>
                        <label>Tipo de usuario invalido</label>
                    </c:otherwise>
                </c:choose>
            </div>

            <br>

            <button id="login" name="login" class="btn btn-outline-primary" onclick="javascript:home.goToHome();">Volver al home</button>
        </div>
    </body>

    <%@include file="../commons/footer.jsp"%>

</html>