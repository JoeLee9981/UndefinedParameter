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

function flagQuestion(questionId) {
	
	var content = '<div style="margin: 10px" class="grid span8">' +
				  		'<h3 class="text-center">Please provide a short explanation to why the question is wrong</h3><br/>' +
				  		'<div class="input-control textarea">' +
					    	'<textarea maxlength="255" id="reason' + questionId + '"></textarea>' +
					    '</div>' +
				  		'<div class="span3 offset2">' +
							'<button style="margin: 5px" onclick="doFlag(' + questionId + ')" class="success large center">Submit</button>' +
							'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
						'</div>' +
				  '</div>';
	
	$.Dialog({
	shadow: true,
	overlay: true,
	icon: '<span class="icon-flag-2"></span>',
	title: 'Flag Question',
	padding: 10,
	content: content
	});	
	
}

function doFlag(questionId) {
	var reason = $('#reason' + questionId).val();	
	$('#flag' + questionId).attr("class", "icon-flag-2 fg-red on-right");
	$.Dialog.close();
	$.ajax({
		type: 'POST',
		url: '/question/flag?questionId=' + questionId,
		data: reason,
		headers: {
			"Content-Type": "application/json"
		},
	});
}

function unflagQuestion(questionId, reason) {
	var content = '<div style="margin: 10px" class="grid span8">' +
						'<h3>This question has been flagged wrong!</h3><br/>' +
						'<p><strong>Reason: </strong>' + reason + '</p>' +
						'<div class="span5 offset1">' +
						'<button style="margin: 5px" onclick="doUnflag(' + questionId + ')" class="success large center">Mark Correct</button>' +
						'<button style="margin: 5px" onclick="" class="primary large center">Edit</button>' +
						'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
					'</div>' +
				'</div>';
				
	$.Dialog({
	shadow: true,
	overlay: true,
	icon: '<span class="icon-flag-2"></span>',
	title: 'Flagged Question',
	padding: 10,
	content: content
	});
}

function doUnflag(questionId) {
	$('#flag' + questionId).attr("class", "icon-flag-2 fg-gray on-right");
	$.Dialog.close();
	$.ajax({
		type: 'POST',
		url: '/question/unflag?questionId=' + questionId,
	});
	
}

function editQuestion(questionId) {
	
}