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
			
			<h1>${organization.name?html}</h1>
			<h2>${organization.city?html}</h2>
			<h2>${organization.state?html}</h2>
			<h2>${organization.country?html}</h2>
			<p>${organization.description?html}</p>
			
			<a href=/service/orgs/0/0>Example Group</a>
		</div>
		
		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>