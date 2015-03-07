function Quiz(questionCount, questions) {
	this.questions = questions;
	this.questionCount = questionCount;
	this.index = -1;
	this.inProgress = false;
	this.submittedAnswers = [];
	
	for(var i = 0; i < this.questionCount; i++) {
		this.submittedAnswers.push(-1);
	}
}

Quiz.prototype.submitQuiz = function() {
	this.inProgress = false;
	var score = 0.0;

	for(var i = 0; i < this.submittedAnswers.length; i++) {
		var submittedIndex = this.submittedAnswers[i];
		var question = this.questions[i];
		
		if(submittedIndex >= 0) {
			var answers = this.questions[i].answers;

			if(answers[submittedIndex] == question.correctAnswer)
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

Quiz.prototype.getAnswers = function() {
	return this.questions[this.index].answers;
}

Quiz.prototype.getQuestionText = function() {
	return this.questions[this.index].questionText;
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

function Question(questionText, correctAnswer, answers, explanation) {
	this.questionText = questionText;
	this.correctAnswer = correctAnswer;
	this.answers = answers;
	this.explanation = explanation;
}

Question.prototype.answerQuestion = function(answer) {
	this.givenAnswer = answer;
}