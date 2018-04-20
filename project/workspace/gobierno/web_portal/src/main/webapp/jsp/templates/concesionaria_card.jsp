<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATE -->
<div class="col-lg-6">
    <div class="input-group">
      <input id="searchConcesionariaText"  type="text" class="form-control" placeholder="   Search for...">
    </div>
</div>

<div id="concesionarias_cards"></div>


<script type="text/html" id="card" >

            <div class="card" id="{%- id %}">

                <img class="img-fluid"  src="https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20%2813%29.jpg" alt="Card image cap">
                <div class="card-block"  >
                    <h4 class="card-title"  > {%- name %}</h4>
                    <p class="card-text"  > {%- text %}</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                </div>
            </div>

</script>


<!-- /TEMPLATE -->








<!-- LOGIC -->


<script type="text/javascript">
function searchConcesionariaText(searchFor){

    let reals = concesionariasAPI.search(searchFor, 4);

    // Set the HTML template
    let card = _.template($('#card').html());

    $('#concesionarias_cards').empty();
    $('#concesionarias_cards').append('<div class="card-group">');

     reals.forEach(function(real, ind) {
         real.id = 'concesionaria_card_'+ind
         $('#concesionarias_cards').append("<div id='"+real.id+"'>" + card(real) + "</div>");
          $('#'+real.id).click(function(){

          alert("Clicked1!");
          moveMap(real.pos);
          });

      });


    $('#concesionarias_cards').append('</div>');
}







$('#searchConcesionariaText').keyup(function() {
    let searchFor = $(this).val();
    searchConcesionariaText(searchFor);
 });

$(() =>   {
 searchConcesionariaText("");
});



</script>


<!-- /LOGIC -->
