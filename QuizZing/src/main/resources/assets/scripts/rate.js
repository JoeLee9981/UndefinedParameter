/*
 * This javascript file is used to perform ratings for quality and difficulty for
 * 		all objects (quiz, question, group, organization)
 */


function rateQuizQuality(rating, quizId, groupId) {
	$.ajax({
		type: 'POST',
		url: '/quiz/rate/rating?groupId=' + groupId + '&quizId=' + quizId + '&rating=' + rating,
	});
}

function rateQuizDifficulty(rating, quizId, groupId) {
	$.ajax({
		type: 'POST',
		url: '/quiz/rate/difficulty?groupId=' + groupId + '&quizId=' + quizId + '&rating=' + rating,
	});
}

function rateQuestionQuality(rating, questionId, groupId) {
	$.ajax({
		type: 'POST',
		url: '/question/rate/rating?groupId=' + groupId + '&questionId=' + questionId + '&rating=' + rating,
	});
}

function rateQuestionDifficulty(rating, questionId, groupId) {
	$.ajax({
		type: 'POST',
		url: '/question/rate/difficulty?groupId=' + groupId + '&questionId=' + questionId + '&rating=' + rating,
	});
}