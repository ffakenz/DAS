<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<div id="header_home">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary gradient-background">
        <ul class="navbar-nav mr-auto">
            <c:choose>
                <c:when test="${sessionScope.ssid != null}">
                    <li class="nav-item">
                        <a id="logout_btn" name="logout" class="nav-link" ><fmt:message key="logout_btn" bundle="${etq}" /></a>
                    </li>
                    <li class="nav-item">
                        <a id="go_to_profile_btn" name="goToProfile" class="nav-link" ><fmt:message key="go_to_profile_btn" bundle="${etq}" /></a>
                    </li>
                </c:when>
                <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/web_portal/home/ShowLogin.do'}">
                    <li class="nav-item">
                        <a id="show_login_btn" name="login" class="nav-link"><fmt:message key="show_login_btn" bundle="${etq}" /></a>
                    </li>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/web_portal/home/ShowRegistrarConcesionaria.do'}">
                    <li class="nav-item" id="registrar_concesionaria_div">
                        <a id="registrar_concesionaria_btn" name="registrarConcesionaria" class="nav-link">
                            <fmt:message key="registrar_concesionaria_btn" bundle="${etq}" />
                        </a>
                    </li>
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>
        </ul>
        <c:choose>
            <c:when test="${(requestScope['javax.servlet.forward.request_uri'] != '/web_portal/home/Home.do') and (requestScope['javax.servlet.forward.request_uri'] != '/web_portal/')}">
                <div id="go_to_home_div">
                    <button id="go_to_home_btn" name="login" class="btn btn-primary float-right" >
                        <fmt:message key="go_to_home_btn" bundle="${etq}" />
                    </button>
                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
        <%@include file="./language.jsp" %>
    </nav>
</div>