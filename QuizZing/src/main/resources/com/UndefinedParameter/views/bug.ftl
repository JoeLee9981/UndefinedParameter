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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>
	<body class="metro">
	
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<h3>Help us improve your experience.</h3>
			<p id="resultsText"/>
			<div class="grid fluid">
				<div class="row">
						<form id="create-question-form">
							<label><h5>1. Select the urgency of the bug</h5>
								<div class="input-control textarea">
								    <select id="urgencySelect">
								    	<option value="0">Not Urgent - General feedback</option>
								    	<option value="1">Minor Urgency - Minor annoyance</option>
								    	<option value="2">Medium Urgency - Some features not working</option>
								    	<option value="3">High Urgency - Most features not working</option>
								    	<option value="4">Major Urgency - Everything is completely broken</option>
								    </select>
								</div>
							</label>
							<label><h5>2. Please provide a description of the bug.</h5>
								<div class="input-control textarea">
								    <textarea id="descriptionText"></textarea>
								</div>
							</label>
							<label><h5>3. Please provide steps to reproduce the bug.</h5>
								<div class="input-control textarea">
								    <textarea id="reproduceText"></textarea>
								</div>
							</label>
							<input type="button" onclick="submitBug()" value="Submit" class="success"></input>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
	<script>
		
		function submitBug() {
			
			var urgency = document.getElementById('urgencySelect').value;
			var desc = document.getElementById('descriptionText').value;
			var reproduce = document.getElementById('reproduceText').value;
	
			if(!urgency) {
				document.getElementById('resultsText').innerHTML = "* Please select an urgency";
				return;
			}
			if(!desc) {
				document.getElementById('resultsText').innerHTML = "* Please enter a description";
				return;
			}
			if(!reproduce) {
				document.getElementById('resultsText').innerHTML = "* Please enter steps to reproduce";
				return;
			}
	
			 $.ajax({
				type: 'POST',
				url: "/feedback/bug",
				data: JSON.stringify({urgency: urgency, description: desc, stepsToReproduce: reproduce }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(response) {
				
					if("success" == response["response"]) {
						document.getElementById('resultsText').innerHTML = "* Thank you - Your bug report has been submitted";
						document.getElementById('descriptionText').value = "";
						document.getElementById('urgencySelect').value = "";
						document.getElementById('reproduceText').value = "";
					}
					else {
						document.getElementById('resultsText').innerHTML = response["message"];
					}
					
				}
			});	
		}
		
	</script>
</html>

