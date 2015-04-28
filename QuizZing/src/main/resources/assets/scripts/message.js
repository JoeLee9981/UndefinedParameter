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

function setViewed(messageId) {
	$.ajax({
		type: 'POST',
		url: '/user/message/view?messageId=' + messageId,
		success: function(data) {
			$('#' + messageId + 'Icon').hide();
		}
	});
}

function deleteMessage(messageId, type) {
	
	$.ajax({
		type: 'POST',
		url: '/user/message/delete?messageId=' + messageId + '&type=' + type,
		success: function(data) {
			var notify = $.Notify({
				content: "Message deleted",
				style: {background: 'red', color: 'white'},
				timeout: 3000
			});
			$('#' + messageId + 'Button').hide();
		}
	});
}

function displayTermsAndConditions() {
	var content = "<pre style='white-space: pre-wrap; width: 700px'>";
	content += "<h2>Web Site Tems and Conditions of Use</h2>";
	content += "<h3>1. Terms</h3>";
	content += "<p>By accessing this web site, you are agreeing to be bound by these web site Terms and Conditions of Use, all applicable laws and regulations, and agree that you are responsible for compliance with any applicable local laws. If you do not agree with any of these terms, you are prohibited from using or accessing this site. The materials contained in this web site are protected by applicable copyright and trade mark law.</p>";
	content += "<h3>2. Use License</h3>";
	content += "<ol type=\"a\">";
	content += "<li>Permission is granted to temporarily download one copy of the materials (information or software) on QuizZing's web site for personal, non-commercial transitory viewing only. This is the grant of a license, not a transfer of title, and under this license you may not:";
	content += "<ol type=\"i\">";
	content += "<li>modify or copy the materials;</li>";
	content += "<li>use the materials for any commercial purpose, or for any public display (commercial or non-commercial);</li>";
	content += "<li>attempt to decompile or reverse engineer any software contained on QuizZing's web site;</li>";
	content += "<li>remove any copyright or other proprietary notations from the materials; or</li>";
	content += "<li>transfer the materials to another person or \"mirror\" the materials on any other server.</li></ol></li>";
	content += "<li>This license shall automatically terminate if you violate any of these restrictions and may be terminated by QuizZing at any time. Upon terminating your viewing of these materials or upon the termination of this license, you must destroy any downloaded materials in your possession whether in electronic or printed format.</li></ol>";
	content += "</pre><br/><br/>";

	$.Dialog({
        shadow: true,
        overlay: true,
        flat: true,
        icon: '<span class="icon-power"></span>',
        title: 'Terms & Conditions',
        width: 600,
        padding: 20,
        content: content
    });
}