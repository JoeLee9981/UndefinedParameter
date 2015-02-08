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

					<h1>${quiz.name}</h1><br/>
					<h2>${quiz.description}</h2>
					
					<#if quiz.questions??>
						<#list quiz.questions as question>
							<h4>${question.questionText}</h4>
						</#list>
					</#if>
					<a href="/question/create?quizId=${quiz.quizId}">create a question</a>
					<a href="/question/add?quizId=${quiz.quizId}">Add an existing question</a>
					<label id="errorLabel" />
				</div>
			</div>
		</div>
		<#include "../includes/footer.ftl">
	</body>

</html>