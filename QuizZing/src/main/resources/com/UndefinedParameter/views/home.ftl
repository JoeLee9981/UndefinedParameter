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


		<div id="home-content" class=" metro">
			<div class="grid fluid">
				<div id="homeBanner">
					<div class="page-content">
						<div id="bannerContent" class="row">
							<div class="span3">
								<div id="loginFormContainer">
									<form id="loginForm">
										<div class="input-control text">
										 	<input type="text" id="email" value="" placeholder="Email Address"/>
										</div>	
										<div class="input-control password">
										    <input type="password" id="password" value="" placeholder="Password"/>
										</div>
										<div class="input-control checkbox">
										    <label class="todo">
										        <input type="checkbox" />
										        <span class="check"></span>
										       	Keep me logged in
										    </label>
										</div>		
										<div class="row">
											<button type="submit" class="primary">Login</button>
										</div>								
									</form>	
									<div class="row topMarginMedium">
										<p class="tertiary-text-secondary span12">Unable to login?  Use the <a href="forgot">Account Retrieval</a> process to regain access to your account.</p>
									</div>		
								</div>	
							</div>
							<div class="span9">
								<div class="row">
									<div id="bannerHeader" class="span6">
										<h2><strong>You can learn anything.</strong></h2>
										<span class="subheader-secondary">
											QuizZing offers the tools you need to succeed in any subject. Receive help, and help others
											to increase your knowledge.
										</span>
										<div id="headerButtons">
											<button type="button" onclick="location.href = '/register'" class="success large">Register</button>
											<button type="button" class="warning large" onclick="location.href = '/about'">Learn More</button>		
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="page-content">
					<div class="row">
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/quiz/quizzes'">
						    <i class="icon-help"></i>
						   	Find a Quiz
						</button>
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/login'">
						    <i class="icon-lightning"></i>
						   	Create a Quiz
						</button>
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/group/top'">
						    <i class="icon-comments-2"></i>
						    Find a Group
						</button>
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/orgs'">
							<a href="#">
						    <i class="icon-user-3"></i>
						    Find an Organization
						    </a>
						</button>											
				    </div>
				</div>
			
				<div class="page-content row">
					<div class="span6">
					
						<#include "../includes/top_quizzes.ftl">
						<#include "../includes/recent_quizzes.ftl">
						     			
					</div>
					
					<div class="offset1 span5">
						<#include "../includes/top_groups.ftl">
						<div class="row">
							<h2>Top Categories</h2>
							<table class="table hovered">
		                        <thead>
			                        <tr>
			                            <th class="text-left">Subject</th>
			                            <th class="text-right">Quizzes</th>
			                            <th class="text-right">Groups</th>
			                        </tr>
		                        </thead>
		                        <tbody>                         
		                        </tbody>
									<tr>
										<td><a href="#" class="todo"> Computer Science </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Board Games </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Math </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Media </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Television </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Movies </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Electrical Engineering </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
		                        <tfoot></tfoot>
		                    </table>  
	                    </div>  					
					</div>
				</div>
			
			
			
				
			</div>	
		</div>
		
					
									
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
		
		$('#loginForm').submit(function(event) {
			event.preventDefault();
			
			var username = $('#email').val();
			var password = $('#password').val();
			
			if(!username) {
				//doLoginError("Please enter your email");
				//return;
			}
			
			if(!password) {
				//doLoginError("Please enter your password");
				//return;
			}
			
			$.ajax({
			    url: '/login',
			    username: username,
			    password: password,
			    type: 'POST',
			    success: function(data) {
			    	console.log(data);
			    	window.location='/';
			    },
			    error: function(error) {
			    	doLoginError("Invalid email and/or password");
			    }
			});
		});
		
		$('#register-form').submit(function(event) {
			event.preventDefault();
			
			window.location='/register';
		});
		
		function doLoginError(message) {
			document.getElementById('loginErrorLabel').innerHTML = message;
		}
		
	</script>
</html>