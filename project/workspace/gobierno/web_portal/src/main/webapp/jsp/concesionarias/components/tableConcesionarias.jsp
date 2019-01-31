<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>


<%@ page import="static ar.edu.ubp.das.src.utils.Constants.RESULT_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>

<%
    StringBuilder result = new StringBuilder();

    try {

        List<ConcesionariaForm> concesionariaFormList = (List<ConcesionariaForm>) request.getAttribute(RESULT_RQST_ATTRIBUTE);


        StringBuilder rows = new StringBuilder();
        for( ConcesionariaForm c : concesionariaFormList) {
            String row = FrontUtils.concesionariaFormRow(c);
            rows.append(row);
        }


        result.append("<thead>                                                                                     ");
        result.append("<tr>                                                                                        ");
        result.append("   <th>Id</th>                                                                              ");
        result.append("   <th>Nombre</th>                                                                          ");
        result.append("   <th>Cuit</th>                                                                            ");
        result.append("   <th>Codigo</th>                                                                          ");
        result.append("   <th>Email</th>                                                                           ");
        result.append("   <th>Direccion</th>                                                                       ");
        result.append("   <th>Tel</th>                                                                             ");
        result.append("   <th>FechaAlta</th>                                                                       ");
        result.append("   <th>FechaRegistracion</th>                                                               ");
        result.append("   <th>Action</th>                                                                          ");
        result.append("   <th>Configurar</th>                                                                      ");
        result.append("</tr>                                                                                       ");
        result.append("</thead>                                                                                    ");
        result.append("<tbody>                                                                                     ");
        result.append(    rows.toString()                                                                           );
        result.append("</tbody>                                                                                    ");
   } catch(Exception e) {
        result.append(e.getMessage());
   }
%>

<%= result.toString() %>
