<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/organizations.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
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
						<div style="position:absolute;top:20px;left:20px;padding:20px;background:red;">
							<h2 class="fg-white"><strong>${organization.name?html}</strong></h2>
							<p class="fg-white">${organization.city?html}, ${organization.state?html}, ${organization.country?html}</p>
						</div>
						<div style="position:absolute;right:20px;bottom:20px;">
							<#if user??>
								<#if userIsInOrganization>
									<button onclick="leaveOrg('${organization.id}')" style="margin-right:10px;" class="bg-white border1">Leave Organization</button>				
								<#else>
									<button onclick="joinOrg('${organization.id}')" style="margin-right:10px;" class="bg-white border1">Join Organization</button>
								</#if>
							</#if>	
							<button class="bg-white border1" onclick="location.href='/orgs/org/create?orgId=${organization.id}'"><i class="icon-tools on-left"></i>Create Group</button>
						</div>
					</div>
				</div>
				<div class="row noMargin">
					<nav class="navigation-bar white white-custom">
					    <nav class="navigation-bar-content">
					        <item class="element active"><a href="#" class="todo"><strong>${organization.name?html}</strong></a></item>
					        <item class="element-divider"></item>
					        <item class="element"><a href="#" class="todo"><strong>About</strong></a></item>
					        <item class="element-divider"></item>
					        <item class="element"><a href="#" class="todo"><strong>Statistics</strong></a></item>
					        
		        	        <div class="element input-element noHover">
					            <form id="searchOrg">
					                <div class="input-control text searchbox">
					                    <input type="text" placeholder="Search ${organization.name?html}..." class="size5">
					                    <button class="btn-search todo"></button>
					                </div>
					            </form>
					        </div>				        		        
					        <item class="element place-right"><a href="#" class="todo"><strong>${organization.quizCount?html} <i class="icon-clipboard-2 on-right"></i></strong></a></item>
					        <item class="element place-right"><a href="#" class="todo"><strong>${organization.questionCount?html} <i class="icon-help-2 on-right"></i></strong></a></item>
					        <item class="element place-right"><a href="#" class="todo"><strong>${organization.memberCount?html} <i class="icon-user-3 on-right"></i></strong></a></item>
					    </nav>
					</nav>					
				</div>

				<div class="row">
					<div class="span8">
						<div class="row noMargin">					
							<div class="accordion with-marker" data-role="accordion" data-closeany="false">
								
								<#if user??>
									<div class="accordion-frame <#if registeredGroups?size gt 0>active</#if>">
										<a href="#" class="heading bg-lightBlue fg-white">My Joined Groups</a>			
										<div class="content">									
											<#if registeredGroups?size gt 0>								
												<table class="table hovered">
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
								
								<#if user??>
									<div class="accordion-frame">
										<a href="#" class="heading bg-lightBlue fg-white">My Created Groups</a>
										<div class="content">			
											<div style="text-align:center;padding:20px;">								
												<h6>You have not created any groups.</h6>						
											</div>														
										
										</div>
									</div>
								</#if>
								
								<div class="accordion-frame <#if groups?size gt 0>active</#if>">
									<a href="#" class="heading bg-lightBlue fg-white">Recommended Groups</a>			
									<div class="content">									
										<#if groups?size gt 0>								
											<table class="table hovered">
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
		<#include "../includes/footer.ftl">
	</body>
</html>


<script>
			
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