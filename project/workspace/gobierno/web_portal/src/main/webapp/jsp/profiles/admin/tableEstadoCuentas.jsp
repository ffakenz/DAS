<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

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
        result.append("<thead>                                                                                     ");
        result.append("<tr>                                                                                        ");
        result.append("   <th>Id</th>                                                                              ");
        result.append("   <th>Estado</th>                                                                          ");
        result.append("   <th>ConcesionariaId</th>                                                                 ");
        result.append("   <th>NroPlanConcesionaria</th>                                                            ");
        result.append("   <th>DocumentoCliente</th>                                                                ");
        result.append("   <th>FechaAltaConcesionaria</th>                                                          ");
        result.append("   <th>FechaAltaSistema</th>                                                                ");
        result.append("   <th>Vehiculo</th>                                                                        ");
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
