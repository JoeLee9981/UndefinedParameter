<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing - Quiz Creator</title>
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
		<link rel="stylesheet" type="text/css" href="/assets/css/quiz.css" />
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="page-content">
			<div class="grid fluid">
				<div class="page-content">
					<div class="row">
						<div class="span6">
							<h2><i class="icon-tools on-left"></i>Quiz Creator</h2>
							<p id="mainCreateQuizError" class="tertiary-text-secondary errorFormText1 createQuizError marginTop30" hidden>* There were errors with the information you entered.  Fix the errors in red and then click 'Continue To Editor'.</p>
						</div>
					</div>
					
					<form class="noMargin" id="quizCreateForm">
						<div class="row noMargin">
							<div class="span6">
								<div class="noMargin row">		
									<div>
										<div>
											<h4>Choose a joined organization for this quiz</h4>
										</div>
										<div class="row noMargin">
											<p id="orgError" class="tertiary-text-secondary errorFormText1 createQuizError" hidden>Please select an organization</p>
											<div class="input-control text">
											    <input type="text" id="orgFilterSearch" value="" placeholder="Filter joined organizations..."/>
											</div>
										</div>
										<div class="row noMargin">
											<div class="input-control select">
											    <select multiple style="height: 200px"id="orgFilteredList">
											    </select>
											</div>
										</div>
								    </div>
								 </div>
								 <div class="row noMargin">
								    <div>
								    	<div>
											<h4>Choose a joined group in this organization for this quiz</h4>
										</div>
							    		<div class="row noMargin">
							    			<p id="groupError" class="tertiary-text-secondary errorFormText1 createQuizError" hidden>Please select a group</p>
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
								    <div>
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
								    <div>
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
								<div class="row noMargin">
									<div class="input-control checkbox noMargin">
									    <label>
									        <input id="autoCheck" class="createOrgError" id="iAccept" type="checkbox" checked/>
									        <span class="check"></span>
									        <strong>Auto-generate quiz</strong>
									    </label>
									</div>
								</div>			
								<div class="row">
									<div>
										<button type="button" class="large success" onclick="createQuiz()">Continue To Editor</button>	
									</div>							
								</div>
							</div>
							<div class="span5">
								<div>
									<h4>Max number of questions</h4>
								</div>
								<p id="numberError" class="tertiary-text-secondary errorFormText1 createQuizError" hidden>Please select a number over 0</p>
					    		<div class="row noMargin">
									<div class="input-control text">
									    <input type="text" id="questionCount" class="focusOutTrim focusOutValidateNotEmpty" value="10" />
									</div>
								</div>
								<div>
									<h4>Select target rating</h4>
								</div>
								<div id="rating" class="fg-yellow rating">
								</div>
								<div>
									<h4>Select target difficulty</h4>
								</div>
								<div id="difficulty" class="rating fg-yellow">
								</div>
								<div>
									<h4>Select tags for this quiz</h4>
								</div>
								<div class="row noMargin quizQuestionList" style="height: 430px; width:323px">
									<div class="quizQuestionListContent">
										<div id="catList" class="listview">
											<#if categories??>
											<#list categories as category>
		                                    <a id="${category_index}Button" class="list" href="#" onclick="toggleCategory(${category_index}, '${category}')">
		                                        <div class="list-content">
		                                            <span class="list-title">${category}</span>
		                                        </div>
		                                    </a>
		                                    </#list>
		                                    <#else>
		                                    <a class="list marked" href="#">
		                                    	<div class="list-content">   
		                                            <span class="list-title">No tags for this group</span>
		                                        </div>
		                                    </a>
		                                    </#if>
		                                    
		                            	</div>
		                            </div>
		                    	</div>
							</div>
						</div>	
					</form>			
				</div>
			</div>
		</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
	
	
	
	
	<script>
	
	var categories = [];
	var quality = 3;
	var difficulty = 3;
	
	function toggleCategory(index, cat) {
		if($.inArray(cat, categories) == -1) {
			categories.push(cat);
			$('#' + index + 'Button').addClass("selected");
		}
		else {
			removeCategory(cat)
			$('#' + index + 'Button').removeClass("selected");
		}
	}
	
	function removeCategory(cat) {
		var temp = [];
		for(var j = 0; j < categories.length; j++) {
			if(cat != categories[j]) {
				temp.push(categories[j]);
			}
		}
		categories = temp;
	}
	
	$("#difficulty").rating({
		static: false,
		score: 3,
		stars: 5,
		showHint: true,
		hints: ['cake', 'easy', 'average', 'hard', 'impossible'],
		click: function(value, rating) {
			difficulty = value;
			rating.rate(value);
		}
	});
	
	$("#rating").rating({
		static: false,
		score: 3,
		stars: 5,
		showHint: true,
		hints: ['useless', 'poor', 'average', 'good', 'excellent'],
		click: function(value, rating) {
			quality = value;
			rating.rate(value);
		}
	});
	
	$('#autoCheck').click(function() {
		if($('#autoCheck').is(":checked")) {
			$('#questionCount').attr('disabled', false);
			$('#rating').attr('disabled', false);
			
		}
		else {
			$('#questionCount').attr('disabled', true);
			$('#rating').attr('disabled', false);
		}
	});
	
	var joinedOrganizationList =
		{		
			<#if joinedOrganizations??>
				<#list joinedOrganizations as org>
					'${org.name}':${org.id}
					<#if org_has_next>,</#if>
				</#list>
			</#if>
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
			var groupId = $('#groupFilteredList option:selected').val();
			$('#groupFilterSearch').val(selectedOption);
			setCategoriesForGroup(groupId);
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
		
		if(!$('#orgFilteredList').val()) {
			$("#orgError").show();
			hasNoErrors = false;
		}
		if(!selectedGroupId) {
			$('#groupError').show();
			hasNoErrors = false;
		}
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
		
		var auto = $('#autoCheck').is(":checked");
		var count = 0;
		if(auto)	{
			count = $("#questionCount").val();

			if(count < 1 || isNaN(count)) {
				$("#numberError").show();
				$("#questionCount").removeClass('valid').addClass('invalid');
				hasNoErrors = false;
			}
			else {
				$("#questionCount").removeClass('invalid').addClass('valid');
			}
		}
		
		if (hasNoErrors)
		{
			$.ajax({
				type: 'POST',
				url: "/quiz/create?groupId=" + selectedGroupId + "&auto=" + auto + "&questionCount=" + count + "&rating=" + quality + "&difficulty=" + difficulty,
				data: JSON.stringify({name: quizTitle, description: quizDescription, categories: categories }),
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
	
	function setCategoriesForGroup(groupId) {

		$.ajax({
			type: 'GET',
			url: '/group/categories?groupId=' + groupId,
			headers: {
				Accept: "application/json",
			},
			success: function(data)
			{
				var html = "";
				for(var i = 0; i < data.length; i++) {
					html += '<a id="' + i + 'Button" class="list" href="#" onclick="toggleCategory(' + i + ', \''+ data[i] +'\')">'
		                +	 '<div class="list-content">'
		                +		'<span class="list-title">' + data[i] + '</span>'
		                +	 '</div>'
	            		+ '</a>';
	            		
	            		
				}
				
            	$('#catList').html(html);
			}
		});
	}
	
	</script>
</html>