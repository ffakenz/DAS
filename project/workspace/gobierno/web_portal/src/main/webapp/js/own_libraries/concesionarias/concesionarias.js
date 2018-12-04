var concesionarias = {

      sendForm : function () {

          jUtils.executing( "mensaje" );
          $.ajax({
              url: "/web_portal/concesionarias/RegistrarConcesionaria.do",
              type: "post",
              data: $( "#formConcesionarias" ).serialize(),
              dataType: "html",
              error: function(hr){
                  jUtils.hiding("mensaje");
                  jUtils.showing("error", hr.responseText);
              },

              success: function(html) {
                  window.location.href = "/web_portal/concesionarias/SuccessRegistrar.do";
              }
          });
      }
};
