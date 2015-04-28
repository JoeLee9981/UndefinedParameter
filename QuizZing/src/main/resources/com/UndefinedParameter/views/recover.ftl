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
									<p>We have sent a 6-digit recovery to your email. Please check your email and enter the code provided below:</p>
									<p id="recoveryCodeError" class="tertiary-text-secondary errorFormText1 registerError" hidden>Your recovery code does not match our records. Please try again.</p>
									<div class="row">
										<div class="input-control text span6">
										    <input type="text" id="recoveryCode" />
										</div>										
									</div>
									<div class="row">
											<button type="button" class="large primary" id="recoverButton">Recover</button>								
									</div>	
									
									<div id="recoverPassword" hidden>
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
											<button type="button" class="large primary" onclick="saveChanges()">Save New Password</button>								
										</div>	
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

	$("#recoverButton").click(function() {
		var recoveryEmail = "${userRecover.email}";
		var activeCode = "${userRecover.activeCode}";
		var givenCode = $('#recoveryCode').val();
		
		if(activeCode == givenCode)
			$("#recoverPassword").show();
		else
			$("#recoveryCodeError").show();
	});
	
	function saveChanges()
	{
		var passwordChange = false;
		var allFieldsValid = true;
		
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
		
		if (allFieldsValid)
		{
			var userID = ${userRecover.id?html};
			var payload = JSON.stringify({ 
							id: userID,
							password: password,
			});
													
			$.ajax({
				type: 'POST',
				url: "/recover",
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
						window.location="/";
					}
					else
					{
						alert('Some sort of error occurred.');
					}
				},
				error: function(data) {
					alert('An unexpected error occurred: Try again later.  Developers: TODO');
				}
			});	
		}
		else
		{
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
	
</script>