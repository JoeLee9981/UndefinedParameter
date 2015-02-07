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

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="page-content">
			<div class="grid fluid">
				<#if group??>
					<h5><a href="/group?groupId=${group.id}">Return to ${group.name}</a></h5>
				</#if>
				<div class="row">

					<h1>Create a Quiz</h1><br/>
					<form id="create-form" class="medium">
						<input type="text" name="nameText" id="nameText" placeholder="name" /><br/><br/>
						<input type="text" name="descText" id="descText" placeholder="description" />
						<p class="text-alert" id="errorLabel"> </p>
					    <input class="success span2" type="submit" value="Create"/>
					</form>
					<label id="errorLabel" />
				</div>
			</div>
		</div>
		<#include "../includes/footer.ftl">
	</body>
	
	<script>
	
	$('#create-form').submit(function(event) {
		event.preventDefault();
		
		var name = $('#nameText').val();
		var description = $('#descText').val();
		
		if(!name) {
			$('#errorLabel').html("Please enter a name");
			return;
		}
		
		if(!description) {
			$('#errorLabel').html("Please enter a description");
			return;
		}
		
		$.ajax({
			type: 'POST',
			url: "/quiz/create?groupId=${group.id}",
			data: JSON.stringify({name: name, description: description }),
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function(data) {
				if("success" == data["response"]) {
					window.location = data["redirect"];
				}
				else {
					$('#errorLabel').html(data["message"]);
				}
			},
			error: function(error) {
		    	$('#errorLabel').html("Unable to create your Quiz.");
		    }
		});

	});
	
	</script>
</html>