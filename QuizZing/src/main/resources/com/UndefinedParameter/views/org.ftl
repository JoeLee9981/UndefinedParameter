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
						<h2>${organization.name?html} Groups <button class="place-right success" onclick="location.href='/orgs/org/create?orgId=${organization.id}'">Create A New Group</button></h2>
						<h2>${organization.city?html}</h2>
						<h3>${organization.state?html}</h2>
						<h3>${organization.country?html}</h2>
						<p>${organization.description?html}</p>
					</div>
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
										<td><a href="/group/${group.id}">${group.name?html}</a></td>
										<td class="right">${group.memberCount}</td><td class="right">${group.quizCount}</td>
										<td class="right">${group.questionCount}</td><td class="right">35</td><td class="right">3</td>
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
									<td><a href="/group/${group.id}">${group.name?html}</a></td>
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
	
	function addGroup() {
		
		//TODO Prevalidate these fields
		var groupName = document.getElementById('nameText').value;
		var desc = document.getElementById('descriptionText').value;
		//TODO Add more fields to the entry - see Group.java file for more info

		 $.ajax({
			type: 'POST',
			url: "/group",
			data: JSON.stringify({organizationId: ${organization.id}, name: groupName, description: desc }),
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function(data) {
				if("success" == data["response"]) {

					location.reload();
				}
		

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