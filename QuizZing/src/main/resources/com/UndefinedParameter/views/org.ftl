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
									<button onclick="leaveOrg('${organization.id}')" style="margin-right:10px;" class="bg-white large border1">Leave Organization</button>				
								<#else>
									<button onclick="joinOrg('${organization.id}')" style="margin-right:10px;" class="bg-white large border1">Join Organization</button>
								</#if>
							</#if>	
							<button class="bg-white large border1" onclick="location.href='/orgs/org/create?orgId=${organization.id}'"><i class="icon-tools on-left"></i>Create Group</button>
						</div>
					</div>
				</div>
				<div class="row noMargin">
					<nav class="navigation-bar white white-custom">
					    <nav class="navigation-bar-content">
					        <item class="element active"><a href="#" class="todo">${organization.name?html}</a></item>
					        <item class="element-divider"></item>
					        <item class="element"><a href="#" class="todo">About</a></item>
					        <item class="element-divider"></item>
					        <item class="element"><a href="#" class="todo">Statistics</a></item>
					        
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

				<div class="row noMargin">

				</div>
				<div class="row">
					<p>${organization.description?html}</p>
				</div>

				
			    <!-- Registered Groups -->
			    <#if registeredGroups??>
				    <div class="row">
				    	<div>
							<h4>Your Registered Groups</h4>
						</div>
					</div>
				
				    <div class="row">
						<table class="table hovered">
	                        <thead>
	                        <tr>
	                            <th class="text-left">Group</th>
	                            <th class="text-left">Members</th>
	                            <th class="text-left">Quizzes</th>
	                            <th class="text-left">Questions</th>
	                            <th class="text-left">Contribution Score <a href="#" data-hint="Contribution Score|A contribution score is something that we must figure out later. It will be super cool" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></th>
	                            <th class="text-left">Quizzes Participated</th>
	                            <th class="text-left">Date Created</th>
                            	<th class="text-left">Leave</th>
	                        </tr>
	                        </thead>
	
	                        <tbody>
		                        <#list registeredGroups as group>
									<tr>
										<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
										<td class="right">${group.memberCount}</td>
										<td class="right">${group.quizCount}</td>
										<td class="right">${group.questionCount}</td>
										<td class="right">35</td>
										<td class="right">3</td>
										<td class="right">${group.dateAsString}</td>
										<td class="right"><button class="danger" onClick="leave(${group.id})">-</button></td>
									</tr>
								</#list>     
	                        </tbody>
	
	                        <tfoot></tfoot>
	                    </table>    
				    </div>
			    </#if>
			    
			    <!-- Top Groups -->
			    <div class="row">
			    	<div>
						<h4>Suggested Groups</h4>
					</div>
				</div>
			    <div class="row">
					<table class="table hovered">
                        <thead>
                        <tr>
                            <th class="text-left">Group</th>
                            <th class="text-left">Members</th>
                            <th class="text-left">Quizzes</th>
                            <th class="text-left">Questions</th>
                            <th class="text-left">Contribution Score <a href="#" data-hint="Contribution Score|A contribution score is something that we must figure out later. It will be super cool" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></th>
                            <th class="text-left">Quizzes Participated</th>
                            <th class="text-left">Date Created</th>
                            <#if loggedIn>
                            	<th class="text-left">Join</th>
                            </#if>
                        </tr>
                        </thead>

                        <tbody>
	                        <#list groups as group>
								<tr>
									<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
									<td class="right">${group.memberCount}</td><td class="right">${group.quizCount}</td>
									<td class="right">${group.questionCount}</td><td class="right">35</td><td class="right">3</td>
									<td class="right">${group.dateAsString}</td>
									<#if loggedIn>
										<td class="right"><button class="success" onClick="register(${group.id})">+</button></td>
									</#if>
								</tr>
							</#list>     
                        </tbody>

                        <tfoot></tfoot>
                    </table>    
			    </div>
			</div>
			<div class="content-width center">
				Content is going to go here
				
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

	
	function register(groupId) {

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
	
	function leave(groupId) {
	
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