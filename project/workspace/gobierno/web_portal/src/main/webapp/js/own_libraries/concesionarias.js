const concesionarias = {

    sendForm: function () {

          if( !concesionarias.isDataValid() ) {
              return false;
          }

          var url = Globals.REGISTRAR_CONCESIONARIA_ENDPOINT;

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