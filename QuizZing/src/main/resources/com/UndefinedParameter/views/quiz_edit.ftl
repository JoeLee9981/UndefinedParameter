<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
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
						<!--<div class="span1">
							<h4 class="noMargin noPadding"><i class="icon-help-2 on-left"></i>${quiz.questionCount}</h4>
						</div>			
						<div class="span4">
							<button class="success">Save All Changes</button>
							<button id="openChangeQuizContent">Change Quiz</button>
						</div>-->		
					</div>
					<div id="changeQuizContent" class="row noMargin" style="display:none;">
						<br/>
						<br/>
						<br/>
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
							    <input id="questionFilter" type="text" placeholder="Search questions in quiz..."/>
							</div>
						</div>
						<div id="quizQuestionList" class="row noMargin">
							<div id="quizQuestionListContent">


								<div class="listview-outlook" data-role="listview">
                                    <a class="list marked" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                    <a class="list" href="#">
                                        <div class="list-content">
                                            <span class="list-title">subscribe@metroui.net</span>
                                            <span class="list-subtitle">MetroUI: News on 26/10/2013</span>
                                            <span class="list-remark">Hello friend! Newest for Metro UI CSS</span>
                                        </div>
                                    </a>
                                </div>





							</div>
						</div>
					</div>
					<div class="span7">
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
			<!--<#include "../includes/footer.ftl">-->
		</div>
	</body>

	<script>
	
		$("#openChangeQuizContent").click(function(e)
		{
			e.preventDefault();
			$("#changeQuizContent").toggle();
		})
		
		var currentQuizName;
		
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
		}
		
		$("#editName").click(function(e)
		{
			e.preventDefault();
			manageNameSave();
		})
		
		
		$("#nameForm").submit(function(e){
			e.preventDefault();
			manageNameSave();
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
	</script>	


</html>