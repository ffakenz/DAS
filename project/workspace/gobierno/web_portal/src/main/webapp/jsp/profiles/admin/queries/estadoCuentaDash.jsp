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
<%@ page import="ar.edu.ubp.das.src.queries.forms.EstadoCuentaDashForm" %>

<table id="table_admin_result" class="stripe">
<thead>
<tr>
   <th><fmt:message key="update_cell_update_btn" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_nombreCliente_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_documentoCliente_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_estadoPlan_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_nombreConcesionaria_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_nroPlanConcesionaria_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_vehiculoNombre_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_emailCliente_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_concesionariaEmail_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_fechaUltimaActualizacionPlan_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_fechaUltimaActualizacionCuota_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_totalCuotasPagas_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_totalCuotasPorPagar_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_minimaCuotaPaga_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_maxCuotaPaga_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_avgCuotaPaga_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_totalMontoPagado_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_totalMontoPorPagar_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuenta_dash_vehiculoPrecio_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>

<%
    try {
        List<EstadoCuentaDashForm> dashrows = (List<EstadoCuentaDashForm>) request.getAttribute(QUERY_RESULT_RQST_ATTRIBUTE);
        for(EstadoCuentaDashForm row : dashrows) {
            String updateBtnClass = String.format("btn btn-info %s", "estado_cuenta_update_btn");
        %>
            <tr>
                <td>
                    <form method="post">
                        <input type="hidden" name="documento" value="<%= row.getDocumentoCliente() %>"/>
                        <input type="hidden" name="nro_plan_concesionaria" value="<%= row.getNroPlanConcesionaria() %>"/>
                        <input type="hidden" name="id_concesionaria" value="<%= row.getConcesionariaId() %>"/>
                        <button type="button" class="<%= updateBtnClass %>" >
                            <fmt:message key="update_cell_update_btn" bundle="${etq}" />
                        </button>
                    </form>
                </td>
                <td> <%= row.getNombreCliente() %> </td>
                <td> <%= row.getDocumentoCliente() %> </td>
                <td> <%= row.getEstadoPlan() %> </td>
                <td> <%= row.getNombreConcesionaria() %> </td>
                <td> <%= row.getNroPlanConcesionaria() %> </td>
                <td> <%= row.getVehiculoNombre() %> </td>
                <td> <%= row.getEmailCliente() %> </td>
                <td> <%= row.getConcesionariaEmail() %> </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= row.getFechaUltimaActualizacionPlan() %>" />
                </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= row.getFechaUltimaActualizacionCuota() %>" />
                </td>
                <td> <%= row.getTotalCuotasPagas() %> </td>
                <td> <%= row.getTotalCuotasPorPagar() %> </td>
                <td> <%= FrontUtils.emptyIfNull(row.getMinimaCuotaPaga()) %> </td>
                <td> <%= FrontUtils.emptyIfNull(row.getMaxCuotaPaga()) %> </td>
                <td> <%= FrontUtils.emptyIfNull(row.getAvgCuotaPaga()) %> </td>
                <td> <%= FrontUtils.emptyIfNull(row.getTotalMontoPagado()) %> </td>
                <td> <%= FrontUtils.emptyIfNull(row.getTotalMontoPorPagar()) %> </td>
                <td> <%= row.getVehiculoPrecio() %> </td>
            </tr>
        <% } // END FOR LOOP
    } catch(Exception e) {
        e.getMessage();
    }
%>

</tbody>
</table>

<fmt:message key="table_estado_cuentas_dash_title" var="title" bundle="${etq}" />

<jsp:include page="../../../commons/datatable.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>