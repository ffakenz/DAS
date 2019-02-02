<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header_home">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav mr-auto">
                <c:choose>
                    <c:when test="${sessionScope.ssid != null}">
                        <li class="nav-item active">
                            <a id="logout_btn" name="logout" class="nav-link" >Cerrar Sesion</a>
                        </li>
                        <li class="nav-item active">
                            <a id="go_to_profile_btn" name="goToProfile" class="btn btn-secondary" >Profile</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item active">
                            <a id="show_login_btn" name="login" class="nav-link">Login</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/web_portal/home/ShowRegistrarConcesionaria.do'}">
                        <li class="nav-item">
                            <div class="collapse navbar-collapse" id="navbarColor01">
                                <div id="registrar_concesionaria_div">
                                    <button id="registrar_concesionaria_btn" name="registrarConcesionaria" class="btn btn-primary float-right">Registrar Concesionaria</button>
                                </div>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${(requestScope['javax.servlet.forward.request_uri'] != '/web_portal/home/Home.do') and (requestScope['javax.servlet.forward.request_uri'] != '/web_portal/')}">
                        <li class="nav-item">
                            <div class="collapse navbar-collapse" id="navbarColor01">
                                <div id="go_to_home_div">
                                    <button id="go_to_home_btn" name="login" class="btn btn-primary float-right" >Volver al home</button>
                                </div>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</div>