var concesionarias = {

    sendForm: function () {

          if( !concesionarias.isDataValid() ) {
              return false;
          }

          var url = '/web_portal/concesionarias/RegistrarConcesionaria.do';

          $('#formConcesionarias')
              .attr('action', url)
              .submit();
    },

    isDataValid: function () {

        if (!jUtils.inputsAreOk($('#formConcesionarias'))) {
            return false;
        }

        if (!jUtils.isValidEmail($("#email").val())) {
            return false;
        }

        return true;
    }

};