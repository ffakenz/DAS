<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATE -->


<script type="text/javascript">


function onKeyUp(inputText){
    console.log(inputText);
    let result = searchText(inputText);
    let callback = ${param.callback};
    callback(result);
}

</script>

<script type="text/html" id="searchBox-template">
    <div class="col-lg-6">
        <div class="input-group">
          <input id=${param.searchTextBoxId}  type="text" class="form-control" placeholder="   Search for..."
           onkeyup="onKeyUp($(this).val());"
          />
        </div>
    </div>
</script>







<!-- /TEMPLATE -->








<!-- LOGIC -->


<script type="text/javascript">


function searchText(searchFor){
    let numberOfResults = Number(${param.numberOfResults});
    let data = ${param.data};
    return search.search(searchFor, data, numberOfResults);
}

// Set the HTML template
let searchBox = _.template($('#searchBox-template').html());

// render the template using the data
$(${param.id}).append(searchBox({}));



</script>


<!-- /LOGIC -->
