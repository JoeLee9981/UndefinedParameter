<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/home.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/assets/plugins/unicorn/unicorn_buttons.css" />		
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		
		
		<div class="content-width center">
			Content is going to go here
			
			<h1>Create A Quiz</h1><br/>
			<a href="/quiz/create/question">Add a question</a>
			<form action="addQuiz()">
				Description:<br/>
				<input type="text" name="descText" id="descText" />
				
			</form>

		</div>
		
	</body>
	
	<script>
	
	function addGroup() {
		
		//TODO Prevalidate these fields
		var groupName = document.getElementById('nameText').value;
		var desc = document.getElementById('descriptionText').value;
		//TODO Add more fields to the entry - see Group.java file for more info

		 $.ajax({
			type: 'POST',
			url: "/group",
			data: JSON.stringify({organizationId: ${organization.id}, name: groupName }),
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			}
		});
	}
	</script>
</html>