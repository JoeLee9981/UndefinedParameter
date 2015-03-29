<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" href="/assets/plugins/metro_ui/min/iconFont.min.css" >
		<link rel="stylesheet" href="/assets/css/overrides.css">
		<link rel="stylesheet" href="/assets/css/groups.css">
		<script src="/assets/plugins/metro_ui/js/metro-accordion.js"></script>
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
							<div class="row noMargin">
							    <div class="indent30 span6">
							    	<div class="row noMargin">
										<h2><strong>${group.name}<strong></h2>
										<p>${group.description}</p>
									</div>	
								</div>
						    </div>
						</div>
						
						<div class="row noMargin">
							<nav class="navigation-bar white" id="groupNav">
							    <nav class="navigation-bar-content">
							    	<a href="" class="element" id="memberLink">
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
							    	<a href="" id="quizLink" class="element">
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
							    	<a href="#" class="element" id="questionLink">
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
						
						<div id="group-content"></div>
	
						<div class="row">					
							<div class="accordion with-marker" data-role="accordion" data-closeany="false">
								<div class="accordion-frame <#if userQuizzes?size gt 0>active</#if>">
									<a href="#" class="heading bg-lightBlue fg-white">My Group Quizzes</a>			
									<div class="content">									
										<#if userQuizzes?size gt 0>								
											<table class="table hovered">
												<tbody>
													<#list userQuizzes as quiz>
														<tr>
															<td class="padding5">
																<a href="/quiz?groupId=${group.id}&quizId=${quiz.quizId}">${quiz.name}</a>
															</td>
															<td class="padding5">
																<span>${quiz.description}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Number of Questions"><i class="icon-help-2 on-right"></i> ${quiz.questionCount}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Quiz Difficulty"><i class="icon-power on-right"></i> ${quiz.difficulty}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Quiz Rating"><i class="icon-star-3 on-right"></i> ${quiz.rating}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Expected Time"><i class="icon-busy on-right"></i> ${quiz.timeString}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Edit Quiz"><a href="/quiz/edit?groupId=${group.id}&quizId=${quiz.quizId}"><i class="icon-pencil join"></i></a></span>
															</td>
														</tr>
															<!-- <td class="right padding5"><a href="#" class="place-right" onClick="joinOrg({org.id})"><i class="icon-plus join"></i></a></td> -->
													</#list>
												</tbody>
											</table>													
										<#else>
											<div style="text-align:center;padding:20px;">
												<h6>There are no quizzes for this group.</h6>
											</div>
										</#if>																				
									</div>
								</div>		
							</div>				
							<div class="accordion with-marker" data-role="accordion" data-closeany="false">
								<div class="accordion-frame">
									<a href="#" class="heading bg-lightBlue fg-white">All Group Quizzes</a>			
									<div class="content">									
										<#if quizzes?size gt 0>								
											<table class="table hovered">
												<tbody>
													<#list quizzes as quiz>
														<tr>
															<td class="padding5">
																<a href="/quiz?groupId=${group.id}&quizId=${quiz.quizId}">${quiz.name}</a>
															</td>
															<td class="padding5">
																<span>${quiz.description}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Number of Questions"><i class="icon-help-2 on-right"></i> ${quiz.questionCount}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Quiz Difficulty"><i class="icon-power on-right"></i> ${quiz.difficulty}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Quiz Rating"><i class="icon-star-3 on-right"></i> ${quiz.rating}</span>
															</td>
															<td class="padding5">
																<span class="place-right" title="Expected Time"><i class="icon-busy on-right"></i> ${quiz.timeString}</span>
															</td>
														</tr>
															<!-- <td class="right padding5"><a href="#" class="place-right" onClick="joinOrg()"><i class="icon-plus join"></i></a></td> -->
													</#list>
												</tbody>
											</table>													
										<#else>
											<div style="text-align:center;padding:20px;">
												<h6>There are no quizzes for this group.</h6>
											</div>
										</#if>																				
									</div>
								</div>		
							</div>
						</div>										
					</div>
				</div>
			<div>	
								
		<#include "../includes/footer.ftl">

	</body>
</html>

<script>

	$('#quizLink').click(function(event) {
		event.preventDefault();
		$('#group-content').html("");
	});

	$('#memberLink').click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/group/members?groupId=" + ${group.id},
			success: function(data) {
				$('#group-content').html(data);
			},
			error: function(error) {
		    	$('#group-content').html("<h3>No Members Found</h3>");
		    }
		});

	});

	$('#questionLink').click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/group/questions?groupId=" + ${group.id},
			success: function(data) {
				$('#group-content').html(data);
			},
			error: function(error) {
		    	$('#group-content').html("<h3>No Questions Found</h3>");
		    }
		});

	});
	

	function register(groupId) {
		<#if user??>
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
		<#else>
		location.href='/login';
		</#if>
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