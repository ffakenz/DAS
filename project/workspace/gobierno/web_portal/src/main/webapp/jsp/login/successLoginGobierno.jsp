<%@ page

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

            <div class="page-header text-center">
                <h3>USUARIO ADMIN CORRECTO ... ( Session de login nº:  ${sessionScope.ssid} )</h3>
            </div>

            <br>

            <div id="test_consumo_div">
                <button id="test_consumo_btn" name="test_consumo_btn" class="btn btn-outline-primary btn-lg btn-block">TEST CONSUMO</button>
            </div>

            <br>

            <div id="concesionariasDiv" class="container">
                <table id="tableConcesionarias"  class="table table-striped table-bordered" style="width:100%">
                </table>
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

    <%@include file="../commons/footer.jsp"%>q
    <script src="/web_portal/util/Javascript.do/load=own_libraries/successGobierno"></script>

</html>