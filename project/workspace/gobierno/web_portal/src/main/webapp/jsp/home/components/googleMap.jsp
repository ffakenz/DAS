<%@ page

    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
%>


  <div id="map" style="width:500px; height:300px"></div>
    <script>

      function initMap() {

        var myLatLng = {lat: -25.363, lng: 131.044};

        window.map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: myLatLng
        });

        window.marker = new google.maps.Marker({
          position: myLatLng,
          map: window.map
        });
      }

    function moveMap( pos) {

        window.marker.setPosition( pos);
        window.map.panTo( pos );

    };
    </script>