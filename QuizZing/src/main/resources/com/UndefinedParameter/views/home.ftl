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
						<div class="offset1 span11">
							<div>
								<h2 class="fg-white"><strong>Welcome to QuizZing, the new way to study.</strong></h2>
							</div>
							<div>
								<p class="fg-white">
									QuizZing provides a better, more efficient way to study. Study in groups from within your
									classes and schools as well as those across the country. Create your own questions, quizzes,
									and all sorts of other cool stuff that I can't think of right now.
								</p>
							</div>
						</div>
						<div id="home-page-quick-links" class="offset1 span11 fg-white">
							<div>
								<span>
									<a href="#">
										<i class="icon-globe on-left"></i>
										Get Connected To Your Group
									</a>
								</span>
							</div>
							<div>
								<span>
									<a href="#">
										<i class="icon-glasses-2 on-left"></i>
										Learn More
									</a>
								</span>
							</div>
							<div>
								<span>
									<a href="#">
										<i class="icon-search on-left"></i>
										Search For More
									</a>
								<span>
							</div>
						</div>						
					</div>
				</div>
			</div>	
		</div>
		
		
		<#include "../includes/footer.ftl">
		
		
		<!--
		<div class="page-content" style="background:white">
			<#list news as n>
				<h1>${n.headline}</h1>
				<p>${n.body}</p>
			</#list>
		</div>
		-->

</div>

	</body>
</html>