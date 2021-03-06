<%@ page

        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../commons/head.jsp" %>
    </head>
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=loginbox" />
            <div class="loginbox loginbox-registrar">
                <img src="/web_portal/img/login/avatar.png" class="avatar"/>
                <h1><fmt:message key="registrar_conc_h1" bundle="${etq}" /></h1>
                <form class="form-horizontal" id="form_registrar_concesionaria" method="post"/>
                    <label for="nombre"><fmt:message key="registrar_conc_nombre_lbl" bundle="${etq}" /></label>
                    <input id="nombre" name="nombre" type="text" placeholder="" value="Concesionaria Fiat" required=""/>
                    <label for="direccion"><fmt:message key="registrar_conc_dir_lbl" bundle="${etq}" /></label>
                    <input id="direccion" name="direccion" type="text" value="Aca en la China" placeholder="" />
                    <label for="cuit"><fmt:message key="registrar_conc_cuit_lbl" bundle="${etq}" /></label>
                    <input id="cuit" name="cuit" type="text" placeholder="" value="20-93337511-1" required=""/>
                    <label for="tel"><fmt:message key="registrar_conc_tel_lbl" bundle="${etq}" /></label>
                    <input id="tel" name="tel" type="text" placeholder="" value="3513059165"  required="" onkeypress="javascript:return jUtils.validNum();"/>
                    <label for="email"><fmt:message key="registrar_conc_email_lbl" bundle="${etq}" /></label>
                    <input id="email" name="email" type="text" placeholder="" value="email@email.com" required=""/>
                    <input id="concesionaria_registrar_send_form_btn" type="submit" name="btn_send_form"
                        value="<fmt:message key="registrar_conc_confirm_btn" bundle="${etq}" />" />
                </form>
            </div>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
</html>