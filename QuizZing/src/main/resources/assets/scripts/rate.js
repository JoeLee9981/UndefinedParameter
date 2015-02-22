/*
 * This javascript file is used to perform ratings for quality and difficulty for
 * 		all objects (quiz, question, group, organization)
 */


function rateQuizQuality(rating, quizId) {
	$.ajax({
		type: 'POST',
		url: '/quiz/rate/rating?quizId=' + quizId + '&rating=' + rating,
	});
}

function rateQuizDifficulty(rating, quizId) {
	$.ajax({
		type: 'POST',
		url: '/quiz/rate/difficulty?quizId=' + quizId + '&rating=' + rating,
	});
}

function rateQuestionQuality() {
	alert("Not yet implemented.");
}

function rateQuestionDifficulty() {
	alert("Not yet implemented.");
}