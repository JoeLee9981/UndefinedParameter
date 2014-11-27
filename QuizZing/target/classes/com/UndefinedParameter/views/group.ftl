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

			<h1>${group.name?html}!</h1>
			
			<a href="/service/quiz/0">Take a Quiz</a>
			<a href="/service/quiz/create">Create a New Quiz</a>
			
			<p>Return to: </p><br/>
			<a href="/service/orgs/${organization.id}">${organization.name}</a>
		</div>
		
		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>