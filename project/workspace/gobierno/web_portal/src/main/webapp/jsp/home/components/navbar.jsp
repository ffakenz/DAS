<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
%>


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
   <%@include file="./login_modal.jsp" %>



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