<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

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
<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>

<%
    ConcesionariaForm aprobada = (ConcesionariaForm) request.getAttribute(CONCESIONARIA_RQST);
    
    final String concesionariaRowId = CONCESIONARIA_ROW + "-" + aprobada.getId() ;
    final String concesionariaRowClass = aprobada.getCodigo() == null ? CONCESIONARIA_NO_APROBADA : CONCESIONARIA_APROBADA;
    
%>
<tr id="<%= concesionariaRowId %>" class="<%= concesionariaRowClass %>" >
    <td> <%= aprobada.getNombre() %> </td>
    <td> <%= aprobada.getCuit() %> </td>
    <td> <%= FrontUtils.emptyIfNull(aprobada.getCodigo()) %> </td>
    <td> <%= aprobada.getEmail() %> </td>
    <td> <%= aprobada.getDireccion() %> </td>
    <td> <%= aprobada.getTel() %> </td>
    <td>
        <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= aprobada.getFechaAlta() %>" />
    </td>
    <td>
        <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="<%= aprobada.getFechaRegistracion() %>" />
    </td>

    <% if (concesionariaRowClass.equals(CONCESIONARIA_NO_APROBADA)) {
        String aprobarBtnId = APROBAR_BTN + "-" + aprobada.getId();
        String aprobarBtnClass = String.format("btn btn-success %s", APROBAR_BTN );
        String aprobarBtnDesc = "Aprobar";
    %>

        <td>
            <button type="button" id="<%= aprobarBtnId %>" class="<%= aprobarBtnClass %>" >
                <%= aprobarBtnDesc %>
            </button>
        </td>

    <% } else {
        String desaprobarBtnId = DESAPROBAR_BTN + "-" + aprobada.getId();
        String desaprobarBtnClass = String.format("btn btn-danger %s", DESAPROBAR_BTN );
        String desaprobarBtnDesc = "Desaprobar";
    %>

        <td>
            <button type="button" id="<%= desaprobarBtnId %>" class="<%= desaprobarBtnClass %>" >
                <%= desaprobarBtnDesc %>
            </button>
        </td>

    <% } // END IF ELSE
        String configBtnId = CONFIG_BTN + "-" + aprobada.getId();
        String configBtnClass = String.format("btn btn-info %s", CONFIG_BTN );
        String configBtnDesc = "Configurar";
    %>

    <td>
        <button type="button" id="<%= configBtnId %>" class="<%= configBtnClass %>" >
            <%= configBtnDesc %>
        </button>
    </td>
</tr>