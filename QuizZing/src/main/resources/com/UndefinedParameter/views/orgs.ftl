<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/js/metro-accordion.js"></script>
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
                            <li class="active"><a>Organizations</a></li>
                        </ul>
                    </nav>			
				</div>
						
				<div class="row">
					<div class="span8">
					
						<div class="row containerFill noMargin">
							<h4>Find an Organization</h4>
							<div class="">
								<div class="input-control text">
								    <input id="orgSearch" type="text" placeholder="Search for an Organization..."/>
								    <button class="btn-search" onclick="searchOrgs();"></button>
								</div>
							</div>
						</div>
				
						<div class="row">					
							<div class="accordion with-marker" data-role="accordion" data-closeany="false">
								
								<#if user??>
									<div class="accordion-frame <#if registeredOrganizations?size gt 0>active</#if>">
										<a href="#" class="heading bg-lightBlue fg-white">My Joined Organizations</a>			
										<div class="content">									
											<#if registeredOrganizations?size gt 0>								
												<table class="table hovered striped">
													<tbody>
														<#list registeredOrganizations as org>
															<tr>
																<td class="padding5"><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
																<td class="padding5"><span class="place-right">${org.quizCount}<i class="icon-clipboard-2 on-right"></i></span></td>
																<td class="padding5"><span class="place-right">${org.questionCount}<i class="icon-help-2 on-right"></i></span></td>
																<td class="padding5"><span class="place-right">${org.memberCount}<i class="icon-user-3 on-right"></i></span></td>
				
																<#if user??>
																	<td class="right padding5"><a href="#" class="place-right" onClick="leave(${org.id})"><i class="icon-minus leave"></i></a></td>
																</#if>
															</tr>
														</#list>
													</tbody>
												</table>													
											<#else>
												<div style="text-align:center;padding:20px;">
													<h6>You are not a member of any organization yet.</h6>
												</div>
											</#if>																				
										</div>
									</div>
								</#if>
								
								<div class="accordion-frame <#if organizations?size gt 0>active</#if>">
									<a href="#" class="heading bg-lightBlue fg-white">Recommended Organizations</a>			
									<div class="content">									
										<#if organizations?size gt 0>								
											<table class="table hovered striped">
												<tbody>
													<#list organizations as org>
														<tr>
															<td class="padding5"><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
															<td class="padding5"><span class="place-right">${org.quizCount}<i class="icon-clipboard-2 on-right"></i></span></td>
															<td class="padding5"><span class="place-right">${org.questionCount}<i class="icon-help-2 on-right"></i></span></td>
															<td class="padding5"><span class="place-right">${org.memberCount}<i class="icon-user-3 on-right"></i></span></td>
			
															<#if user??>
																<td class="right padding5"><a href="#" class="place-right" onClick="joinOrg(${org.id})"><i class="icon-plus join"></i></a></td>
															</#if>
														</tr>
													</#list>
												</tbody>
											</table>													
										<#else>
											<div style="text-align:center;padding:20px;">
												<h6>You have no recommended organizations.</h6>
											</div>
										</#if>																				
									</div>
								</div>
								
								
							</div>
						</div>

						
					</div>
					<div class="span4">
						<div class="row noMargin containerFill">
							<div>
								<strong>Create A New Organization</strong>
							</div>
							<div>
								Not finding the organization you are looking for?  Create an organization that focuses
								on what you are interested in.
							</div>
							<div>
								<div class="marginTop">
									<button class="success" onclick="location.href='/orgs/create'">Create Organization</button>
								</div>
							</div>
						</div>
						<div class="row">
							<h5>Largest Organizations</h5>
							<div class="container">
								<table class="table hovered striped">
									<tbody>
										<#list largestOrganizations as org>
											<tr>
												<td class="padding5"><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
												<td class="right padding5"><span class="place-right">${org.memberCount}</span></td>
											</tr>
										</#list>
									</tbody>
								</table>		
							</div>
						</div>
						<div class="row noMargin">
							<h5>Newest Organizations</h5>
							<div class="container">	
								<table class="table hovered striped">
									<tbody>
										<#list newestOrganizations as org>
											<tr>
												<td class="padding5"><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
												<td class="right padding5"><span class="place-right">${org.dateAsString}</span></td>
											</tr>
										</#list>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>

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
			
			function leave(orgId) {
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
			
			function displayError(message) {
				alert(message);
			}
			
			function searchOrgs()
			{	
				var keywords = $("#orgSearch").val();
				window.location='/search?keywords=' + keywords + '&dest=org';
			}
			
			$("#orgSearch").keypress(function(e) {
				// Enter button
				if (e.keyCode == 13)
				{
					searchOrgs();
				}
			});
			
		</script>
	</body>
</html>