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
					    	'<textarea style="resize: none" maxlength="255" id="reason' + questionId + '"></textarea>' +
					    '</div>' +
				  		'<div class="row" style="text-align: center">' +
							'<button style="margin: 5px" onclick="doFlag(' + questionId + ')" class="success large center">Submit</button>' +
							'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
						'</div>' +
				  '</div>';
	
	$.Dialog({
	shadow: true,
	overlay: true,
	flat: true,
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
	onFlag();
}

function unflagQuestion(questionId, groupId, reason) {
	var content = '<div style="margin: 10px" class="grid span8">' +
					'<p class="text-right text-alert">Has this been flaged incorrectly? <button class="link" onclick="doUnflag(' + questionId + ')">Mark as correct</button></p>' +
					'<h3 class="text-center">This question has been flagged for the following reason:</h3><br/>' +
					'<p class="text-center" style="font-size: 20px">' + reason + '</p>' +
					'<div class="row" style="text-align: center">' +
						'<button style="margin: 5px; width: 100px" onclick="editQuestion(' + questionId + ', ' + groupId + ')" class="primary large">Edit</button>' +
						'<button style="margin: 5px; width: 100px" onclick="$.Dialog.close()" class="danger large">Cancel</button>' +
					'</div>' +
				'</div>';
				
	$.Dialog({
	shadow: true,
	overlay: true,
	flat: true,
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

function editQuestion(questionId, groupId) {
	$.ajax({
		type: 'GET',
		url: "/question/edit?groupId=" + groupId + "&questionId=" + questionId,
		success: function(data) {
			$('#group-content').html(data);
		},
		error: function(error) {
	    	$('#group-content').html("<h3>You do not have access to edit this question</h3>");
	    }
	});
	$.Dialog.close();
}

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