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

        <form class="form-horizontal" id="formConcesionarias" method="post" action="javascript:concesionarias.validateForm();">
            <fieldset>

            <!-- Form Name -->
            <legend>Registracion concesionarias</legend>

            <!-- Text input-->
            <div class="form-group">
              <label class="col-md-4 control-label" for="name">Nombre</label>
              <div class="col-md-4">
              <input id="name" name="name" type="text" placeholder="" class="form-control input-md" required="">

              </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
              <label class="col-md-4 control-label" for="codigo">Codigo</label>
              <div class="col-md-4">
              <input id="codigo" name="codigo" type="text" placeholder="" class="form-control input-md">

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
              <label class="col-md-4 control-label" for="telefono">Telefono</label>
              <div class="col-md-4">
              <input id="telefono" name="telefono" type="text" placeholder="" class="form-control input-md" required="">

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

    </body>

    <%@include file="../footer/footer.jsp" %>


</html>