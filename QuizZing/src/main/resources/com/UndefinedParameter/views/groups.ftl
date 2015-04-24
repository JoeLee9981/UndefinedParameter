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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">	
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div id="home-content" class=" metro">
			<div class="grid fluid">	
				<#if user??>
				<div id="homeBanner">
					<div class="page-content">
						<div id="bannerContent" class="row">
							<div>
								<div class="row">
									<div class="span3">
										<img src="/assets/images/person_circ.png"></img>
									</div>
									<div id="bannerHeader" class="span5">
										<h2><strong>Studying is hard right?</strong></h2>
										<span class="subheader-secondary">
											<strong>Not anymore!</strong> Whether for school, certification courses, job interviews, or just fun, QuizZing gives you the tools to make studying simple.
										</span>
										<div id="headerButtons">
											<button type="button" class="warning large" onclick="location.href = '/about'">Learn More</button>
											<button type="button" class="primary large" onclick="location.href = '/tutorial'">Take the Tutorial</button>	
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<#else>
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
										<p id="loginErrorLabel" class="tertiary-text-secondary errorFormText1 loginError"></p>		
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
										<h2><strong>Studying is hard right?</strong></h2>
										<span class="subheader-secondary">
											<strong>Not anymore!</strong> Whether for school, certification courses, job interviews or just fun. QuizZing gives you the tools to make studying simple.
										</span>
										
									</div>
									
								</div>
								<div id="headerButtons span9">
									<button type="button" onclick="location.href = '/register'" class="success large" style="margin-right: 10px">Register</button>
									<button type="button" class="warning large" onclick="location.href = '/about'" style="margin-right: 10px">Learn More</button>
									<button type="button" class="primary large" onclick="location.href = '/tutorial'">Take the Tutorial</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				</#if>
				<div class="page-content">
					<div class="grid fluid">		
						
					    <!-- Registered Groups -->
					    <#if userGroups??>
						    <div class="row noMargin">
						    	<div>
									<h4>Your Registered Groups</h4>
								</div>
							</div>
						
						    <div class="row noMargin">
								<table class="table hovered striped">
			                        <thead>
				                       <tr>
							                <th class="text-left">Group</th>
										    <th title="Members" class="text-center"><i class="icon-user-3 on-right"></i></th>
										    <th title="Quizzes" class="text-center"><i class="icon-clipboard-2 on-right"></i></th>
										    <th title="Questions" class="text-center"><i class="icon-help-2 on-right"></i></th>
										    <th class="text-center">Date Created</th>
				                        </tr>
			                        </thead>
			
			                        <tbody>
				                        <#list userGroups as group>
											<tr>
												<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
												<td class="text-center">${group.memberCount}</td>
												<td class="text-center">${group.quizCount}</td>
												<td class="text-center">${group.questionCount}</td>
												<td class="text-center">${group.dateAsString}</td>
											</tr>
										</#list>     
			                        </tbody>
			
			                        <tfoot></tfoot>
			                    </table>    
						    </div>
					    </#if>
					    
					    <!-- Top Groups -->
					    <div class="row noMargin">
					    	<div>
								<h4>Top Groups</h4>
							</div>
						</div>
					    <div class="row noMargin">
							<table class="table hovered striped">
		                        <thead>
						            <tr>
						                <th class="text-left">Group</th>
									    <th title="Members" class="text-center"><i class="icon-user-3 on-right"></i></th>
									    <th title="Quizzes" class="text-center"><i class="icon-clipboard-2 on-right"></i></th>
									    <th title="Questions" class="text-center"><i class="icon-help-2 on-right"></i></th>
									    <th class="text-center">Date Created</th>
			                        </tr>
		                        </thead>
		
		                        <tbody>
			                        <#list topGroups as group>
										<tr>
											<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
											<td class="text-center">${group.memberCount}</td>
											<td class="text-center">${group.quizCount}</td>
											<td class="text-center">${group.questionCount}</td>
											<td class="text-center">${group.dateAsString}</td>
										</tr>
									</#list>     
		                        </tbody>
		
		                        <tfoot></tfoot>
		                    </table>    
					    </div>
					</div>
				</div>
				
				<div style="padding-top: 50px" class="row">
					<#include "../includes/footer.ftl">
				</div>
			</div>
		</div>
	</body>
</html>


<script>
	
	function register(groupId) {

		$.ajax({
		    url: '/orgs/org/register?groupId=' + groupId,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/group/top';
		    },
		    error: function(error) {
		    	displayError("Unable to register for the group");
		    }
		});
	}
	
	function leave(groupId) {
	
		$.ajax({
		    url: '/orgs/org/leave?groupId=' + groupId,
		    type: 'DELETE',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/group/top';
		    },
		    error: function(error) {
		    	displayError("There was an error when attempting to leave the group");
		    }
		});
	}
	
	function displayError(message) {
		alert(message);
	}

	$('#loginForm').submit(function(event) {
		event.preventDefault();
		
		var username = $('#email').val();
		var password = $('#password').val();
		
		if(!username) {
			doLoginError("Please enter your email");
			return;
		}
		
		if(!password) {
			doLoginError("Please enter your password");
			return;
		}

		$.ajax({
		    url: '/login',
		    username: username,
		    password: password,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);

		    	window.location='/group/top';
		    },
		    error: function(error) {
		    	doLoginError("Invalid email and/or password");
		    }
		});
	});

	function doLoginError(message) {
		document.getElementById('loginErrorLabel').innerHTML = message;
	}
	
</script>