<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=simple-sidebar,admin,dataTables.bootstrap4.min" />

    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <div class="bg-light border-right gradient-background" id="sidebar-wrapper">
                <div class="sidebar-heading"><h3><fmt:message key="gobierno_sidebar_heading" bundle="${etq}" /></h3></div>
                <div id="admin_side_bar" class="list-group list-group-flush">
                    <a id="show_config_concesionarias_btn" class="list-group-item list-group-item-action bg-light gradient-background">
                        <fmt:message key="sidebar_option_config_concesionarias" bundle="${etq}" />
                    </a>
                    <a id="show_sorteos_btn" class="list-group-item list-group-item-action bg-light gradient-background">
                        <fmt:message key="sidebar_option_sorteos" bundle="${etq}" />
                    </a>
                    <a id="show_planes_btn" class="list-group-item list-group-item-action bg-light gradient-background">
                        <fmt:message key="sidebar_option_planes" bundle="${etq}" />
                    </a>
                    <a id="show_job_results_report_btn" class="list-group-item list-group-item-action bg-light gradient-background">
                        <fmt:message key="sidebar_option_job_results_report" bundle="${etq}" />
                    </a>
                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">

                <!-- ### HEADER ### -->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom gradient-background" style="padding-left: 80% !IMPORTANT;">
                    <div id="go_to_home_div" style="margin-right: 5px;">
                        <button id="go_to_home_btn" name="login" class="btn btn-primary pull-right" >
                            <fmt:message key="go_to_home_btn" bundle="${etq}" />
                        </button>
                    </div>
                    <div id="logout_div" style="margin-left: 5px;">
                        <button id="logout_btn" name="logout" class="btn btn-primary pull-right">
                            <fmt:message key="logout_btn" bundle="${etq}" />
                        </button>
                    </div>
                    <%@include file="../commons/language.jsp" %>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </nav>

                <div id="page_content_administrador_div" class="container-fluid">

                    <div id="calendario">
                        <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=calendar" />
                        <div id="calendar_main_div"></div>
                    </div>

                    <div id="content_admin_page_div"></div>

                    <!-- Modal -->
                    <div class="modal">
                        <div class="modal-dialog modal-dialog-centered modal-sm">
                            <div id="loadingDiv" class="modal-content">
                            </div>
                        </div>
                    </div>

                    <div class="modal" id="config_concesionaria_modal">
                        <div class="modal-dialog modal-dialog-centered modal-lg">
                            <div id="modal_content" class="modal-content">
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


</html>