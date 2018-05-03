var search = {



search : function(search, documents, N) {


            var idx = lunr(function () {
                this.ref('title');
                this.field('title');
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
                                                             return documents.find(function (obj) { return obj.title === result.ref; });
                                                           });
            return  enrichedWithText;

          }

};