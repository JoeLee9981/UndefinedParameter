function Quiz(questionCount, questions) {
	this.questions = questions;
	this.questionCount = questionCount;
	this.index = -1;
}

Quiz.prototype.startQuiz = function() {
	alert("Quiz Started");
	this.index = 0;
}

function Question(questionText, correctAnswer, answers) {
	this.questionText = questionText;
	this.correctAnswer = correctAnswer;
	this.answers = answers;
}

Question.prototype.answerQuestion = function() {
	this.answer = answer;
}