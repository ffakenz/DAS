<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>






<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>


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
        public CarouselItem(String title, String text, Double lat, Double lng, Integer index){
            this.title = title;
            this.text = text;
            this.pos = new Pos(lat, lng);
            this.index = index;
        }
        public String title;
        public String text;
        public Integer index;
        public Pos pos;
    };

    ObjectMapper mapper = new ObjectMapper();

    List<CarouselItem> list = Arrays.asList(
        new CarouselItem(
           "rio primero","This JSON was made in JAVA using Jackson for object serialization."
           , new Double(-31.3317205),new Double(-63.622070199999996)
           , 0)
       ,
       new CarouselItem(
           "rio segundo"," All hail Jackson!  It is better than GSON!"
           , new Double(-31.650785),new Double(-63.90584530000001)
           , 1)
       ,
       new CarouselItem(
           "rio tercero","It is better than GSON! Why? It supports local classes! Yay! :D"
           , new Double(-32.1766541),new Double(-64.2059244)
           , 2)
       ,
        new CarouselItem(
           "rio primero 2","This JSON was made in JAVA using Jackson for object serialization."
           , new Double(-31.3317205),new Double(-63.622070199999996)
           , 3)
       ,
       new CarouselItem(
           "cercano","Es un lugar muy conocido. Fant'astico hospedaje."
           , new Double(-31.650785),new Double(-63.90584530000001)
           , 4)
       ,
       new CarouselItem(
           "mediano","A veces es visitado"
           , new Double(-32.1766541),new Double(-64.2059244)
           , 5)
       ,
        new CarouselItem(
           "lejano","Es un lugar olvidado"
           , new Double(-31.3317205),new Double(-63.622070199999996)
           , 6)
       ,
       new CarouselItem(
           "La Pampa.", "eaeaea"
           , new Double(-31.650785),new Double(-63.90584530000001)
           , 7)
       ,
       new CarouselItem(
           "Mercado Libre FTW", "Google knows best"
           , new Double(-32.1766541),new Double(-64.2059244)
           , 8)
        ,
       new CarouselItem(
           "Globant > ML", "competicion competicion competicion"
           , new Double(-32.1766541),new Double(-64.2059244)
           , 9)
    );

    String ConcesionariasStringifiedJSON = mapper.writeValueAsString(list);

%>


<script type="text/javascript">
let concesionarias = <%= ConcesionariasStringifiedJSON %>;
concesionariasAPI.setData(concesionarias);

</script>