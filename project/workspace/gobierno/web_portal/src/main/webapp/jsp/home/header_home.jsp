<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header_home">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <c:choose>
                        <c:when test="${sessionScope.ssid != null}">
                            <a id="logout_btn" name="logout" class="nav-link" >Cerrar Sesion</a>
                            <a id="go_to_profile_btn" name="goToProfile" class="btn btn-secondary" >Profile</a>
                        </c:when>
                        <c:otherwise>
                            <a id="show_login_btn" name="login" class="nav-link">Login</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contacto</a>
                </li>
            </ul>
        </div>
    </nav>
</div>