function Quiz(questionCount, questions) {
	this.questions = questions;
	this.questionCount = questionCount;
	this.index = -1;
	this.inProgress = false;
	this.submittedAnswers = [];
	
	for(var i = 0; i < this.questionCount; i++) {
		if(questions[i].type == "FILL_IN_THE_BLANK") {
			var submitAnswers = [];
		
			for(var j = 0; j < questions[i].answers.length; j++) {

				submitAnswers.push("");
			}
			this.submittedAnswers.push(submitAnswers);
		}
		else {
			this.submittedAnswers.push("");
		}		
	}
}

Quiz.prototype.submitQuiz = function() {
	this.inProgress = false;
	var score = 0.0;

	for(var i = 0; i < this.submittedAnswers.length; i++) {
		var submittedAnswer = this.submittedAnswers[i];
		var question = this.questions[i];
		if(question.type == "FILL_IN_THE_BLANK") {
			for(var j = 0; j < submittedAnswer.length; j++) {
				
				if(submittedAnswer[j] == question.answers[j]) {
					score += 1.0 / submittedAnswer.length;
				}
			}
		}
		else if(question.type == "MATCHING") {
			for(var j = 0; j < submittedAnswer.length; j++) {
				
				if(submittedAnswer[j] == question.correctAnswers[j]) {
					score += 1.0 / submittedAnswer.length;
				}
			}
		}
		else {
			if(submittedAnswer == question.correctAnswer)
				score++;
		}
	}

	return (score / this.questionCount * 100).toFixed(2);
}

Quiz.prototype.startQuiz = function() {

	this.index = -1;
	this.inProgress = true;
}

Quiz.prototype.submitAnswer = function(answer) {
	this.submittedAnswers[this.index] = answer;
}

Quiz.prototype.getSubmittedAnswer = function() {
	return this.submittedAnswers[this.index];
}

Quiz.prototype.getProgressPercent = function() {
	return ((this.index + 1) / this.questionCount) * 100;
}

Quiz.prototype.getExplanation = function() {
	return this.questions[this.index].explanation;
}

Quiz.prototype.getCorrectAnswer = function() {
	return this.questions[this.index].correctAnswer;
}

Quiz.prototype.getCorrectAnswers = function() {
	return this.questions[this.index].correctAnswers;
}

Quiz.prototype.getAnswers = function() {
	return this.questions[this.index].answers;
}

Quiz.prototype.getQuestionType = function() {
	return this.questions[this.index].type;
}

Quiz.prototype.getQuestionText = function() {
	return this.questions[this.index].questionText;
}

Quiz.prototype.getQuestion = function() {
	return this.questions[this.index];
}

Quiz.prototype.nextQuestion = function() {
	
	if(this.hasNext())
		this.index++;
	
	return this.questions[this.index];
}

Quiz.prototype.previousQuestion = function() {
	
	if(this.hasPrevious())
		this.index--;
	
	return this.questions[this.index];
}

Quiz.prototype.hasNext = function() {
	return this.index < this.questionCount - 1;
}

Quiz.prototype.hasPrevious = function() {
	return this.index > 0;
}

function Question(id, type, questionText, correctAnswer, answers, explanation, rating, difficulty, userRating, userDifficulty) {
	this.id = id;
	this.type = type;
	this.questionText = questionText;
	this.correctAnswer = correctAnswer;
	this.answers = answers;
	this.explanation = explanation;
	this.rating = rating;
	this.difficulty = difficulty;
	this.userRating = userRating;
	this.userDifficulty = userDifficulty;
	
	if(type == "MATCHING") {
		
		this.correctAnswers = [];
		for(var i = 0; i < answers.length; i++) {
			var pattern = /&amp;lt;([A-E])&amp;gt;(.*)/g;
			var match = pattern.exec(answers[i]);
			if(match && match.length > 2) {
				answers[i] = match[2];
				this.correctAnswers.push(match[1]);
			}
			else {
				answers[i] = "Whoops something has gone wrong! There must be an error in your question format.";
				this.correctAnswers.push("");
			}
		}
	}
}

Question.prototype.answerQuestion = function(answer) {
	this.givenAnswer = answer;
}