<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/register.css" rel="stylesheet">
	</head>

	<body>
		
		<#include "../includes/navigation.ftl">
		
			<div class="metro">
				<div class="grid fluid">
					<div class="page-content">
						
					    <div class="row">
					    	<div>
								<h2>Sign Up For QuizZing</h2>
							</div>
						</div>
						<div class="row">
							<form id="register" class="span6" onsubmit="register();return false;">
								<div>
									<h4>Profile Information</h4>
								</div>
								<div class="row">
									<div class="input-control text span6">
									    <input type="text" id="firstname" value="" placeholder="First Name"/>
									</div>
									<div class="input-control text span6">
									    <input type="text" id="lastname" value="" placeholder="Last Name"/>
									</div>		
								</div>
								<div class="row">
									<div class="input-control text span12">
									    <input type="text" id="email" value="" placeholder="Email Address"/>
									</div>											
								</div>		
								<div class="row">
									<div class="input-control text span4">
									    <input type="text" id="city" value="" placeholder="City"/>
									</div>		
									<div class="input-control text span4">
									    <input type="text" id="state" value="" placeholder="State/Province"/>
									</div>	
									<div class="input-control text span4">
									    <input type="text" id="country" value="" placeholder="Country"/>
									</div>											
								</div>
								<div>
									<h4>Password And Account Recovery</h4>
								</div>
								<div class="row">
									<div class="input-control password span12">
									    <input type="password" id="password" value="" placeholder="Password"/>
									</div>											
								</div>
								<div class="row">
									<div class="input-control password span12">
									    <input type="password" id="confirmPassword" value="" placeholder="Confirm Password"/>
									</div>		
								</div>
								<div class="row">
									<div class="input-control text span12">
									    <input type="text" id="secretQuestion" value="" placeholder="Secret Question"/>
									</div>									
								</div>		
								<div class="row">
									<div class="input-control text span12">
									    <input type="text" id="secretAnswer" value="" placeholder="Answer To Secret Question"/>
									</div>									
								</div>
								<div class="row">
								</div>
								<div class="row" id="acceptterms">						
									<div class="input-control checkbox">
									    <label>
									        <input type="checkbox" />
									        <span class="check"></span>
									        I Accept the <a href="#" class="todo">Terms and Conditions</a>.
									    </label>
									</div>
								</div>	
								<div class="row">
									<button type="submit" class="large primary">Create Account</button>								
								</div>						
							</form>
						</div>
						
					</div>
				</div>
			<div>	
								
		<#include "../includes/footer.ftl">

	</body>
</html>

<script>
	
	function register()
	{
		alert('hi');
		return false;
	}
	
	function validateNotEmpty(field)
	{
		field.val($.trim(field.val()));
		field.addClass('valid');
		if (field.val().length > 0)
		{
			field.removeClass('invalid').addClass('valid');
			return true;
		}
		else
		{
			field.removeClass('valid').addClass('invalid');
			return false;
		}
	}
	
	$('#firstname').focusout(function(){
		var field = $('#firstname');
		validateNotEmpty(field);
	});
	
	$('#lastname').focusout(function(){
		var field = $('#lastname');
		validateNotEmpty(field);
	});
	
	$('#email').focusout(function(){
		var field = $('#email');
		field.val($.trim(field.val()));
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (re.test(field.val()))
		{
			field.removeClass('invalid').addClass('valid');
		}
		else
		{
			field.removeClass('valid').addClass('invalid');
		}
	});
	
	$('#city').focusout(function(){
		var field = $('#city');
		field.val($.trim(field.val()));
	});
	
	$('#state').focusout(function(){
		var field = $('#state');
		field.val($.trim(field.val()));
	});
	
	$('#country').focusout(function(){
		var field = $('#country');
		field.val($.trim(field.val()));
	});		
	
	var confirmHasBeenEntered = false;
	$('#password').focusout(function(){
		var field = $('#password');
		if (field.val().length > 8)
		{
			field.removeClass('invalid').addClass('valid');
		}
		else
		{
			field.removeClass('valid').addClass('invalid');
		}
		
		if (confirmHasBeenEntered)
		{
			$('#confirmPassword').focusout();
		}
	});
	
	$('#confirmPassword').focusout(function(){
		confirmHasBeenEntered = true;
		var field = $('#confirmPassword');
		if (field.val() === $('#password').val())
		{
			field.removeClass('invalid').addClass('valid');
		}
		else
		{
			field.removeClass('valid').addClass('invalid');
		}
	});	
	
	function submit() {
		
		var email = document.getElementById('email').value;
		var first = document.getElementById('firstName').value;
		var mid = document.getElementById('middleName').value;
		var last = document.getElementById('lastName').value;
		var password = document.getElementById('password').value;
		var reenterPassword = document.getElementById('reenterPassword').value;
		var country = document.getElementById('country').value;
		var state = document.getElementById('state').value;
		var city = document.getElementById('city').value;
		var squestion = document.getElementById('squestion').value;
		var sanswer = document.getElementById('sanswer').value;

		if(!first || !last) {
			doError("Please enter First and Last name.");
			return;
		}
		
		if(!isValidEmail(email)) {
			doError("Please enter a valid email address.");
			return;
		}
		
		if(!password || !reenterPassword) {
			doError("Please enter and confirm your password.");
			return;
		}
		
		if(password != reenterPassword) {
			doError("Your passwords don't match.");
			return;
		}
		
		if(!country || !city) {
			doError("Please fill out your country and city.");
			return;
		}
		
		if(!squestion || !sanswer) {
			doError("Please enter your secret question and answer.");
			return;
		}
		
		
		var payload = JSON.stringify({ userName: email, firstName: first, middleName: mid, lastName: last,
						country: country, city: city, state: state, email: email, password: password,
						secretQuestion: squestion, secretAnswer: sanswer });

		$.ajax({
			type: 'POST',
			url: "/register",
			data: payload,
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function(data) {
				if(data["response"] == "success") {
					doLogin(email, password);
				}
				else {
					doError(data["message"]);
				}
			},
			error: function(data) {
				doError("Unable to complete registration, please try again");
			}
		});
	}
	
	//posts an error message into the error label
	function doError(message) {
		document.getElementById('error_label').innerHTML = message;
	}
	
	//validates an email pattern
	function isValidEmail(email) {
		var pattern = /^.*@.*\..*$/
		return email.match(pattern);
	}
	
	
	function doLogin(username, password) {
	
		$.ajax({
		    url: '/login',
		    username: username,
		    password: password,
		    type: 'POST',
		    success: function(data) {
		    	console.log(data);
		    	window.location='/';
		    },
		    error: function(error) {
		    	console.log(error);
		    }
		});
	}
	
</script>