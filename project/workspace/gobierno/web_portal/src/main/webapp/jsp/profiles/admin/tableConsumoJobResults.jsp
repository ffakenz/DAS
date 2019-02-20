<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<%@ page import="ar.edu.ubp.das.src.jobs.consumo.forms.ViewConsumoResultsForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>

<%@ page import="static ar.edu.ubp.das.src.utils.Constants.JOB_RESULTS_REPORT_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%
        StringBuilder result = new StringBuilder();
        try {
            List<ViewConsumoResultsForm> viewResults =
                (List<ViewConsumoResultsForm>) request.getAttribute(JOB_RESULTS_REPORT_RQST_ATTRIBUTE);
            StringBuilder rows = new StringBuilder();
            for( ViewConsumoResultsForm v : viewResults) {
                String row = FrontUtils.viewConsumoResultsFormRow(v);
                rows.append(row);
            }
            result.append(rows.toString()                                                                                    );
        } catch(Exception e) {
            result.append(e.getMessage());
        }
        %>

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
<%= result.toString() %>
</tbody>
</table>

<c:set var = "title" scope = "session" value ="<fmt:message key='table_consumo_job_consumo_result_title' bundle='${etq}' />" />

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="Results" />
</jsp:include>