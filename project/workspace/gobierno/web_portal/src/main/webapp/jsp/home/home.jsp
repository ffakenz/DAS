<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>
<html>
    <%@include file="../commons/head.jsp" %>
    <body>
        <div class="content-body">
            <div id="lang_div">
                <input id="es_lang_btn" type="button" value="es" />
                <input id="en_lang_btn" type="button" value="en" />
            </div>
            <%@include file="../commons/header.jsp"%>
            <!-- concesionarias aprobadas -->
            <div class="jumbotron">
                <h1 class="display-3"><fmt:message key="concesionarias_disponibles" bundle="${etq}" /></h1>
                <hr class="my-4">
                <div id="conc_aprobadas_div"></div>
                <br><hr class="my-4"><br>
                <p class="lead">
                    Si querés formar parte de nuestro programa, puedes hacerlo desde
                    <a href="/web_portal/home/ShowRegistrarConcesionaria.do" name="registrarConcesionaria">
                        aquí
                    </a>
                </p>
            </div>
            <!-- carousel -->
            <%@include file="./carousel_home.jsp"%>

            <div class="nuestros_partners_div">
                <h1 class="display-3">Nuestros partners</h1>
                <hr class="my-4">
                <div class="marcas_partners" style="border-right: 2px solid #eee">
                    <div class="logos_marcas">
                        <img src="/web_portal/img/ford.png"  style="width:100% !important">
                    </div>
                    <div class="logos_marcas">
                        <h2>FORD</h2>
                    </div>
                </div>
                <div class="marcas_partners" style="border-right: 2px solid #eee">
                    <div class="logos_marcas">
                        <img src="/web_portal/img/nissan.png"  style="width:100% !important">
                    </div>
                    <div class="logos_marcas">
                        <h2>NISSAN</h2>
                    </div>
                </div>
                <div class="marcas_partners">
                    <div class="logos_marcas">
                        <img src="/web_portal/img/volkswagen.png" style="width:100% !important">
                    </div>
                    <div class="logos_marcas">
                        <h2>VOLKSWAGEN</h2>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="../commons/footer.jsp"%>
    
    <!-- Home Slider -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</html>
