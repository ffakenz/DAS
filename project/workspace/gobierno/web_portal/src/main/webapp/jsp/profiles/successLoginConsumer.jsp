<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <div class="page-header text-center">
                <h2><fmt:message key="header_login_consumo" bundle="${etq}" /></h2>
                <br>
                <h3><fmt:message key="header_login_session_consumo" bundle="${etq}" /><${sessionScope.ssid}</h3>
            </div>
            <div class="container"></div>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
</html>