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

            <div>
                <button id="registrarConcesionaria" name="registrarConcesionaria" class="btn btn-outline-primary btn-lg btn-block" onclick="javascript:home.showRegistrarConcesionaria();">Registrar Concesionaria</button>
            </div>

        </div>

    </body>

    <%@include file="../commons/footer.jsp"%>

</html>
