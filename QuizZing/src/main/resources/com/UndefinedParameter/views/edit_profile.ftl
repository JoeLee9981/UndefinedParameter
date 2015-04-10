<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" href="/assets/plugins/metro_ui/min/iconFont.min.css" >
		<link rel="stylesheet" href="/assets/css/overrides.css">
		<link rel="stylesheet" href="/assets/css/register.css">
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>

	<body>
		
		<#include "../includes/navigation.ftl">
		
			<div class="metro">
				<div class="grid fluid">		
					<div class="page-content">						
					    <div class="row">
					    	<div class="offset1 span6">
								<h2>Change User Information</h2>
								<p id="mainRegisterError" class="tertiary-text-secondary errorFormText1 registerError" hidden>* There were errors with the information you entered.  Fix the errors in red and then click 'Save Changes'.</p>										
							</div>
						</div>
						<div class="row">
							<form id="registerForm">
								<div id="register" class="offset1 span6">
									<div>
										<h4>Profile Information</h4>
									</div>
									<p id="firstNameLastNameError" class="tertiary-text-secondary errorFormText1 registerError" hidden>Enter a first and last name</p>
									<div class="row">
										<div class="input-control text span6">
										    <input type="text" id="firstname" value="${userProf.firstName?html}" placeholder="${userProf.firstName?html}"/>
										</div>
										<div class="input-control text span6">
										    <input type="text" id="lastname" value="${userProf.lastName?html}" placeholder="${userProf.lastName?html}"/>
										</div>		
									</div>
									<p id="emailError" class="tertiary-text-secondary errorFormText1 registerError" hidden>Enter a valid email address <i>(ex. learning@myquizzing.com)</i></p>
									<p id="alreadyExistsError" class="tertiary-text-secondary errorFormText1 registerError" hidden></p>		
									<div class="row">
										<div class="input-control text span12">
										    <input type="text" id="email" value="${userProf.email?html}" placeholder="Email Address"/>
										</div>										
									</div>		
									<p class="tertiary-text">Though not required, adding a location to your profile will help us to connect you to people near you who are interested in the same subjects or are in the same class.</p>
									<div class="row">
										<div class="input-control text span4">
										    <input type="text" id="city" value="${userProf.city?html}" placeholder="City"/>
										</div>		
										<div class="input-control text span4">
										    <input type="text" id="state" value="${userProf.state?html}" placeholder="State/Province"/>
										</div>	
										<div class="input-control text span4">
										    <input type="text" id="country" value="${userProf.country?html}" placeholder="Country"/>
										</div>											
									</div>
									<div>
										<h4>Change Password</h4>
									</div>
									<p id="passwordError" class="tertiary-text-secondary errorFormText1 registerError" hidden>Your password must be at least 8 characters long</p>
									<div class="row">
										<div class="input-control password span12">
										    <input type="password" id="password" value="" placeholder="Password (at least 8 characters)"/>
										</div>											
									</div>
									<p id="confirmPasswordError" class="tertiary-text-secondary errorFormText1 registerError" hidden>The two passwords you entered do not match</p>
									<div class="row">
										<div class="input-control password span12">
										    <input type="password" id="confirmPassword" value="" placeholder="Confirm Password"/>
										</div>		
									</div>
									<div class="row">
									</div>						
									<div class="row">
										<button type="button" class="large primary" onclick="saveChanges()">Save Changes</button>								
									</div>						
								</div>
							</form>
						</div>
						
					</div>
				</div>
			<div>	
								
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>

	</body>
</html>

<script>
	
	function saveChanges()
	{
		var passwordChange = false;
		var allFieldsValid = true;
		$(".registerError").hide();
	
		var email = $('#email').val();
		if (!validateEmail(email))
		{
			allFieldsValid = false;
			$('#emailError').show();
			$('#email').removeClass('valid').addClass('invalid');
		}
		
		var first = $('#firstname').val();
		if (!validateNotEmptyField($('#firstname')))
		{
			allFieldsValid = false;
			$("#firstNameLastNameError").show();
		}
		
		// Currently there is no middlefield input box
		var mid = "";//document.getElementById('#middleName').val();
		
		var last = $('#lastname').val();
		if (!validateNotEmptyField($('#lastname')))
		{
			allFieldsValid = false;
			$("#firstNameLastNameError").show();
		}
		
		var password = $('#password').val();
		var reenterPassword = $('#confirmPassword').val();
		if(validateNotEmptyString(password) || validateNotEmptyString(reenterPassword))
		{
			passwordChange = true;
			if (!validatePassword(password))
			{
				allFieldsValid = false;
				$("#passwordError").show();
				$('#password').removeClass('valid').addClass('invalid');
				passwordChange = false;
			}
			
			
			if (reenterPassword !== password)
			{
				allFieldsValid = false;
				$("#confirmPasswordError").show();
				$('#confirmPassword').removeClass('valid').addClass('invalid');
				passwordChange = false;
			}
		}
		
		var country = $('#country').val();
		var state = $('#state').val();
		var city = $('#city').val();
		
		if (allFieldsValid)
		{
			var payload;
			var userID = ${userProf.id?html};
			if(passwordChange)
			{
				payload = JSON.stringify({ 
								id: userID,
								userName: email, 
								firstName: first, 
								middleName: mid, 
								lastName: last,
								country: country, 
								city: city, 
								state: state, 
								email: email, 
								password: password,
				});
			}
			else
			{
				payload = JSON.stringify({ 
								id: userID,
								userName: email, 
								firstName: first, 
								middleName: mid, 
								lastName: last,
								country: country, 
								city: city, 
								state: state, 
								email: email, 
				});
			}
													
			$.ajax({
				type: 'POST',
				url: "/user/edit",
				data: payload,
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) 
				{
					if (data["response"] == "success")
					{
						window.location="/user?userid=${userProf.id?html}";
					}
					else
					{
						$("#mainRegisterError").show();
						$("#alreadyExistsError").html("The email " + email + " already exists in our system.  If this is your email you can <a href='/login'>Login</a>, otherwise register using a different email.");
						$("#alreadyExistsError").show();
						$('#email').removeClass('valid').addClass('invalid');
						$("html, body").animate({ scrollTop: 0 }, 300);
					}
				},
				error: function(data) {
					alert('An unexpected error occured: Try again later.  Developers: TODO');
				}
			});	
		}
		else
		{
			$("#mainRegisterError").show();
			$("html, body").animate({ scrollTop: 0 }, 300);
		}		
	}
	
	function validateNotEmptyField(field)
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
	
	function validateNotEmptyString(value)
	{
		if (value.length > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	$('#firstname').focusout(function(){
		var field = $('#firstname');
		validateNotEmptyField(field);
	});
	
	$('#lastname').focusout(function(){
		var field = $('#lastname');
		validateNotEmptyField(field);
	});
	
	$('#email').focusout(function(){
		var field = $('#email');
		field.val($.trim(field.val()));
		if (validateEmail(field.val()))
		{
			field.removeClass('invalid').addClass('valid');
		}
		else
		{
			field.removeClass('valid').addClass('invalid');
		}
	});
	
	$('#secretQuestion').focusout(function(){
		var field = $('#secretQuestion');
		validateNotEmptyField(field);
	});
	
	$('#secretAnswer').focusout(function(){
		var field = $('#secretAnswer');
		validateNotEmptyField(field);
	});
	
	function validateEmail(email)
	{
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (re.test(email))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	function validatePassword(password)
	{
		if (password.length >= 8)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
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
		if (validatePassword(field.val()))
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