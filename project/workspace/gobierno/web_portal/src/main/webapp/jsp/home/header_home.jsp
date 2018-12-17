<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header_home">
    <header>
        <c:choose>
            <c:when test="${sessionScope.ssid != null}">
                <button id="logout" name="logout" class="btn btn-secondary" >Cerrar Sesion</button>
                <button id="goToProfile" name="goToProfile" class="btn btn-secondary" >Profile</button>
            </c:when>
            <c:otherwise>
                <button id="login" name="login" class="btn btn-primary">Login</button>
            </c:otherwise>
        </c:choose>
    </header>
</div>