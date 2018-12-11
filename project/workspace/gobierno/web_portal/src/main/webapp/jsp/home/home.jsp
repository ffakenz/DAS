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

            <div id="registrar_concesionaria_div">
                <button id="registrar_concesionaria" name="registrar_concesionaria" class="btn btn-outline-primary btn-lg btn-block" onclick="javascript:home.showRegistrarConcesionaria();">Registrar Concesionaria</button>
            </div>

            <br>

            <div id="test_consumo_div">
                <button id="test_consumo_btn" name="test_consumo_btn" class="btn btn-outline-primary btn-lg btn-block">TEST CONSUMO</button>
            </div>

            <br><br>

            <div id="aprobarConcesionariaDiv" class="container">
                <ct:concesionariasTable idTable="tableConcesionarias"></ct:concesionariasTable>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="modal_generic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel"> CONFIGURAR CONCESIONARIA </h4>
                        </div>
                        <div class="modal-body" id="modal_content" style="overflow-x: scroll;"></div>
                    </div>
                </div>
            </div>


        </div>

    </body>

    <%@include file="../commons/footer.jsp"%>

    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="/web_portal/util/Javascript.do/load=own_libraries/updateConfigForm"></script>
    <script src="/web_portal/util/Javascript.do/load=own_libraries/successGobierno"></script>

</html>
