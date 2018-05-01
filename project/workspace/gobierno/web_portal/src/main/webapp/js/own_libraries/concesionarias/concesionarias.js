var concesionariasAPI = {

    data: [],

    setData : function(data){
        this.data = data;
    },

    getData : function(){
        return this.data;
    },


    search : function(search, N) {

            var documents = [
                {
                "name": "rio primero",
                "text": "aca vende mucho peugeot",
                "pos": {lat:-31.3317205,lng: -63.622070199999996}
                },
                {
                "name": "rio segundo",
                "text": "aca se vende renault",
                "pos": {lat:-31.650785,lng: -63.90584530000001}
                },
                {
                "name": "rio tercero",
                "text": "aca re pegaron las hilux, de toyota",
                "pos": {lat:-32.1766541,lng: -64.2059244}
                },
                {
                "name": "rio cuarto",
                "text": "aca la gente anda en bondi",
                "pos": {lat:-33.1231585,lng: -64.3493441}
                },
                {
                "name": "rio quinto",
                "text": "Mucho cuatriciclo",
                "pos": {lat:-33.6541814,lng: -65.48783739999999}
                },
                {
                "name": "cordoba capital",
                "text": "aca la gente anda en bondi",
                "pos": {lat:-31.42008329999999,lng: -64.18877609999998}
                }
                ];

            var idx = lunr(function () {
                this.ref('name');
                this.field('name');
                this.field('text');

                documents.forEach(function (doc) {
                this.add(doc);
                }, this)
            });

            /*
            example of an lunr_result
            [{"ref":"React","score":0.2573821872559409,"matchData":{"metadata":{"javascript":{"text":{}}}}}
                ,{"ref":"Lodash","score":0.17542718388541695,"matchData":{"metadata":{"javascript":{"text":{}}}}
                }]
            */



            function compareLunrResults(a,b) {
              if (a.score < b.score)
                return -1;
              if (a.score > b.score)
                return 1;
              return 0;
            }

            const lunr_result = idx.search("*" + search + "*");
            const orderedLunrResults = lunr_result.sort(compareLunrResults);
            const firstNResults = orderedLunrResults.slice(0, N);
            const enrichedWithText = firstNResults.map((result) => {
                                                             return documents.find(function (obj) { return obj.name === result.ref; });
                                                           });
            return  enrichedWithText;

          }
};
