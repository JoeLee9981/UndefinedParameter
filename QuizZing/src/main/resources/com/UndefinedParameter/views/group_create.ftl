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
                            <li class="active"><a>Create Group</a></li>
                        </ul>
                    </nav>
				</div>


				 <div class="row">
					<div class="offset1 span6">
						<h2>Create a <strong>Group</strong></h2>
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
								    <input class="focusOutTrim focusOutValidateNotEmpty" type="text" id="groupName" value="" placeholder="Group Name"/>
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
							    <textarea class="focusOutTrim focusOutValidateNotEmpty expanding" maxlength="2000" id="description" placeholder="Describe what this group is about."></textarea>
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
								<button type="button" class="large primary" onclick="createGroup()">Create Group</button>								
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
						url: '/group',
						data: JSON.stringify({name: name, description: description, organizationId: ${organization.id} }),
						dataType: "json",
						headers: {
							Accept: "application/json",
							"Content-Type": "application/json"
						},
						success: function(data) {
							if("success" == data["response"]) {
								window.location = data["redirect"];
							}
							else {
								$('#errorLabel').html(data["message"]);
							}
						},
						error: function(error) {
					    	$('#errorLabel').html("Unable to create your organization.");
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