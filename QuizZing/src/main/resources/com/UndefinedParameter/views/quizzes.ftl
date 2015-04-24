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
		<style>
			ul
			{
				cursor: default;
			}
			
			li
			{
				cursor: default;
			}
		</style>
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		
		<div id="home-content" class="metro">
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
					<#if userQuizzes??>
						<div class="home-subsection">
							<h3>Your Quizzes</h3>
							<table class="table hovered striped">
								<thead>
								<tr>
					                <th class="text-left">Quiz</th>
					                <th class="text-left">Group</th>
					                <th class="text-left">Creator</th>
					                <th class="text-left">Description</th>
					                <th title="Questions"><i class="icon-help-2 on-right"></i></th>
					                <th style="width: 120px">Difficulty</th>
					                <th style="width: 120px">Rating</th>
					            </tr>
								</thead>
								<tbody>
								<#list userQuizzes as quiz>	
									<tr>
										<td><a href="/quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
										<td class="text-left left"><a href="/group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a></td>
										<td class="text-left left"><a href="/user?userid=${quiz.creatorId}">${quiz.creatorName}</a></td>
										<td class="text-left left">
											<#if quiz.description??>
												<#if quiz.description?length &gt; 20>
													${quiz.description?substring(0, 20)}...
												<#else>
													${quiz.description}
												</#if>
											<#else>
												No Description
											</#if>
										</td>
										<td class="text-center center">${quiz.questionCount}</td>
										<td class="text-right right">
											<div id="diff${quiz.quizId}" class="rating small fg-red"></div>
										</td>
										<td class="text-right right">
											<div id="rating${quiz.quizId}" class="rating small"></div>
										</td>
										
										<script>
											//Star rating for quiz quality (entry page)
											$(function() {
												$("#rating${quiz.quizId}").rating({
													static: true,
													score: ${quiz.rating},
													stars: 5,
													showHint: true,
													hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
												});
											});
											
											//Star rating for quiz quality (entry page)
											$(function() {
												$("#diff${quiz.quizId}").rating({
													static: true,
													score: ${quiz.difficulty},
													stars: 5,
													showHint: true,
													hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
												});
											});
										</script>
									</tr>
								</#list>
								</tbody>
								<tfoot></tfoot>
							</table>
						</div>
					</#if>
					
					<#if topQuizzes??>
						<div class="home-subsection">
							<h3>Top Quizzes</h3>
							<table class="table hovered striped">
								<thead>
									<tr>
						                <th class="text-left">Quiz</th>
						                <th class="text-left">Group</th>
						                <th class="text-left">Creator</th>
						                <th class="text-left">Description</th>
						                <th title="Questions"><i class="icon-help-2 on-right"></i></th>
						                <th style="width: 120px">Difficulty</th>
						                <th style="width: 120px">Rating</th>
						            </tr>
								</thead>
								<tbody>
								<#list topQuizzes as quiz>
								
									<tr>
										<td><a href="/quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
										<td class="text-left left"><a href="/group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a></td>
										<td class="text-left left"><a href="/user?userid=${quiz.creatorId}">${quiz.creatorName}</a></td>
										<td class="text-left left">
											<#if quiz.description??>
												<#if quiz.description?length &gt; 20>
													${quiz.description?substring(0, 20)}...
												<#else>
													${quiz.description}
												</#if>
											<#else>
												No Description
											</#if>
										</td>
										<td class="text-center center">${quiz.questionCount}</td>
										<td class="text-right right">
											<div id="top-diff${quiz.quizId}" class="rating small fg-red"></div>
										</td>
										<td class="text-right right">
											<div id="top-rating${quiz.quizId}" class="rating small"></div>
										</td>
										
										<script>
											//Star rating for quiz quality (entry page)
											$(function() {
												$("#top-rating${quiz.quizId}").rating({
													static: true,
													score: ${quiz.rating},
													stars: 5,
													showHint: true,
													hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
												});
											});
											
											//Star rating for quiz quality (entry page)
											$(function() {
												$("#top-diff${quiz.quizId}").rating({
													static: true,
													score: ${quiz.difficulty},
													stars: 5,
													showHint: true,
													hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
												});
											});
										</script>
									</tr>
								</#list>
								</tbody>
								<tfoot></tfoot>
							</table>
							<br/>

						</div>
					</#if>
					</div>
				</div>
			</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>

<script>
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
		    	window.location='/quiz/quizzes';
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

