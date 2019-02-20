<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<%@ page import="ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>

<%@ page import="static ar.edu.ubp.das.src.utils.Constants.SORTEOS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%
        StringBuilder result = new StringBuilder();
        try {
            List<SorteoForm> sorteosList = (List<SorteoForm>) request.getAttribute(SORTEOS_LIST_RQST_ATTRIBUTE);
            StringBuilder rows = new StringBuilder();
            for(SorteoForm ec : sorteosList) {
                String row = FrontUtils.sorteosFormRow(ec);
                rows.append(row);
            }
            result.append(rows.toString());
        } catch(Exception e) {
            result.append(e.getMessage());
        }
%>
<table id="table_admin_result" class="stripe">
<thead>
<tr>
   <th><fmt:message key="table_sorteos_state_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_sorteos_created_at_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_sorteos_execution_date_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>
<%= result.toString() %>
</tbody>
</table>

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="Sorteos"/>
</jsp:include>