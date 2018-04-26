



<%@include file="./components/navbar.jsp" %>




<div class="ParallaxBackground one">
</div>

<div id="portion1" class="info" style="background-color:grey">
  <h3>Heading for Portion 1</h3>
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Debitis nobis ut dicta quisquam officia deleniti, amet. Obcaecati atque, cumque quae culpa ad hic commodi provident pariatur, iure nobis voluptatibus laborum amet temporibus quia, dolores veritatis, repellendus consectetur. Ut ullam officiis minus quod assumenda facere quaerat, esse, magni, aliquid culpa vero.</p>
<br/><br/><br/><br/><br/><br/>

  <%@include file="./components/googleMap.jsp" %>

<br/><br/><br/><br/><br/><br/>

<div id="carousel_template_here"></div>


    <%@page import="org.codehaus.jackson.map.ObjectMapper"%>
    <%@page import="java.util.Arrays"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.Collections"%>

    <%


    class CarouselItem {
        class Pos {
            public Pos(Double lat, Double lng){
               this.lat = lat;
               this.lng = lng;
            }
            public Double lat;
            public Double lng;
        };
        public CarouselItem(String title, String text, Double lat, Double lng){
            this.title = title;
            this.text = text;
            this.pos = new Pos(lat, lng);
        }
        public String title;
        public String text;
        public Pos pos;
    };

ObjectMapper mapper = new ObjectMapper();

List<CarouselItem> list = Arrays.asList(
    new CarouselItem(
    "rio primero","This JSON was made in JAVA using Jackson for object serialization."
    , new Double(-31.3317205),new Double(-63.622070199999996))
,
new CarouselItem(
    "rio segundo"," All hail Jackson!  It is better than GSON!"
    , new Double(-31.650785),new Double(-63.90584530000001))
,
new CarouselItem(
    "rio tercero","It is better than GSON! Why? It supports local classes! Yay! :D"
    , new Double(-32.1766541),new Double(-64.2059244))
);

String data = mapper.writeValueAsString(list);

%>

<script>
function moveMapByItem(item){
   moveMap(item.pos);
};
</script>

<jsp:include page="../reusable/carousel.jsp" >
   <jsp:param name="JQuery_ID_source" value="'#carousel1'" />
   <jsp:param name="JQuery_ID_target" value="'#carousel_template_here'" />
   <jsp:param name="callback_on_click" value="moveMapByItem" />

   <jsp:param name="data" value="<%= data %>"   />
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


