<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var = "js_import" value="/web_portal/util/Javascript.do/load="/>
<c:set var = "uri" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<%-- HOME --%>
<c:set var = "home_config" value="own_libraries/home/home_config"/>
<c:set var = "home_service" value="own_libraries/home/home_service"/>
<c:set var = "home_module" value="own_libraries/home/home_module"/>
<c:set var = "home_loader" value="own_libraries/home/home_loader"/>
<%-- LOGIN --%>
<c:set var = "login_config" value="own_libraries/login/login_config"/>
<c:set var = "login_module" value="own_libraries/login/login_module"/>
<%-- CONCESIONARIAS --%>
<c:set var = "concesionarias_config" value="own_libraries/concesionarias/concesionarias_config"/>
<c:set var = "concesionarias_helpers" value="own_libraries/concesionarias/concesionarias_helpers"/>
<c:set var = "concesionarias_config_param" value="own_libraries/concesionarias/config_param"/>
<c:set var = "concesionarias_service" value="own_libraries/concesionarias/concesionarias_service"/>
<c:set var = "concesionarias_module" value="own_libraries/concesionarias/concesionarias_module"/>
<%-- ADMIN --%>
<c:set var = "admin_config" value="own_libraries/admin/admin_config"/>
<c:set var = "admin_service" value="own_libraries/admin/admin_service"/>
<c:set var = "admin_module" value="own_libraries/admin/admin_module"/>
<c:set var = "admin_loader" value="own_libraries/admin/admin_loader"/>
<c:set var = "admin_estado_cuenta_service" value="own_libraries/admin/estado_cuenta/estado_cuenta_service"/>
<%-- CALENDARIO --%>
<c:set var = "calendario_config" value="own_libraries/admin/calendario/calendario_config"/>
<c:set var = "calendario_module" value="own_libraries/admin/calendario/calendario_module"/>
<c:set var = "calendario_service" value="own_libraries/admin/calendario/calendario_service"/>
<%-- CONSUMO --%>
<c:set var = "consumo_service" value="own_libraries/admin/jobs/consumo_service"/>

<c:choose>
    <c:when test="${(uri == '/web_portal/') or (uri == '/web_portal/home/Home.do')}">
        <script src="${js_import}${home_config},${home_service},${home_module},${home_loader},${login_config},${login_module}">
        </script>
    </c:when>
    <c:when test="${(uri == '/web_portal/home/ShowRegistrarConcesionaria.do')}">

        <script src="${js_import}${home_config},${home_service},${home_module},${login_config},${login_module},${concesionarias_config},${concesionarias_helpers},${concesionarias_config_param},${concesionarias_service},${concesionarias_module}">
        </script>
    </c:when>

    <c:when test="${uri == '/web_portal/home/ShowLogin.do'}">
        <script src="${js_import}${home_config},${home_service},${home_module},${login_config},${login_module}">
        </script>
    </c:when>
    <c:when test="${uri == '/web_portal/usuarios/RegistrarConsumer.do'}">
        <script src="${js_import}${home_config},${home_service},${home_module},${login_config},${login_module}">
        </script>
    </c:when>

    <c:when test="${uri == '/web_portal/usuarios/ShowPrimerIngreso.do'}">
        <script src="${js_import}${home_config},${home_service},${home_module},${login_config},${login_module}">
        </script>
    </c:when>

    <c:when test="${uri == '/web_portal/concesionarias/RegistrarConcesionaria.do'}">
        <script src="${js_import}${home_config},${home_module},${login_config},${login_module}  ">
        </script>
    </c:when>

    <c:when test="${(uri == '/web_portal/login/Login.do') and (sessionScope.forwardName == 'success_gobierno')}">
        <script src="/web_portal/util/Javascript.do/load=external_libraries/jquery.i18n.properties.min.js"></script>
        <script src="${js_import}${home_config},${home_service},${home_module},${login_config},${login_module},${concesionarias_config},${concesionarias_helpers},${concesionarias_config_param},${concesionarias_service},${concesionarias_module},${concesionarias_loader},${calendario_config},${calendario_module},${calendario_service},${consumo_service},${admin_config},${admin_service},${admin_estado_cuenta_service},${admin_module},${admin_loader}">
        </script>
    </c:when>

    <c:when test="${(uri == '/web_portal/login/Login.do') and (sessionScope.forwardName == 'success_consumer')}">
        <script src="${js_import}${home_config},${home_service},${home_module},${login_config},${login_module}">
        </script>
    </c:when>

    <c:otherwise></c:otherwise>
</c:choose>