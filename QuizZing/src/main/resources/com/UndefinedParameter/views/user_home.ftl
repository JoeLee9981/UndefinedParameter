<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/home.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/quiz.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/home.css" rel="stylesheet">	
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">		
	</head>
	<body>
		
		<#include "../includes/navigation.ftl">


		<div id="home-content" class=" metro">
			<div class="grid fluid">
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
											<button type="button" class="success large todo" onclick="location.href = '#'">Take the Tutorial</button>	
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
						    	<item id="myQuizzesItem" class="element text-center span2 active"><a href="" id="myQuizzesLink"><strong>My Quizzes</strong></a></item>
						        <item id="myGroupsItem" class="element text-center span2"><a href="" id="myGroupsLink"><strong>My Groups</strong></a></item>
						        <item id="newQuizzesItem" class="element text-center span2"><a href="" id="newQuizzesLink"><strong>New Quizzes</strong></a></item>
						        <item id="topQuizzesItem" class="element text-center span2"><a href="" id="topQuizzesLink"><strong>Top Quizzes</strong></a></item>
						        <item id="topGroupsItem" class="element text-center span2"><a href="" id="topGroupsLink"><strong>Top Groups</strong></a></item>
						        <item id="topCategoriesItem" class="element text-center span2"><a href="" id="topCategoriesLink"><strong>Top Categories</strong></a></item>
						    </nav>
						</nav>					
					</div>
					<div class="row">
						<div id="quiz-div" class="span12">
							<div class="row noMargin">
								<h2><strong>My Quizzes</strong></h2>
								<#if quizzes??>
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
							        		<#list quizzes as quiz>
												<tr>
													<td><a href="quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
													<td class="text-left left"><a href="group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a></td>
													<td class="text-left left"><a href="user?userid=${quiz.creatorId}">${quiz.creatorName}</a></td>
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
							    <#else>
									<tr>
										<td colspan="3">
											<h3>No Quizzes Found</h3>
										</td>
									</tr>
								</#if>
							</div>     			
						</div>
					</div>
				</div>
			</div>	
		
					
									
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</div>

	</body>
	<script>
	
		$('#myQuizzesLink').click(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/quiz/myquizzes?userId=' + ${user.id},
			    type: 'GET',
			    success: function(data) {
			    	console.log(data);
					$('#quiz-div').html(data);
			    },
			    error: function(error) {
			    	$('#quiz-div').html("<p>No Quizzes Found</p>");
			    }
			});
			toggleActive('#myQuizzesItem');
		});
		
		$('#myGroupsLink').click(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/group/mygroups?userId=' + ${user.id},
			    type: 'GET',
			    success: function(data) {
			    	console.log(data);
					$('#quiz-div').html(data);
			    },
			    error: function(error) {
			    	$('#quiz-div').html("<p>No Groups Found</p>");
			    }
			});
			toggleActive('#myGroupsItem');
		});
		
		$('#newQuizzesLink').click(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/quiz/recent',
			    type: 'GET',
			    success: function(data) {
			    	console.log(data);
					$('#quiz-div').html(data);
			    },
			    error: function(error) {
			    	$('#quiz-div').html("<p>No Quizzes Found</p>");
			    }
			});
			toggleActive('#newQuizzesItem');
		});
	
		$('#topQuizzesLink').click(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/quiz/top',
			    type: 'GET',
			    success: function(data) {
			    	console.log(data);
					$('#quiz-div').html(data);
			    },
			    error: function(error) {
			    	$('#quiz-div').html("<p>No Quizzes Found</p>");
			    }
			});
			toggleActive('#topQuizzesItem');
		});
		
		$('#topGroupsLink').click(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/group/top_groups',
			    type: 'GET',
			    success: function(data) {
			    	console.log(data);
					$('#quiz-div').html(data);
			    },
			    error: function(error) {
			    	$('#quiz-div').html("<p>No Groups Found</p>");
			    }
			});
			toggleActive('#topGroupsItem');
		});
		
		$('#topCategoriesLink').click(function(event) {
			event.preventDefault();
			$.ajax({
			    url: '/quiz/top_categories',
			    type: 'GET',
			    success: function(data) {
			    	console.log(data);
					$('#quiz-div').html(data);
			    },
			    error: function(error) {
			    	$('#quiz-div').html("<p>No Categories Found</p>");
			    }
			});
			toggleActive('#topCategoriesItem');
		});

		function toggleActive(id) {
			$('#newQuizzesItem').attr("class","element text-center span2");
			$('#myQuizzesItem').attr("class","element text-center span2");
			$('#myGroupsItem').attr("class","element text-center span2");
			$('#topQuizzesItem').attr("class","element text-center span2");
			$('#topGroupsItem').attr("class","element text-center span2");
			$('#topCategoriesItem').attr("class","element text-center span2");
			$(id).attr("class","element text-center span2 active");
		}
	
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