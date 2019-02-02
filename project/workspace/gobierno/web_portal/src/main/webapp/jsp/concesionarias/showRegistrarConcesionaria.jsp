<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../commons/head.jsp" %>
</head>
<body>
    <%@include file="../commons/header.jsp"%>
    <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=loginbox" />
    </style>
    <div class="loginbox">
        <img src="/web_portal/img/login/avatar.png" class="avatar"/>
        <h1>Registracion concesionaria</h1>
        <form class="form-horizontal" id="form_registrar_concesionaria" method="post"/>
            <p>Nombre</p>
            <input id="nombre" name="nombre" type="text" placeholder="" value="Concesionaria Fiat" required=""/>
            <p>Direccion</p>
            <input id="direccion" name="direccion" type="text" value="Aca en la China" placeholder="" />
            <p>CUIT</p>
            <input id="cuit" name="cuit" type="text" placeholder="" value="20-93337511-1" required="" onkeypress="javascript:return jUtils.validNum();"/>
            <p>Telefono</p>
            <input id="tel" name="tel" type="text" placeholder="" value="3513059165"  required="" onkeypress="javascript:return jUtils.validNum();"/>
            <p>Email</p>
            <input id="email" name="email" type="text" placeholder="" value="email@email.com" required=""/>
            <input id="concesionaria_registrar_send_form_btn" type="submit" name="btn_send_form" value="Enviar info"/>
        </form>
    </div>
</body>
<%@include file="../commons/footer.jsp"%>
<%@include file="../js_imports/home.jsp"%>
<%@include file="../js_imports/login.jsp"%>
<%@include file="../js_imports/concesionarias.jsp"%>
</html>