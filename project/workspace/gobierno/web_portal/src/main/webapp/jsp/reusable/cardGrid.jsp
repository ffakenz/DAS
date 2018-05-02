<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATES


 -->




<script type="text/html" id="card-template">
    <div class="col-md-4">
        <div class="card">
        <img class="img-fluid"  src="https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20%2813%29.jpg" alt="Card image cap">

          <div class="card-block">
            <h4 class="card-title">{%- item.title %}</h4>
            <h6 class="card-subtitle text-muted">{%- item.title %} (subtitle)</h6>
            <p class="card-text p-y-1">{%- item.text %}</p>
             <p align="center">
                    <a href="#" class="btn btn-danger btn-sm"
                      onclick='${param.callback}({%= JSON.stringify(item) %});'>

                      <span class="fa fa-map-marker" style="color:white;display: flex;justify-content: center;  font-size: large;"></span>
                    </a>
                  </p>
          </div>
        </div>
      </div>

</script>




<script type="text/html" id="cardGrid-template">


  <div class="py-5">
    <div class="container">
    <div class="row">
{%
                     let cardTemplate = _.template($('#card-template').html());

                    needToAddRow = true;
                    needToFinishRow = false;
                    data.forEach(function(item, index){
                        // render the template using the data
%}


{%=
                        cardTemplate({"item":item, "callback": callback})
%}


{%
                });
%}

</div>


    </div>
  </div>

</script>



<!-- /TEMPLATE -->











<!-- LOGIC -->



<script type="text/javascript">

    // Set the HTML template
    let carousel = _.template($('#cardGrid-template').html());


    let data = ${param.data};



    let callback = ${param.callback};
    let target = ${param.JQuery_ID_target}; // "'#cardGrid_template_here'"
    let source = "#cardGrid-template";
    let parameters =  {data: data, "source_id": source, "callback": callback}


    // render the template using the data
    $(target).append(carousel(parameters));







</script>

<!-- /LOGIC -->
