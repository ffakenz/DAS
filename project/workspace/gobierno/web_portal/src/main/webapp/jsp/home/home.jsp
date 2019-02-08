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
            <div class="card">
                <div class="card-header">
                    <h2 class="title" >Los más vendidos</h2>
                </div>
            </div>
            <%@include file="./carousel_home.jsp"%>
            <HR>

            <div id="plan_incentivo_text" class="card">
                <div class="card-header">
                    <h2 class="card-title">¿Cómo funciona nuestro plan?</h2>
                </div>
                <div class="card-body">
                    <p class="card-text">El Gobierno Nacional relanzó el Plan Incentivo a la Compra de Autos 0KM, que contiene una serie de beneficios para los compradores y abarca a diversos modelos de taxis, autos particulares, vehículos comerciales, camiones y utilitarios. Uno de los objetivos principales de la medida es impulsar la industria automotriz argentina, ya que los vehículos que integran este régimen de financiamiento son unidades de producción MERCOSUR. </p>
                    <p class="card-text">Entre las novedades más importantes está la financiación del 100% del valor del vehículo que se pagará a 5 años, con entrega pactada en la sexta cuota. Las cuotas se ajustarán en función del incremento del valor del costo del auto adquirido durante la vigencia de la financiación. El precio final de la cuota incluye seguro de vida y costo administrativos.</p>
                    <p class="card-text">Para darle más impulso a la iniciativa se agregó el sorteo de la cancelación total de la deuda de aquel comprador que esté al día en sus cuotas y haya pagado más de 24 y menos de 36 cuotas inclusive. Cada sorteo se realizará mensualmente entre todos los compradores que hayan adquirido este plan.</p>
                    <h5 class="card-title">¡Que estás esperando para ir a comprar tu 0km!</h5>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h2 class="title" >Nuestros partners</h2>
                </div>
            </div>

            <div class="nuestros_partners_div">

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
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.1/js/mdb.min.js"></script>

    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</html>
