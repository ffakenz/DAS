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

    <%@ include file="../commons/header.jsp"%>

    <!-- Form Name -->
    <div class="page-header text-center">
        <h1>Registracion concesionaria</h1>
    </div>

    <div class="container">
        <fieldset>

            <form class="form-horizontal" id="formConcesionarias" method="post">

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="nombre">Nombre</label>
                    <input id="nombre" name="nombre" type="text" placeholder="" value="1" class="form-control form-control-lg" required="">
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="direccion">Direccion</label>
                    <input id="direccion" name="direccion" type="text" value="xxx" placeholder="" class="form-control form-control-lg">
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="cuit">CUIT</label>
                    <input id="cuit" name="cuit" type="text" placeholder="" value="111" class="form-control form-control-lg" required="" onkeypress="javascript:return jUtils.validNum();">
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="tel">Telefono</label>
                    <input id="tel" name="tel" type="text" placeholder="" value="111111" class="form-control form-control-lg" required="" onkeypress="javascript:return jUtils.validNum();">
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">Email</label>
                    <input id="email" name="email" type="text" placeholder="" value="email@email.com" class="form-control form-control-lg" required="">
                </div>
            </form>
        </fieldset>

        <button id="btn_send_form" name="btn_send_form" class="btn btn-outline-primary btn-lg btn-block" onclick="javascript:concesionarias.sendForm();">Enviar info</button>
    </div>

</body>

<%@include file="../commons/footer.jsp"%>

</html>