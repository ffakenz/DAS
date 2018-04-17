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
     <%@include file="./body.jsp" %>
    </body>

    <%@include file="../footer/footer.jsp" %>

</html>
