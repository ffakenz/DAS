<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATES

 1. carousel-item-base
        contains the HTML kernel of a carousel item.
        - is reused by carousel-item-passive
                    and carousel-item-active

 2. carousel-item-passive
        contains the HTML of an active carousel item.
        -uses carousel-item-base

 3. carousel-item-active
        contains the HTML of an passive carousel item.
        - uses carousel-item-base

 4. carousel-item-template
        abstracts away the active/passive property of a carousel item.
        - uses carousel-item-passive
        - uses carousel-item-active

 5. carousel-indicators-template
        contains the HTML of a carousel indicator.

 6. carousel-template
        abstracts away the HTML of a carousel.


 -->




<script type="text/html" id="carousel-item-base">
   <div class="carousel-caption">
                <h3 class="h3-responsive">{%- carousel_item.title %} </h3>
                <div class="mask rgba-black-strong">
                    <p> {%- carousel_item.text %}</p>
                </div>
            </div>
</script>





<script type="text/html" id="carousel-item-passive">
         <div class="carousel-item">
            <img class="d-block w-100" src="/web_portal/img/concesionarias/1.jpg">

            {%= carousel_slide_base({"carousel_item":carousel_item}) %}
        </div>
</script>






<script type="text/html" id="carousel-item-active">
       <div class="carousel-item active">
                <div class="view"> <img src="/web_portal/img/concesionarias/2.jpg" class="img-fluid "> </div>
                {%= carousel_slide_base({"carousel_item":carousel_item}) %}
            </div>
</script>






<script type="text/html" id="carousel-item-template">

{%
            let carousel_slide_active = _.template($('#carousel-item-active').html());
            let carousel_slide_passive = _.template($('#carousel-item-passive').html());
            let carousel_slide_base = _.template($('#carousel-item-base').html());

            if (index == 0){
%}
                    {%= carousel_slide_active({"carousel_item":carousel_item, "carousel_slide_base": carousel_slide_base})%}


{%
            }else{
%}
                    {%= carousel_slide_passive({"carousel_item":carousel_item, "carousel_slide_base": carousel_slide_base})%}

{%
            }


%}

</script>





<script type="text/html" id="carousel-indicators-template">
   <li data-target= {%=source_id%} data-slide-to="{%- data_slide_to %}"></li>
</script>






<script type="text/html" id="carousel-template">
    <div id={%=source_id.substr(1) %} class="carousel slide carousel-fade" data-ride="carousel" data-interval="false">
        <ol class="carousel-indicators">


{%
                    let carousel_indicator_template = _.template($('#carousel-indicators-template').html());

                    data.forEach(function(item, index){
                        // render the template using the data
%}
{%=
                        carousel_indicator_template({"source_id": source_id, "data_slide_to":index})
%}
{%
                    });
%}
        </ol>


        <div class="carousel-inner w-100" role="listbox">
{%
                    let carouse_slide_template = _.template($('#carousel-item-template').html());

                    data.forEach(function(item, index){
                        // render the template using the data
%}
{%=
                        carouse_slide_template({"carousel_item":item, "index": index})
%}
{%
                });
%}
        </div>



        <!--Controls-->
        <a class="carousel-control-prev" href={%=source_id%} role="button" data-slide="prev" style="z-index: 10;">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href={%=source_id%} role="button" data-slide="next" style="z-index: 10;" >
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


    let data = ${param.data};



    let callback = ${param.callback};
    let target = ${param.JQuery_ID_target}; // "'#carousel_template_here'"
    let source = "#carousel1";
    let parameters =  {data: data, "source_id": source, "callback": callback}


    // render the template using the data
    $(target).append(carousel(parameters));

    $(target).bind('slid.bs.carousel', function (e) {

        let currentItemIndex = $(this).find('.active').index();
        let currentItem = data[currentItemIndex];
        callback(currentItem);
    });






</script>

<!-- /LOGIC -->
