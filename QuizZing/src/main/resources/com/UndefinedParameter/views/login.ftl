<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" href="/assets/plugins/metro_ui/min/iconFont.min.css" >
		<link rel="stylesheet" href="/assets/css/overrides.css">
		<link rel="stylesheet" href="/assets/css/login.css">
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
	</head>

	<body>
		
		<#include "../includes/navigation.ftl">
		
			<div class="metro">
				<div class="grid fluid">		
					<div class="page-content">
						<#if redirectUrl??>
							<div class="row">
								<div class="offset1 span6">
									<h4>You must log into QuizZing to access this page.</h4>
								</div>
							</div>	
						</#if>					
					    <div class="row">
					    	<div class="offset1 span6">
								<h2>Sign In To QuizZing</h2>
							</div>
						</div>
						<div class="row">
							<form id="loginForm">
								<div id="login" class="offset1 span6">
									<p id="mainLoginError" class="tertiary-text-secondary errorFormText1 loginError" hidden></p>
									<div class="row">
										<div class="input-control text span8">
										    <input type="text" id="email" value="" placeholder="Email Address"/>
										</div>	
									</div>
									<div class="row">
										<div class="input-control password span8">
										    <input type="password" id="password" value="" placeholder="Password"/>
										</div>	
									</div>	
									<div class="row topMarginMedium">
										<button type="button" class="large primary" onclick="login()">Login</button>								
									</div>								
									<div class="row topMarginMedium">
										<p class="tertiary-text-secondary span12">Unable to login?  Use the <a href="forgot">Account Retrieval</a> process to regain access to your account.</p>
									</div>	
									<div class="row">
										<p class="tertiary-text-secondary span12">Not signed up for QuizZing? <a href="/register">Register</a> now.  It's quick, easy, and free. <a href="#" class="todo">Learn More</a> about what
										QuizZing has to offer and why we are the best thing since sliced bread.</p>
									</div>																																										<div class="row topMarginMedium">
										<button type="button" class="large success" onclick="goToRegister()">Register</button>								
									</div>	
								</div>
							</form>
							<div class="offset1 span4">
								<div id="sideContent">
								</div>
							</div>
						</div>					
					</div>
				</div>
			<div>	
								
		<#include "../includes/footer.ftl">

	</body>
</html>

<script>

	$("#loginForm").keypress(function(e) {
		// Enter button
		if (e.keyCode == 13)
		{
			login();
		}
	});

	function login()
	{
		$(".loginError").hide();
		var email = $("#email").val();
		var password = $("#password").val();
		if (email.length < 1 || password.length < 1)
		{
			$("#mainLoginError").html("Enter an email and password.");
			$("#mainLoginError").show();
			return;
		}
		doLogin(email, password);
	}
	
	function goToRegister()
	{
		window.location = "/register";
	}
	
	function doLogin(username, password) {
		$.ajax({
		    url: '/login',
		    username: username,
		    password: password,
		    type: 'POST',
		    success: function(data) {
		    	//console.log(data);
			    <#if redirectUrl??>
			    	window.location='${redirectUrl?html}';
			    <#else>
			    	window.location='/';
			    </#if>
		    },
		    error: function(error) {
		    	$("#mainLoginError").html("The email and/or password you entered are incorrect.");
				$("#mainLoginError").show();
		    }
		});
	}
	
</script>