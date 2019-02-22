<%
    String result = (String) request.getAttribute("result"); // in case of failure
%>
<fmt:message key="<%= result %>" bundle="${etq}" />