<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<%@ page import="java.lang.Exception" %>

<%
    String cellDay = (String) request.getAttribute("cell_day");
    String cellMonth = (String) request.getAttribute("cell_month");
    String cellYear = (String) request.getAttribute("cell_year");

    StringBuilder result = new StringBuilder();
    result.append(cellDay + "/" + cellMonth + "/" + cellYear);
%>
<form id="create_sorteo" class="form-horizontal" method="post" >
    <div class="form-group">
       <p>Desea crar un Sorteo para el dia de la fecha ?</p>
       <p>Fecha: <%= result.toString() %> </p>
       <button type="button" id="create_sorteo_btn" class="btn-info">Confirmar</button>
       <button type="button" id="cancelar_create_sorteo_btn" class="btn-danger">Cancelar</button>
    </div>
</form>

