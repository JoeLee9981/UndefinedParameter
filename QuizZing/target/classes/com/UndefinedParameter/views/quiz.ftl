<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
		<script src="/scripts/jquery-ui.min.js"></script>
		<script src="/plugins/metro_ui/min/metro.min.js"></script>
		<script src="/plugins/metro_ui/js/metro-countdown.js"></script>
		<link href="/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/css/quiz.css" rel="stylesheet">	
		<link href="/css/overrides.css" rel="stylesheet">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<div class="grid fluid">
				<div class="row">
					<div class="progress-bar small">
						<div id="quiz-progress-bar" class="bar bg-cyan">
						</div>
					</div>
				</div>
				<div id="quiz-subnav" class="row">
					<div class="span12">
						Upper Content
					</div>
				</div>
				<div id="start-quiz-div">
					<div class="row">
						<div class="offset2 span4">
							<p>${quiz.description?html}<br/>
							Created By: ${quiz.creatorId?html}<br/>
							Difficulty: ${quiz.difficulty?html}<br/>
							Rating: ${quiz.rating?html}<br/>
							<i class="icon-clock"></i> ${quiz.time?html}</p>
							<br/>
							<br/>				
						</div>
						<div class="span2">
							<button class="primary large" onclick="startQuiz()">Start</button>
						</div>
`					</div>
				</div>
				<div id="quizDiv" hidden="true">
					<div class="row">
						<div class="span8">
							<div class="row">
								<p id="questionHead"> </p>
							</div>
							<div class="row">
								<div id="answerDiv">
									
								</div>												
							</div>
							<div class="row">
								<div>
									<button id="prevQuestion" onclick="previousQuestion()" class="primary">Previous</button>
									<button id="nextQuestion" onclick="nextQuestion()" class="success">Next</next>
									<button id="submitQuiz" onclick="submitQuiz()" class="success">submit</next>
								</div>
							</div>
						</div>
						<div class="span4">
							Side menu
						</div>
					</div>
				</div>
				<div id="quizFinish" hidden="true">
					<h2>Your Quiz Stats</h2>
				</div>
				<div class="row">
					Bottom
				</div>
			</div>
		</div>
		
		<#include "../includes/footer.ftl">
	</body>
	
	<script>
		var quiz = [];
		//this is a multidimensional array of the answers by question
		var questions = [];
		var correctAnswers = [];
		var submittedAnswers = [];
		var quizPosition = -1;
		var quizInProgress = false;
	
		
		
		//On the window being loaded we read from freemarker and add the quiz information into an
		//	array
		window.onload = function() {
			<#list quiz.questions as quest>
				quiz.push("${quest.questionText}");
				correctAnswers.push("${quest.correctAnswer}");
				var answers = [];
				<#list quest.answers as answer>
					answers.push("${answer}");
				</#list>
				questions.push(answers);
				submittedAnswers.push(-1);
			</#list>
			
			/*startQuiz();*/
		}
		
		function SetProgressBar()
		{
			var percentage = quizPosition / (quiz.length) * 100;
			
			if (percentage >= 100)
			{
				$("#quiz-progress-bar").removeClass("bg-cyan").addClass("bg-green");	
			}
			else
			{
				$("#quiz-progress-bar").removeClass("bg-green").addClass("bg-cyan");
			}
			
			var percentageString = "" + percentage + "%";
			$("#quiz-progress-bar").css("width", percentageString);
		}
		
		
		
		function startQuiz() {
			quizInProgress = true;
			document.getElementById('start-quiz-div').hidden = true;
			document.getElementById('quizDiv').hidden = false;
			nextQuestion();
		}

		function nextQuestion() {
			$("#answerDiv").fadeOut(150, function() {
				quizPosition++;
				if (quizPosition == quiz.length)
				{
					$("#quizDiv").hide();
					$("#quizFinish").show();
					SetProgressBar();
					return;
				}
				
				if (quizPosition == quiz.length - 1)
				{
					$("#nextQuestion").html("Finish");
				}
				else
				{
					$("#nextQuestion").html("Next");
				}

				document.getElementById('questionHead').innerHTML = quiz[quizPosition];
				
				setAnswers();
				
				if(quizPosition == quiz.length) {
					document.getElementById('nextQuestion').disabled = true;
				}
				else {
					document.getElementById('nextQuestion').disabled = false;
					document.getElementById('prevQuestion').disabled = false;
				}
				SetProgressBar();
				$("#answerDiv").fadeIn(150);	
			});

		}
		
		function previousQuestion() {
			$("#answerDiv").fadeOut(150, function()
			{
				if(quizPosition > 0)
					quizPosition--;
				document.getElementById('questionHead').innerHTML = quiz[quizPosition];
				
				if (quizPosition == quiz.length - 1)
				{
					$("#nextQuestion").html("Finish");
				}
				else
				{
					$("#nextQuestion").html("Next");
				}
				
				setAnswers();
				
				if(quizPosition == 0) {
					document.getElementById('prevQuestion').disabled = true;
				}
				else {
					document.getElementById('prevQuestion').disabled = false;
					document.getElementById('nextQuestion').disabled = false;
				}
				SetProgressBar();
				$("#answerDiv").fadeIn(150);
			});

		}
		
		function setAnswers() 
		{
			var answers = questions[quizPosition];
			var html = ''; 
			
			/*
			for(var i = 0; i < answers.length; i++) 
			{
				var isChecked = submittedAnswers[quizPosition] == i ? true : false;
				html += '<tr><td><input type="radio" name="answer" onclick="setAnswer(' + i + ')"'; 
				if(isChecked)
					html += ' checked';
				html += '></td><td><h3>' + answers[i] + '</h3></td>';
			}*/
			
			
			for(var i = 0; i < answers.length; i++) 
			{
				var style = "";
				var isChecked = submittedAnswers[quizPosition] == i ? true : false;
				if (isChecked)
				{
					style = "primary";
				}
				html += "<button class='command-button block " + style +" size8' onclick='setAnswer(" + i + ")'><small>";
				html += answers[i];
				html += "</small></button>";
			}
			

			document.getElementById('answerDiv').innerHTML = html;
		}
		
		function setAnswer(val) {
			submittedAnswers[quizPosition] = parseInt(val);
			nextQuestion();
		}
		
		function submitQuiz() {
			var score = 0.0;

			for(var i = 0; i < submittedAnswers.length; i++) {

				if(submittedAnswers[i] != -1) {
					var answers = questions[i];
					if(answers[submittedAnswers[i]] == correctAnswers[i])
						score++;
				}
			}
			alert("Score: " + score / correctAnswers.length * 100 + "%");
		}
		
		
	</script>
</html>