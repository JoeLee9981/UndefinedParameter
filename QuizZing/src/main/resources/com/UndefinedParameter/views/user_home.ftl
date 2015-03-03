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
			
				
				<div class="page-content">
					<div class="row">
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/quiz/quizzes'">
						    <i class="icon-help"></i>
						   	Find A Quiz
						</button>
						<button class="shortcut bg-amber fg-white span3 todo">
						    <i class="icon-lightning"></i>
						   	Random Quiz
						</button>
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/group/top'">
						    <i class="icon-comments-2"></i>
						    Find A Group
						</button>
						<button class="shortcut bg-amber fg-white span3" onclick="location.href = '/orgs'">
							<a href="#">
						    <i class="icon-user-3"></i>
						    Find An Organization
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
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
										<td class="text-right right">17</td>
										<td class="text-right right">4.3</td>
									</tr>
									<tr>
										<td><a href="#" class="todo"> Content here </a></td>
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