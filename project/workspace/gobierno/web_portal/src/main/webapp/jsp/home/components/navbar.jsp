<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
%>




<header>
  <nav>


    <span>
        <div class="nav navbar-left" >
            <button class="btn btn-info" data-toggle="collapse" data-target="#mynavbar">Languages
            </button>
        </div>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="nav navbar-left" >
                <li>  <input type="image" src="/web_portal/img/i18n/spanish.png"  onclick="javascript:translator.setIdioma('es');" style="width:30px;height:30px;"/></li>
                <li>  <input type="image" src="/web_portal/img/i18n/english.png"  onclick="javascript:translator.setIdioma('en');" style="width:30px;height:30px;"/></li>
                <a class="navbar-brand" href="#"><strong><fmt:message key="home_cabecera" bundle="${etq}" /></strong></a>
            </ul>
        </div>
    </span>


    <!--
<jsp:include page="../reusable/collapsable.jsp" >
  <jsp:param name="data_target" value="LanguageMenu" />
  <jsp:param name="html_content" value="<h1>LanguageMenu</h1>" />
  <jsp:param name="button_text" value="Collapsible" />
</jsp:include>

-->







 <!-- Trigger the modal with a button -->
   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>


    <a href="#portion1" class="active">Portion1</a>
    <a href="#portion2">Portion2</a>
    <a href="#portion3">Portion3</a>
    <a href="#portion4">Portion4</a>
  </nav>
</header>

  <%@include file="./login_modal.jsp" %>

<!-- this is important. Do not delete. Doing so breaks translation. -->
<div id="resultIdioma"></div>





<script>






// Smooth Scroll on clicking nav items
jQuery('nav a[href^="#"]').click(function(e) {
    jQuery('html,body').animate({ scrollTop: jQuery(this.hash).offset().top}, 1000);
    return false;
});



// back to top
$('#toTop a').click(function () {
  $('body').animate({
    scrollTop: 0
  }, 1000);
  return false;
});

// Smooth Scroll on clicking nav items
$('nav a').click(function () {
  var $href = $(this).attr('href');
  $('body').stop().animate({
    scrollTop: $($href).offset().top
  }, 1000);
  return false;
});

// back to top
$('#toTop a').click(function () {
  $('body').animate({
    scrollTop: 0
  }, 1000);
  return false;
});

// Parallaxing + add class active on scroll
$(document).scroll(function () {

  // parallaxing
  var $movebg = $(window).scrollTop() * -0.3;
  $('.portion').css('background-positionY', ($movebg) + 'px');


   // add class active to nav a on scroll
  var scrollPos = $(document).scrollTop() + 100;
  $('nav a').each(function () {

  });

  //changing padding of nav a on scroll
    if (scrollPos > 250) {
      $('nav a').addClass('small');
      $('nav img').addClass('move');
      $('nav span').removeClass('movetext');
    } else {
      $('nav a').removeClass('small');
      $('nav img').removeClass('move');
      $('nav span').addClass('movetext');
    }

});
</script>


<!-- stickyNavbar and parallax -->
   <script type="text/javascript"
            src="/web_portal/util/Javascript.do/load=presentation/parallaxAndStickyNavbar">
         </script>
