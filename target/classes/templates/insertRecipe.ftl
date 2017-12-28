<html>
<head>
	<title>Insert recipe</title>
	<#include "/libs.ftl">
</head>
<body>
	<div class="container mt-5">
	
		<div class="centered">
			<h1 class="display-4 mb-4">Insert a recipe</h1>
		</div>	
		
		<form id="formInsert" class="centered" method="post" action="/insert">
		
		    <div class="centered">
		      	<label for="Description">Description</label>
		      	<input type="text" class="form-control" name="description" id="Description" placeholder="Description" required>
		    </div>
		    
		    <div class="centered">
		    	<label for="Name">Name</label>
		      	<input type="text" class="form-control" name="name" id="Name" placeholder="Name" required>
			</div>
		   
		    <div class="centered">
		   		<div style="margin-top: 15px;">
			   		<label for=" "></label>
			  		<input class="btn btn-primary" value="Save" type="submit"/>
			  		<input class="btn btn-primary" type="button" onclick="location.href='/recipes'" value="Back to recipe list"/>
		  		</div>		
		  	</div>
		</form>
	</div>
</body>
</html>