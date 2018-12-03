<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <head>

        <%@include file="../header/header.jsp" %>

    </head>

    <body>
        <div>
            <form class="form-horizontal" id="formConcesionarias" method="post" action="javascript:concesionarias.sendForm();">
                <fieldset>

                <!-- Form Name -->
                <legend>Registracion concesionarias</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nombre">Nombre</label>
                  <div class="col-md-4">
                  <input id="nombre" name="nombre" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="direccion">Direccion</label>
                  <div class="col-md-4">
                    <input id="direccion" name="direccion" type="text" placeholder="" class="form-control input-md">
                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="cuit">CUIT</label>
                  <div class="col-md-4">
                  <input id="cuit" name="cuit" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="tel">Telefono</label>
                  <div class="col-md-4">
                  <input id="tel" name="tel" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="email">Email</label>
                  <div class="col-md-4">
                  <input id="email" name="email" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="btn_send_form"></label>
                  <div class="col-md-4">
                    <button id="btn_send_form" name="btn_send_form" class="btn btn-primary">Enviar</button>
                  </div>
                </div>

                </fieldset>

            </form>

            <button id="login" name="login" class="btn btn-primary" onclick="javascript:home.goToHome();">Volver al home</button>

            <div id="resultado">

                <div id="mensaje"></div>

                <div id="error"></div>

            </div>
        </div>

    </body>

    <%@include file="../footer/scripts_import.jsp"%>

</html>