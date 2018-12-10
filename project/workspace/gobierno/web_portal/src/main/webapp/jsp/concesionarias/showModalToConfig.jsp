<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.CONFIG_PARAMS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="java.util.List" %>
<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<%
    List<ConfigurarConcesionariaForm> configsParams = (List<ConfigurarConcesionariaForm>) request.getAttribute(CONFIG_PARAMS_LIST_RQST_ATTRIBUTE);

    String result = FrontUtils.makeFormConfigTecnologica(configsParams);
%>
<div id="showConfigSuccess" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Configuracion Teconologica</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div>
                    <%= result %>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>