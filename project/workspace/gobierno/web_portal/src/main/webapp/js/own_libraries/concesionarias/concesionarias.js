var concesionarias = {

    sendForm: function () {

          var url = '/concesionarias/RegistrarConcesionaria.do';

          $('#formConcesionarias')
              .attr('action', url)
              .submit();

          console.log("ASDASD")
      }
};


// $.ajax({
//     url: "/web_portal/concesionarias/RegistrarConcesionaria.do",
//     type: "post",
//     data: $( "#formConcesionarias" ).serialize(),
//     dataType: "html",
//     error: function(hr){
//         console.log("entro al error");
//         console.log(hr);
//         jUtils.hiding("mensaje");
//         jUtils.showing("error", hr.responseText);
//     },
//     success: function(html) {
//         console.log("entro al success");
//         console.log(html);
//         window.location.href = "/web_portal/concesionarias/SuccessRegistrar.do";
//     }
// });