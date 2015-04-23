function sendMessage(senderId, sendeeId) {
	var content = '<div style="margin: 10px" class="grid span8">' +
				  		'<h3 class="text-center">Enter your message</h3><br/>' +
				  		'<div class="input-control textarea">' +
					    	'<textarea style="resize: none" maxlength="2000" id="messageText"></textarea>' +
					    '</div>' +
				  		'<div class="row" style="text-align: center">' +
							'<button id="messageSendBttn" style="margin: 5px" onclick="doSend(' + senderId + ', ' + sendeeId + ')" class="success large center">Send</button>' +
							'<button style="margin: 5px" onclick="$.Dialog.close()" class="danger large center">Cancel</button>' +
						'</div>' +
				  '</div>';
	
	$.Dialog({
	shadow: true,
	overlay: true,
	flat: true,
	icon: '<span class="icon-mail"></span>',
	title: 'Send Message',
	padding: 10,
	content: content
	});	
	
}

function doSend(senderId, sendeeId) {
	var message = $('#messageText').val();

	$('#messageSendBttn').attr('disabled', true);
	$('#messageSendBttn').removeClass('success');
	$('#messageSendBttn').html("Sending...");
	$.ajax({
		type: 'POST',
		url: '/user/message?senderId=' + senderId + '&sendeeId=' + sendeeId,
		data: message,
		headers: {
			"Content-Type": "application/json"
		},
		success: function(data) {
			$('#group-content').html(data);
			$.Dialog.close();
			var notify = $.Notify({
				content: "Your Message Was Sent",
				style: {background: 'green', color: 'white'},
				timeout: 3000
			});
		},
		error: function(error) {
			var notify = $.Notify({
				content: "Unable to Send Message",
				style: {background: 'red', color: 'white'},
				timeout: 3000
			});
			$('#messageSendBttn').attr('disabled', false);
			$('#messageSendBttn').addClass('success');
			$('#messageSendBttn').html("Send");
	    }
	});
}