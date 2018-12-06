var concesionarias = {

    sendForm: function () {

          var url = '/web_portal/concesionarias/RegistrarConcesionaria.do';

          $('#formConcesionarias')
              .attr('action', url)
              .submit();
      }
};