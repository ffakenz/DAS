<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

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
        result.append("<thead>                                                                                     ");
        result.append("<tr>                                                                                        ");
        result.append("   <th>Job Id</th>                                                                           ");
        result.append("   <th>Job Fecha Ejecucion</th>                                                               ");
        result.append("   <th>Consumo Id</th>                                                                       ");
        result.append("   <th>Concesionaria Id</th>                                                                 ");
        result.append("   <th>Estado Consumo</th>                                                                   ");
        result.append("   <th>Offset Consumo</th>                                                                   ");
        result.append("   <th>Id Request-Resp Consumo</th>                                                            ");
        result.append("   <th>Estado Description</th>                                                               ");
        result.append("   <th>Consumo Result Id</th>                                                                 ");
        result.append("   <th>Consumo Result</th>                                                                   ");
        result.append("   <th>Consumo Result Description</th>                                                        ");
        result.append("</tr>                                                                                       ");
        result.append("</thead>                                                                                    ");
        result.append("<tbody>                                                                                     ");
        result.append(rows.toString()                                                                               );
        result.append("</tbody>                                                                                    ");
        } catch(Exception e) {
        result.append(e.getMessage());
        }
        %>
<table id="table_admin_result" class="table table-striped table-bordered">
<%= result.toString() %>
</table>
