<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing - Edit Quiz</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/quiz.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/assets/plugins/unicorn/unicorn_buttons.css" />	
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
		<link rel="stylesheet" type="text/css" href="/assets/css/categories.css" />	
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		
		<div id="quizEditTopPanel">
			<div class="page-content">
				<div class="grid fluid noMargin">
					<div id="quizEditTopPanelContent" class="row noMargin">
						<div class="span6">
							<form class="noMargin" id="nameForm">
								<div class="input-control text noMargin">
								    <input id="quizName" type="text" value="${quiz.name}" disabled/>
								</div>
								<input type="submit" style="display:none"/>
							</form>
						</div>
						<div class="span1">
							<a href="#" id="editName">Edit Title</a>
						</div>			
						<div class="span1">
							<h4 class="noMargin noPadding"><i class="icon-help-2 on-left"></i>
								<span id="questionCount">${quiz.questionCount}</span>
							</h4>
						</div>	
						<div class="span4">
							<div class="place-right">
								<button id="saveAllChangesButton" class="success" onclick="saveAllChanges();">Save All Changes</button>
								<button id="openChangeQuizContent" class="primary todo">Change Quiz</button>
							</div>
						</div>		
					</div>
					<div id="changeQuizContent" class="row noMargin" style="display:none;">
						<br/>
						<br/>
						<br/>
					</div>
				</div>
			</div>
		</div>
		<div id="quizEditTopPannelBuffer">
		</div> 
		<div id="quizEditSubPanel">
			<div class="page-content">
				<div class="grid fluid noMargin">
					<div class="row noMargin">
						<div class="span6">
							<div class="input-control textarea noMargin" data-role="input-control">
		                        <textarea id="quizDescription" class="noResize" disabled>${quiz.description}</textarea>
		                    </div>
		              	</div>
		              	<div class="span2">
							<a href="#" id="editDescription">Edit Description</a>
						</div>
						<div class="span4">
							<div class="row noMargin">
								<#if quiz.time != 0>
									<div class="input-control checkbox">
									    <label>
									        <input id="timeLimitCheck" type="checkbox" checked/>
									        <span class="check"></span>
									        Time limit
									    </label>
									</div>
									<div class="place-right">
									    <div class="input-control text size1">
										    <input id="mm" type="text" value="${quiz.timeMinutes}" placeholder="MM" />
										</div><strong> min </strong>
										<div class="input-control text size1">
										    <input id="ss" type="text" value="${quiz.timeSeconds}" placeholder="SS" />
										</div><strong> sec </strong>
									</div>
								<#else>
									<div class="input-control checkbox">
									    <label>
									        <input id="timeLimitCheck" type="checkbox" />
									        <span class="check"></span>
									        Time limit
									    </label>
									</div>
									<div class="place-right">
									    <div class="input-control text size1">
										    <input id="mm" type="text" placeholder="MM" disabled/>
										</div><strong> min </strong>
										<div class="input-control text size1">
										    <input id="ss" type="text" placeholder="SS" disabled/>
										</div><strong> sec </strong>
									</div>
								</#if>
							</div>
							<div class="row noMargin">
								<div class="input-control checkbox">
								    <label class="noMargin">
								        <input id="allowOthersEditCheck" type="checkbox" <#if quiz.open>checked</#if> />
								        <span class="check"></span>
								        Allow others to edit this quiz
								    </label>
								</div>
							</div>
							<!--<div class="row noMargin">
								<div class="input-control checkbox">
								    <label class="noMargin todo">
								        <input id="randomizeCheck" type="checkbox"/>
								        <span class="check"></span>
								        Randomize questions
								    </label>
								</div>
							</div>-->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="page-content">
			<div class="grid fluid">
				<div class="row containerFill">
					<div class="row noMargin">
						<p class="span2"><strong>Create Question</strong></p>
						<button class="primary span2" onclick="showCreateQuestion('MULTIPLE_CHOICE');">Multiple Choice</button>
						<button class="primary span2" onclick="showCreateQuestion('TRUE_FALSE');">True or False</button>
						<button class="primary span2" onclick="showCreateQuestion('SHORT_ANSWER');">Short Answer</button>
						<button class="primary span2" onclick="showCreateQuestion('FILL_IN_THE_BLANK');">Fill in the Blank</button>
						<button class="primary span2" onclick="showCreateQuestion('MATCHING');">Matching</button>
					</div>
					<div class="row hidden" id="questionCreateContent" style="display:none;">
						<#include "../includes/multiple_choice_quiz_edit.ftl">
						<#include "../includes/true_false_quiz_edit.ftl">
						<#include "../includes/short_answer_quiz_edit.ftl">
						<#include "../includes/fill_blank_quiz_edit.ftl">
					</div>
				</div>
				<div class="row">
					<div class="span5">
						<div class="row noMargin">
							<div class="input-control text">
							    <input id="findQuestionSearch" type="text" placeholder="Find existing questions..."/>
							    <button class="btn-search todo"></button>
							</div>
						</div>						
						<div class="row noMargin quizQuestionList">
							<div class="quizQuestionListContent">
								<div id="findQuestionList" class="listview-outlook" data-role="listview">
									<#list unaddedGroupQuestions as question>
	                                    <a class="list" href="#" id="quizQuestion${question.questionId}" onclick="return false;">
	                                        <div class="list-content">
	                                            <span class="list-title">${question.questionTextFirstLine}</span>
	                                            <span class="list-title questionText" style="display: none">
	                                            	${question.questionText}
	                                            </span>
	                                          	<span class="list-subtitle questionCategories">${question.categoriesString}</span>
	                                          	<span class="list-remark">
	                                          		<span>
	                                          			<strong>Rating: </strong> ${question.rating}/5
	                                          			<strong>Difficulty: </strong> ${question.difficulty}/5
	                                          		</span>
	                   								<span class="place-right">
	                   									<#if question.creatorId == user.id>
	                   										<button id="editQuestion${question.questionId}" class="small primary">Edit</button>
	                   									</#if>
	                   									<button id="addButton${question.questionId}" onclick="addQuestion(${question.questionId});" class="small success">Add</button>
	                   								</span>
	                   							</span>
	                                        </div>
	                                    </a>
	                            	</#list>
                                </div>
							</div>
						</div>						
					</div>
					<div class="span7">			
						<div class="row noMargin">
							<div class="input-control text">
							    <input id="currentQuestionFilter" type="text" placeholder="Filter questions in quiz..."/>
							</div>
						</div>
						<div class="row noMargin quizQuestionList">
							<div class="quizQuestionListContent">
								<div id="currentQuestionList" class="listview-outlook" data-role="listview">
									<#list quiz.questions as question>
	                                    <a class="list" href="#" id="quizQuestion${question.questionId}">
	                                        <div class="list-content">
	                                            <span class="list-title">${question.questionTextFirstLine}</span>
	                                            <span class="list-title questionText" style="display: none">
	                                            	${question.questionText}
	                                            </span>
	                                          	<span class="list-subtitle questionCategories">${question.categoriesString}</span>
	                                          	<span class="list-remark">
	                                          		<span>
	                                          			<strong>Rating: </strong> ${question.rating}/5
	                                          			<strong>Difficulty: </strong> ${question.difficulty}/5
	                                          		</span>
	                   								<span class="place-right">
	                   									<#if question.creatorId == user.id>
	                   										<button id="editQuestion${question.questionId}" class="small primary">Edit</button>
	                   									</#if>
	                   									<button id="removeButton${question.questionId}" onclick="removeQuestion(${question.questionId});return false;" class="small danger">Remove</button>
	                   								</span>
	                   							</span>
	                                        </div>
	                                    </a>
	                            	</#list>
                                </div>
							</div>
						</div>											
					</div>
				</div>
			</div>
		</div>
						
		
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>

	<script>
	
		var currentCreateQuestionType;
		
		$("#openChangeQuizContent").click(function(e)
		{
			e.preventDefault();
			$("#changeQuizContent").toggle();
		});
		
		$("#timeLimitCheck").click(function(e){
			
			if ($("#timeLimitCheck").is(':checked'))
			{
				$("#mm").prop('disabled', false);
				$("#ss").prop('disabled', false);
			}
			else
			{
				$("#mm").prop('disabled', true);
				$("#ss").prop('disabled', true);
			}
		});
		
		function saveAllChanges()
		{
			$("#saveAllChangesButton").attr("disabled", true);
			$("#saveAllChangesButton").removeClass("success");
			
			saveName();
			saveDescription();
			
			// Save miscellaneous data
			var id = ${quiz.quizId};
			
			var timeLimit = parseInt($("#mm").val() * 60) + parseInt($("#ss").val());
			if (!$("#timeLimitCheck").is(':checked'))
			{
				timeLimit = 0;
			}
			var openToPublic = $("#allowOthersEditCheck").is(':checked');

			$.ajax({
				type: 'POST',
				url: "/quiz/edit/save/",
				data: JSON.stringify({time: timeLimit, 
									open: openToPublic, 
									quizId: id, 
									name: currentQuizName, 
									description: currentDescription }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
				
					$("#saveAllChangesButton").addClass("success");
					$("#saveAllChangesButton").attr("disabled", false);
				
					if("success" == data["response"])
					{

					}
					else
					{

					}
				},
				error: function(error) {

			    }
			});
		}
		
		var currentQuizName;
		
		function saveName()
		{
			$("#quizName").prop('disabled', true);
			$("#editName").html("Edit Title");
			
			var quizName = $("#quizName").val();
			// Only continue if the quiz name is different
			if (quizName == currentQuizName)
			{
				return;
			}
			else
			{
				currentQuizName = $("#quizName").val();
			}
			var id = ${quiz.quizId};
			$.ajax({
				type: 'POST',
				url: "/quiz/edit/name/",
				data: JSON.stringify({name: quizName, quizId: id }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					if("success" == data["response"])
					{

					}
					else
					{

					}
				},
				error: function(error) {

			    }
			});
		}
		
		function manageNameSave()
		{
			if ($("#quizName").is(':disabled'))
			{
				currentQuizName = $("#quizName").val();
				$("#quizName").prop('disabled', false);
				$("#editName").html("Save Title");
			}
			else
			{
				saveName();
			}	
		}
		
		$("#editName").click(function(e)
		{
			e.preventDefault();
			manageNameSave();
		});
		
		
		$("#nameForm").submit(function(e){
			e.preventDefault();
			manageNameSave();
		});
		
		var currentDescription;
		
		function saveDescription()
		{
			$("#quizDescription").prop('disabled', true);
			$("#editDescription").html("Edit Description");
				
			var quizDescription = $("#quizDescription").val();
			// Only continue if the quiz name is different
			if (quizDescription == currentDescription)
			{
				return;
			}
			else
			{
				currentDescription = $("#quizDescription").val();
			}
						
			var id = ${quiz.quizId};

			$.ajax({
				type: 'POST',
				url: "/quiz/edit/description/",
				data: JSON.stringify({description: quizDescription, quizId: id }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					if("success" == data["response"])
					{

					}
					else
					{

					}
				},
				error: function(error) {

			    }
			});
		}
		
		function manageDescriptionSave()
		{
			if ($("#quizDescription").is(':disabled'))
			{
				currentDescription = $("#quizDescription").val();
				$("#quizDescription").prop('disabled', false);
				$("#editDescription").html("Save Description");
			}
			else
			{
				saveDescription();
			}
		}
		
		$("#editDescription").click(function(e)
		{
			e.preventDefault();
			manageDescriptionSave();
		});
	
	
	
	
	
	
	
	
		$(document).ready(function() {
			$.ajax({
				type: 'GET',
				url: '/question/add?groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		$("#addButton").click(function() {
			$.ajax({
				type: 'GET',
				url: '/question/add?groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		$("#createButton").click(function() {
			$.ajax({
				type: 'GET',
				url: '/question/create?groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		$("#editButton").click(function() {
			$.ajax({
				type: 'GET',
				url: '/quiz/edit/questions?quizId=${quiz.quizId}',
				success: function(data) {
					$("#questionDiv").html(data);
				}
			});
		});
		
		
		function removeQuestion(questionId) {
			$("#removeButton" + questionId).attr("disabled", true);
			$("#removeButton" + questionId).removeClass("danger");
			$("#editQuestion" + questionId).attr("disabled", true);
			$("#editQuestion" + questionId).removeClass("primary");
			$.ajax({
				type: 'DELETE',
				url: '/question/remove?quizId=${quiz.quizId}&questionId=' + questionId,
				success: function(data)
				{
					$("#quizQuestion" + questionId).fadeOut(300, function(){
						$("#quizQuestion" + questionId).remove();
					});
					
					// Decrement the question count
					$("#questionCount").html(parseInt($("#questionCount").html()) - 1);
				},
				error: function()
				{
					
				}
			});
			
			return false;
		}
		
		
		$("#currentQuestionFilter").on('input', function (){
			var searchText = $("#currentQuestionFilter").val().toLowerCase().trim();
			$("#currentQuestionList").children(".list").each(function(){
				// Get the current search text
				var questionText = $(this).find(".questionText").html().toLowerCase();
				var questionCategories = $(this).find(".questionCategories").html().toLowerCase();
				
				if (questionText.indexOf(searchText) >= 0 || questionCategories.indexOf(searchText) >= 0)
				{
					$(this).show();
				}
				else
				{
					$(this).hide();
				}
				
			});
		});
		
		
		function addQuestion(questionId)
		{
			$("#addButton" + questionId).attr("disabled", true);
			$("#addButton" + questionId).removeClass("success");
			$("#editQuestion" + questionId).attr("disabled", true);
			$("#editQuestion" + questionId).removeClass("primary");
			$.ajax({
				type: 'POST',
				url: '/question/add?questionId=' + questionId + '&groupId=${group.id}&quizId=${quiz.quizId}',
				success: function(data)
				{
					var questionToAdd = $("#quizQuestion" + questionId).clone();
					questionToAdd.removeClass("active");
					
					// Remove it from the add question list
					$("#quizQuestion" + questionId).fadeOut(300, function(){
						$("#quizQuestion" + questionId).remove();
						
						// Add the element to the current quiz question list
						questionToAdd.hide();
						
						// Change the values to be a remove option now
						var buttonToChange = questionToAdd.find("#addButton" + questionId);
						buttonToChange.addClass("danger");
						buttonToChange.html("Remove");
						buttonToChange.attr("disabled", false);
						buttonToChange.attr("onclick", "removeQuestion(" + questionId + "); return false;");
						buttonToChange.attr("id", "removeButton" + questionId);
						
						var editButton = questionToAdd.find("#editQuestion" + questionId)
						editButton.attr("disabled", false);
						editButton.addClass("primary");
						
						$("#currentQuestionList").append(questionToAdd);
						
						// Increment the question count
						$("#questionCount").html(parseInt($("#questionCount").html()) + 1);
						
						questionToAdd.fadeIn(300);
					});
					
				},
				error: function()
				{
					
				}
			});
		}
		
		
		
		
		
		function addNewlyCreatedQuestionToList(questionData)
		{
			var questionInListFormat =	'<a class="list" href="#" id="quizQuestion' + questionData['questionId'] + '"> \
							                <div class="list-content"> \
							                    <span class="list-title">' + questionData['textFirstLine'] + '</span> \
							                    <span class="list-title questionText" style="display: none"> \
							                    	' + questionData['text'] + ' \
							                    </span> \
							                  	<span class="list-subtitle questionCategories">' + questionData['categoryString'] + '</span> \
							                  	<span class="list-remark"> \
							                  		<span> \
							                  			<strong>Rating: </strong> ' + questionData['rating'] + ' \
							                  			<strong>Difficulty: </strong> ' + questionData['difficulty'] + ' \
							                  		</span> \
													<span class="place-right"> \
														<button id="removeButton' + questionData['questionId'] + '" onclick="removeQuestion(' + questionData['questionId'] + ');return false;" class="small danger">Remove</button> \
													</span> \
												</span> \
							                </div> \
							            </a>';
							            
			$(questionInListFormat).hide();
			$("#currentQuestionList").append(questionInListFormat).fadeIn(300);
			manageQuestionCount();
			categories = [];
			setCategoryButtons();
		}
		
		var categories = [];
		
		function clearCommonFields(type)
		{
			$('#descriptionText' + type).val('');
			$('#descriptionText' + type).removeClass('valid');
			$('#descriptionText' + type).removeClass('invalid');
			$('#explanationText' + type).val('');
			$('#create' + type).prop('disabled', false);
			$('#create' + type).addClass('success');
		}
		
		function countBlanks(description) {
			return (description.match(/<blank>/g) || []).length;
			return 0;
		}
		
		function createQuestion(type)
		{
			$('#create' + type).removeClass('success');
			$('#create' + type).prop('disabled', true);
			
			// Common variables between all types of questions
			var quizId = ${quiz.quizId};
			var creatorId = ${user.id};
			var desc = document.getElementById('descriptionText' + type).value;
			var explanation = document.getElementById('explanationText' + type).value;
			var reference = "";
			var hyperlink = "";
			
			if (type == 'MULTIPLE_CHOICE')
			{
				//TODO Prevalidate these fields
				var maxAnswers = 5;		
				var correct;
				var incorrect = [];		
				var path = "/question/create?quizId=" + quizId;
				var correctPos = 0;
				
				var ordered = document.getElementById('randomize').checked;
		
				for(var i = 1; i <= maxAnswers; i++) {
					if(document.getElementById('qCheck' + i).checked) {
						correct = document.getElementById('qText' + i).value;
						correctPos = i - 1;
					}
					else {
						incorrect.push(document.getElementById('qText' + i).value);
					}
				}
				
				if(!desc) {
					return;
				}
				
				if(!correct) {

					return;
				}			
								
				if(hyperlink && !reference) {
					return;
				}		
				
				
				 $.ajax({
					type: 'POST',
					url: path,
					data: JSON.stringify({groupId: ${group.id}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, correctPosition: correctPos, categories: categories }),
					dataType: "json",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function(data) {
						if("success" == data["response"])
						{
							addNewlyCreatedQuestionToList(data);
							clearCommonFields(type);
							
							// Clear the MULTIPLE_CHOICE form
							for (var i = 1; i <= 5; i++)
							{
								$('#qCheck' + i).attr('checked', false);
								$('#qText' + i).val('');
								$('#qText' + i).removeClass('valid');
								$('#qText' + i).removeClass('invalid');
								
							}
						}
						else
						{

						}
					}
				});
			}
			else if (type == 'TRUE_FALSE')
			{
				//TODO Prevalidate these fields
				var maxAnswers = 5;
				var correct;
				var incorrect = [];
				var correctPos = 0;
				var path = "/question/create?quizId=" + quizId;
		
				var ordered = true;
				
				
				if($('#trueCheck').is(':checked')) {
					correct = "True";
					incorrect.push("False");
					correctPos = 0;
				}
				else if($('#falseCheck').is(':checked')) {
					correct = "False";
					incorrect.push("True");
					correctPos = 1;
				}
				else {
					return;
				}
				
				incorrect.push("");
				incorrect.push("");
				incorrect.push("");
				incorrect.push("");
				
				if(hyperlink && !reference) {
					return;
				}
					
				
				 $.ajax({
					type: 'POST',
					url: path,
					data: JSON.stringify({groupId: ${group.id}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, correctPosition: correctPos, categories: categories }),
					dataType: "json",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function(data) {
						if("success" == data["response"]) {
							
							addNewlyCreatedQuestionToList(data);
							clearCommonFields(type);
							
							// Clear the TRUE_FALSE form

						}
						else {
						}
					}
				});
			}
			else if (type == 'SHORT_ANSWER')
			{
				//TODO Prevalidate these fields
				var maxAnswers = 5;
				var correct = document.getElementById('answerTextSHORT_ANSWER').value;
				var incorrect = ["", "", "", ""];
				var path = "/question/create?quizId=" + quizId;
				
				//TODO: once ready uncomment this code
				var reference = "";
				var hyperlink = "";
				
				var ordered = true;
				
				if(!desc) {

					return;
				}
				
				if(!correct) {

					return;
				}
				
				if(hyperlink && !reference) {

					return;
				}
					
				 $.ajax({
					type: 'POST',
					url: path,
					data: JSON.stringify({groupId: ${group.id}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, categories: categories}),
					dataType: "json",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function(data) {
						if("success" == data["response"]) {
							addNewlyCreatedQuestionToList(data);
							clearCommonFields(type);
							
							// Clear the SHORT_ANSWER form
						}
						else {

						}
					}
				});
			}
			else if (type == 'FILL_IN_THE_BLANK')
			{
				//TODO Prevalidate these fields
				var maxAnswers = 5;
				var correct = "";
				var answers = [];
				var path = "/question/create?quizId=" + quizId;
				
				count = countBlanks(desc);
	
				for(var i = 1; i <= maxAnswers; i++) {

						if(i == 1)
							correct = ($('#fb-qText' + i).val());
						else if(i <= count)
							answers.push($('#fb-qText' + i).val());
						else
							answers.push("");
					
				}

				 $.ajax({
					type: 'POST',
					url: path,
					data: JSON.stringify({groupId: ${group.id}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: answers, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, categories: categories }),
					dataType: "json",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function(data) {
						if("success" == data["response"]) {
							addNewlyCreatedQuestionToList(data);
							clearCommonFields(type);
							
							// Clear the FILL IN THE BLANK form
						}
						else {

						}
					}
				});
			}
		}
		
		function closeCreateQuestionContainer()
		{
			$('#questionCreateContent').fadeOut(300);
			categories = [];
			setCategoryButtons();
		}
		
		function showCreateQuestion(type)
		{
			currentCreateQuestionType = type;
			categories = [];
			setCategoryButtons();
			
			$('#questionCreateContent > div').each(function() {
			
				if ($(this).attr('id') == ('div' + type))
				{
					$(this).fadeIn(300);
				}
				else
				{
					$(this).hide();
				}
				
				$('#questionCreateContent').fadeIn(300);
			});
		}
		
		function manageQuestionCount()
		{
			var count = $("#currentQuestionList > .list").length;
			
			$("#questionCount").html(count);
		}
		
		
		
		
		
		
		
		
		// functions for managing tags and categories
		//set up auto complete for categories
		$(function() {
	
			/*
			var allCategories = [];
			<#if allCategories??>
			<#list allCategories as category>
			allCategories.push('${category}');
			</#list>
			</#if>
	
			$('#categories').autocomplete({
				source: allCategories
			});
			*/
		});
	
		
		function setCategoryButtons() {
			var html = "";
			for(var i = 0; i < categories.length; i++) {
				var cat = categories[i];
				if(cat[0] != '#') {
					cat = '#' + cat;
				}
				html += '<button id="catButton' + i + '" class="default" style="margin: 5px"><i onclick="removeCategory(' + i + ')" class="icon-cancel on-left"></i>  ' + cat + '</button>';
			}
			$('#categoryTags' + currentCreateQuestionType).html(html);
			
			for(var i = 0; i < categories.length; i++) {
				$('#catButton' + i).click(function(event) {
					event.preventDefault();
				});
				
			}
		}
		
		function removeCategory(index) {
			var temp = [];
			for(var j = 0; j < categories.length; j++) {
				if(index != j) {
					temp.push(categories[j]);
				}
			}
			categories = temp;
			setCategoryButtons();
		}

		$('.categoriesInput').keydown(function(event) {
			if(event.which == 188 || event.which == 13) {
				var cat = $('#categories' + currentCreateQuestionType).val().trim();
				if(cat == ""  || cat == "#") {
					event.preventDefault();
					$('#categories' + currentCreateQuestionType).val("");
					return;
				}
				if($.inArray(cat, categories) == -1)
					categories.push(cat);
				setCategoryButtons();
				$('#categories' + currentCreateQuestionType).val("");
				event.preventDefault();
			}
			
		});
	
		$('.categoriesInput').blur(function() {
	
			var cat = $('#categories' + currentCreateQuestionType).val().trim();
			
			if(cat == "" || cat == "#") {
				$('#categories' + currentCreateQuestionType).val("");
				return;
			}
			if($.inArray(cat, categories) == -1)
				categories.push(cat.substring(0));
			setCategoryButtons();
			$('#categories' + currentCreateQuestionType).val("");
			event.preventDefault();
		});
		
	</script>	


</html>