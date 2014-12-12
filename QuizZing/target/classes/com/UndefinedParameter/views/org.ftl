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
						<h2>${organization.name?html} Groups</h2>
						<h2>${organization.city?html}</h2>
						<h3>${organization.state?html}</h2>
						<h3>${organization.country?html}</h2>
						<p>${organization.description?html}</p>
					</div>
				</div>
			    
			    
			    <div class="row">
			    	<div>
						<h4>Top Organizations and Groups</h4>
					</div>
				</div>
			    <div class="row">
					<table class="table hovered">
                        <thead>
                        <tr>
                            <th class="text-left">Group</th>
                            <th class="text-left">Members</th>
                            <th class="text-left">Quizes</th>
                            <th class="text-left">Questions</th>
                            <th class="text-left">Contribution Score <a href="#" data-hint="Contribution Score|A contribution score is something that we must figure out later. It will be super cool" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></th>
                            <th class="text-left">Quizzes Participated</th>
                            <th class="text-left">Date Created</th>
                        </tr>
                        </thead>

                        <tbody>
	                        <#list groups as group>
								<tr><td><a href="/group/${group.id}">${group.name?html}</a></td><td class="right">255</td><td class="right">35</td><td class="right">100</td><td class="right">35</td><td class="right">3</td><td class="right">11/12/14</td></tr>
							</#list>     
                        </tbody>

                        <tfoot></tfoot>
                    </table>    
			    </div>
			</div>
		</div>
		
		
		
		
		<div class="content-width center">
			Content is going to go here
			
			<h1></h1>
			
			
			
			
			<br/>
			<h2>Add a group</h2>
			<input type="text" id="nameText" name="name" placeholder="Name"><br/>
			<input type="text" id="descriptionText" name="description" placeholder="Description"><br/>
			<button onclick="addGroup()" >Add</button><br/>
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
	

	
</script>