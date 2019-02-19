<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<%@ page import="ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>

<%@ page import="static ar.edu.ubp.das.src.utils.Constants.ESTADO_CUENTAS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%
        StringBuilder result = new StringBuilder();
        try {
            List<EstadoCuentasForm> estadoCuentasFormList = (List<EstadoCuentasForm>) request.getAttribute(ESTADO_CUENTAS_LIST_RQST_ATTRIBUTE);
            StringBuilder rows = new StringBuilder();
            for( EstadoCuentasForm ec : estadoCuentasFormList) {
                String row = FrontUtils.estadoCuentasFormRow(ec);
                rows.append(row);
            }
            result.append(rows.toString()                                                                                    );
        } catch(Exception e) {
            result.append(e.getMessage());
        }
%>
<table id="table_admin_result" class="table table-hover">
<thead>
<tr>
   <th><fmt:message key="table_estado_cuentas_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_estado_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_conc_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_nro_plan_conc_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_doc_cli_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_fecha_alta_conc_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_fecha_alta_sys_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_estado_cuentas_vehiculo_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>
<%= result.toString() %>
</tbody>
</table>

<script src="/web_portal/js/external_libraries/datatables.min.js"></script>
<script>
        $(()=> {

            $('#table_admin_result').DataTable();

        });
</script>