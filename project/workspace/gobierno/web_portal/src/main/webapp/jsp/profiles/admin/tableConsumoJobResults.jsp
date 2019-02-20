<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%-- JSTL --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Language --%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<%-- Common Imports --%>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.*" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>

<%-- Specific Imports --%>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="ar.edu.ubp.das.src.jobs.consumo.forms.ViewConsumoResultsForm" %>


<table id="table_admin_result" class="stripe">
<thead>
<tr>
   <th><fmt:message key="table_consumo_job_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_fecha_ej_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_consumo_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_conc_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_estado_consumo_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_from_date_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_to_date_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_rqst_resp_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_estado_desc_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_consumo_result_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_consumo_result_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_consumo_job_consumo_result_desc_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>

<%
    try {
        List<ViewConsumoResultsForm> viewResults = (List<ViewConsumoResultsForm>) request.getAttribute(JOB_RESULTS_REPORT_RQST_ATTRIBUTE);
        for( ViewConsumoResultsForm view : viewResults) {

            final String viewId = VIEW_CONSUMO_RESULTS_ROW + "-" + view.getJobId();
            // todo: show and check possible null values
        %>
            <tr id= <%= viewId %> >
                <td> <%= view.getJobId() %> </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= view.getJobFechaEjecucion() %>" />
                </td>
                <td> <%= view.getConsumoId() %> </td>
                <td> <%= view.getConcesionariaId() %> </td>
                <td> <%= view.getEstadoConsumo() %> </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= view.getFromConsumo() %>" />
                </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= view.getToConsumo() %>" />
                </td>
                <td> <%= view.getIdRequestRespConsumo() %> </td>
                <td> <%= view.getEstadoDescription() %> </td>
                <td> <%= view.getConsumoResultId() %> </td>
                <td> <%= view.getConsumoResult() %> </td>
                <td> <%= view.getConsumoResultDescription() %> </td>
            </tr>
        <% } // END FOR LOOP
    } catch(Exception e) {
        e.getMessage();
    }
%>

</tbody>
</table>

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="Results" />
</jsp:include>