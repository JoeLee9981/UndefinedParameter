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
		
				
	</head><head>
		<title>QuizZing</title>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		<h1>QUIZ VIEW</h1>
		<p>${quiz.description?html}<br/>
		Created By: ${quiz.creatorId?html}<br/>
		Difficulty: ${quiz.difficulty?html}<br/>
		Rating: ${quiz.rating?html}<br/>
		Time: ${quiz.time?html}</p>
		<br/>
		<br/>

		
		<#list 0..quiz.questionCount-1 as _>
			<h2>${quiz.nextQuestion.questionText}</h2>
			
			<#list quiz.currentQuestion.answers as answer>
				<p>${answer}</p>
			</#list>
		</#list>

	</body>
</html>