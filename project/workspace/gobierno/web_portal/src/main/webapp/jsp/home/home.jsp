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

        <!-- Latest compiled and minified CSS
                    ---------------
                WHY DID I USE A CDN?
                See this:
                https://imgur.com/HMzVHjF


                And this is why I use CDN too!
                https://imgur.com/dwO1ffI
        -->

       <!-- Font Awesome -->
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.1/css/mdb.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome-animation/0.0.10/font-awesome-animation.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="shortcut icon" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/favicon.ico">

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



         <!-- SCRIPTS -->
         <!-- JQuery -->
         <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
         <!-- JQuery.i18n alongside with utils and login-->
         <script type="text/javascript" src="/web_portal/util/Javascript.do/load=jquery.i18n.properties,utils,login"> </script>

       </head>

       <body>




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
                 <input type="image" src="/web_portal/img/i18n/spanish.png"  onclick="javascript:alert('you clicked spanish!');administradores.setIdioma('es');" style="width:60px;height:60px;"/>

                 <!-- Commented out <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a> -->
                 </li>
                 <li class="nav-item">
                    <input type="image" src="/web_portal/img/i18n/english.png"  onclick="javascript:alert('you clicked english!');administradores.setIdioma('en');" style="width:60px;height:60px;"/>
                 </li>
                 <li class="nav-item">
                   <a class="nav-link" href="#">Pricing</a>
                 </li>
                 <li class="nav-item">
                   <a class="nav-link" href="#">Opinions</a>
                 </li>
               </ul>
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
               <div class="col-md-7">
                 <!--Featured image -->
                 <div class="view overlay hm-white-light z-depth-1-half">
                   <img src="https://mdbootstrap.com/images/proffesions/slides/socialmedia/img%20(2).jpg" class="img-fluid " alt="">
                   <div class="mask">
                   </div>
                 </div>
                 <br>
               </div>

               <!--Main information-->
               <div class="col-md-5">
                 <h2 class="h2-responsive wow fadeInDown">MDBoostrap CDN Starter Template <small>by Mr. M.</small></h2>
                 <hr>
                 <p>

                   <ul class="wow fadeInUp">
                     <li>This is a starter template using CDN (Content Delivery Network). </li>
                     <li class="red-text">It's a great template to start learning <a href="https://mdbootstrap.com/">MDBootstrap</a>.</li>
                     <li>Everything you need to get started is in a <strong>single</strong> HTML page.</li>
                     <li><a href="https://www.thoughtco.com/content-delivery-network-3469509">Why use CDN's ?</a></li>
                   </ul>
                   <a href="https://gist.github.com/mrmccormack/1c3ba355f020d9901f0bb5af25f871f2" class="btn btn-primary">Get it now from GitHub.com!</a>
               </div>
             </div>
             <!--/.First row-->

             <hr class="extra-margins">

             <!--Second row-->
             <div class="row">
               <!--First columnn-->
               <div class="col-md-4">
                 <!--Card-->
                 <div class="card">

                   <!--Card image-->
                   <div class="view overlay hm-white-slight">
                     <img src="/web_portal/img/concesionarias/placer.jpg" class="img-fluid" alt="">
                     <a href="#">
                       <div class="mask"></div>
                     </a>
                   </div>
                   <!--/.Card image-->

                   <!--Card content-->
                   <div class="card-block p-3">
                     <!--Title-->
                     <h4 class="card-title">Concesionaria 1</h4>
                     <!--Text-->
                     <p class="card-text">La concesionaria 1 se caracteriza por el importe de autos de lujo al país.</p>
                     <a href="#" class="btn btn-primary">Read more</a>
                   </div>
                   <!--/.Card content-->

                 </div>
                 <!--/.Card-->
               </div>
               <!--First columnn-->

               <!--Second columnn-->
               <div class="col-md-4">
                 <!--Card-->
                 <div class="card">

                   <!--Card image-->
                   <div class="view overlay hm-white-slight">
                     <img src="/web_portal/img/concesionarias/negocio.jpg" class="img-fluid" alt="">
                     <a href="#">
                       <div class="mask"></div>
                     </a>
                   </div>
                   <!--/.Card image-->

                   <!--Card content-->
                   <div class="card-block p-3">
                     <!--Title-->
                     <h4 class="card-title">Concesionaria 2</h4>
                     <!--Text-->
                     <p class="card-text">La concesionaria 2 se caracteriza por la venta de vehículos orientados a el transporte de escala industrial.</p>
                     <a href="#" class="btn btn-primary">Read more</a>
                   </div>
                   <!--/.Card content-->

                 </div>
                 <!--/.Card-->
               </div>
               <!--Second columnn-->


             </div>
             <!--/.Second row-->
           </div>
           <!--/.Main layout-->

         </main>

         <div class="container">

           <!-- Example row of columns -->
           <div class="row">
             <div class="col-md-3">
             </div>

             <div class="col-md-6">
               <!-- Form login -->
               <form>
                 <p class="h5 text-center mb-4">Sample Form</p>

                 <div class="md-form">
                   <i class="fa fa-envelope prefix grey-text"></i>
                   <input type="text" id="defaultForm-email" class="form-control">
                   <label for="defaultForm-email">Your email</label>
                 </div>

                 <div class="md-form">
                   <i class="fa fa-lock prefix grey-text"></i>
                   <input type="password" id="defaultForm-pass" class="form-control">
                   <label for="defaultForm-pass">Your password</label>
                 </div>

                 <div class="text-center">
                   <button class="btn btn-default">Login</button>
                 </div>
               </form>
               <!-- Form login -->


             </div>

             <div class="col-md-3">

             </div>

           </div>
           </div>

         <!--Footer-->
         <footer class="page-footer primary-color-dark center-on-small-only">

           <!--Footer Links-->
           <div class="container">
             <div class="row">

               <!--First column-->
               <div class="col-md-6">
                 <h5 class="title">Footer Content</h5> &copy; 2017 <a href="https://mdbootstrap.com"> MDBootstrap.com </a>
                 <p>
                   Built with <i style="color:red;" class="fa fa-heart faa-pulse animated"></i> by Rob McCormack on Github.com <br>
                   <a href="https://validator.w3.org/nu/?doc=http://mrmccormack.com/mdbootstrap-template-cdn.html" target="_blank"><small>• HTML valid • </small></a>
                 </p>
               </div>
               <!--/.First column-->

               <!--Second column-->
               <div class="col-md-6">
                 <h5 class="title">Links</h5>
                 <ul>
                   <li><a href="http://mrmccormack.com/mdbootstrap-template-cdn.html">This page on GitHub</a></li>
                   <li><a href="#!">Link 2</a></li>
                   <li><a href="#!">Link 3</a></li>
                   <li><a href="#!">Link 4</a></li>
                 </ul>
               </div>
               <!--/.Second column-->
             </div>
           </div>
           <!--/.Footer Links-->


         </footer>
         <!--/.Footer-->


         <!-- SCRIPTS -->
         <!-- Bootstrap tooltips -->
         <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
         <!-- Bootstrap core JavaScript -->
         <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
         <!-- MDB core JavaScript -->
         <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.1/js/mdb.min.js"></script>

         <script>
           wow = new WOW().init();
         </script>
       </body>

</html>