<!-- TEMPLATE -->
<div class="content"></div>

<script type="text/html" id="userlist">
            <span>{%= users %}</span>

            {% _.each(users, function(pepe) { %}
                   			 {%= "pepe" %}
                   		{% }); %}
</script>




<!-- USAGE EXAMPLE -->
<script type="text/javascript">

// An array, object or any data (eg. from an ajax call)
let users = ['fred', 'barney', 'pebble', 'wilma', 'betty', 'bambam']

// Set the HTML template
let userlist = _.template($('#userlist').html());

// render the template using the data
$('.content').append(userlist(users));

</script>