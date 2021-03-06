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
                            <li class="active"><a>Create Organization</a></li>
                        </ul>
                    </nav>
				</div>


				 <div class="row">
					<div class="offset1 span6">
						<h2>Edit <strong>${organization.name}</strong></h2>
						<p id="mainCreateError" class="tertiary-text-secondary errorFormText1 createOrgError" hidden>* There were errors with the information you entered.  Fix the errors in red and then click 'Create Organization'.</p>										
					</div>
				</div>
				<div class="row">
					<form id="createOrgForm">
						<div id="register" class="offset1 span6">
							<div>
								<h4>Basic Organization Information</h4>
							</div>
							<p id="organizationNameError" class="tertiary-text-secondary errorFormText1 createOrgError" hidden>Enter a name for this organization.</p>
							<p id="alreadyExistsError" class="tertiary-text-secondary errorFormText1 createOrgError" hidden></p>		
							<div class="row">
								<label for="organizationType">Type: 
									<div class="input-control text span12">
								    <select class="focusOutTrim" id="organizationType">
								    	<option value="University" <#if organization.type == 'University'>selected="selected"</#if>>University</option>
								    	<option value="Certification" <#if organization.type == 'Certification'>selected="selected"</#if>>Certification</option>
								    	<option value="Club" <#if organization.type == 'Club'>selected="selected"</#if>>Club</option>
								    	<option value="SocialGroup" <#if organization.type == 'Social Group'>selected="selected"</#if>>Social Group</option>
								    	<option value="Business" <#if organization.type == 'Business'>selected="selected"</#if>>Business</option>
								    	<option value="Subject" <#if organization.type == 'Subject'>selected="selected"</#if>>Subject</option>
								    	<option value="Other" <#if organization.type == 'Other'>selected="selected"</#if>>Other</option>
								    </select>
								</div></label>
							</div>
							
							<div class="row">	
								<div class="input-control text span12">
								    <input class="focusOutTrim" type="text" id="organizationName" placeholder="Organization Name" value="${organization.name}"></input>
								</div>
							</div>	
							<p class="tertiary-text">A location is not required, but adding a location will help others to find this organization.</p>
							<div class="row">
								<div class="input-control text span4">
								    <input class="focusOutTrim" type="text" id="city" placeholder="City" value="${organization.city}"/>
								</div>		
								<div class="input-control text span4">
								    <input class="focusOutTrim" type="text" id="state" placeholder="State/Province" value="${organization.state}"/>
								</div>	
								<div class="input-control text span4">
								    <input class="focusOutTrim" type="text" id="country" placeholder="Country" value="${organization.country}"/>
								</div>										
							</div>
							<div>
								<h4>Description</h4>
							</div>
							<p id="descriptionError" class="tertiary-text-secondary errorFormText1 createOrgError" hidden>Enter a description for this organization.</p>
							<div class="input-control textarea">
							    <textarea maxlength="2000" class="expanding" id="description" placeholder="Describe this organization.">${organization.description}</textarea>
							</div>
				
							<div class="row">
							</div>
							<div class="row" id="acceptterms">	
								<p id="iAcceptError" class="tertiary-text-secondary errorFormText1 createOrgError" hidden>Accept the terms and conditions to create an organization.</p>					
								<div class="input-control checkbox">
								    <label>
								        <input class="createOrgError" id="iAccept" type="checkbox" />
								        <span class="check"></span>
								        I Accept the <a href="#" class="todo">Terms and Conditions</a>.
								    </label>
								</div>
							</div>							
							<div class="row">
								<button type="button" class="large success" onclick="editOrganization()">Submit</button>
								<button type="button" class="large danger" onclick="location.href = '/orgs/org?orgId=${organization.id}'">Cancel</button>							
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
			
			$('#organizationName').focusout(function(){
				if ($('#organizationName').val().length < 1)
				{
					$('#organizationName').removeClass('valid').addClass('invalid');
				}
				else
				{
					$('#organizationName').removeClass('invalid').addClass('valid');
				}
			});

			$('#description').focusout(function(){
				if ($('#description').val().length < 1)
				{
					$('#description').removeClass('valid').addClass('invalid');
				}
				else
				{
					$('#description').removeClass('invalid').addClass('valid');
				}
			});
			
			$("#createOrgForm").keypress(function(e) {
				// Enter button
				if (e.keyCode == 13 && e.target.id != "description")
				{
					createOrganization();
				}
			});
			
			function editOrganization()
			{
				$(".createOrgError").hide();
				
				var allFieldsValid = true;
				if ($('#organizationName').val().length < 1)
				{
					allFieldsValid = false;
					$('#organizationNameError').show();
					$('#organizationName').removeClass('valid').addClass('invalid');
				}
				else
				{
					$('#organizationName').removeClass('invalid').addClass('valid');
				}
				
				if ($('#description').val().length < 1)
				{
					allFieldsValid = false;
					$('#descriptionError').show();
					$('#description').removeClass('valid').addClass('invalid');
				}
				else
				{
					$('#description').removeClass('invalid').addClass('valid');
				}
				
				if (!$("#iAccept").is(":checked"))
				{
					allFieldsValid = false;
					$("#iAcceptError").show();
				}
				
				if (allFieldsValid)
				{
					var name = $('#organizationName').val();
					var description = $('#description').val();
					var city = $('#city').val();
					var state = $('#state').val();
					var country = $('#country').val();
					var type = $('#organizationType').val();
					
					$.ajax({
						type: 'POST',
						url: "/orgs/org/edit?orgId=${organization.id}",
						data: JSON.stringify({id: ${organization.id}, type: type, name: name, description: description, city: city, state: state, country: country }),
						headers: {
							"Content-Type": "application/json"
						},
						success: function(data) {
							window.location = '/orgs/org?id=${organization.id}';
						},
						error: function(error) {
					    	alert('An unexpected error occured');
					    }
					});
				}
				else
				{
					$('#mainCreateError').show();
					$("html, body").animate({ scrollTop: 0 }, 300);
				}
				
			}
			
			function displayError(message) {
				alert(message);
			}
			
		</script>
	</body>
</html>