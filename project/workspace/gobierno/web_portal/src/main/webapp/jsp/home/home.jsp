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

    </head>

       <body>
         <%@include file="./body.jsp" %>
       </body>


</html>
