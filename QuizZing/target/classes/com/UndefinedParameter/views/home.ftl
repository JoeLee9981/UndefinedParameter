<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<#include "../includes/header.ftl">
	</head>

	<body>
		<h1>${news.headline?html}!</h1>
		<p>${news.body?html}!</p>
		<#include "../includes/footer.ftl">
	</body>
</html>