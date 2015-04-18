<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/d3.min.js"></script>
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/rate.js"></script>
		<script src="/assets/scripts/quiz.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<script src="/assets/plugins/metro_ui/js/metro-countdown.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/quiz.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
		
		<style>		
			path { 
				stroke: #60a917;
				stroke-width: 2;
				fill: none;
			}
			
			circle {
				fill: #4390df;
			}

			.axis path,
			.axis line {
				fill: none;
				stroke: grey;
				stroke-width: 1;
				shape-rendering: crispEdges;
			}
		</style>
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
								<button id="editButton" onclick="location.href='/quiz/edit?groupId=${groupId}&quizId=${quiz.quizId}'" class="place-right success large">Edit Quiz</button>
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
					       			<h5>Created By: ${quiz.creatorName?html}</h5>
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
					</div>
				</div>
				<div id="quizDiv" hidden="true">
					<div class="row">
						<div class="span12">
							<div class="row">
								<div>
									<button id="prevQuestion" onclick="previousQuestion()" class="primary large" hidden><i class="icon-previous"></i></button>
									<button id="nextQuestion" onclick="nextQuestion()" class="success large"><i class="icon-next"></i></button>
									<button id="submitQuiz" class="warning place-right large" hidden><i class="icon-checkmark"></i> Submit Quiz</button>
									<button id="flagQuestionButton" style="margin-right: 5px" class="danger large place-right" onclick="flagButtonClick()"><i title="Flag Question as Wrong" class="icon-flag-2 fg-white"></i> Flag Question</button>
									<div class="row" id="questionRatings">
									</div>
									
									
								</div>
							</div>
							<div id="flagDiv"></div>
							<div class="row">
								<pre style="white-space: pre-wrap"><p id="questionHead"> </p></pre>
							</div>
							<div class="row">
								<div id="answerDiv">
									
								</div>										
							</div>
						</div>
						<div class="span4">
							
						</div>
					</div>
				</div>
				
				<div id="quizFinish" hidden="true">
					<h2>Your Quiz Stats: </h2><br/>
					<div id="quizGraphs"></div>
					<h3 id="scoreText"></h3>
					<h3 id="prevScoreText"></h3>
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
		
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
	
	<script>
	
		/************************* QUIZ RATINGS HERE *********************************/
	
		function flagButtonClick() {
			flagQuestion(q.getQuestion().id);
		}

		function onFlag() {
			q.getQuestion().flagged = true;
			$('#flagQuestionButton').attr("class", "large place-right");
			$('#flagQuestionButton').prop("disabled", true);
			$('#flagDiv').html("<h3 class='text-alert'><i title='Flag Question as Wrong' class='icon-flag-2 fg-red'></i> This question has been flagged as wrong</h3>");
		}
	
		function setQuestionRatings(questionId, rating, difficulty, userRating, userDifficulty) {

			var rateColor = "fg-red";
			var diffColor = "fg-blue";

			if(userRating) {
				rateColor = "fg-yellow";
				rating = userRating;
			}

			if(userDifficulty) {
				diffColor = "fg-yellow";
				difficulty = userDifficulty;
			}
			
			var html = '<div class="row noMargin"> \
							<div class="span1"><p>Rating:</p></div> \
							<div id="quest-rating" class="span2 rating small ' + rateColor + '"> \
							</div> \
							<div class="span1"><p>Difficulty:</p></div> \
							<div id="quest-difficulty" class="span2 rating small ' + diffColor + '"> \
							</div> \
						</div>';

			$('#questionRatings').html(html);

			$("#quest-difficulty").rating({
				<#if loggedIn>
					static: false,
				<#else>
					static: true,
				</#if>
				score: difficulty,
				stars: 5,
				showHint: true,
				hints: ['cake', 'easy', 'average', 'hard', 'impossible'],
				click: function(value, rating) {
					<#if loggedIn>
						rateQuestionDifficulty(value, questionId, ${groupId});
						$("#quest-difficulty").attr('class', 'span2 rating small fg-yellow');
						rating.rate(value);
						q.getQuestion().userDifficulty = value;
					<#else>
						loginDialog();
					</#if>
				}
			});
			
			$("#quest-rating").rating({
				<#if loggedIn>
					static: false,
				<#else>
					static: true,
				</#if>
				score: rating,
				stars: 5,
				showHint: true,
				hints: ['useless', 'poor', 'average', 'good', 'excellent'],
				click: function(value, rating) {
					<#if loggedIn>
						rateQuestionQuality(value, questionId, ${groupId});
						$("#quest-rating").attr('class', 'span2 rating small fg-yellow');
						rating.rate(value);
						q.getQuestion().userRating = value;
					<#else>
						loginDialog();
					</#if>
				}
			});
		}
	
		var hint = "Please take the quiz before you rate it.";
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
				hints: [hint, hint, hint, hint, hint],
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
				hints: [hint, hint, hint, hint, hint],
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
						rateQuizDifficulty(value, ${quiz.quizId}, ${groupId});
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
						rateQuizQuality(value, ${quiz.quizId}, ${groupId});
						$("#setrating").attr('class', 'rating small fg-yellow');
						rating.rate(value);
					<#else>
						loginDialog();
					</#if>
				}
			});
		});
		
		/*********************** QUIZ SYSTEM ****************************/
		var q;
		
		//On the window being loaded we read from freemarker and add the quiz information into an
		//	array
		window.onload = function() {

			var quest = [];
			<#list quiz.questions as quest>
				var answers = [];

				<#list quest.answers as answer>
					answers.push("${answer}".replace(/&amp;#39;/g, "&#39;"));
				</#list>

				var question;
				
				<#if quest.explanation??>
					question = new Question(${quest.questionId}, "${quest.type}", "${quest.questionText}".replace(/&amp;#39;/g, "&#39;"), "${quest.correctAnswer}".replace(/&amp;#39;/g, "&#39;"), answers, "${quest.explanation}".replace(/&amp;#39;/g, "&#39;"), ${quest.rating}, ${quest.difficulty}, ${quest.userRating}, ${quest.userDifficulty}, ${quest.flagged?c});
				<#else>
					question = new Question(${quest.questionId}, "${quest.type}", "${quest.questionText}".replace(/&amp;#39;/g, "&#39;"), "${quest.correctAnswer}".replace(/&amp;#39;/g, "&#39;"), answers, "No explanation has been given", ${quest.rating}, ${quest.difficulty}, ${quest.userRating}, ${quest.userDifficulty}, ${quest.flagged?c});
				</#if>
				quest.push(question);
			</#list>
			q = new Quiz(quest.length, quest);

		}
		
		$('#submitQuiz').click(function() {
		
			var content = '<div style="margin: 10px" class="grid span8">' +
						  	'<h3 class="text-center">You are about to submit the quiz, once you do this you can not change your answers.</h3><br/>' +
						  	'<div class="span3 offset2">' +
									'<button style="margin: 5px" onclick="submitQuiz()" class="success large center">Submit</button>' +
									'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
							'</div>' +
						  '</div>';
			
			$.Dialog({
		        shadow: true,
		        overlay: true,
		        icon: '<span class="icon-power"></span>',
		        title: 'Submit Quiz',
		        padding: 10,
		        content: content
		    });

		});
		
		function SetProgressBar()
		{
			var percentage = q.getProgressPercent();

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
			q.startQuiz();
		
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
				
				var question = q.nextQuestion();
				
				if(!q.hasPrevious()) {
					document.getElementById('prevQuestion').disabled = true;
					$('#prevQuestion').attr("class", "large");
				}
				else {
					document.getElementById('prevQuestion').disabled = false;
					$('#prevQuestion').attr("class", "primary large");
				}
				
				if(!q.hasNext()) {
					document.getElementById('nextQuestion').disabled = true;
					$('#nextQuestion').attr("class", "large");
				}

				if(question.flagged) {
					$('#flagQuestionButton').attr("class", "large place-right");
					$('#flagQuestionButton').prop("disabled", true);
					$('#flagDiv').html("<h3 class='text-alert'><i title='Flag Question as Wrong' class='icon-flag-2 fg-red'></i> This question has been flagged as wrong</h3>");
				}
				else {
					$('#flagQuestionButton').attr("class", "danger large place-right");
					$('#flagQuestionButton').prop("disabled", false);
					$('#flagDiv').html("");
				}

				setAnswers();
				setQuestionRatings(question.id, question.rating, question.difficulty, question.userRating, question.userDifficulty);
				
				SetProgressBar();
				$("#answerDiv").fadeIn(150);	
			});

		}
		
		function previousQuestion() {
			$("#answerDiv").fadeOut(150, function()
			{
				var question = q.previousQuestion();
				
				if(!q.hasPrevious()) {
					document.getElementById('prevQuestion').disabled = true;
					$('#prevQuestion').attr("class", "large");
				}
				document.getElementById('nextQuestion').disabled = false;
				$('#nextQuestion').attr("class", "success large");
				
				if(quizPosition > 0)
					quizPosition--;

				if(question.flagged) {
					$('#flagQuestionButton').attr("class", "large place-right");
					$('#flagQuestionButton').prop("disabled", true);
					$('#flagDiv').html("<h3 class='text-alert'><i title='Flag Question as Wrong' class='icon-flag-2 fg-red'></i> This question has been flagged as wrong</h3>");
				}
				else {
					$('#flagQuestionButton').attr("class", "danger large place-right");
					$('#flagQuestionButton').prop("disabled", false);
					$('#flagDiv').html("");
				}
				
				setAnswers();
				setQuestionRatings(question.id, question.rating, question.difficulty, question.userRating, question.userDifficulty);
				
				SetProgressBar();
				$("#answerDiv").fadeIn(150);
			});

		}
		
		function setAnswers() 
		{
			var html = "";

			if(q.getQuestionType() == "MULTIPLE_CHOICE" || q.getQuestionType() == "TRUE_FALSE") {
				document.getElementById('questionHead').innerHTML = q.getQuestionText();
				html = getMultiChoiceDiv();
			}
			else if(q.getQuestionType() == "FILL_IN_THE_BLANK") 
			{
				var questText = q.getQuestionText();
				var answers = q.getAnswers();
				for(var i = 0; i < answers.length; i++) 
				{
					questText = questText.replace('&amp;lt;blank&amp;gt;', '<strong>' + (i + 1) + ': __________</strong>');
				}
				document.getElementById('questionHead').innerHTML = questText;
				html = getFillBlankDiv();
			}
			else if(q.getQuestionType() == "SHORT_ANSWER") {
				document.getElementById('questionHead').innerHTML = q.getQuestionText();
				html = getShortAnswerDiv();
			}
			else if(q.getQuestionType() == "MATCHING") {
	
				var question = q.getQuestionText();
				var pattern5 = /&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)&amp;lt;4&amp;gt;(.*)&amp;lt;5&amp;gt;(.*)/g;
				var pattern4 = /&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)&amp;lt;4&amp;gt;(.*)/g;
				var pattern3 = /&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)/g;
				var pattern2 = /&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)/g;

				var foundMatch = 0;

				if(match = pattern5.exec(question)) {
					foundMatch = 5;
				}
				else if(match = pattern4.exec(question)) {
					foundMatch = 4;
				}
				else if(match = pattern3.exec(question)) {
					foundMatch = 3;
				}
				else if(match = pattern2.exec(question)) {
					foundMatch = 2;
				}
				//there is no matching with a single question
				var qText = "";
				
				for(var i = 1; i <= foundMatch; i++) {
					qText += "<p><strong>" + String.fromCharCode(64 + i) + ":</strong> " + match[i] + "</p>";
				}
				document.getElementById('questionHead').innerHTML = qText;
				html = getMatchingDiv();
			}
			
			document.getElementById('answerDiv').innerHTML = html;
		}

		function getFillBlankDiv() {
			var answers = q.getAnswers();
			var submitAnswers = q.getSubmittedAnswer();
			
			var html = '';
			
			for(var i = 0; i < answers.length; i++) {

				if(!q.inProgress) {
					var style = "success";
					if(submitAnswers[i] != answers[i]) {
						style = "alert";
					}
					
					html += '<p><strong>Correct Answer: </strong></p><h3 class="text-' + style + '">' + answers[i] + '</h3>';
					html += '<h3>' + (i+1) + ': <input type="text" id="answerInput' + i + '" value="' + submitAnswers[i] + '" onchange="submitAnswers()" disabled/></h3>';
				}
				else {
					html += '<h3>' + (i+1) + ': <input type="text" id="answerInput' + i + '" value="' + submitAnswers[i] + '" onchange="submitAnswers()"/></h3>';
				}
			}
			if(!q.inProgress) {
				html += '<button class="info large" onclick="showExplanation()">Show Explanation</button>';

			}
			return html;
		}

		function getMultiChoiceDiv() {
			var answers = q.getAnswers();
			var correct = q.getCorrectAnswer();

			var html = ''; 

			for(var i = 0; i < answers.length; i++) 
			{
				var style = "";
				var isChecked = q.getSubmittedAnswer() == answers[i] ? true : false;

				if (isChecked && quizInProgress)
				{
					style = "primary";
				}
				if (!q.inProgress) {
					if(answers[i] == correct)
						style = "success";
					else if(isChecked) {
						style = "danger";
					}
				}
				html += "<button id='answerButton" + i + "' class='command-button block " + style +" size8' onclick='setAnswer(\"" + answers[i] + "\")'><small>";
				if(!q.inProgress) {
					if(answers[i] == correct && isChecked)
						html += '<i class="icon-checkmark"></i> ';
					else if(answers[i] == correct)
						html += '<i class="icon-cancel-2 fg-red"></i> ';
				}
				html += answers[i];
				html += "</small></button>";
			}
			
			if(!q.inProgress) {
				html += '<button class="info large" onclick="showExplanation()">Show Explanation</button>';

			}

			return html;
		}

		function getShortAnswerDiv() {
			var answers = q.getAnswers();
			var correct = q.getCorrectAnswer();

			var html = ''; 
			var style = "";

			if (!q.inProgress) {
				if(q.getCorrectAnswer() == q.getSubmittedAnswer()) {
					style = "success";
				}
				else {
					style = "alert";
				}
			}

			html += '<div class="input-control text size5">'

			if (!q.inProgress) {
				html += '<input type="text" id="answerInput" value="' + q.getSubmittedAnswer() + '" onchange="submitAnswer()" disabled/>';
				html += '</div>';
				html += '<br/><p><strong>Correct Answer: </strong></p><h3 class="text-' + style + '">' + q.getCorrectAnswer() + '</h3>';
				
			}
			else {
				html += '<input type="text" id="answerInput" value="' + q.getSubmittedAnswer() + '" onchange="submitAnswer()"/>';
				html += '</div>';
			}

			if(!q.inProgress) 
				html += '<br/><button class="info large" onclick="showExplanation()">Show Explanation</button>';


			return html;
		}

		function getMatchingDiv() {

			var html = '';
			var answers = q.getAnswers();
			var submitAnswers = q.getSubmittedAnswer();
			var correctAnswers = q.getCorrectAnswers();
			var style = '';

			html += '<div class="row">';
			for(var i = 0; i < answers.length; i++) {
				//build the select for the answer
				var options = '';

				if(!q.inProgress) {
					if(submitAnswers[i] != correctAnswers[i]) {
						style = "error-state";
						html += '<p><strong>Correct Answer: </strong></p><h3 class="text-alert">' + correctAnswers[i] + '</h3>';
					}
					else {
						style = "success-state";
						html += '<p><strong>Correct Answer: </strong></p><h3 class="text-success">' + correctAnswers[i] + '</h3>';
					}
					
				}
				
				
				for(var j = 0; j < answers.length; j++) {
					
					if(submitAnswers[i] == String.fromCharCode(65 + j)) {
						options += '<option value="' + String.fromCharCode(65 + j) + '" selected="selected">' + String.fromCharCode(65 + j) + '</option>';
						
					}
					else
						options += '<option value="' + String.fromCharCode(65 + j) + '">' + String.fromCharCode(65 + j) + '</option>';
				}
				

				html += '<div class="span2"><strong><h4>' + answers[i] + '</h4></strong><div class="input-control select size1 inline' + style + '"><select id="answerInput' + i + '" value="' + submitAnswers[i] + '" onchange="submitAnswers()">' + options + '</select></div></div>';
			}
			html += '</div>';

			if(!q.inProgress) 
				html += '<br/><button class="info large" onclick="showExplanation()">Show Explanation</button>';
			
			return html;
		}

		function submitAnswer() {
			q.submitAnswer($('#answerInput').val());
		}

		function submitAnswers() {
			var submitAnswers = [];
			var count = q.getAnswers().length;

			for(var i = 0; i < count; i++) {
				submitAnswers.push($('#answerInput' + i).val());
			}
			q.submitAnswer(submitAnswers);
		}

		function setAnswer(val) {
			if(!q.inProgress)
				return;

			q.submitAnswer(val);
			
			nextQuestion();
		}
		
		function showExplanation() {
			var content = q.getExplanation();
			
			$.Dialog({
		        shadow: true,
		        overlay: true,
		        flat: true,
		        icon: '<span class="icon-power"></span>',
		        title: 'Answer Explanation',
		        width: 500,
		        padding: 10,
		        content: "<div class='span8'>" + content + "</div>"
		    });
		}
		
		function submitQuiz() {
			$.Dialog.close();
			$('#submitQuiz').prop("disabled", true);
			$('#submitQuiz').attr("class", "place-right large");
			
			$("#quizFinish").show();
			var scored = q.submitQuiz();

			<#if user??>
				var userID = ${user.id};
			
				var quizID = ${quiz.quizId};
				var payload = JSON.stringify({ 
								quizId: quizID,
								userId: userID,
								score: scored,
				});
														
				$.ajax({
					type: 'POST',
					url: "/quiz",
					data: payload,
					dataType: "json",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function(data) 
					{
							document.getElementById('scoreText').innerHTML = "Score: " + scored + "%";
							
							drawScoreLinePlot(data["scores"]);
					},
					error: function(data) {
						document.getElementById('scoreText').innerHTML = "Score: " + scored + "% (Warning: This score was not saved.)";
					}
				});	
							
				var prevBestScore = ${userBestScore};
	
				if(prevBestScore < 0.0) {
					document.getElementById('prevScoreText').innerHTML = "Previous Best Score: n/a";
				}
				else {
					document.getElementById('prevScoreText').innerHTML = "Previous Best Score: " + prevBestScore + "%";
				}
			<#else>
				document.getElementById('scoreText').innerHTML = "Score: " + scored + "%";
			</#if>

			setAnswers();
		}
		
		/*********************** GRAPHING UTILITIES ****************************/
		
		function drawScoreBarGraph(dataset) {
			$("#quizGraphs").empty();
			
			// Set graph canvas
			var margin = {top: 30, right: 20, bottom: 30, left: 50},
				width = 300 - margin.left - margin.right,
				height = 300 - margin.top - margin.bottom;
			
			var x = d3.scale.ordinal()
			    .rangeRoundBands([0, width], .1);
			
			var y = d3.scale.linear()
			    .range([height, 0]);
			
			var xAxis = d3.svg.axis()
			    .scale(x)
			    .orient("bottom");
			
			var yAxis = d3.svg.axis()
			    .scale(y)
			    .orient("left")
			    .ticks(10, "%");
			
			// Adds the svg canvas
			var svg = d3.select("#quizGraphs")
				.append("svg")
					.attr("width", width + margin.left + margin.right)
					.attr("height", height + margin.top + margin.bottom)
				.append("g")
					.attr("transform", 
						  "translate(" + margin.left + "," + margin.top + ")");
			
			/*d3.tsv("data.tsv", type, function(error, data) {
			  x.domain(dataset.map(function(d) { return d.letter; }));
			  y.domain([0, d3.max(data, function(d) { return d.frequency; })]);*/
			// Scale the range of the data
			x.domain([0, d3.max(dataset, function(d) { return d.score; })]);
			y.domain([0, d3.max(dataset, function(d) { return d.score; })]);
			
			  svg.append("g")
			      .attr("class", "x axis")
			      .attr("transform", "translate(0," + height + ")")
			      .call(xAxis);
			
			  svg.append("g")
			      .attr("class", "y axis")
			      .call(yAxis)
			    .append("text")
			      .attr("transform", "rotate(-90)")
			      .attr("y", 6)
			      .attr("dy", ".71em")
			      .style("text-anchor", "end")
			      .text("Frequency");
			
			  svg.selectAll(".bar")
			      .data(dataset)
			    .enter().append("rect")
			      .attr("class", "bar")
			      .attr("x", function(d) { return x(d.score); })
			      .attr("width", x.rangeBand())
			      .attr("y", function(d) { return y(d.score); })
			      .attr("height", function(d) { return height - y(d.score); });
		}

		function type(d) {
			d.score = +d.score;
			return d;
		}
		
		function drawScoreLinePlot(dataset) {
			$("#quizGraphs").empty();
			
			// Set graph canvas
			var margin = {top: 30, right: 20, bottom: 30, left: 50},
				width = 300 - margin.left - margin.right,
				height = 300 - margin.top - margin.bottom;

			// Parse the date / time
			var parseDate = d3.time.format("%d-%b-%y").parse;

			// Set dataset
			var scores = aggregateScores(dataset);
			var data = dataset;
			var i = 0;
			
			// Set the ranges
			var x = d3.scale.linear().range([0, width]).domain([0, 100]);
			var y = d3.scale.linear().range([height, 0]);

			// Define the axes
			var xAxis = d3.svg.axis().scale(x)
				.orient("bottom").ticks(5);

			var yAxis = d3.svg.axis().scale(y)
				.orient("left").ticks(5);

			// Define the line
			var valueline = d3.svg.line()
				.x(function(d) { return x(d.score); })
				.y(function(d) { return y(scores[d.score]); });
				
			// Adds the svg canvas
			var svg = d3.select("#quizGraphs")
				.append("svg")
					.attr("width", width + margin.left + margin.right)
					.attr("height", height + margin.top + margin.bottom)
				.append("g")
					.attr("transform", 
						  "translate(" + margin.left + "," + margin.top + ")");

				// Scale the range of the data
				x.domain([0, 100]);
				y.domain([0, d3.max(data, function(d) { return scores[d.score]; })]);
				
				// Add title.
				svg.append("text")
			        .attr("x", (width / 2))             
			        .attr("y", 0 - (margin.top / 2))
			        .attr("text-anchor", "middle")  
			        .style("font-size", "16px") 
			        .style("text-decoration", "underline")  
			        .text("Total Previous Scores");

				// Add the valueline path.
				svg.append("path")
					.attr("class", "line")
					.attr("d", valueline(data));

				// Add the scatterplot
				svg.selectAll("dot")
					.data(data)
				  .enter().append("circle")
					.attr("r", 3.5)
					.attr("cx", function(d) { return x(d.score); })
					.attr("cy", function(d) { return y(scores[d.score]); });

				// Add the X Axis
				svg.append("g")
					.attr("class", "x axis")
					.attr("transform", "translate(0," + height + ")")
					.call(xAxis);

				// Add the Y Axis
				svg.append("g")
					.attr("class", "y axis")
					.call(yAxis);
		}
		
		function aggregateScores(dataset) {
			var aggregatedScores = {};
			
			for(var i in dataset) {
				if(dataset[i].score in aggregatedScores) {
					aggregatedScores[dataset[i].score] += 1;
				} else {
					aggregatedScores[dataset[i].score] = 1;
				}
			}
			
			return aggregatedScores;
		}

	</script>
</html>