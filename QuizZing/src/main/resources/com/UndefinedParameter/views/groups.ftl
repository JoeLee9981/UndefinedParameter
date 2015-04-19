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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<div class="grid fluid">		
				
			    <!-- Registered Groups -->
			    <#if userGroups??>
				    <div class="row">
				    	<div>
							<h4>Your Registered Groups</h4>
						</div>
					</div>
				
				    <div class="row">
						<table class="table hovered striped">
	                        <thead>
		                       <tr>
					                <th class="text-left">Group</th>
								    <th title="Members" class="text-center"><i class="icon-user-3 on-right"></i></th>
								    <th title="Quizzes" class="text-center"><i class="icon-clipboard-2 on-right"></i></th>
								    <th title="Questions" class="text-center"><i class="icon-help-2 on-right"></i></th>
								    <th title="Contribution is how much you've contributed towards the group" class="text-center">Contribution</th>
								    <th class="text-center">Date Created</th>
		                        </tr>
	                        </thead>
	
	                        <tbody>
		                        <#list userGroups as group>
									<tr>
										<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
										<td class="text-center">${group.memberCount}</td>
										<td class="text-center">${group.quizCount}</td>
										<td class="text-center">${group.questionCount}</td>
										<td class="text-center">35</td>
										<td class="text-center">${group.dateAsString}</td>
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
						<h4>Top Groups</h4>
					</div>
				</div>
			    <div class="row">
					<table class="table hovered striped">
                        <thead>
				            <tr>
				                <th class="text-left">Group</th>
							    <th title="Members" class="text-center"><i class="icon-user-3 on-right"></i></th>
							    <th title="Quizzes" class="text-center"><i class="icon-clipboard-2 on-right"></i></th>
							    <th title="Questions" class="text-center"><i class="icon-help-2 on-right"></i></th>
							    <th title="Contribution is how much you've contributed towards the group" class="text-center">Contribution</th>
							    <th class="text-center">Date Created</th>
	                        </tr>
                        </thead>

                        <tbody>
	                        <#list topGroups as group>
								<tr>
									<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
									<td class="text-center">${group.memberCount}</td>
									<td class="text-center">${group.quizCount}</td>
									<td class="text-center">${group.questionCount}</td>
									<td class="text-center">35</td>
									<td class="text-center">${group.dateAsString}</td>
								</tr>
							</#list>     
                        </tbody>

                        <tfoot></tfoot>
                    </table>    
			    </div>
			</div>
		</div>
		
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>


<script>
	
	function register(groupId) {

		$.ajax({
		    url: '/orgs/org/register?groupId=' + groupId,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/group/top';
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
		    	window.location='/group/top';
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