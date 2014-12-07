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

			<h1>${group.name?html}!</h1>
			
			<a href="/quiz/0">Take a Quiz</a>
			<a href="/quiz/create">Create a New Quiz</a>
			
			<p>Return to: </p><br/>
			<a href="/orgs/${organization.id}">${organization.name}</a>
		</div>
		
	</body>
</html>