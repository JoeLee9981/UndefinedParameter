/*
 * This javascript file is used to perform ratings for quality and difficulty for
 * 		all objects (quiz, question, group, organization)
 */


function rateQuizQuality(rating, quizId) {
	$.ajax({
		type: 'POST',
		url: '/quiz/rate/rating?quizId=' + quizId + '&rating=' + rating,
		headers: {
			Accept: "application/json",
		},
		success: function(data) {
			if(data['response'] == 'login') {
				alert("login");
			}
		},
		error: function() {
			alert("Error");
		}
	});
}

function rateQuestionQuality() {
	alert("Not yet implemented.");
}

function rateQuizDifficulty() {
	alert("Not yet implemented.");
}

function rateQuestionDifficulty() {
	alert("Not yet implemented.");
}