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



        <div id="resultIdioma"></div>




         <!--Main Navigation-->
         <header>


           <nav class="navbar navbar-expand-lg navbar-dark primary-color-dark">
             <a class="navbar-brand" href="#"><strong><fmt:message key="home_cabecera" bundle="${etq}" /></strong></a>
             <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                     <span class="navbar-toggler-icon"></span>
                 </button>
             <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav mr-auto">
                 <li class="nav-item active">
                 <input type="image" src="/web_portal/img/i18n/spanish.png"  onclick="javascript:translator.setIdioma('es');" style="width:60px;height:60px;"/>


                 <!-- Commented out <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a> -->
                 </li>
                 <li class="nav-item">
                    <input type="image" src="/web_portal/img/i18n/english.png"  onclick="javascript:translator.setIdioma('en');" style="width:30px;height:30px;"/>
                 </li>
               </ul>

               <!-- Trigger the modal with a button -->
               <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>


                        <!-- Login Modal -->
                        <div id="myModal" class="modal fade" role="dialog">
                          <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                              <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Modal Header</h4>
                              </div>
                              <div class="modal-body">

                                    <%@include file="../login/login.jsp" %>







                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                              </div>
                            </div>

                          </div>
                        </div>


               <ul class="navbar-nav nav-flex-icons">
                 <li class="nav-item">
                   <a class="nav-link"><i class="fa fa-facebook"></i></a>
                 </li>
                 <li class="nav-item">
                   <a class="nav-link"><i class="fa fa-twitter"></i></a>
                 </li>
                 <li class="nav-item">
                   <a class="nav-link"><i class="fa fa-instagram"></i></a>
                 </li>
               </ul>

             </div>
           </nav>

         </header>
         <!--Main Navigation-->

         <main>

           <!--Main layout-->
           <div class="container">
             <!--First row-->
             <div class="row">


<!--Carousel Wrapper-->
<div id="carousel-example-1z" class="carousel slide carousel-fade" data-ride="carousel">
    <!--Indicators-->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-1z" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-1z" data-slide-to="1"></li>
        <li data-target="#carousel-example-1z" data-slide-to="2"></li>
    </ol>
    <!--/.Indicators-->
    <!--Slides-->
    <div class="carousel-inner" role="listbox">
        <!--First slide-->
        <div class="carousel-item active"> <!--Mask color-->
            <div class="view">
              <img src="/web_portal/img/concesionarias/1.jpg" class="img-fluid " alt="First slide">
              <!-- maybe add a filter? <div class="mask rgba-black-light"></div> -->
            </div>
            <div class="carousel-caption">
               <h3 class="h3-responsive">Banco Naciòn Lorem Ipsum</h3>
               <div class="mask rgba-black-light">
                    <p> Cras dapibus. Vivamus elementum semper nisi. Gobierno Argentino Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue.  </p>
               </div>
            </div>
        </div>
        <!--/First slide-->
        <!--Second slide-->
        <div class="carousel-item">
            <img class="d-block w-100" src="/web_portal/img/concesionarias/2.jpg" alt="Second slide">
            <div class="carousel-caption">
                <h3 class="h3-responsive">Sorteo Lorem Ipsum</h3>
                <div class="mask rgba-black-strong">
                    <p>Lorem ipsum dolor sit amet Sorteo, consectetuer adipiscing elit. Todos los meses Aenean commodo ligula eget dolor. Aenean massa. 0 KM. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. </p>
                </div>
            </div>
        </div>
        <!--/Second slide-->
        <!--Third slide-->
        <div class="carousel-item">
            <img class="d-block w-100" src="/web_portal/img/concesionarias/3.png" alt="Third slide">
            <div class="carousel-caption">
                <h3 class="h3-responsive">Sorteo Lorem Ipsum</h3>
                <div class="mask rgba-black-strong">
                    <p>Lorem ipsum dolor sit amet Sorteo, consectetuer adipiscing elit. Todos los meses Aenean commodo ligula eget dolor. Aenean massa. 0 KM. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. </p>
                </div>
            </div>
        </div>
        <!--/Third slide-->
    </div>
    <!--/.Slides-->
    <!--Controls-->
    <a class="carousel-control-prev" href="#carousel-example-1z" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carousel-example-1z" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
    <!--/.Controls-->
</div>
<!--/.Carousel Wrapper-->


























             <!--/.First row-->

             <hr class="extra-margins">

             <!--Second row-->

             <!--/.Second row-->

           </div>
           <!--/.Main layout-->

         </main>







  <%@include file="../footer/footer.jsp" %>









</html>
