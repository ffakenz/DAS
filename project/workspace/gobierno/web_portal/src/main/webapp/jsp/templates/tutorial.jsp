<%@ page
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>




<!-- TEMPLATE -->


<div class="content"></div>

<script type="text/html" id="userlist">
            <span>{%= users %}</span>

            {% _.each(users, function(pepe) { %}
                   			 {%= "pepe" %}
                   		{% }); %}
</script>

<!--
<script type="text/html" id="bio">
	<div>
		<h1>This is a lodash basic example</h1>
		<br>
		<h2>My name is {%- name %}</h2>
		<p>I like {%- hobbies %} but hate being a {%- occupation %}</p>
	</div>
</script>
-->

<!-- /TEMPLATE -->








<!-- LOGIC -->


<script type="text/javascript">


// An array, object or any data (eg. from an ajax call)
let users = ['fred', 'barney', 'pebble', 'wilma', 'betty', 'bambam']

let person = {
	name: 'fred',
	occupation: 'quarry worker',
	hobbies: 'bowling'
}


// Set the HTML template
let userlist = _.template($('#userlist').html());
let bio = _.template($('#bio').html());

// render the template using the data
$('.content').append(userlist(users));
$('.content').append(bio(person));

</script>


<!-- /LOGIC -->
