<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"


%>


<header>
  <nav>


    <span>
        <div class="nav navbar-left" >
            <button class="btn btn-info" data-toggle="collapse" data-target="#languageNavbar">Languages
            </button>
        </div>
        <div class="collapse navbar-collapse" id="languageNavbar">
            <ul class="nav navbar-left" >
                <li>  <input type="image" src="/web_portal/img/i18n/spanish.png"  onclick="javascript:translator.setIdioma('es');" style="width:30px;height:30px;"/></li>
                <li>  <input type="image" src="/web_portal/img/i18n/english.png"  onclick="javascript:translator.setIdioma('en');" style="width:30px;height:30px;"/></li>
                <a class="navbar-brand" href="#"><strong><fmt:message key="home_cabecera" bundle="${etq}" /></strong></a>
            </ul>
        </div>
    </span>



    <%
    String language = (session.getAttribute("idioma").toString().equals("en"))? "english" : "spanish";
    String languageLogo = "/web_portal/img/i18n/"+language+".png";
    %>

    <img src='<%=languageLogo%>'>

<!-- Trigger the modal with a button -->
   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>


    <a href="#portion1">Portion1</a>
    <a href="#portion2">Portion2</a>
  </nav>
</header>

<!-- this is important. Do not delete. Doing so breaks login modal. -->
<%@include file="./login_modal.jsp" %>

<!-- this is important. Do not delete. Doing so breaks translation. -->
<div id="resultIdioma"></div>




