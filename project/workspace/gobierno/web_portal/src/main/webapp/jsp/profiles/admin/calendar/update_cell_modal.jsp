<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="static ar.edu.ubp.das.src.utils.Constants.SORTEOS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>

<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />
<%
    String cellDay = (String) request.getAttribute("cell_day");
    String cellMonth = (String) request.getAttribute("cell_month");
    String cellYear = (String) request.getAttribute("cell_year");
    SorteoForm sorteo = (SorteoForm) request.getAttribute("sorteo");
%>
<div class="modal-header">
    <h5 class="modal-title">
        <fmt:message key="update_cell_modal_header" bundle="${etq}" />
    </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="modal-body">
    <form id="update_sorteo" class="form-horizontal" method="post" >
        <p><fmt:message key="update_cell_modal_update_label" bundle="${etq}" /><%=sorteo.getFechaEjecucion().toString()%></p>
        <div class="form-group">
           <label class="col-md-4 control-label" for="">
                <fmt:message key="update_cell_modal_new_date_label" bundle="${etq}" />
           </label>
           <input type="date" class="form-control form-control-lg">
        </div>
    </form>
</div>
<div class="modal-footer" >
   <button type="button" id="update_sorteo_btn" class="btn btn-success">
        <fmt:message key="update_cell_modal_update_label" bundle="${etq}" />
   </button>
   <button type="button" id="cancelar_update_sorteo_btn" class="btn btn-danger" data-dismiss="modal">
        <fmt:message key="update_cell_cancel_btn" bundle="${etq}" />
   </button>
   <button type="button" id="show_sorteo_details_btn" class="btn btn-info">
        <fmt:message key="update_cell_details_btn" bundle="${etq}" />
    </button>
</div>

