<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" href="/assets/plugins/metro_ui/min/iconFont.min.css" >
		<link rel="stylesheet" href="/assets/css/overrides.css">
		<link rel="stylesheet" href="/assets/css/groups.css">
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
	</head>

	<body>
		
		<#include "../includes/navigation.ftl">
		
			<div class="metro">
				<div class="grid fluid">		
					<div class="page-content">				
					    <div class="row">
					    	<nav class="breadcrumbs">
		                        <ul>
		                            <li><a href="/"><i class="icon-home"></i></a></li>
		                            <li><a href="/orgs">Organizations</a></li>
		                            <li><a href="/orgs/org?id=${organization.id}">${organization.name}</a></li>
		                            <li class="active"><a>${group.name}</a></li>
		                        </ul>
		                    </nav>
						</div>
						<div class="row" id="groupImageContainer">
						</div>
						<div class="row noMargin">
							<nav class="navigation-bar white" id="groupNav">
							    <nav class="navigation-bar-content">
							    	<a href="#" class="element todo">
							    		<strong>
							    			${group.memberCount?html} 
							        		<#if group.memberCount == 1>
							        			member
							        		<#else>
							        			members
							        		</#if>
							    		</strong>
							    	</a>
							    	<span class="element-divider"></span>
							    	<a href="#" class="element todo">
								     	<strong>
							        		${quizCount?html} 
							        		<#if group.quizCount == 1>
							        			quiz
							        		<#else>
							        			quizzes
							        		</#if>
							        	</strong>
							    	</a>
							    	<span class="element-divider"></span>
							    	<a href="#" class="element todo">
							        	<strong>
							        		${questionCount?html} 
							        		<#if group.questionCount == 1>
							        			question
							        		<#else>
							        			questions
							        		</#if>
							        	</strong>
						        	</a>
						        	<span class="element-divider"></span>
							        <#if userIsGroupMember>  
							        	<a href="/quiz/create?groupId=${group.id}" class="element brand place-right"><strong><i class="icon-tools on-left"></i>Create Quiz</strong></a>
								        <span class="element-divider place-right"></span>      
							        	<a href="#" class="element brand place-right" onclick="leave(${group.id})"><strong>Leave Group</strong></a>
						        	<#else>
						        		<a href="#" class="element brand place-right" onclick="register(${group.id})"><strong>Join Group</strong></a>
						        	</#if>
						        	<span class="element-divider place-right"></span>
							    </nav>
							</nav>
						</div>
						<div class="row noMargin">
							<h2><strong>${group.name}<strong></h2>
							<p>${group.description}</p>
						</div>	
						
						
						<br/><br/><br/><br/><br/>
						<#if userQuizzes??>
								<div class="home-subsection">
									<h3>Your Group Quizzes</h3>
									<table>
											<tr>
												<th>Quiz Name</th>
												<th>Description</th>
												<th># of Questions</th>
												<th>Difficulty</th>
												<th>Rating</th>
												<th>Time</th>
												<th>Edit</th>
											</tr>
											
										<#list userQuizzes as quiz>		
										
											<tr>
												<td>
													<a href="/quiz?groupId=${group.id}&quizId=${quiz.quizId}"><h3 class="text-info">${quiz.name} </h3></a>
												</td>
												<td>
													<h3>${quiz.description}</h3>
												</td>
												<td>
													<h3>${quiz.questionCount}</h3>
												</td>
												<td>
													<h3>${quiz.difficulty}</h3>
												</td>
												<td>
													<h3>${quiz.rating}</h3>
												</td>
												<td>
													<h3>${quiz.time}</h3>
												</td>
												<td>
													<a href="/quiz/edit?groupId=${group.id}&quizId=${quiz.quizId}"><h3 class="text-success"><button id="editQuizButton">+</button></h3></a>
												</td>
											</tr>
										</#list>
									</table>
								</div>
							</#if>
							<div class="home-subsection">
								<h3>All Group Quizzes</h3>
								<table>
										
										<tr>
											<th>Quiz Name</th>
											<th>Description</th>
											<th># of Questions</th>
											<th>Difficulty</th>
											<th>Rating</th>
											<th>Time</th>
										</tr>
										
									<#list quizzes as quiz>
										
										<tr>
											<td>
												<h3><a href="/quiz?groupId=${group.id}&quizId=${quiz.quizId}"><h3 class="text-info">${quiz.name} </h3></a>
											</td>
											<td>
												<h3>${quiz.description}</h3>
											</td>
											<td>
												<h3>${quiz.questionCount}</h3>
											</td>
											<td>
												<h3>${quiz.difficulty}</h3>
											</td>
											<td>
												<h3>${quiz.rating}</h3>
											</td>
											<td>
												<h3>${quiz.time}</h3>
											</td>
										</tr>
									</#list>
								</table>
								<br/>

							</div>
						
						
						
						
														
					</div>
				</div>
			<div>	
								
		<#include "../includes/footer.ftl">

	</body>
</html>

<script>

	function register(groupId) {

		$.ajax({
		    url: '/orgs/org/register?groupId=' + groupId,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/group?groupId=${group.id}';
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
		    	window.location='/group?groupId=${group.id}';
		    },
		    error: function(error) {
		    	displayError("There was an error when attempting to leave the group");
		    }
		});
	}
	
	function displayError(message) {
		alert(message);
	}
	
</script>