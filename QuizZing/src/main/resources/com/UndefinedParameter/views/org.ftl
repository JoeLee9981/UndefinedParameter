<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/message.js"></script>
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/organizations.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<div class="grid fluid">
				<div class="row">
					<nav class="breadcrumbs">
                        <ul>
                            <li><a href="/"><i class="icon-home"></i></a></li>
                            <li><a href="/orgs">Organizations</a></li>
                            <li class="active"><a>${organization.name}</a></li>
                        </ul>
                    </nav>
				</div>			

				<div class="row">
					<div id="orgImage" style="position:relative;">
						<div class="span12" style="position:absolute;left:20px;padding:20px;">
							<h2 style="text-shadow: 0 0 3px #000000, 0 0 5px #000000;" class="fg-white">
								<strong>${organization.name?html}</strong>
								<#if moderator>
								<a href="/orgs/org/edit?orgId=${organization.id}" id="editOrg"><i title="manage organization" class="icon-pencil place-right bg-green" style="color: white; padding: 10px; border-radius: 50%; margin-top: -10px; margin-right: 20px"></i></a>
								</#if>
							</h2>
							<p style="text-shadow: 0 0 3px #000000, 0 0 5px #000000;" class="fg-white">${organization.location}</p>
						</div>
						<div style="position:absolute;right:20px;bottom:20px;">
							<#if user??>
								<#if userIsInOrganization>
									<button onclick="leaveOrg('${organization.id}')" style="margin-right:10px;" class="danger large">Leave Organization</button>				
								<#else>
									<button onclick="joinOrg('${organization.id}')" style="margin-right:10px;" class="success large">Join Organization</button>
								</#if>
							</#if>	
						</div>
					</div>
				</div>
				<div class="row noMargin">
					<nav class="navigation-bar white white-custom">
					    <nav class="navigation-bar-content">
					        <item class="element active"><a href="" id="groupsLink"><i class="icon-link on-right"></i> <strong>Groups (${groupCount})</strong></a></item>
					        <item class="element-divider"></item>
					        <item class="element place-right"><a href="/orgs/org/create?orgId=${organization.id}"><strong><i class="icon-tools on-left"></i>Create Group</strong></a></item>
					        <item class="element-divider place-right"></item>      		        
					        <item class="element place-right"><a href="" id="quizzesLink"><i class="icon-clipboard-2 on-right"></i> <strong>Quizzes (${quizCount})</strong></a></item>
					        <item class="element-divider place-right"></item>
					        <item class="element place-right"><a href="" id="memberLink"><i class="icon-user-3 on-right"></i> <strong>Members (${memberCount})</strong></a></item>
					    	<item class="element-divider place-right"></item>
						    
					    </nav>
					</nav>					
				</div>

				<div class="row">
					<div class="span8">
						<div class="row noMargin" id="quizzesDiv">
						<#if quizzes?size gt 0>								
							<table class="table hovered striped">
								<thead>
									<tr>
										<th>Quiz</th>
										<th>Group</th>
										<th>Description</th>
										<th><i title="Questions" class="icon-help-2 on-right"></i></th>
										<th><i title="Difficulty" class="icon-power on-right"></i></th>
										<th><i title="Rating" class="icon-star-3 on-right"></i></th>
									</tr>
								</thead>
								<tbody>
										
									<#list quizzes as quiz>
										<tr>
											<td class="padding5">
												<a href="/quiz?groupId=${quiz.parentGroupId}&quizId=${quiz.quizId}">${quiz.name}</a>
											</td>
											<td class="padding5">
												<a href="/group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a>
											</td>
											<td class="padding5">
												<span>${quiz.description}</span>
											</td>
											<td width="50px" class="padding5">
												<span class="place-right" title="Number of Questions">${quiz.questionCount}</span>
											</td>
											<td width="50px" class="padding5">
												<span class="place-right" title="Quiz Difficulty">${quiz.difficulty}</span>
											</td>
											<td width="50px" class="padding5">
												<span class="place-right" title="Quiz Rating">${quiz.rating}</span>
											</td>
										</tr>
											<!-- <td class="right padding5"><a href="#" class="place-right" onClick="joinOrg()"><i class="icon-plus join"></i></a></td> -->
									</#list>
								</tbody>
							</table>													
						<#else>
							<h3>There are no quizzes for this group.</h3>
						</#if>
						</div>			
					
						<div class="row noMargin" id="membersDiv">
							<#if members??>
								<table class="table hovered striped">
									<#list members as member>
										<tr>
											<td class="padding5">
												<a href="/user?userid=${member.userId}">${member.displayName}
												<#if member.contribution &gt; 1000><i title="Moderator" class="icon-heart on-right"></i></#if>
												</a>
											</td>
											<td class="padding5">
											<span class="place-right">${member.contribution}
												<#if member.contribution &gt;= 1000>
												<i title="Contribution Score" class="icon-medal on-right fg-amber"></i>
												<#elseif member.contribution &gt;= 800>
												<i title="Contribution Score" class="icon-medal on-right fg-magenta"></i>
												<#elseif member.contribution &gt;= 600>
												<i title="Contribution Score" class="icon-medal on-right fg-emerald"></i>
												<#elseif member.contribution &gt;= 400>
												<i title="Contribution Score" class="icon-medal on-right fg-red"></i>
												<#elseif member.contribution &gt;= 200>
												<i title="Contribution Score" class="icon-medal on-right fg-olive"></i>
												<#else>
												<i title="Contribution Score" class="icon-medal on-right"></i>
												</#if>
											</span>
											</td>
											<td class="padding5"><span class="place-right">${member.questions}<i title="Questions" class="icon-help-2 on-right"></i></span></td>
											<td class="padding5"><span class="place-right">${member.quizzes}<i title="Quizzes" class="icon-clipboard-2 on-right"></i></span></td>
											<td class="padding5"><span class="place-right">${member.joinDateString}</span></td>
											<#if user?? && user.id != member.userId>
											<td class="padding5">
												<span class="place-right">
													<a href="" id="messageLink${member_index}" onclick="sendMessage(${user.id}, ${member.userId})">
														<i class="icon-mail on-left fg-lightBlue"></i>
													</a>
												</span>
											</td>
											
											<script>$('#messageLink${member_index}').click(function(event) { event.preventDefault(); })</script>
											</#if>
										</tr>
									</#list>
								</table>
							<#else>
								<h3>There are currently no members of this group</h3>
							</#if>
						</div>
						
						<div class="row noMargin" id="groupsDiv">					
							<div class="accordion with-marker" data-role="accordion" data-closeany="false">
								
								<#if user??>
									<div class="accordion-frame <#if registeredGroups?size gt 0>active</#if>">
										<a href="#" class="heading bg-lightBlue fg-white">My Groups</a>			
										<div class="content">									
											<#if registeredGroups?size gt 0>								
												<table class="table hovered striped">
													<tbody>
														<#list registeredGroups as group>
															<tr>
																<td class="padding5"><a href="/group?groupId=${group.id}">${group.name}</a></td>
																<td class="padding5"><span class="place-right">${group.quizCount}<i class="icon-clipboard-2 on-right"></i></span></td>
																<td class="padding5"><span class="place-right">${group.questionCount}<i class="icon-help-2 on-right"></i></span></td>
																<td class="padding5"><span class="place-right">${group.memberCount}<i class="icon-user-3 on-right"></i></span></td>
																<#if user??>
																	<td class="right padding5"><a href="#" class="place-right" onClick="leaveGroup(${group.id})"><i class="icon-minus leave"></i></a></td>
																</#if>
															</tr>
														</#list>
													</tbody>
												</table>													
											<#else>
												<div style="text-align:center;padding:20px;">
													<h6>You have not joined any groups.</h6>
												</div>
											</#if>																				
										</div>
									</div>
								</#if>
								
								
								<div class="accordion-frame <#if groups?size gt 0>active</#if>">
									<a href="#" class="heading bg-lightBlue fg-white">Recommended Groups</a>			
									<div class="content">									
										<#if groups?size gt 0>								
											<table class="table hovered striped">
												<tbody>
													<#list groups as group>
														<tr>
															<td class="padding5"><a href="/group?groupId=${group.id}">${group.name}</a></td>
															<td class="padding5"><span class="place-right">${group.quizCount}<i class="icon-clipboard-2 on-right"></i></span></td>
															<td class="padding5"><span class="place-right">${group.questionCount}<i class="icon-help-2 on-right"></i></span></td>
															<td class="padding5"><span class="place-right">${group.memberCount}<i class="icon-user-3 on-right"></i></span></td>
			
															<#if user??>
																<td class="right padding5"><a href="#" class="place-right" onClick="joinGroup(${group.id})"><i class="icon-plus join"></i></a></td>
															</#if>
														</tr>
													</#list>
												</tbody>
											</table>													
										<#else>
											<div style="text-align:center;padding:20px;">
												<h6>You have no recommended groups.</h6>
											</div>
										</#if>																				
									</div>
								</div>														
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="row noMargin">
							<p>${organization.description?html}</p>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>


<script>

	$(document).ready(function() {

		$('#membersDiv').hide();
		$('#quizzesDiv').hide();
	})

	$('#memberLink').click(function(event) {

		event.preventDefault();
		$('#membersDiv').show();
		$('#groupsDiv').hide();
		$('#quizzesDiv').hide();
	});

	$('#groupsLink').click(function(event) {
		event.preventDefault();
		$('#membersDiv').hide();
		$('#groupsDiv').show();
		$('#quizzesDiv').hide();
	});
	
	$('#quizzesLink').click(function(event) {
		event.preventDefault();
		$('#membersDiv').hide();
		$('#groupsDiv').hide();
		$('#quizzesDiv').show();
	});
			
	function joinOrg(orgId) {
		$.ajax({
		    url: '/orgs/register?orgId=' + orgId,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/orgs/org?id=' + orgId;
		    },
		    error: function(error) {
		    	displayError("Unable to register for organization");
		    }
		});
	}
	
	function leaveOrg(orgId) {
		var content = '<div style="margin: 10px" class="grid span7">' +
					  		'<h3 class="text-center">Are you sure you wish to leave this organization?</h3><br/>' +
					  		'<div class="span3 offset2">' +
								'<button style="margin: 5px" onclick="doLeave(' + orgId + ')" class="success large center">Leave</button>' +
								'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
							'</div>' +
					  '</div>';
		
		$.Dialog({
		shadow: true,
		overlay: true,
		flat: true,
		icon: '<span class="icon-warning fg-amber"></span>',
		title: 'Leave Organization',
		padding: 10,
		content: content
		});
	}

	function doLeave(orgId) {
		$.ajax({
		    url: '/orgs/leave?orgId=' + orgId,
		    type: 'DELETE',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/orgs';
		    },
		    error: function(error) {
		    	displayError("There was an error when attempting to leave organization");
		    }
		});
	}

	
	function joinGroup(groupId) {

		$.ajax({
		    url: '/orgs/org/register?groupId=' + groupId,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/orgs/org?id=' + ${organization.id};
		    },
		    error: function(error) {
		    	displayError("Unable to register for the group");
		    }
		});
	}
	
	function leaveGroup(groupId) {

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