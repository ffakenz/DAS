<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATE -->
<div class="col-lg-6">
    <div class="input-group">
      <input id="searchConcesionariaText"  type="text" class="form-control" placeholder="   Search for...">
    </div>
</div>



<!-- /TEMPLATE -->








<!-- LOGIC -->


<script type="text/javascript">


function searchText(searchFor){
    return search.search(searchFor, 4);
}

$('#searchConcesionariaText').keyup(function() {
    let searchFor = $(this).val();
    let result = searchText(searchFor);
    let callback = ${param.callback};
    callback(result);
 });

</script>


<!-- /LOGIC -->
