<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		
		
		<div class="content-width center">
			Content is going to go here
			
			<h1>${organization.name?html}</h1>
			<h2>${organization.city?html}</h2>
			<h2>${organization.state?html}</h2>
			<h2>${organization.country?html}</h2>
			<p>${organization.description?html}</p>
			
			<#list groups as group>
				<a href="/service/group/${group.id}">${group.name?html}</a><br/>
			</#list>
			
			<br/>
			<h2>Add a group</h2>
			<input type="text" id="nameText" name="name" placeholder="Name"><br/>
			<input type="text" id="descriptionText" name="description" placeholder="Description"><br/>
			<button onclick="addGroup()" >Add</button><br/>
		</div>
		
		
		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>


<script>
	
	function addGroup() {
		
		//TODO Prevalidate these fields
		
		//TODO Add more fields to the entry - see Group.java file for more info
		
		 $.ajax({
			type: 'POST',
			url: "/service/group",
			data: JSON.stringify({organizationId: 123, name:"123" }),
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			}
		});
	}
	

	
</script>