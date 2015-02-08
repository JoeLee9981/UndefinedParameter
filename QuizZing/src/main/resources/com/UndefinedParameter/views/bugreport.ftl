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
		<div class="divider1">
			<div class="metro" id="home-page-subsection">
				<div class="grid fluid">
					<div class="page-content">
						<h1>Bug Reports</h1>
						
						<#if bugs??>
							<#list bugs as bug>
								<div class="home-subsection">
									<table>
										<tr>
											<th>Urgency</th>
											<th>Description</th>
											<th>Steps To Reproduce</th>
										</tr>
										<tr>
											<td>${bug.urgency}</td>
											<td>${bug.description}</td>
											<td>${bug.stepsToReproduce}</td>
										</tr>
									</table>
							</#list>
						</#if>
					</div>
				</div>
			<div>
		</div>
		<#include "../includes/footer.ftl">

	</body>
</html>