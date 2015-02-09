
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