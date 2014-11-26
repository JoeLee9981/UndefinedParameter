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
		
		
		<div class="content-width center">
			Content is going to go here
			
			<h1>Create A Quiz</h1><br/>
			<a href="/service/quiz/create/question">Add a question</a>

		</div>
		
		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>