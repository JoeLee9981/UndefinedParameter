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

function rateQuestionQuality(rating, questionId) {
	$.ajax({
		type: 'POST',
		url: '/question/rate/rating?questionId=' + questionId + '&rating=' + rating,
	});
}

function rateQuestionDifficulty(rating, questionId) {
	$.ajax({
		type: 'POST',
		url: '/question/rate/difficulty?questionId=' + questionId + '&rating=' + rating,
	});
}