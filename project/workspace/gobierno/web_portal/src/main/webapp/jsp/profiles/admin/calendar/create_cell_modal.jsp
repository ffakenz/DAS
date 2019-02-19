<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.lang.Exception" %>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${idioma}" scope="session" />

<%
    String cellDay = (String) request.getAttribute("cell_day");
    String cellMonth = (String) request.getAttribute("cell_month");
    String cellYear = (String) request.getAttribute("cell_year");

    StringBuilder result = new StringBuilder();
    result.append(cellDay + "/" + cellMonth + "/" + cellYear);
%>
<div class="modal-header">
    <h5 class="modal-title"><fmt:message key="creat_cell_modal_header" bundle="${etq}" /></h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="modal-body">
   <p><fmt:message key="creat_cell_confirm_question" bundle="${etq}" /></p>
   <p><fmt:message key="creat_cell_fecha_label" bundle="${etq}" /><%= result.toString() %></p>
</div>
<div class="modal-footer">
   <form id="create_sorteo" class="form-horizontal" method="post" >
       <div class="form-group">
          <input type="hidden" name="cell_day" value="<%=cellDay%>"/>
          <input type="hidden" name="cell_month" value="<%=cellMonth%>"/>
          <input type="hidden" name="cell_year" value="<%=cellYear%>"/>
          <button type="submit" id="create_sorteo_btn" class="btn btn-success ">
            <fmt:message key="creat_cell_confirm_btn" bundle="${etq}" />
          </button>
          <button type="button" id="cancelar_create_sorteo_btn" class="btn btn-danger" data-dismiss="modal">
            <fmt:message key="creat_cell_cancel_btn" bundle="${etq}" />
          </button>
       </div>
   </form>
</div>

