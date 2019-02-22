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
<%@ page import="ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm" %>

<table id="table_admin_result" class="stripe table_report display nowrap" style="width:100%">
<thead>
<tr>
   <th><fmt:message key="table_sorteos_state_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_sorteos_created_at_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_sorteos_execution_date_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>

<%
    try {
        List<SorteoForm> sorteosList = (List<SorteoForm>) request.getAttribute(SORTEOS_LIST_RQST_ATTRIBUTE);
        for(SorteoForm sorteo : sorteosList) {

            final String sorteoRowId = SORTEO_ROW + "-" + sorteo.getId() ;
        %>
            <tr id="<%= sorteoRowId %>"  >
                <td> <%= sorteo.getEstado() %> </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= sorteo.getFechaCreacion() %>" />
                </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= sorteo.getFechaEjecucion() %>" />
                </td>
            </tr>
        <% } // END FOR LOOP
    } catch(Exception e) {
        e.getMessage();
    }
%>

</tbody>
</table>

<fmt:message key="table_sorteos_title" var="title" bundle="${etq}" />

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>