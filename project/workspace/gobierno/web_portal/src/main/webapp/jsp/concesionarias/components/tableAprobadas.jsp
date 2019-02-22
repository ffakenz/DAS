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

<table id="aprobadas_table" class="table_report display nowrap row-border hover order-colum">
<thead>
<tr>
   <th><fmt:message key="table_conc_name_th" bundle="${etq}" /></th>
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

<!-- Language -->
<fmt:message key="datatables_empty_table" var="empty_table" bundle="${etq}" />
<fmt:message key="datatables_zero_records" var="zero_records" bundle="${etq}" />
<fmt:message key="datatables_info" var="info" bundle="${etq}" />
<fmt:message key="datatables_info_empty" var="info_empty" bundle="${etq}" />
<fmt:message key="datatables_info_filtered" var="info_filtered" bundle="${etq}" />
<fmt:message key="datatables_length_menu" var="length_menu" bundle="${etq}" />
<fmt:message key="datatables_loading_records" var="loading_records" bundle="${etq}" />
<fmt:message key="datatables_processing" var="processing" bundle="${etq}" />
<fmt:message key="datatables_search" var="search" bundle="${etq}" />
<fmt:message key="datatables_paginate_first" var="paginate_first" bundle="${etq}" />
<fmt:message key="datatables_paginate_last" var="paginate_last" bundle="${etq}" />
<fmt:message key="datatables_paginate_next" var="paginate_next" bundle="${etq}" />
<fmt:message key="datatables_paginate_previous" var="paginate_previous" bundle="${etq}" />
<fmt:message key="datatables_aria_sort_ascending" var="aria_sort_ascending" bundle="${etq}" />
<fmt:message key="datatables_aria_sort_descending" var="aria_sort_descending" bundle="${etq}" />
<script>
        $(()=> {
            $('#aprobadas_table').DataTable( {
                language: {
                    decimal:        "",
                    emptyTable:     '<c:out value="${empty_table}" />',
                    info:           '<c:out value="${info}" />',
                    infoEmpty:      '<c:out value="${info_empty}" />',
                    infoFiltered:   '<c:out value="${info_filtered}" />',
                    infoPostFix:    "",
                    thousands:      ",",
                    lengthMenu:     '<c:out value="${length_menu}" />',
                    loadingRecords: '<c:out value="${loading_records}" />',
                    processing:     '<c:out value="${processing}" />',
                    search:         '<c:out value="${search}" />',
                    zeroRecords:    '<c:out value="${zero_records}" />',
                    paginate: {
                        first:      '<c:out value="${paginate_first}" />',
                        last:       '<c:out value="${paginate_last}" />',
                        next:       '<c:out value="${paginate_next}" />',
                        previous:   '<c:out value="${paginate_previous}" />'
                    },
                    aria: {
                        sortAscending:  '<c:out value="${aria_sort_ascending}" />',
                        sortDescending: '<c:out value="${aria_sort_descending}" />'
                    }
                }
            } );
        });
</script>
