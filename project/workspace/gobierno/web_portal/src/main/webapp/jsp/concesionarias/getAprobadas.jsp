<%@ page import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.APROBADAS_RQST_ATTRIBUTE" %>
<%@ page import="java.util.List" %>
<%@ page language="java"
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    List<ConcesionariaForm> aprobadasList = (List<ConcesionariaForm>) request.getAttribute(APROBADAS_RQST_ATTRIBUTE);
    String result = FrontUtils.listOfConcesionarias(aprobadasList);
%>

<%= result %>