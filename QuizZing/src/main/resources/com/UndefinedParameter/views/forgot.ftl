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
								<h2>Recover Your QuizZing Account</h2>
							</div>
						</div>
						<div class="row">
							<form id="registerForm">
								<div id="register" class="offset1 span6">
									<p>Please enter your email below to retrieve your QuizZing account recovery code:</p>
									<p id="recoveryEmailError" class="tertiary-text-secondary errorFormText1 registerError" hidden>Not a valid email.</p>
									<div class="row">
										<div class="input-control text span6">
										    <input type="text" id="recoveryEmail" />
										</div>										
									</div>
									<div class="row">
											<button type="button" class="large primary" onclick="recover()">Email me the code</button>								
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

	function recover()
	{
		var email = $('#recoveryEmail').val();
		if (!validateEmail(email))
		{
			$('#recoveryEmailError').show();
			$('#recoveryEmail').removeClass('valid').addClass('invalid');
		}
		else
		{
			window.location="/recover?email="+email;
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
	
</script>