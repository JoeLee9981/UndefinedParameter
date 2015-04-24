<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" href="/assets/plugins/metro_ui/min/iconFont.min.css" >
		<link rel="stylesheet" href="/assets/css/overrides.css">
		<link rel="stylesheet" href="/assets/css/groups.css">
		<script src="/assets/scripts/message.js"></script>
		<script src="/assets/plugins/metro_ui/js/metro-accordion.js"></script>
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
		<link rel="stylesheet" type="text/css" href="/assets/css/categories.css" />	
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
						    <div class="indent30 span12">
						    	<div class="row noMargin">
									<h2 style="text-shadow: 0 0 3px #000000, 0 0 5px #000000;" class="fg-white">
										<strong>${group.name}</strong> 
										<#if moderator>
										<a href="/group/edit?groupId=${group.id}" id="editGroup"><i title="manage group" class="icon-pencil place-right bg-green" style="color: white; padding: 10px; border-radius: 50%; margin-right: 15px"></i></a>
										</#if>
									</h2>
									<p style="text-shadow: 0 0 3px #000000, 0 0 5px #000000;" class="fg-white"><strong>${group.description}</strong></p>
								</div>	
							</div>
					    </div>
					</div>
					
					<div class="row noMargin">
						<nav class="navigation-bar white" id="groupNav">
						    <nav class="navigation-bar-content">
						    	<a href="" class="element" id="memberLink" title="Members">
						    		<strong>
						    			<i class="icon-user-3 on-right"></i>  Members (${group.memberCount?html})
						    		</strong>
						    	</a>
						    	<span class="element-divider"></span>
						    	<a href="" id="quizLink" class="element" title="Quizzes">
							     	<strong>
						        		<i class="icon-clipboard-2 on-right"></i>  Quizzes (${quizCount?html})
						        	</strong>
						    	</a>
						    	<span class="element-divider"></span>
						    	<a href="#" class="element" id="questionLink" title="Questions">
						        	<strong>
						        		<i class="icon-help-2 on-right"></i>  Questions (${questionCount?html})
						        	</strong>
					        	</a>
					        	<span class="element-divider"></span>
					        	<a href="#" class="element" id="flagLink" title="Flags">
						        	<strong>
						        		<#if group.flagCount &gt; 0>
						        		<i class="icon-flag-2 fg-red on-right"></i>  Flags (${group.flagCount})
						        		<#else>
						        		<i class="icon-flag-2 on-right"></i>  No Flags
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
						<#if userQuizzes??>			
						<div class="accordion with-marker" data-role="accordion" data-closeany="false">
							<div class="accordion-frame <#if userQuizzes?size gt 0>active</#if>">
								<a href="#" class="heading bg-lightBlue fg-white">My Group Quizzes</a>			
								<div class="content">									
									<#if userQuizzes?size gt 0>								
										<table class="table hovered striped">
											<tbody>
												<#list userQuizzes as quiz>
													<tr>
														<td class="padding5">
															<a href="/quiz?groupId=${group.id}&quizId=${quiz.quizId}">${quiz.name}</a>
														</td>
														<td class="padding5">
															<span>${quiz.description}</span>
														</td>
														<td width="50px" class="padding5">
															<span class="place-right" title="Number of Questions"><i class="icon-help-2 on-right"></i> ${quiz.questionCount}</span>
														</td>
														<td width="50px" class="padding5">
															<span class="place-right" title="Quiz Difficulty"><i class="icon-power on-right"></i> ${quiz.difficulty}</span>
														</td>
														<td width="50px" class="padding5">
															<span class="place-right" title="Quiz Rating"><i class="icon-star-3 on-right"></i> ${quiz.rating}</span>
														</td>
														<td width="90px" class="padding5">
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
							</#if>
						</div>				
						<div class="accordion with-marker" data-role="accordion" data-closeany="false">
							<div class="accordion-frame <#if user??><#else>active</#if>">
								<a href="#" class="heading bg-lightBlue fg-white">All Group Quizzes</a>			
								<div class="content">									
									<#if quizzes?size gt 0>								
										<table class="table hovered striped">
											<tbody>
												<#list quizzes as quiz>
													<tr>
														<td class="padding5">
															<a href="/quiz?groupId=${group.id}&quizId=${quiz.quizId}">${quiz.name}</a>
														</td>
														<td class="padding5">
															<span>${quiz.description}</span>
														</td>
														<td width="50px" class="padding5">
															<span class="place-right" title="Number of Questions"><i class="icon-help-2 on-right"></i> ${quiz.questionCount}</span>
														</td>
														<td width="50px" class="padding5">
															<span class="place-right" title="Quiz Difficulty"><i class="icon-power on-right"></i> ${quiz.difficulty}</span>
														</td>
														<td width="50px" class="padding5">
															<span class="place-right" title="Quiz Rating"><i class="icon-star-3 on-right"></i> ${quiz.rating}</span>
														</td>
														<td width="90px" class="padding5">
															<span class="place-right" title="Expected Time"><i class="icon-busy on-right"></i> ${quiz.timeString}</span>
														</td>
														<#if user?? && (user.admin || quiz.open || user.id = quiz.creatorId)>
														<td class="padding5">
															<span class="place-right" title="Edit Quiz"><a href="/quiz/edit?groupId=${group.id}&quizId=${quiz.quizId}"><i class="icon-pencil join"></i></a></span>
														</td>
														<#else>
														<td></td>
														</#if>
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
		</div>	
		<div style="padding-top: 90px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
	
</html>

<script>

	$('#flagLink').click(function(event) {

		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/group/flagged?groupId=" + ${group.id},
			success: function(data) {
				$('#group-content').html(data);
			},
			error: function(error) {
		    	$('#group-content').html("<h3>No Flagged Questions</h3>");
		    }
		});
	});
	
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

		var content = '<div style="margin: 10px" class="grid span7">' +
					  		'<h3 class="text-center">Are you sure you wish to leave this group?</h3><br/>' +
					  		'<div class="span3 offset2">' +
								'<button style="margin: 5px" onclick="doLeaveGroup(' + groupId + ')" class="success large center">Leave</button>' +
								'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
							'</div>' +
					  '</div>';
		
		$.Dialog({
		shadow: true,
		overlay: true,
		flat: true,
		icon: '<span class="icon-warning fg-amber"></span>',
		title: 'Leave Group',
		padding: 10,
		content: content
		});

	}

	function doLeaveGroup(groupId) {
		$.ajax({
		    url: '/orgs/org/leave?groupId=' + groupId,
		    type: 'DELETE',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/orgs/org?id=' + ${organization.id};
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