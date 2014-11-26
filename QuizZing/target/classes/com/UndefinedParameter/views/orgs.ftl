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
			
			<h1>Organizations Page</h1>
			<br/>
			<#list getOrganizations("Salt Lake") as org>
				<a href="/service/orgs/${org.id}">${org.name}</a><br/>
			</#list>
			
		</div>
		
		<#include "../includes/footer/unauthenticated_user_home_footer.ftl">
	</body>
</html>