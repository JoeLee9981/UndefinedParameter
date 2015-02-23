<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/rate.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<script src="/assets/plugins/metro_ui/js/metro-countdown.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/quiz.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
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
						<h2>
							<strong>${quiz.name}</strong>
							<#if editable>
								<button id="editButton" onclick="location.href='/quiz/edit?groupId=${groupId}&quizId=${quiz.quizId}'" class="place-right success">Edit Quiz</button>
							</#if>
						</h2>
						<h2>${quiz.description?html}</h2>
					</div>
				</div>
				<div id="start-quiz-div">
					<div class="row">
						<div class="span3">
							<div class="panel">
							    <div class="panel-header">
							        Quiz Details
							    </div>
							    <div class="panel-content">
					       			<h5>Created By: ${quiz.creatorId?html}</h5>
									<h5>Difficulty:</h5>
									
									<#if userDifficulty &gt; 0>
										<div id="difficulty" class="rating small fg-yellow">
										</div>
									<#else>
										<div id="difficulty" class="rating small fg-red">
										</div>
									</#if>
									
									<h5>Rating:</h5>
									<#if userRating &gt; 0>
										<div id="rating" class="fg-yellow rating small">
										</div>
									<#else>
										<div id="rating" class="rating small">
										</div>
									</#if>
									
									<h5><i class="icon-clock"></i> ${quiz.timeString}</h5>
							    </div>
							</div>			
						</div>
						<#if !empty>
							<div class="span2">
									<button class="primary large" onclick="startQuiz()">Start</button>
							</div>
						<#else>
							<div class="span4">
								<h2 class="text-alert"><strong>There are no questions in this quiz.</strong></h2>
							</div>
						</#if>
						
`					</div>
				</div>
				<div id="quizDiv" hidden="true">
					<div class="row">
						<div class="span8">
								<div class="row">
									<pre><p id="questionHead"> </p></pre>
								</div>
							<div class="row">
								<div id="answerDiv">
									
								</div>										
							</div>
							<div class="row">
								<div>
									<button id="prevQuestion" onclick="previousQuestion()" class="primary">Previous</button>
									<button id="nextQuestion" onclick="nextQuestion()" class="success">Next</next>
								</div>
							</div>
						</div>
						<div class="span4">
							
						</div>
					</div>
				</div>
				<div id="quizFinish" hidden="true">
					<h2>Your Quiz Stats: </h2><br/>
					<h3 id="scoreText"/>
					<h5>Rate the Quiz Difficulty:</h5>
					<#if userDifficulty &gt; 0>
						<div id="setdifficulty" class="rating small fg-yellow">
						</div>
					<#else>
						<div id="setdifficulty" class="rating small fg-red">
						</div>
					</#if>
					<h5>Rate the Quiz Quality:</h5>
					<#if userRating &gt; 0>
						<div id="setrating" class="fg-yellow rating small">
						</div>
					<#else>
						<div id="setrating" class="rating small">
						</div>
					</#if>
					<h3><a href="/feedback">Give Us Your Feedback</a></h3>
				</div>
				<div class="row">
					
				</div>
			</div>
		</div>
		
		<#include "../includes/footer.ftl">
	</body>
	
	<script>
	
		//Star rating for quiz quality (entry page)
		$(function() {
			$("#rating").rating({
				static: true,
				<#if userRating &gt; 0>
					score: ${userRating},
				<#else>
					score: ${quiz.rating},
				</#if>
				stars: 5,
				showHint: true,
				hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
			});
		});
		
		//Star rating for difficulty (entry page)
		$(function() {
			$("#difficulty").rating({
				static: true,
				<#if userDifficulty &gt; 0>
					score: ${userDifficulty},
				<#else>
					score: ${quiz.difficulty},
				</#if>
				stars: 5,
				showHint: true,
				hints: ['cake', 'easy', 'average', 'hard', 'impossible'],
			});		
		});
		
		//star rating for finish page (end of quiz)
		$(function() {
			$("#setdifficulty").rating({
				<#if loggedIn>
					static: false,
				<#else>
					static: true,
				</#if>
				<#if userRating &gt; 0>
					score: ${userDifficulty},
				<#else>
					score: ${quiz.difficulty},
				</#if>
				stars: 5,
				showHint: true,
				hints: ['cake', 'easy', 'average', 'hard', 'impossible'],
				click: function(value, rating) {
					<#if loggedIn>
						rateQuizDifficulty(value, ${quiz.quizId});
						$("#setdifficulty").attr('class', 'rating small fg-yellow');
						rating.rate(value);
					<#else>
						loginDialog();
					</#if>
				}
			});		
		});
		
		//star difficulty for finish page (end of quiz)
		$(function() {
			$("#setrating").rating({
				<#if loggedIn>
					static: false,
				<#else>
					static: true,
				</#if>
				<#if userRating &gt; 0>
					score: ${userRating},
				<#else>
					score: ${quiz.rating},
				</#if>
				stars: 5,
				showHint: true,
				hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
				click: function(value, rating) {
					<#if loggedIn>
						rateQuizQuality(value, ${quiz.quizId});
						$("#setrating").attr('class', 'rating small fg-yellow');
						rating.rate(value);
					<#else>
						loginDialog();
					</#if>
				}
			});
		});
		
		function loginDialog() {
			$.Dialog({
		        shadow: true,
		        overlay: true,
		        flat: true,
		        icon: '<span class="icon-rocket"></span>',
		        title: 'Login',
		        width: 500,
		        content: '<h5>You must log in to participate in ratings</h5><button class=\"primary\" onclick=\"location.href=\'/login\'\">Login</button><button class=\"success\" onclick=\"location.href=\'/register\'\">Register</button>',
		        onShow: function(_dialog){
		            console.log(_dialog);
		        }
		    });
		}
		
		/*********************** QUIZ SYSTEM ****************************/
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
			
			// Quick hack to trick it so that it will be on quesiton 0 when starting quiz. TODO
			quizPosition = -1;

			nextQuestion();
		}

		function nextQuestion() {
			$("#answerDiv").fadeOut(150, function() {
				quizPosition++;
				if (quizPosition == 0)
				{
					$("#prevQuestion").hide();
				}
				else
				{
					$("#prevQuestion").show();
				}
				
				if (quizPosition == quiz.length)
				{
					$("#quizFinish").show();
					SetProgressBar();
					submitQuiz();
					document.getElementById('nextQuestion').disabled = true;
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
				
				if(quizPosition < quiz.length) {
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
				
				if (quizPosition == 0)
				{
					$("#prevQuestion").hide();
				}
				else
				{
					$("#prevQuestion").show();
				}
				
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
			var correct = correctAnswers[quizPosition];

			var html = ''; 
			
			for(var i = 0; i < answers.length; i++) 
			{
				var style = "";
				var isChecked = submittedAnswers[quizPosition] == i ? true : false;
				if (isChecked && quizInProgress)
				{
					style = "primary";
				}
				if (!quizInProgress) {
					if(answers[i] == correct)
						style = "success";
					else if(isChecked) {
						style = "danger";
					}
				}
				html += "<button class='command-button block " + style +" size8' onclick='setAnswer(" + i + ")'><small>";
				html += answers[i];
				html += "</small></button>";
			}
			

			document.getElementById('answerDiv').innerHTML = html;
		}
		
		function setAnswer(val) {
			if(!quizInProgress)
				return;
			submittedAnswers[quizPosition] = parseInt(val);
			nextQuestion();
		}
		
		function submitQuiz() {
			quizInProgress = false;
			var score = 0.0;

			for(var i = 0; i < submittedAnswers.length; i++) {

				if(submittedAnswers[i] != -1) {
					var answers = questions[i];
					if(answers[submittedAnswers[i]] == correctAnswers[i])
						score++;
				}
			}
			document.getElementById('scoreText').innerHTML = "Score: " + (score / correctAnswers.length * 100).toFixed(2) + "%";
		}
		
		
	</script>
</html>