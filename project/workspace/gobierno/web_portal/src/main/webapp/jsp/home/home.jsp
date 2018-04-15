<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <title><fmt:message key="home_titulo" bundle="${etq}" /></title>

        <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=page,messages" />

        <script type="text/javascript" src="/web_portal/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,login,bootstrap"> </script>

        <!-- Latest compiled and minified CSS
                    ---------------
                WHY DID I USE A CDN?
                See this:
                https://imgur.com/HMzVHjF
        -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">



    </head>
    
    <body>
    
    <input type="button" onclick="javascript:administradores.setIdioma('es');" value="<fmt:message key="espanol" bundle="${etq}" />"/>
    <input type="button" onclick="javascript:administradores.setIdioma('en');" value="<fmt:message key="ingles" bundle="${etq}" />"/>
    <div id="resultIdioma"></div>
                

            <h1><fmt:message key="home_cabecera" bundle="${etq}" /></h1>




<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="/web_portal/img/concesionarias/placer.jpg" alt="placer">
    </div>

    <div class="item">
      <img src="/web_portal/img/concesionarias/negocio.jpg" alt="negocio">
    </div>


  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

        
    </body>

</html>