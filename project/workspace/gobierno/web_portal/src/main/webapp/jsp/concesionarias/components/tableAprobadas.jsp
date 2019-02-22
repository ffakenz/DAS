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
<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>

<table id="aprobadas_table" class="stripe table_report">
<thead>
<tr>
   <th><fmt:message key="table_conc_name_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_cuit_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_email_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_dir_th" bundle="${etq}" /></th>
   <th><fmt:message key="table_conc_tel_th" bundle="${etq}" /></th>
   <th>Contact</th>
</tr>
</thead>
<tbody>
<%
    try {
        List<ConcesionariaForm> aprobadasList = (List<ConcesionariaForm>) request.getAttribute(APROBADAS_RQST_ATTRIBUTE);
        for(ConcesionariaForm aprobada : aprobadasList) {

            final String aprobadaRowId = CONCESIONARIA_ROW + "-" + aprobada.getId() ;
        %>
            <tr id="<%= aprobadaRowId %>" >
                <td> <%= aprobada.getNombre() %> </td>
                <td> <%= aprobada.getCuit() %> </td>
                <td> <%= aprobada.getEmail() %> </td>
                <td> <%= aprobada.getDireccion() %> </td>
                <td> <%= aprobada.getTel() %> </td>
                <td>
                    <%
                        String contactBtnId = CONFIG_BTN + "-" + aprobada.getId();
                        String contactBtnClass = String.format("btn btn-info %s", CONFIG_BTN );
                        String contactBtnDesc = "Contact";
                    %>
                    <button type="button" id="<%= contactBtnId %>" class="<%= contactBtnClass %>" >
                        <%= contactBtnDesc %>
                    </button>
                </td>
            </tr>
        <% } // END FOR LOOP
   } catch(Exception e) {
       e.getMessage();
   }
%>
</tbody>
</table>

<script src="/web_portal/js/external_libraries/jquery.dataTables.min.js"></script>
<script src="/web_portal/js/external_libraries/dataTables.bootstrap4.min.js"></script>
<script src="/web_portal/js/external_libraries/dataTables.buttons.min.js"></script>
<script src="/web_portal/js/external_libraries/vfs_fonts.js"></script>
<script src="/web_portal/js/external_libraries/buttons.html5.min.js"></script>
<script>
        $(()=> {
            $('#aprobadas_table').DataTable( { } );
        });
</script>
