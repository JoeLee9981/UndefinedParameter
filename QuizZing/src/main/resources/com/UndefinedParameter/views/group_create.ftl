<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/organizations.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<div id="register-form-container" class="span12">
				<div class="form-title-header">
					<span class="subheader-secondary">Create your <strong>Group</strong></span>
				</div>
				
				<form id="create-form" class="medium">
					<div class="input-control text">
					    <input id="name" type="text" value="" placeholder="Group Name"/>
					</div>
					<div class="input-control text">
					    <input id="categories" type="text" value="" placeholder="Categories (not used yet)"/>
					</div>
					<div class="input-control textarea">
					    <textarea id="description" placeholder="description"></textarea>
					    <br/>
					    <p class="text-alert" id="errorLabel"> </p>
					    <input class="success span2" type="submit" value="Create"/>
					</div>									
				</form>
			
		</div>
		
		<#include "../includes/footer.ftl">
	</body>
</html>


<script>
	
	$('#create-form').submit(function(event) {

		event.preventDefault();
		
		var name = $('#name').val();
		var categories = $('#categories').val();
		var description = $('#description').val();
		
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
			url: '/group',
			data: JSON.stringify({name: name, description: description, organizationId: ${organization.id} }),
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
		    	$('#errorLabel').html("Unable to create your organization.");
		    }
		});

	});
	

	
</script>