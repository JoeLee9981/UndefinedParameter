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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">	
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="page-content">
			<div class="grid fluid">
				<#if group??>
					<h5><a href="/group?groupId=${group.id}">Return to ${group.name}</a></h5>
				</#if>
				<div class="row">

					<h1>${quiz.name} 
						<button id="createButton" class="place-right success">Create A Question</button>
						<button id="addButton" class="place-right warning">Add A Question</button>
						<button id="editButton" class="place-right primary">Edit Quiz</button>
					</h1><br/>
					<h2>${quiz.description}</h2>
					<label id="errorLabel"></label>
					
					<div id="questionDiv"></div>
					
				</div>
			</div>
		</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>

	<script>
	
		$(document).ready(function() {
			$.ajax({
				type: 'GET',
				url: '/question/add?groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		$("#addButton").click(function() {
			$.ajax({
				type: 'GET',
				url: '/question/add?groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		$("#createButton").click(function() {
			$.ajax({
				type: 'GET',
				url: '/question/create?groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		$("#editButton").click(function() {
			$.ajax({
				type: 'GET',
				url: '/quiz/edit/questions?quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
	</script>	


</html>