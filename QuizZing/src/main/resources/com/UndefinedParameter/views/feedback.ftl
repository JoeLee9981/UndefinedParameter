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
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">		
	</head>
	<body class="metro">
	
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<h3>Help us improve your experience.</h3>
			<p id="resultsText"/>
			<div class="grid fluid">
				<div class="row">
						<form id="create-question-form">
							<label><h5>1. Please give one feature you would like to see on QuizZing.</h5>
								<div class="input-control textarea">
								    <textarea id="featureText"></textarea>
								</div>
							</label>
							<label><h5>2. Please give one improvement we could make to improve your experience.</h5>
								<div class="input-control textarea">
								    <textarea id="imrpoveText"></textarea>
								</div>
							</label>
							<label><h5>3. Please provide any other feedback.</h5>
								<div class="input-control textarea">
								    <textarea id="miscText"></textarea>
								</div>
							</label>
							<input type="button" onclick="addFeedback()" class="success">Submit</input>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</body>
	<script>
		var maxAnswers = 5;
		
		function addFeedback() {
			
			var feature = document.getElementById('featureText').value;
			var improve = document.getElementById('imrpoveText').value;
			var misc = document.getElementById('miscText').value;
	
			 $.ajax({
				type: 'POST',
				url: "/feedback",
				data: JSON.stringify({suggestedFeature: feature, improvement: improve, miscellaneous: misc }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data, textStatus, JQxhr) {
					alert(JSON.parse(data));
					alert(textStatus);
					alert(JQxhr);
				}
			});
			
			//TODO: Validate the post was sent
			document.getElementById('resultsText').innerHTML = "* Thank you - Your feedback has been submitted";
			document.getElementById('featureText').value = "";
			document.getElementById('imrpoveText').value = "";
			document.getElementById('miscText').value = "";
			
		}
		
	</script>
</html>

