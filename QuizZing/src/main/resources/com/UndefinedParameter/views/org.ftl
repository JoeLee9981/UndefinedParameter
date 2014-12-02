<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
		<script src="/scripts/jquery-ui.min.js"></script>
		<script src="/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/css/overrides.css" rel="stylesheet">
		<link href="/css/question.css" rel="stylesheet">
		
		<link rel="stylesheet" type="text/css" href="/plugins/unicorn/unicorn_buttons.css" />
		
				
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
		
		
	</body>
</html>


<script>
	
	function addGroup() {
		
		//TODO Prevalidate these fields
		var groupName = document.getElementById('nameText').value;
		var desc = document.getElementById('descriptionText').value;
		//TODO Add more fields to the entry - see Group.java file for more info

		 $.ajax({
			type: 'POST',
			url: "/service/group",
			data: JSON.stringify({organizationId: ${organization.id}, name: groupName }),
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			}
		});
	}
	

	
</script>