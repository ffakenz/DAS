<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.DESAPROBADA_RQST_ATTRIBUTE" %>
<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<%
    ConcesionariaForm desaprobada = (ConcesionariaForm) request.getAttribute(DESAPROBADA_RQST_ATTRIBUTE);
    String result = FrontUtils.concesionariaFormRow(desaprobada);
%>
<%= result %>