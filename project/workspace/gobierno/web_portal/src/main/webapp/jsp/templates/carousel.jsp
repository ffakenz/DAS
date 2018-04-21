<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATE -->


<div id="carousel_template_here"></div>




<script type="text/html" id="carousel-item-passive">
         <div class="carousel-item">
            <img class="d-block w-100" src="/web_portal/img/concesionarias/1.jpg" >
            <div class="carousel-caption">
                <h3 class="h3-responsive">{%- carousel_item.text %} </h3>
                <div class="mask rgba-black-strong">
                    <p> {%- carousel_item.text %} </p>
                </div>
            </div>
        </div>
</script>

<script type="text/html" id="carousel-item-active">
       <div class="carousel-item active">
                <div class="view"> <img src="/web_portal/img/concesionarias/2.jpg" class="img-fluid "> </div>
                <div class="carousel-caption">
                   <h3 class="h3-responsive">{%- carousel_item.text %}</h3>
                   <div class="mask rgba-black-light">
                     <p> {%- carousel_item.text %} </p>
                   </div>
                </div>
            </div>
</script>

<script type="text/html" id="carousel-item-template">
{%
           let carouse_slide_active = _.template($('#carousel-item-active').html());
           let carouse_slide_passive = _.template($('#carousel-item-passive').html());

           if (index == 0){
%}
                    {%= carouse_slide_active({"carousel_item":carousel_item})%}


{%
           }else{
%}
                    {%= carouse_slide_passive({"carousel_item":carousel_item})%}

{%
           }


%}
</script>


<script type="text/html" id="test">
<h1> {%=  text %}</h1>
</script>



<script type="text/html" id="carousel-indicators-template">
   <li data-target="#carousel-example-2z" data-slide-to="{%- data_slide_to %}"></li>
</script>






<script type="text/html" id="carousel-template">
    <div id="carousel-example-2z" class="carousel slide carousel-fade" data-ride="carousel">
        <ol class="carousel-indicators">


{%

                    array.forEach(function(real, index){
                        // render the template using the data
%}
{%=
                        carouseIndicatorTemplate({"data_slide_to":index})
%}
{%
                    });
%}
        </ol>


        <div class="carousel-inner" role="listbox">
{%

                    array.forEach(function(real, index){
                        // render the template using the data
%}
{%=
                        carouseSlideTemplate({"carousel_item":{"text":""+index}, "index": index})
%}
{%
                });
%}
        </div>



        <!--Controls-->
        <a class="carousel-control-prev" href="#carousel-example-2z" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carousel-example-2z" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
        <!--/.Controls-->
    </div>
</script>





<!-- /TEMPLATE -->











<!-- LOGIC -->


<script type="text/javascript">

// Set the HTML template
let carousel = _.template($('#carousel-template').html());
    let carousel_indicator_template = _.template($('#carousel-indicators-template').html());
    let carouse_slide_template = _.template($('#carousel-item-template').html());



let my_array = [0,1,2]
const parameters =  {
                    array: my_array
                   , carouseSlideTemplate: carouse_slide_template
                   , carouseIndicatorTemplate: carousel_indicator_template
                }


// render the template using the data
$('#carousel_template_here').append(carousel(parameters));




</script>

<!-- /LOGIC -->
