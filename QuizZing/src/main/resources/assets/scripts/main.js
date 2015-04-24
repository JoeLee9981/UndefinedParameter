
$.ajax({
    url: '/user/message/count',
    type: 'GET',
    headers: {
		Accept: "application/json",
	},
    success: function(data) {
    	var count = parseInt(data['results']);
    	if(count > 0) {
    		$('#mailIcon').addClass('bg-red');
    		$('#mailIcon').css('padding', '10px');
    		$('#mailIcon').css('border-radius', '50%');
    		$('#mailIcon').css('margin-top', '-20px');
    	}
	}
});

$(".todo").click(function(e){
	e.preventDefault();
	alert('This feature is not yet implemented.');
});

function loginUser()
{
	alert('loginUser in main.js is not yet implemented');
}

function logoutUser()
{
	$.ajax({
	    url: '/logout',
	    username: '',
	    password: '',
	    type: 'POST',
	    error: function(error) {
	    	window.location='/';
	    }
	});
}

/* Trim the leading and trailing white space of text when leaving an input 
 * Do not use this class on password fields */
$(".focusOutTrim").focusout(function(e){
	var trimmedValue = $.trim(e.target.value);
	e.target.value = trimmedValue;
});


/* Check that the input is not empty.  If it is, give it the invalid class, otherwise give it the valid class */
$(".focusOutValidateNotEmpty").focusout(function(e){
	var trimmedValue = $.trim(e.target.value);
	if (trimmedValue.length > 0)
	{
		$(e.target).addClass('valid').removeClass('invalid');
	}
	else
	{
		$(e.target).addClass('invalid').removeClass('valid');
	}
});


/* Verify the field (typically an input box) is not empty and give it the cooresponding valid or invalid class 
 * and return a boolean stating whether it is valid or not */
function fieldTrimValidateNotEmpty(field)
{
	var trimmedValue = $.trim(field[0].value);
	field[0].value = trimmedValue;
	if (trimmedValue.length > 0)
	{
		$(field).addClass('valid').removeClass('invalid');
		return true;
	}
	else
	{
		$(field).addClass('invalid').removeClass('valid');
		return false;
	}
}


/* Scroll to the top of the page */
function scrollToTop()
{
	$("html, body").animate({ scrollTop: 0 }, 300);
}