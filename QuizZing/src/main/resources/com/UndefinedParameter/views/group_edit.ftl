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
		<link href="/assets/css/groups.css" rel="stylesheet">	
		<script src="/assets/scripts/expanding.js"></script>
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
                            <li><a href="/orgs/org?id=${organization.id}">${organization.name}</a></li>
                            <li><a href="/orgs/group?groupId=${group.id}">${group.name}</a></li>
                            <li class="active"><a>Edit</a></li>
                        </ul>
                    </nav>
				</div>


				 <div class="row">
					<div class="offset1 span6">
						<h2>Edit <strong>${group.name}</strong></h2>
						<p id="mainCreateError" class="tertiary-text-secondary errorFormText1 createGroupError" hidden>* There were errors with the information you entered.  Fix the errors in red and then click 'Create Group'.</p>										
					</div>
				</div>
				<div class="row">
					<form id="createGroupForm">
						<div id="register" class="offset1 span6">
							<div>
								<h4>Basic Group Information</h4>
							</div>
							<div class="row">
								<div class="input-control text span12">
								    <input type="text" id="parentGroup" value="" placeholder="Organization: ${organization.name}" disabled/>
								</div>
							</div>	
							<p id="groupNameError" class="tertiary-text-secondary errorFormText1 createGroupError" hidden>Enter a name for this group.</p>
							<p id="alreadyExistsError" class="tertiary-text-secondary errorFormText1 createGroupError" hidden></p>		
							<div class="row">
								<div class="input-control text span12">
								    <input class="focusOutTrim focusOutValidateNotEmpty" type="text" id="groupName" value="${group.name}" placeholder="Group Name"/>
								</div>
							</div>	
							<div>
								<h4>Description</h4>
							</div>
							<div class="row">
								<div class="input-control text">
								    <input id="categories" type="text" value="" placeholder="Categories" disabled/>
								</div>
							</div>
							<p id="descriptionError" class="tertiary-text-secondary errorFormText1 createGroupError" hidden>Enter a description for this group.</p>
							<div class="input-control textarea">
							    <textarea class="focusOutTrim focusOutValidateNotEmpty expanding" maxlength="2000" id="description" placeholder="Describe what this group is about.">${group.description}</textarea>
							</div>	
				
							<div class="row">
							</div>
							<div class="row" id="acceptterms">	
								<p id="iAcceptError" class="tertiary-text-secondary errorFormText1 createGroupError" hidden>Accept the terms and conditions to create a group.</p>					
								<div class="input-control checkbox">
								    <label>
								        <input class="createOrgError" id="iAccept" type="checkbox" />
								        <span class="check"></span>
								        I Accept the <a href="#" class="todo">Terms and Conditions</a>.
								    </label>
								</div>
							</div>							
							<div class="row">
								<button type="button" class="large success" onclick="createGroup()">Submit</button>
								<button type="button" class="large danger" onclick="location.href = '/group?groupId=${group.id}'">Cancel</button>							
							</div>						
						</div>
					</form>
				</div>
						




			</div>
		</div>

		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
		<script>
			
			$("#createGroupForm").keypress(function(e) {
				// Enter button
				if (e.keyCode == 13 && e.target.id != "description")
				{
					createGroup();
				}
			});
			
			function createGroup()
			{
				$('.createGroupError').hide();
				
				var allFieldsValid = true;
				
				var name = $('#groupName').val();
				if (!fieldTrimValidateNotEmpty($('#groupName')))
				{
					$('#groupNameError').show();
					allFieldsValid = false;
				}
				
				var description = $('#description').val();
				if (!fieldTrimValidateNotEmpty($('#description')))
				{
					$('#descriptionError').show();
					allFieldsValid = false;
				}
				
				if (!$("#iAccept").is(":checked"))
				{
					allFieldsValid = false;
					$("#iAcceptError").show();
				}
				
				if (allFieldsValid)
				{
					$.ajax({
						type: 'POST',
						url: '/group/edit?groupId=${group.id}',
						data: JSON.stringify({ id: ${group.id}, name: name, description: description}),
						headers: {
							"Content-Type": "application/json"
						},
						success: function(data) {
							window.location = '/group?groupId=${group.id}';
						},
						error: function(error) {
					    	$('#errorLabel').html("Unable to edit your group.");
					    }
					});
				}
				else
				{
					scrollToTop();
					$('#mainCreateError').show();
				}
			}
			
		</script>
	</body>
</html>