<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" href="/assets/plugins/metro_ui/min/iconFont.min.css" >
		<link rel="stylesheet" href="/assets/css/overrides.css">
		<link rel="stylesheet" href="/assets/css/register.css">
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>

	<body>
		
		<#include "../includes/navigation.ftl">
		
			<div class="metro">
				<div class="grid fluid">		
					<div class="page-content">						
					    <div class="row">
					    	<div class="offset1 span6">
								<h2>${userEmail?html} does not exist!</h2>
								<p>Please check the spelling of your email and try again.</p>
							</div>
						</div>
						<div class="row">
							<button type="button" class="large primary" onclick="goBack()">Go Back</button>	
						</div>					
					</div>
				</div>
			<div>	
								
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>

	</body>
</html>

<script>

	function goBack() 
	{
    	window.history.back()
	}
	
</script>