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


        <!-- Your custom styles (optional) -->
        <!-- this had NOTHING in it ???  -->
        <!-- link href="css/style.css" rel="stylesheet" -->

        <style>
        /* TEMPLATE STYLES */
        main {
         padding-top: 3rem;
         padding-bottom: 2rem;
        }
        .extra-margins {
         margin-top: 1rem;
         margin-bottom: 2.5rem;
        }
        </style>
        <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=page,messages" />

        <script type="text/javascript"
            src="/web_portal/util/Javascript.do/load=util/utils,util/other,login/login">
         </script>

    </head>

       <body>
         <div id="login-form">

                     <h1><fmt:message key="login_cabecera" bundle="${etq}" /></h1>

                     <fieldset>

                         <form id="formulario" action="javascript:login.validarUsuario();" method="post">

                             <div class="md-form">
                               <input type="text" name="usuario" id="usuario" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />">
                             </div>

                             <div class="md-form">
                                  <input type="password" name="clave" id="clave" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />">
                             </div>

                                          <div class="text-center">
                                             <button type="submit" class="btn btn-default" > <fmt:message key="login_ingresar" bundle="${etq}" /> </button>

                                          </div>

                             <!--

                             <footer class="clearfix">

                                 <p> <span class="info"> ? </span>

                                 <a href="#"> ¿Olvidó su contraseña? </a> </p>

                             </footer>

                             -->

                         </form>

                     </fieldset>

                 </div>

                 <div id="resultado">

         	        <div id="mensaje"></div>

         	        <div id="error"></div>

                 </div>
       </body>




  <%@include file="../footer/footer.jsp" %>
</html>
