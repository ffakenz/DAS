var concesionariasAPI = {



    getJSON : function() {

            var lunr_result = {
                             "employees":[
                                 {"firstName":"John", "lastName":"Doe"},
                                 {"firstName":"Anna", "lastName":"Smith"},
                                 {"firstName":"Peter", "lastName":"Jones"}
                             ]
                             };


            var html = "";

            for (var key in lunr_result) {
              if (lunr_result.hasOwnProperty(key)) {

                    for (var key2 in lunr_result[key]) {
                      if (lunr_result.hasOwnProperty(key)) {

                          html += "\n" + (key2 + " -> " + JSON.stringify(lunr_result[key][key2]));
                      }
                    }
              }
            }

            return html
      }
};
