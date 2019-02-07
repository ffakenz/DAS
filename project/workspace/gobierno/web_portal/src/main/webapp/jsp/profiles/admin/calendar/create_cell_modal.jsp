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
<div class="modal-header">
    <h5 class="modal-title">Crear Sorteo</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="modal-body">
    <form id="create_sorteo" class="form-horizontal" method="post" >
        <div class="form-group">
           <p>Desea crar un Sorteo para el dia de la fecha ?</p>
           <p>Fecha: <%= result.toString() %> </p>
        </div>
    </form>
</div>
<div class="modal-footer">
   <button type="button" id="create_sorteo_btn" class="btn btn-success ">Confirmar</button>
   <button type="button" id="cancelar_create_sorteo_btn" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
</div>

