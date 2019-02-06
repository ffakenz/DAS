<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

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
        result.append("<thead>                                                                                     ");
        result.append("<tr>                                                                                        ");
        result.append("   <th>Id</th>                                                                              ");
        result.append("   <th>Mes Sorteo</th>                                                                      ");
        result.append("   <th>Año Sorteo</th>                                                                      ");
        result.append("   <th>Estado</th>                                                                          ");
        result.append("   <th>Fecha Ejecucion</th>                                                                 ");
        result.append("</tr>                                                                                       ");
        result.append("</thead>                                                                                    ");
        result.append("<tbody>                                                                                     ");
        result.append(rows.toString()                                                                               );
        result.append("</tbody>                                                                                    ");
        } catch(Exception e) {
        result.append(e.getMessage());
        }
        %>
<%= result.toString() %>
