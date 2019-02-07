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
    <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=simple-sidebar" />
    <body>

        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Administrador</div>
                <div id="admin_side_bar" class="list-group list-group-flush">
                    <a id="show_config_concesionarias_btn" class="list-group-item list-group-item-action bg-light">Configurar concesionarias</a>
                    <a id="show_sorteos_btn" class="list-group-item list-group-item-action bg-light">Sorteos</a>
                    <a id="show_planes_btn" class="list-group-item list-group-item-action bg-light">Planes</a>
                    <a id="show_job_results_report_btn" class="list-group-item list-group-item-action bg-light">Job Results Report</a>
                    <a id="execute_consumo_btn" class="list-group-item list-group-item-action bg-light">Consumir planes</a>
                    <a id="execute_sorteo_btn" class="list-group-item list-group-item-action bg-light">Ejecutar sorteo</a>
                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">

                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom" style="padding-left: 80% !IMPORTANT;">
                    <div id="go_to_home_div" style="margin-right: 5px;">
                        <button id="go_to_home_btn" name="login" class="btn btn-primary pull-right" >Volver al home</button>
                    </div>
                    <div id="logout_div" style="margin-left: 5px;">
                        <button id="logout_btn" name="logout" class="btn btn-primary pull-right">Cerrar Sesion</button>
                    </div>


                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </nav>

                <div id="page_content_administrador_div" class="container-fluid">

                    <div id="loadingDiv" stryle=""> </div>
                    <div id="content_admin_page_div">
                        <table id="table_admin_result" class="table table-hover">
                        </table>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="config_concesionaria_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->
    </body>


    <%@include file="../commons/footer.jsp"%>
    <label>USUARIO ADMIN CORRECTO ... ( Session de login nº:  ${sessionScope.ssid} )</label>
</html>