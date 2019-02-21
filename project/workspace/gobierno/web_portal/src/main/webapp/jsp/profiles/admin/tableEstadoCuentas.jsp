<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%-- JSTL --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<%-- Language --%>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setLocale value="${lang}" scope="session" />

<%-- Common Imports --%>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.*" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>

<%-- Specific Imports --%>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm" %>


<table id="table_admin_result" class="stripe table_report">
<thead>
<tr>
   <th><fmt:message key="table_estado_cuentas_estado_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_conc_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_nro_plan_conc_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_doc_cli_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_fecha_alta_conc_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_vehiculo_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>

<%
    StringBuilder result = new StringBuilder();
    try {
        List<EstadoCuentasForm> estadoCuentasFormList = (List<EstadoCuentasForm>) request.getAttribute(ESTADO_CUENTAS_LIST_RQST_ATTRIBUTE);
        for( EstadoCuentasForm ec : estadoCuentasFormList) {
            final String estadoCuentaRowId = ESTADO_CUENTA_ROW + "-" + ec.getId();
            // todo: implement procedure to obtain concesionaria name, and vehiculo name
        %>
            <tr id="<%= estadoCuentaRowId %>">
            <td> <%= ec.getEstado() %> </td>
            <td> <%= ec.getConcesionariaId() %> </td>
            <td> <%= ec.getNroPlanConcesionaria() %> </td>
            <td> <%= ec.getDocumentoCliente() %> </td>
            <td>
                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= ec.getFechaAltaConcesionaria() %>" />
            </td>
            <td> <%= ec.getVehiculo() %> </td>
            </tr>
        <% } // END FOR LOOP
    } catch(Exception e) {
            e.getMessage();
    }
%>

</tbody>
</table>

<fmt:message key="table_estado_cuentas_title" var="title" bundle="${etq}" />

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>