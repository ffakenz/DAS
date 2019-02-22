<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<!DOCTYPE html>
<html lang="${lang}">
    <%@include file="../commons/head.jsp" %>
    <body>
        <div class="content-body">
            <%@include file="../commons/header.jsp"%>
            <!-- carousel -->
            <div class="card">
                <div class="card-header">
                    <h2 class="title" ><fmt:message key="title_the_most_sellers" bundle="${etq}" /></h2>
                </div>
            </div>
            <%@include file="./carousel_home.jsp"%>
            <HR>
            <div id="plan_incentivo_text" class="card">
                <div class="card-header">
                    <h2 class="card-title"><fmt:message key="plan_incentivo_card_title" bundle="${etq}" /></h2>
                </div>
                <div class="card-body">
                    <p class="card-text"><fmt:message key="plan_incentivo_p1" bundle="${etq}" /></p>
                    <p class="card-text"><fmt:message key="plan_incentivo_p2" bundle="${etq}" /></p>
                    <p class="card-text"><fmt:message key="plan_incentivo_p3" bundle="${etq}" /></p>
                    <h5 class="card-title"><fmt:message key="plan_incentivo_h5" bundle="${etq}" /></h5>
                </div>
            </div>
            <!-- concesionarias aprobadas -->
            <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=dataTables.bootstrap4.min" />
            <div class="card">
                <div class="card-header">
                    <h2 class="title" ><fmt:message key="concesionarias_disponibles" bundle="${etq}" /></h2>
                </div>
            </div>
            <div id="conc_aprobadas_div" class="my_container"></div>
            <!-- jumbotron -->
            <div class="jumbotron">
                <p class="lead">
                    <fmt:message key="paragraph_lead" bundle="${etq}" />
                    <a href="/web_portal/home/ShowRegistrarConcesionaria.do" name="registrarConcesionaria">
                        <fmt:message key="anchor_lead" bundle="${etq}" />
                    </a>
                </p>
            </div>
            <!-- Nuestros Partners -->
            <div class="card">
                <div class="card-header">
                    <h2 class="title" ><fmt:message key="our_partners" bundle="${etq}" /></h2>
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
</html>