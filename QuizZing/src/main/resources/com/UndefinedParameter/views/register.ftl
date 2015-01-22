<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/home.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/home.css" rel="stylesheet">			
	</head>

	<body>
		
		<#include "../includes/navigation.ftl">
		<div class="divider1">
			<div class="metro" id="home-page-subsection">
				<div class="grid fluid">
					<div class="page-content">
						
						<p>First Name</p>
						<input type="text" id="firstName"/>
						<br/>
						<p>Middle Name</p>
						<input type="text" id="middleName"/>
						<br/>
						<p>Last Name</p>
						<input type="text" id="lastName"/>
						<br/>
						<p>Email</p>
						<input type="text" id="email"/>
						<br/>
						<p>Create Password</p>
						<input type="text" id="password"/>
						<br/>
						<p>Reenter Password</p>
						<input type="text" id="reenterPassword"/>
						<br/>
						<p>Country</p>
						<input type="text" id="country"/>
						<br/>
						<p>State</p>
						<input type="text" id="state"/>
						<br/>
						<p>City</p>
						<input type="text" id="city"/>
						<br/>
						<p>Secret Question</p>
						<input type="text" id="squestion"/>
						<br/>
						<p>Secret Answer</p>
						<input type="text" id="sanswer"/>
						<br/>
						<button type="submit" id="submit" onClick="submit()">Submit</button>
						<p id="error_label" class="text-alert" />
					</div>
				</div>
			<div>			
		<#include "../includes/footer.ftl">
		</div>

	</body>
</html>

<script>
	
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
					alert("registered");
					//TODO: post to login and redirect to home
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
	
</script>