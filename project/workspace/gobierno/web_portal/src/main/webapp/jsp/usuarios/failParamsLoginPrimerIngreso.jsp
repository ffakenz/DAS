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

    <header>
        <%@include file="../commons/go_to_home.jsp"%>
    </header>

    <div class="page-header text-center">
        <H2>INVALID DATA</H2>
    </div>
</div>
</body>

<%@include file="../commons/footer.jsp"%>
<%@include file="../js_imports/home.jsp"%>

</html>
