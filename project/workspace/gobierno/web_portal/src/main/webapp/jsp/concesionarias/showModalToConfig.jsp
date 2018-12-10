<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.CONFIG_PARAMS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="java.util.List" %>
<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<%
    List<ConfigurarConcesionariaForm> configsParams = (List<ConfigurarConcesionariaForm>) request.getAttribute(CONFIG_PARAMS_LIST_RQST_ATTRIBUTE);

    String result = FrontUtils.makeFormConfigTecnologica(configsParams);
%>
<%= result %>