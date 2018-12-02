



<%@include file="./components/navbar.jsp" %>




<div id="portion1" class="info">
    <br/><br/>
  <h3>Heading for Portion 1</h3>



<div class="container">
  <div class="row">


        <div id="searchBox_template_here"></div>

        <script>
        function setSelected(element){
            currentValue = element.css("background-color");
            element.css("background-color","yellow");
            if (window.old !== undefined){
                window.old.css("background-color",currentValue);
            }
            window.old = element;
            element.css("background-color","yellow");
        }

        function searchCallback(result){
            console.log(JSON.stringify(result));
            if (result.length > 0)
            {
                setSelected($('#TODO_ThisIsNotAUniqueGlobalIndexInsteadUseTargetID_'+result[0].index));
            }
        };
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
        <div id="consesionarias_composite"></div>
    </div>
  </div>
</div>







<script>
function moveMapByItem(item){
   moveMap(item.pos);
};
</script>

<jsp:include page="../reusable/cardGrid.jsp" >
   <jsp:param name="JQuery_ID_target" value="'#consesionarias_composite'" />
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


