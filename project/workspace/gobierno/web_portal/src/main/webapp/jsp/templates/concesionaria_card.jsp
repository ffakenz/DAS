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
                    <h4 class="card-title"  > {%- title %}</h4>
                    <p class="card-text"  > {%- text %}</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                </div>
            </div>

</script>


<!-- /TEMPLATE -->








<!-- LOGIC -->


<script type="text/javascript">
function searchConcesionariaText(searchFor){

    var documents = [
                {
                "title": "rio primero",
                "text": "aca vende mucho peugeot",
                "pos": {lat:-31.3317205,lng: -63.622070199999996}
                },
                {
                "title": "rio segundo",
                "text": "aca se vende renault",
                "pos": {lat:-31.650785,lng: -63.90584530000001}
                },
                {
                "title": "rio tercero",
                "text": "aca re pegaron las hilux, de toyota",
                "pos": {lat:-32.1766541,lng: -64.2059244}
                },
                {
                "title": "rio cuarto",
                "text": "aca la gente anda en bondi",
                "pos": {lat:-33.1231585,lng: -64.3493441}
                },
                {
                "title": "rio quinto",
                "text": "Mucho cuatriciclo",
                "pos": {lat:-33.6541814,lng: -65.48783739999999}
                },
                {
                "title": "cordoba capital",
                "text": "aca la gente anda en bondi",
                "pos": {lat:-31.42008329999999,lng: -64.18877609999998}
                }
                ];

    let reals = concesionariasAPI.search(searchFor, documents, 4);

    // Set the HTML template
    let card = _.template($('#card').html());

    $('#concesionarias_cards').empty();
    $('#concesionarias_cards').append('<div class="card-group">');

     reals.forEach(function(real, ind) {
         real.id = 'concesionaria_card_'+ind
         $('#concesionarias_cards').append("<div id='"+real.id+"'>" + card(real) + "</div>");
          $('#'+real.id).click(function(){

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
