<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
		<script src="/scripts/jquery-ui.min.js"></script>
		<script src="/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/css/overrides.css" rel="stylesheet">
		<link href="/css/question.css" rel="stylesheet">
		
		<link rel="stylesheet" type="text/css" href="/plugins/unicorn/unicorn_buttons.css" />
		
				
	</head><head>
		<title>QuizZing</title>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		<h1>QUIZ VIEW</h1>
		<p>${quiz.description?html}<br/>
		Created By: ${quiz.creatorId?html}<br/>
		Difficulty: ${quiz.difficulty?html}<br/>
		Rating: ${quiz.rating?html}<br/>
		Time: ${quiz.time?html}</p>
		<br/>
		<br/>
		
		<div id="startQuizDiv">
			<h2>Start Quiz</h2>
			<button onclick="startQuiz()">Start</button>
		</div>
		<div id="quizDiv" hidden="true">
			<h2 id="questionHead"> </h2><br/>
			<div id="answerDiv"></div>
			<button id="prevQuestion" onclick="previousQuestion()">previous</button>
			<button id="nextQuestion" onclick="nextQuestion()">next</next>
			<button id="submitQuiz" onclick="submitQuiz()">submit</next>
		</div>


	</body>
	
	<script>
		var quiz = [];
		//this is a multidimensional array of the answers by question
		var questions = [];
		var correctAnswers = [];
		var submittedAnswers = [];
		var quizPosition = -1;

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
		}
		
		function startQuiz() {
			document.getElementById('startQuizDiv').hidden = true;
			document.getElementById('quizDiv').hidden = false;
			nextQuestion();
		}

		function nextQuestion() {
			if(quizPosition < quiz.length - 1)
				quizPosition++;
			document.getElementById('questionHead').innerHTML = quiz[quizPosition];
			
			setAnswers()
			
			if(quizPosition == quiz.length - 1) {
				document.getElementById('nextQuestion').disabled = true;
			}
			else {
				document.getElementById('nextQuestion').disabled = false;
				document.getElementById('prevQuestion').disabled = false;
			}

		}
		
		function previousQuestion() {
			if(quizPosition > 0)
				quizPosition--;
			document.getElementById('questionHead').innerHTML = quiz[quizPosition];
			
			setAnswers()
			
			if(quizPosition == 0) {
				document.getElementById('prevQuestion').disabled = true;
			}
			else {
				document.getElementById('prevQuestion').disabled = false;
				document.getElementById('nextQuestion').disabled = false;
			}
		}
		
		function setAnswers() {
			var answers = questions[quizPosition];
			var html = '<table>'; 
			
			for(var i = 0; i < answers.length; i++) {
				var isChecked = submittedAnswers[quizPosition] == i ? true : false;
				html += '<tr><td><input type="radio" name="answer" onclick="setAnswer(' + i + ')"'; 
				if(isChecked)
					html += ' checked';
				html += '></td><td><h3>' + answers[i] + '</h3></td>';
			}
			html += '</table>';
			document.getElementById('answerDiv').innerHTML = html;
		}
		
		function setAnswer(val) {
			submittedAnswers[quizPosition] = parseInt(val);
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