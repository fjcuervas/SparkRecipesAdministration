<html>
<head>
	<title>Recipe list</title>
	<#include "/libs.ftl">
</head>
<body>
	<div class="container mt-5">
	
		<div class="centered">
			<h1 class="display-4 mb-4">Recipe list</h1>
		</div>	
			
		<form>
			<table class="table table-sm centered">
				<thead>
				    <tr class="table-active">
				      <th scope="col">ID</th>
				      <th scope="col">Description</th>
				      <th scope="col">Name</th>
				      <th scope="col">Edit</th>
				      <th scope="col">Delete</th>				      
				    </tr>
			  </thead>
				<#list recipes as recipe>
					<tr class="table-info">
						<td><p>${recipe.id}</p></td>
						<td><p>${recipe.description}</p></td>
						<td><p>${recipe.name}</p></td> 
						<td><a href='/edit/${recipe.id}'" class="glyphicon glyphicon-edit"></a></td>
						<td><a href='/delete/${recipe.id}'" class="glyphicon glyphicon-trash"></a></td>
					</tr>	
				</#list>
				<tr>
					<td>
				        <a href="/insert" class="btn btn-primary">
          					<span class="glyphicon glyphicon-plus-sign"></span> New recipe
        				</a>
        			</td>
        		</tr>
			</table>
			
		</form>
	</div>
</body>
</html>

