



<%@include file="./components/navbar.jsp" %>




<div class="ParallaxBackground one">
</div>

<div id="portion1" class="info">
  <h3>Heading for Portion 1</h3>



<div class="container">
  <div class="row">


        <div id="searchBox_template_here"></div>

        <script>
        function searchCallback(result){
            console.log(JSON.stringify(result));
            if (result.length > 0)
                $('#carousel_template_here').carousel(result[0].index);
        };

        /*
        TODO ADD THIS BIND SO THAT CAROUSEL STOPS MOVING WHEN SEARCHING
        $('#searchBoxConcesionarias').keyup(function() {
            let searchFor = $(this).val();
           if (searchFor === "")  {
              $('#carousel_template_here').carousel('cycle');alert("resume");
          }
          else {
              $('#carousel_template_here').carousel('pause');alert("pause");
          }
        });
        */
        </script>

        <jsp:include page="../reusable/searchBox.jsp" >
           <jsp:param name="id" value="'#searchBox_template_here'" />
           <jsp:param name="searchTextBoxId" value="'#searchBoxConcesionarias'" />
           <jsp:param name="callback" value="searchCallback"   />
           <jsp:param name="numberOfResults" value="1"   />
           <jsp:param name="data" value="<%= ConcesionariasStringifiedJSON %>"   />
        </jsp:include>
  </div>


  <div class="row">
    <div class="col-sm-6">
        <div id="carousel_template_here"></div>
    </div>
    <div class="col-sm-6"">
        <%@include file="./components/googleMap.jsp" %>
    </div>
  </div>
</div>







<script>
function moveMapByItem(item){
   moveMap(item.pos);
};
</script>

<jsp:include page="../reusable/carousel.jsp" >
   <jsp:param name="JQuery_ID_target" value="'#carousel_template_here'" />
   <jsp:param name="callback" value="moveMapByItem" />
   <jsp:param name="data" value="<%= ConcesionariasStringifiedJSON %>"   />
</jsp:include>

</div>


<div class="ParallaxBackground two">



</div>

<div id="portion2" class="info">
  <h3>Heading for Portion 2</h3>
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Tempora pariatur voluptate laboriosam impedit praesentium sed, nihil, dignissimos et minima recusandae quaerat enim consectetur. Molestiae assumenda distinctio, rem nostrum dolores repellendus.</p>

</div>


<div class="ParallaxBackground three">
</div>


