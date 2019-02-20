<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.CONCESIONARIAS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%
    StringBuilder result = new StringBuilder();
    try {
        List<ConcesionariaForm> concesionariaFormList = (List<ConcesionariaForm>) request.getAttribute(CONCESIONARIAS_LIST_RQST_ATTRIBUTE);
        StringBuilder rows = new StringBuilder();
        for( ConcesionariaForm c : concesionariaFormList) {
            String row = FrontUtils.concesionariaFormRow(c);
            rows.append(row);
        }
        result.append(    rows.toString()                                                                           );
   } catch(Exception e) {
        result.append(e.getMessage());
   }
%>
<table id="table_admin_result" class="stripe">
<thead>
<tr>
   <th><fmt:message key="table_conc_id_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_name_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_cuit_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_code_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_email_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_dir_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_tel_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_created_at_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_registered_at_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_action_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_config_th" bundle="${etq}" /></th>
</tr>
</thead>
<tbody>
<%= result.toString() %>
</tbody>
</table>

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="Concesionarias"/>
</jsp:include>
