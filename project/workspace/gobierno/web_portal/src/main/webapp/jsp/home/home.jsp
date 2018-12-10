<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
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

            <div id="aprobarConcesionariaDiv" class="container">
                <ct:concesionariasTable idTable="tableConcesionarias"></ct:concesionariasTable>
            </div>

            <div id="update_config_div"></div>
        </div>

    </body>

    <%@include file="../commons/footer.jsp"%>

    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="/web_portal/util/Javascript.do/load=own_libraries/successGobierno"></script>

</html>
