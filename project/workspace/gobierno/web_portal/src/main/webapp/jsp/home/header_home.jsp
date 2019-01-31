<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header_home">
    <header>
        <c:choose>
            <c:when test="${sessionScope.ssid != null}">
                <button id="logout_btn" name="logout" class="btn btn-secondary" >Cerrar Sesion</button>
                <button id="go_to_profile_btn" name="goToProfile" class="btn btn-secondary" >Profile</button>
            </c:when>
            <c:otherwise>
                <button id="show_login_btn" name="login" class="btn btn-primary">Login</button>
            </c:otherwise>
        </c:choose>
    </header>
</div>