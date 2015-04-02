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
							
							<div class="span9">
								<div class="row">
									<div id="bannerHeader" class="span6">
										<h2><strong>You can learn anything.</strong></h2>
										<span class="subheader-secondary">
											QuizZing offers the tools you need to succeed in any subject. Receive help, and help others
											to increase your knowledge.
										</span>
										<div id="headerButtons">
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
						<button class="shortcut danger span3" onclick="location.href = '/quiz/quizzes'">
						    <i class="icon-help"></i>
						   	Find a Quiz
						</button>
						<button class="shortcut success span3" onclick="location.href = '/quiz/create'">
						    <i class="icon-lightning"></i>
						   	Create a Quiz
						</button>
						<button class="shortcut primary span3" onclick="location.href = '/group/top'">
						    <i class="icon-comments-2"></i>
						    Find a Group
						</button>
						<button class="shortcut warning span3" onclick="location.href = '/orgs'">
						    <i class="icon-user-3"></i>
						    Find an Organization
						</button>											
				    </div>
				</div>
			
				<div class="page-content row">
					<div class="row noMargin">
						<nav class="navigation-bar white white-custom">
						    <nav class="navigation-bar-content">
						    	<item class="element span2 text-center active"><a href="" id="groupsLink"><strong>My Quizzes</strong></a></item>
						        <item class="element text-center span2"><a href="" id="groupsLink"><strong>My Groups</strong></a></item>
						        <item class="element text-center span2"><a href="#" class="todo"><strong>New Quizzes</strong></a></item>
						        <item class="element text-center span2"><a href="" id="groupsLink"><strong>Top Quizzes</strong></a></item>
						        <item class="element text-center span2"><a href="#" class="todo"><strong>Top Groups</strong></a></item>
						        <item class="element text-center span2"><a href="#" class="todo"><strong>Top Categories</strong></a></item>
						    </nav>
						</nav>					
					</div>
					<div class="row">
						<div class="span6">
						
							<#include "../includes/top_quizzes.ftl">
							<#include "../includes/recent_quizzes.ftl">
							     			
						</div>
					
						<div class="offset1 span5">
							<#include "../includes/top_groups.ftl">
							<div class="row">
								<h2>Top Categories</h2>
								<table class="table hovered striped">
			                        <thead>
				                        <tr>
				                            <th class="text-left">Subject</th>
				                            <th class="text-right">Quizzes</th>
				                            <th class="text-right">Groups</th>
				                        </tr>
			                        </thead>
			                        <tbody>                         
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
									</tbody>
			                        <tfoot></tfoot>
			                    </table>  
		                    </div>  					
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