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
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">		
	</head>
	<body class="metro">
	
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<h3>Create a Question</h3>
			<div class="grid fluid">
				<div class="row">
					<div class="span3">
						<nav class="sidebar light">
							<ul>
						        <li><a href="#">True or False</a></li>
						        <li class="active"><a href="#">Multiple Choice</a></li>
						        <li><a href="#">Fill In The Blank</a></li>
						        <li><a href="#">Essay</a></li>
						        <li><a href="#">Matching</a></li>
						        <li><a href="#">Short Answer</a></li>
							</ul>
						</nav>
					</div>
					<div class="span9">
						<form id="create-question-form">
							<label><h5>Question</h5>
								<div class="input-control textarea">
								    <textarea id="descriptionText"></textarea>
								</div>
							</label>
							
							<div class="row">	
								<div class="span8">
									<label id="mc-answer-options"><h5>Answer Options</h5>
										<div class="input-control text size5">
										    <input type="text" id="qText1"/>
										</div>
										<div class="input-control radio">
										    <label>
										        <input name="question-options" type="radio" id="qCheck1" />
										        <span class="check"></span>
										    </label>
										</div>	
										<div class="input-control text size5">
										    <input type="text" id="qText2"/>
										</div>
										<div class="input-control radio">
										    <label>
										        <input name="question-options" type="radio" id="qCheck2" />
										        <span class="check"></span>
										    </label>
										</div>							
										<div class="input-control text size5">
										    <input type="text" id="qText3"/>
										</div>
										<div class="input-control radio">
										    <label>
										        <input name="question-options" type="radio" id="qCheck3" />
										        <span class="check"></span>
										    </label>
										</div>								
										<div class="input-control text size5">
										    <input type="text" id="qText4"/>
										</div>
										<div class="input-control radio">
										    <label>
										        <input name="question-options" type="radio" id="qCheck4" />
										        <span class="check"></span>
										    </label>
										</div>	
										<div class="input-control text size5">
										    <input type="text" id="qText5"/>
										</div>
										<div class="input-control radio">
										    <label>
										        <input name="question-options" type="radio" id="qCheck5" />
										        <span class="check"></span>
										    </label>
										</div>	
										<div class="span8">
											<input type="button" onclick="addQuestion()" value="Submit"></input>
											<p id="responseLabel" />
										</div>						
									</label>
								</div>
								<div class="span4">
									<div class="panel">
									    <div class="panel-header">
									        Note
									    </div>
									    <div class="panel-content">
									        Use the checkboxes next to your answer to indicate that it is
									        a correct answer.
									    </div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</body>
	<script>
		var maxAnswers = 5;
		var quizId = ${quizId};
		function addQuestion() {
			
			
			//TODO Prevalidate these fields
			var desc = document.getElementById('descriptionText').value;
			var correct;
			var type = "MULTIPLE_CHOICE";
			var incorrect = [];
			var creatorId = 1;
			var path = "/question/create?quizId=" + quizId;
			
			document.getElementById('responseLabel').innerHTML = "";
			
			for(var i = 1; i <= maxAnswers; i++) {
				if(document.getElementById('qCheck' + i).checked) {
					correct = document.getElementById('qText' + i).value;
				}
				else {
					incorrect.push(document.getElementById('qText' + i).value);
				}
			}
			
			if(!correct) {
				document.getElementById('responseLabel').innerHTML = "You must check a correct answer";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}
			
	
			 $.ajax({
				type: 'POST',
				url: path,
				data: JSON.stringify({questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					if("success" == data["response"]) {
						window.location = data['redirect'];
					}
					else {
						document.getElementById('responseLabel').className = "text-alert";
						document.getElementById('responseLabel').innerHTML = data["message"];
					}
				}
			});
		}
		
		
	</script>
</html>

