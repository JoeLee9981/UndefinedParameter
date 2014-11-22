<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/navigation.css" />
		<link rel="stylesheet" type="text/css" href="/css/footer.css" />		
		<script src="/scripts/libraries/jquery.js"></script>
	</head>

	<body>
		<#include "../includes/navigation/login_navigation_bar.ftl">
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

		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>