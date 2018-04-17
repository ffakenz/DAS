<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
%>




<header>
  <nav>
    <span class="movetext">Company Name</span>
    <a class="navbar-brand" href="#"><strong><fmt:message key="home_cabecera" bundle="${etq}" /></strong></a>

    <img class="" src="https://www.matchbook.com/assets/images/icons/white/Twitter_logo_white.png">



    <!--
<jsp:include page="../reusable/collapsable.jsp" >
  <jsp:param name="data_target" value="LanguageMenu" />
  <jsp:param name="html_content" value="<h1>LanguageMenu</h1>" />
  <jsp:param name="button_text" value="Collapsible" />
</jsp:include>

-->


  <a href="#demo" class="btn btn-info" data-toggle="collapse">Simple collapsible</a>
  <div id="demo" class="collapse">
    Lorem ipsum dolor sit amet, consectetur adipisicing elit,
    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
  </div>


		<div class="container">
            <div class="navbar-header">
				<button class="btn btn-info" data-toggle="collapse" data-target="#mynavbar">Languages
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
            </div>
			<div class="collapse navbar-collapse" id="mynavbar">
				<ul class="nav navbar-nav navbar-left" >
					<li><a href="">Home</a></li>
					<li><a href="">about</a></li>
					<li><a href="">contact</a></li>
					<li><a href="">search</a></li>
				</ul>
			</div>
		</div>




 <!-- Trigger the modal with a button -->
   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
   <%@include file="./login_modal.jsp" %>


    <a href="#portion1" class="active">Portion1</a>
    <a href="#portion2">Portion2</a>
    <a href="#portion3">Portion3</a>
    <a href="#portion4">Portion4</a>
  </nav>
</header>


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
