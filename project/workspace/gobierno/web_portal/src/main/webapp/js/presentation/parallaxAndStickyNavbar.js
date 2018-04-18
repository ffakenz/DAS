$(() =>   {





/* Smooth Scroll on clicking nav items*/
jQuery('nav a[href^="#"]').click( function(e) {
    $('html,body').animate({ scrollTop: jQuery(this.hash).offset().top - 150}, 1000);
    return false;
});



/* back to top*/
$('#toTop a').click(() =>  {
  $('body').animate({
    scrollTop: 0
  }, 1000);

  return false;
});


/* back to top*/
$('#toTop a').click(() =>   {
  $('body').animate({
    scrollTop: 0
  }, 1000);
  return false;
});

/* Parallaxing + add class active on scroll*/
$(document).scroll(() =>   {

  /* parallaxing*/
  const $movebg = $(window).scrollTop() * -0.3;
  $('.portion').css('background-positionY', ($movebg) + 'px');


   /* add class active to nav a on scroll*/
  const scrollPos = $(document).scrollTop() + 100;
  $('nav a').each(() =>   {

  });

  /*changing padding of nav a on scroll*/
    if (scrollPos / $(window).height() > 0.25) {
      $('nav a').addClass('small');
      $('nav img').addClass('move');
      $('nav span').removeClass('movetext');
    } else {
      $('nav a').removeClass('small');
      $('nav img').removeClass('move');
      $('nav span').addClass('movetext');
    }

});



});