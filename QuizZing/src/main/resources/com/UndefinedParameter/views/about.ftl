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

		
		<div class="metro" id="home-page-subsection">
			<div class="grid fluid" id="learnmore">
				<div class="page-content">
					<div class="home-subsection">
						<div class="row">
							<h1>What Is Quizzing?</h1>
						</div>

						<#if news??>
							<#list news as n>
												
								<div class="row">
									<div class="span3">
										<div class="notice marker-on-right bg-amber fg-white font-size-medium">
										 	${n.headline}
										</div>
									</div>
									<div class="span9">
											<p>${n.body}</p>
									</div>
								</div>
							
							</#list>			
						</#if>							
					</div>
				</div>
			</div>
		<div>				
									
		<#include "../includes/footer.ftl">
	</div>

	</body>
</html>