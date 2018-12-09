<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <c:choose>
        <c:when test="${sessionScope.ssid != null}">
            <button id="logout" name="logout" class="btn btn-secondary" onclick="javascript:logout.closeSession();">Cerrar Sesion</button>
        </c:when>
        <c:otherwise>
            <button id="login" name="login" class="btn btn-primary" onclick="javascript:home.showLogin();">Login</button>
        </c:otherwise>
    </c:choose>
</header>