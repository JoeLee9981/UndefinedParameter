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
				<div class="row">
					<div class="span5">
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
					<div class="span7">
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
				</div>
			</div>
		</div>
						
		<div class="page-content">
			<div class="grid fluid">
			
			
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				
			
			
				<div class="row">

					<h1>
						<button id="createButton" class="place-right success">Create A Question</button>
						<button id="addButton" class="place-right warning">Add A Question</button>
						<button id="editButton" class="place-right primary">Edit Quiz</button>
					</h1><br/>
					<h2>${quiz.description}</h2>
					<label id="errorLabel"></label>
					
					<div id="questionDiv"></div>
					
				</div>
			</div>
		</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>

	<script>
	
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
		
	</script>	


</html>