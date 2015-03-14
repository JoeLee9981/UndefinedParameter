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
		<link href="/assets/css/home.css" rel="stylesheet">			
	</head>
	<body>
		
		<#include "../includes/navigation.ftl">

		<div id="home-content" class="page-content metro">
			
			<#if user??>
				<div>
					<h2>${displayName?html}</h2>
					<h2>${user.firstName?html}</h2>
					<h2>${user.middleName?html}</h2>
					<h2>${user.lastName?html}</h2>
					<h2>${user.country?html}</h2>
					<h2>${user.city?html}</h2>
					<h2>${user.state?html}</h2>
				</div>
			<#else>
				<h2> Oh no. :((( Your user profile is not available.</h2>
			</#if>
			
		</div>
		
		<div>														
			<#include "../includes/footer.ftl">
		</div>

	</body>
</html>