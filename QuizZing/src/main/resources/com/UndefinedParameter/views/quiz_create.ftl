<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing - Quiz Creator</title>
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="page-content">
			<div class="grid fluid">
				<div class="page-content">
					<div class="row">
						<div class="offset1 span6">
							<h2><i class="icon-tools on-left"></i>Quiz Creator</h2>
							<p id="mainCreateQuizError" class="tertiary-text-secondary errorFormText1 createQuizError marginTop30" hidden>* There were errors with the information you entered.  Fix the errors in red and then click 'Continue To Editor'.</p>
						</div>
					</div>
					<form class="noMargin" id="quizCreateForm">	
						<div class="row">		
							<div class="offset1 span6">
								<div>
									<h4>Choose a joined organization for this quiz</h4>
								</div>
								<div class="row noMargin">
									<div class="input-control text">
									    <input type="text" id="orgFilterSearch" value="" placeholder="Filter joined organizations..."/>
									</div>
								</div>
								<div class="row noMargin">
									<div class="input-control select">
									    <select multiple id="orgFilteredList">
									    </select>
									</div>
								</div>
						    </div>
						 </div>
						 <div class="row noMargin">
						    <div class="offset1 span6">
						    	<div>
									<h4>Choose a joined group in this organization for this quiz</h4>
								</div>
					    		<div class="row noMargin">
									<div class="input-control text">
									    <input type="text" id="groupFilterSearch" value="<#if group??>${group.name}</#if>" placeholder="Filter joined groups..."/>
									</div>
								</div>
								<div class="row noMargin">
									<div class="input-control select">
									    <select multiple id="groupFilteredList">
											<#if group??>
									    		<#list joinedGroupsInOrganization as currentGroup>
									    			<option value="${group.id}" <#if currentGroup.id == group.id>selected</#if>>${currentGroup.name}</option>
									    		</#list>	
									    	</#if>
									    </select>
									</div>
							    </div>
						    </div>				
						</div>
						<div class="row noMargin">
						    <div class="offset1 span6">
						    	<div>
									<h4>Add a title for this quiz</h4>
								</div>
								<p id="titleError" class="tertiary-text-secondary errorFormText1 createQuizError" hidden>Enter a title for this quiz</p>
					    		<div class="row noMargin">
									<div class="input-control text">
									    <input type="text" id="quizTitle"  class="focusOutTrim focusOutValidateNotEmpty" value="" placeholder="Quiz Title"/>
									</div>
								</div>
						    </div>				
						</div>
						<div class="row noMargin">
						    <div class="offset1 span6">
						    	<div>
									<h4>Describe this quiz</h4>
								</div>
								<p id="descriptionError" class="tertiary-text-secondary errorFormText1 createQuizError" hidden>Enter a description for this quiz</p>
					    		<div class="row noMargin">
									<div class="input-control textarea" data-role="input-control">
	                                    <textarea id="quizDescription" class="noResize focusOutTrim focusOutValidateNotEmpty" placeholder="Description about your quiz"></textarea>
	                                </div>
								</div>
						    </div>				
						</div>			
						<div class="row">
							<div class="offset1 span6">
								<button type="button" class="large success" onclick="createQuiz()">Continue To Editor</button>	
							</div>							
						</div>		
					</form>			
				</div>
			</div>
		</div>
		<#include "../includes/footer.ftl">
	</body>
	
	
	
	
	<script>
	
	var joinedOrganizationList =
		{		
			<#list joinedOrganizations as org>
				'${org.name}':${org.id}
				<#if org_has_next>,</#if>
			</#list>
		};
		
	var joinedGroupListInOrganzation = {};
	
	manageOrganizationFilter('');
	
	<#if group??>
 		$("#orgFilteredList option[value='" + ${group.organizationId} + "']").prop("selected", true);
 		var selectedOption = $('#orgFilteredList option:selected').text();
		$('#orgFilterSearch').val(selectedOption);
 	</#if>	
	
	function manageOrganizationFilter(filterKeyword)
	{
		$('option', '#orgFilteredList').remove();
		filterKeyword = filterKeyword.toLowerCase();
		$.each(joinedOrganizationList, function(key, value)
		{
			// If the key contains the filter keyword, display it
			if (key.toLowerCase().search(filterKeyword) >= 0)
			{			 	
				$('<option>').val(value).text(key).appendTo('#orgFilteredList');	
			}
		});
	}
	
	
	function manageGroupFilter(filterKeyword)
	{
		$('option', '#groupFilteredList').remove();
		filterKeyword = filterKeyword.toLowerCase();
		var noResults = true;
		$.each(joinedGroupListInOrganzation, function(key, value)
		{
			// If the key contains the filter keyword, display it
			if (key.toLowerCase().search(filterKeyword) >= 0)
			{
				$('<option>').val(value).text(key).appendTo('#groupFilteredList');
				var noResults = false;
			}
		});
		

	}	
	
	
	function manageJoinedGroupsForSelectedOrganization(orgId)
	{
		joinedGroupListInOrganzation = {};
		$('option', '#groupFilteredList').remove();
		$('#groupFilterSearch').val('');
		$.ajax({
			type: 'POST',
			url: '/orgs/getJoinedGroups?orgId=' + orgId,
			success: function(data)
			{
				// This boolean is a workaround to check that data is not empty
				var enableGroup = false;
				$.each(data, function(key, value)
				{
					joinedGroupListInOrganzation[key] = value;
					enableGroup = true;
				});
				
				$('#groupFilterSearch').prop('disabled', !enableGroup);
				$('#groupFilteredList').prop('disabled', !enableGroup);
				
				manageGroupFilter('');
			}
		});
	}
	
	
	// Event to call whenever the org filter keyword textbox changes value
	$('#orgFilterSearch').on('input', function() {
		var keyword = $('#orgFilterSearch').val();
		manageOrganizationFilter(keyword);
	});
	
	
	// Event to call whenever the group filter keyword textbox changes value
	$('#groupFilterSearch').on('input', function() {
		var keyword = $('#groupFilterSearch').val();
		manageGroupFilter(keyword);
	});
	
	
	// Event to call when an organization is selected
	$('#orgFilteredList').on('change', function() {
		var selectedCount = $("#orgFilteredList :selected").length;
		
		if (selectedCount == 1)
		{
			var selectedOption = $('#orgFilteredList option:selected').text();
			$('#orgFilterSearch').val(selectedOption);
			
			var orgId = $('#orgFilteredList option:selected').val();
			manageJoinedGroupsForSelectedOrganization(orgId);
		}
	});
	
	
	// Event to call when a group is selected
	$('#groupFilteredList').on('change', function() {
		var selectedCount = $("#groupFilteredList :selected").length;
		if (selectedCount == 1)
		{
			var selectedOption = $('#groupFilteredList option:selected').text();
			$('#groupFilterSearch').val(selectedOption);
		}
	});
	
	
	$("#quizCreateForm").keypress(function(e) {
		// Enter button
		if ($(e.target).is('textarea'))
		{
			return;
   		}
		if (e.keyCode == 13)
		{
			createQuiz();
		}
	});
	
	function createQuiz()
	{
		$(".createQuizError").hide();
		
		var quizTitle = $('#quizTitle').val();
		var quizDescription = $('#quizDescription').val();
		
		var selectedGroupId = $('#groupFilteredList option:selected').val();

		// Do not create the quiz if there is no quiz title or quiz description
		var hasNoErrors = true;
		
		if (quizTitle.length <= 0)
		{
			$("#titleError").show();
			$("#quizTitle").removeClass('valid').addClass('invalid');
			hasNoErrors = false;
		}
		else
		{
			$("#quizTitle").removeClass('invalid').addClass('valid');
		}
		
		if (quizDescription.length <= 0)
		{
			$("#descriptionError").show();
			$("#quizDescription").removeClass('valid').addClass('invalid');
			hasNoErrors = false;
		}
		else
		{
			$("#quizDescription").removeClass('invalid').addClass('valid');
		}
		
		if (hasNoErrors)
		{
			$.ajax({
				type: 'POST',
				url: "/quiz/create?groupId=" + selectedGroupId,
				data: JSON.stringify({name: quizTitle, description: quizDescription }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					if("success" == data["response"])
					{
						window.location = data["redirect"];
					}
					else
					{
						$('#errorLabel').html(data["message"]);
					}
				},
				error: function(error) {
			    	$('#errorLabel').html("Unable to create your Quiz.");
			    }
			});
		}
		else
		{
			$("#mainCreateQuizError").show();
			$("html, body").animate({ scrollTop: 0 }, 300);
		}		
	}
	
	</script>
</html>