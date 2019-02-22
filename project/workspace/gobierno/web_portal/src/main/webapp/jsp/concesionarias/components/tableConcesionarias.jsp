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

<table id="table_admin_result" class="stripe table_report">
<thead>
<tr>
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
<%
    try {
        List<ConcesionariaForm> concesionariaFormList = (List<ConcesionariaForm>) request.getAttribute(CONCESIONARIAS_LIST_RQST_ATTRIBUTE);
        for( ConcesionariaForm c : concesionariaFormList) {

            final String concesionariaRowId = CONCESIONARIA_ROW + "-" + c.getId() ;
            final String concesionariaRowClass = c.getCodigo() == null ? CONCESIONARIA_NO_APROBADA : CONCESIONARIA_APROBADA;

        %>

            <tr id="<%= concesionariaRowId %>" class="<%= concesionariaRowClass %>" >
                <td> <%= c.getNombre() %> </td>
                <td> <%= c.getCuit() %> </td>
                <td> <%= FrontUtils.emptyIfNull(c.getCodigo()) %> </td>
                <td> <%= c.getEmail() %> </td>
                <td> <%= c.getDireccion() %> </td>
                <td> <%= c.getTel() %> </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= c.getFechaAlta() %>" />
                </td>
                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= c.getFechaRegistracion() %>" />
                </td>

                <% if (concesionariaRowClass.equals(CONCESIONARIA_NO_APROBADA)) {
                    String aprobarBtnId = APROBAR_BTN + "-" + c.getId();
                    String aprobarBtnClass = String.format("btn btn-success %s", APROBAR_BTN );
                    String aprobarBtnDesc = "Aprobar";
                %>

                    <td>
                        <button type="button" id="<%= aprobarBtnId %>" class="<%= aprobarBtnClass %>" >
                            <%= aprobarBtnDesc %>
                        </button>
                    </td>

                <% } else {
                    String desaprobarBtnId = DESAPROBAR_BTN + "-" + c.getId();
                    String desaprobarBtnClass = String.format("btn btn-danger %s", DESAPROBAR_BTN );
                    String desaprobarBtnDesc = "Desaprobar";
                %>

                    <td>
                        <button type="button" id="<%= desaprobarBtnId %>" class="<%= desaprobarBtnClass %>" >
                            <%= desaprobarBtnDesc %>
                        </button>
                    </td>

                <% } // END IF ELSE
                    String configBtnId = CONFIG_BTN + "-" + c.getId();
                    String configBtnClass = String.format("btn btn-info %s", CONFIG_BTN );
                    String configBtnDesc = "Configurar";
                %>

                <td>
                    <button type="button" id="<%= configBtnId %>" class="<%= configBtnClass %>" >
                        <%= configBtnDesc %>
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

<fmt:message key="table_conc_title" var="title" bundle="${etq}" />

<jsp:include page="../../commons/datatable.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>
