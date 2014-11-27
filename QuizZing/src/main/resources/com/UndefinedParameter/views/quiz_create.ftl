<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		
		
		<div class="content-width center">
			Content is going to go here
			
			<h1>Create A Quiz</h1><br/>
			<a href="/service/quiz/create/question">Add a question</a>

		</div>
		
		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>