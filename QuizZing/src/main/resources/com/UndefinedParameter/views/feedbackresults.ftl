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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">	
	</head>
	<body>
		
		<#include "../includes/navigation.ftl">

		
		<div class="metro" id="home-page-subsection">
			<div class="grid fluid">
				<div class="page-content">
				
					<h3>Suggested Improvements</h3>
					<div class="home-subsection">
						<#list feedbackList as feedback>			
							<p>${feedback.improvement}</p>
						</#list>										
					</div>
					
					<h3>Suggested Features</h3>
					<div class="home-subsection">
						<#list feedbackList as feedback>			
							<p>${feedback.suggestedFeature}</p>
						</#list>										
					</div>
					
					<h3>Miscellaneous Feedback</h3>
					<div class="home-subsection">
						<#list feedbackList as feedback>			
							<p>${feedback.miscellaneous}</p>
						</#list>										
					</div>
				</div>
			</div>
		<div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>