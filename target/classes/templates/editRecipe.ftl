<html>
<head>
	<title>Edit recipe</title>
	<#include "/libs.ftl">	
</head>
<body>

	<div class="container mt-5">
		<div class="centered">
			<h1 class="display-4 mb-4">Edit a recipe</h1>
		</div>	
		<form id="formInsert" class="centered" method="post" action="/edit">
		 	<div class="centered">
				<label for="recipeid">Recipe ID</label>
			    <input type="text" class="form-control" name="recipeid" id="recipeid" placeholder="recipe ID" value="${recipe.id}" readonly="readonly">
			</div>
		
		    <div class="centered">
		      <label for="description">Description</label>
		      <input type="text" class="form-control" name="description" id="description" placeholder="description" value="${recipe.description}" required>
		    </div>
		    
		    <div class="centered">
		      <label for="name">Name</label>
		      <input type="text" class="form-control" name="name" id="name" placeholder="name" value="${recipe.name}" required>
			</div>
		   
		   <div class="centered">
		   		<div style="margin-top: 15px;">
			   		<label for=" "></label>
			  		<input id="submit" class="btn btn-primary" value="Save" type="submit"/>
			  		<input class="btn btn-primary" type="button" onclick="location.href='/recipes'" value="Back to recipe list"/>
		  		</div>		
		  	</div>
		</form>
	</div>
</body>
</html>