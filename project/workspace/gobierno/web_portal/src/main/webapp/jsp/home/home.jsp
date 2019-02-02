<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>

<!DOCTYPE html>

<html>

    <%@include file="../commons/head.jsp" %>

    <body>

        <div class="content-body">

            <%@include file="../commons/header.jsp"%>

            <br><br>

            <%@include file="./carousel_home.jsp"%>



            <div class="page-header text-center">
                    <h3>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et Lorem ipsum dolor sit amet, consectetur
                        adipiscing elit, sed do eiusmod tempor incididunt ut labore etLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                    </h3>
            </div>

            <div class="page-header text-center">
                <h3>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et Lorem ipsum dolor sit amet, consectetur
                    adipiscing elit, sed do eiusmod tempor incididunt ut labore etLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                </h3>
            </div>

            <div class="page-header text-center">
                <h3>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et Lorem ipsum dolor sit amet, consectetur
                    adipiscing elit, sed do eiusmod tempor incididunt ut labore etLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                </h3>
            </div>

        </div>

    </body>

    <%@include file="../commons/footer.jsp"%>
    <%@include file="../js_imports/home.jsp"%>
    <%@include file="../js_imports/login.jsp"%>
</html>
