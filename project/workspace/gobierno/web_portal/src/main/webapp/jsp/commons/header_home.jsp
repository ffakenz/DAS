<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <c:choose>
        <c:when test="${sessionScope.ssid != null}">
            <button id="logout" name="logout" class="btn btn-secondary" onclick="logout.closeSession();">Cerrar Sesion</button>
            <button id="goToProfile" name="goToProfile" class="btn btn-secondary" onclick="home.goToProfile();">Profile</button>
        </c:when>
        <c:otherwise>
            <button id="login" name="login" class="btn btn-primary" onclick="home.showLogin();">Login</button>
        </c:otherwise>
    </c:choose>
</header>