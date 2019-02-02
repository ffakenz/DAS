<!-- Loading HOME Module -->
<script src="/web_portal/util/Javascript.do/load=own_libraries/home/home_config"></script>
<script src="/web_portal/util/Javascript.do/load=own_libraries/home/home_service"></script>
<script src="/web_portal/util/Javascript.do/load=own_libraries/home/home_module"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${(requestScope['javax.servlet.forward.request_uri'] == '/web_portal/') or (requestScope['javax.servlet.forward.request_uri'] == '/web_portal/home/Home.do')}">
        <script src="/web_portal/util/Javascript.do/load=own_libraries/home/home_loader"></script>
    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>


