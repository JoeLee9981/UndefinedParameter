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
					<div>
						<h2>Organizations and Groups <button class="place-right success" onclick="location.href='/orgs/create'">Create A New Organization</button></h2>
						
					</div>
				</div>
			    <div class="row">
					<button class="shortcut primary span3 todo">
					    <i class="icon-bookmark-4"></i>
					    University
					</button>
					<button class="shortcut info span3 todo">
					    <i class="icon-book"></i>
					    Class
					</button>
					<button class="shortcut success span3 todo">
					    <i class="icon-lab"></i>
					    Subject
					</button>
					<button class="shortcut warning span3 todo">
					    <i class="icon-comments-2"></i>
					    Group
					</button>															
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
                            <th class="text-left">Join</th>
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
	                        	<td class="right"><button class="success" onClick="register(${org.id})">+</button></td>
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