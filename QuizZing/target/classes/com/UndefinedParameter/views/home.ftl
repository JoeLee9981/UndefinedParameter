<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
		<script src="/scripts/jquery-ui.min.js"></script>
		<script src="/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/css/overrides.css" rel="stylesheet">
		<link href="/css/home.css" rel="stylesheet">			
	</head>
	<body>
		
		<#include "../includes/navigation.ftl">

		<div id="home-content" class="page-content metro">
			<div class="grid fluid">
				<div class="row">
					<div class="span3 no-span-left-margin" >
						<div id="login-form-container" class="span12">
							<div class="form-title-header">
								<span class="subheader-secondary">Login</span>
							</div>						
							<form id="login-form" class="medium">
								<div class="input-control text">
								    <input type="text" value="" placeholder="Email or Username"/>
								</div>
								<div class="input-control password">
								    <input type="password" value="" placeholder="Password"/>
								</div>
								<div class="span8">
									<div class="input-control checkbox">
									    <label>
									        <input type="checkbox" />
									        <span class="check"></span>
									        Remember me?
									    </label>
									</div>
									<div>
										<a href="#">Forgot your email?</a>
									</div>
									<div>
										<a href="#">Forgot your password?</a>
									</div>
								</div>			
								<div class="span4">
									<input class="place-right primary" type="submit" value="Login"/>
								</div>											
							</form>
						</div>

						<div id="register-form-container" class="span12">
							<div class="form-title-header">
								<span class="subheader-secondary">New to This? <strong>Sign Up.</strong></span>
							</div>
							<form id="register-form" class="medium">
								<div class="input-control text">
								    <input type="text" value="" placeholder="Email Address"/>
								</div>
								<div class="input-control text">
								    <input type="text" value="" placeholder="Username"/>
								</div>
								<div class="input-control password">
								    <input type="password" value="" placeholder="Password"/>
								</div>
								<div class="input-control password">
								    <input type="password" value="" placeholder="Confirm Password"/>
								</div>
								<div class="input-control checkbox">
								    <label>
								        <input type="checkbox" />
								        <span class="check"></span>
								        I Accept the <a href="#">Terms and Conditions</a>.
								    </label>
								</div>
								<div class="span8">
	
								</div>			
								<div class="span4">
									<input class="place-right success" type="submit" value="Register"/>
								</div>											
							</form>
						</div>											
					</div>
					<div class="span9 no-span-left-margin">
						<div id="home-content-right" class="span12">
						</div>
					</div>
				</div>
			</div>	
		</div>
		
		<script>		
			$(window).load(function(){
				$("#home-content-right").height($("#home-content").height());
			});
		</script>
		
		<#include "../includes/footer.ftl">
		
		
		<br>
		<br>
		<br>
		<br>

<div class="" style="background:gray">

		<#list news as n>
			<h1>${n.headline}</h1>
			<p>${n.body}</p>
		</#list>

</div>

	</body>
</html>