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
                            <li class="active"><a>Organizations</a></li>
                        </ul>
                    </nav>
				</div>
				<div class="row" id="orgWelcomeContainer">
					<div class="span7 notificationBox">
					</div>
					<div class="span5 notificationBox">
					</div>
				</div>
				<div class="row">
					<div>
						<h2>Organizations<button class="place-right success" onclick="location.href='/orgs/create'">Create Organization</button></h2>			
						<p>Organizations provide a way to contain a specific area of interest.  Universities, clubs, or even subjects can make great
						organizations. Inside organizations you will be able to create subgroups which can focus even more on a specific subject
						or area of interest.</p>					
					</div>
				</div>
	
			    
			    <#if registeredOrganizations??>
			    	<div class="row">
			    	<div>
						<h4>Your Registered Organizations</h4>
					</div>
					<div class="row">
						<table class="table hovered">
	                        <thead>
	                        <tr>
	                            <th class="text-left">Organization/Group</th>
	                            <th class="text-left">Members</th>
	                            <th class="text-left">Quizes</th>
	                            <th class="text-left">Questions</th>
	                            <th class="text-left">Contribution Score <a href="#" data-hint="Contribution Score|A contribution score is something that we must figure out later. It will be super cool" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></th>
	                            <th class="text-left">Quizzes Participated</th>
	                            <th class="text-left">Date Created</th>
	                            <th class="text-left">Leave</th>
	                        </tr>
	                        </thead>
	
	                        <tbody>
	                        <#list registeredOrganizations as org>
	                        	<tr>
		                        	<td><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
		                        	<td class="right">${org.memberCount}</td>
		                        	<td class="right">${org.quizCount}</td><td class="right">${org.questionCount}</td>
		                        	<td class="right">${getContributionScore(org.id)}</td>
		                        	<td class="right">${getQuizzesParticipated(org.id)}</td>
		                        	<td class="right">11/12/14</td>
		                        	<td class="right"><button class="danger" onClick="leave(${org.id})">-</button></td>
	                        	</tr>
	                        </#list>            
	                        </tbody>
	
	                        <tfoot></tfoot>
	                    </table>    
				    </div>
			    </#if>
			    
			    <div class="row">
			    	<div>
						<h4>Top Organizations and Groups</h4>
					</div>
				</div>
			    <div class="row">
					<table class="table hovered">
                        <thead>
                        <tr>
                            <th class="text-left">Organization/Group</th>
                            <th class="text-left">Members</th>
                            <th class="text-left">Quizes</th>
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
                        <#list organizations as org>
                        	<tr>
	                        	<td><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
	                        	<td class="right">${org.memberCount}</td>
	                        	<td class="right">${org.quizCount}</td><td class="right">${org.questionCount}</td>
	                        	<td class="right">${getContributionScore(org.id)}</td>
	                        	<td class="right">${getQuizzesParticipated(org.id)}</td>
	                        	<td class="right">11/12/14</td>
	                        	<#if loggedIn>
	                        		<td class="right"><button class="success" onClick="register(${org.id})">+</button></td>
	                        	</#if>
                        	</tr>
                        </#list>            
                        </tbody>

                        <tfoot></tfoot>
                    </table>    
			    </div>
			</div>
		</div>

		<#include "../includes/footer.ftl">

		<script>
			
			function register(orgId) {
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
			
		</script>
	</body>
</html>