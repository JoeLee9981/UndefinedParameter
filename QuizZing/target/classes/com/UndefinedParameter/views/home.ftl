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
								    <input id="username" type="text" value="" placeholder="Email or Username"/>
								</div>
								<div class="input-control password">
								    <input id="password" type="password" value="" placeholder="Password"/>
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
										<a href="#" class="todo">Forgot your email?</a>
									</div>
									<div>
										<a href="#" class="todo">Forgot your password?</a>
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
								        I Accept the <a href="#" class="todo">Terms and Conditions</a>.
								    </label>
								</div>
								<div class="span8">
	
								</div>			
								<div class="span4">
									<input class="place-right success todo" type="submit" value="Register"/>
								</div>											
							</form>
						</div>											
					</div>
					<div class="span9 no-span-left-margin">
						<div class="offset1 span11">
							<div>
								<h2 id="welcome-message" class="fg-white"><strong>Welcome to QuizZing.</strong>  Get connected to study groups
								and discover the new, better way to study.  Try it out with our tailored for you <a href="/group/CS4400">CS4400 Quiz.</a></h2>
							</div>
							<!--<div>
								<p class="fg-white">
									QuizZing provides a better, more efficient way to study. Study in groups from within your
									classes and schools as well as those across the country. Create your own questions, quizzes,
									and all sorts of other cool stuff that I can't think of right now.
								</p>
							</div>-->
						</div>
						<div id="home-page-quick-links" class="offset1 span11 fg-white">
							<div>
								<span>
									<a href="#" class="todo">
										<i class="icon-globe on-left"></i>
										Get Connected To Your Group
									</a>
								</span>
							</div>
							<div>
								<span>
									<a href="#" onclick="scrollToLearnMore();">
										<i class="icon-glasses-2 on-left"></i>
										Learn More
									</a>
								</span>
							</div>
							<div>
								<span>
									<a href="#" class="todo">
										<i class="icon-search on-left"></i>
										Search For More
									</a>
								<span>
							</div>
							<div>
								<span>
									<a href="/feedback">
										<i class="icon-printer on-left"></i>
										Give Us Your Feedback
									</a>
								<span>
							</div>
						</div>						
					</div>
				</div>
			</div>	
		</div>
		
		<div class="divider1">
		</div>
		
		<div class="metro" id="home-page-subsection">
			<div class="grid fluid" id="learnmore">
				<div class="page-content">
					<div class="home-subsection">
						<div class="row">
							<h1>What Is Quizzing?</h1>
						</div>

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
					</div>
				</div>
			</div>
		<div>				
									
		<#include "../includes/footer.ftl">
	</div>

	</body>
	<script>
		function scrollToLearnMore()
		{
			$('html, body').animate({
	   			scrollTop: $("#learnmore").offset().top
				}, 1000);
		}
		
		$('#login-form').submit(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/login',
			    username: $('#username').val(),
			    password: $('#password').val(),
			    type: 'POST',
			    success: function(data) {
			    	console.log(data);
			    	window.location='/';
			    },
			    error: function(error) {
			    }
			});
		});
	</script>
</html>