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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">	
		<link rel="stylesheet" type="text/css" href="/assets/css/categories.css" />	
		<style>
			ul
			{
				cursor: default;
			}
			
			li
			{
				cursor: default;
			}
		</style>
	</head>
	<body>
		
		<#include "../includes/navigation.ftl">


		<div id="home-content" class="metro">
			<div class="grid fluid">
			
				<#if user??>
				<div id="homeBanner">
					<div class="page-content">
						<div id="bannerContent" class="row">
							<div>
								<div class="row">
									<div class="span3">
										<img src="/assets/images/person_circ.png"></img>
									</div>
									<div id="bannerHeader" class="span5">
										<h2><strong>Studying is hard right?</strong></h2>
										<span class="subheader-secondary">
											<strong>Not anymore!</strong> Whether for school, certification courses, job interviews, or just fun, QuizZing gives you the tools to make studying simple.
										</span>
										<div id="headerButtons">
											<button type="button" class="warning large" onclick="location.href = '/about'">Learn More</button>
											<button type="button" class="primary large" onclick="location.href = '/tutorial'">Take the Tutorial</button>	
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<#else>
				<div id="homeBanner">
					<div class="page-content">
						<div id="bannerContent" class="row">
							<div class="span3">
								<div id="loginFormContainer">
									<form id="loginForm">
										<div class="input-control text">
										 	<input type="text" id="email" value="" placeholder="Email Address"/>
										</div>	
										<div class="input-control password">
										    <input type="password" id="password" value="" placeholder="Password"/>
										</div>
										<p id="loginErrorLabel" class="tertiary-text-secondary errorFormText1 loginError"></p>		
										<div class="row">
											<button type="submit" class="primary">Login</button>
										</div>								
									</form>	
									<div class="row topMarginMedium">
										<p class="tertiary-text-secondary span12">Unable to login?  Use the <a href="forgot">Account Retrieval</a> process to regain access to your account.</p>
									</div>		
								</div>	
							</div>
							<div class="span9">
								<div class="row">
									<div id="bannerHeader" class="span6">
										<h2><strong>Studying is hard right?</strong></h2>
										<span class="subheader-secondary">
											<strong>Not anymore!</strong> Whether for school, certification courses, job interviews or just fun. QuizZing gives you the tools to make studying simple.
										</span>
										
									</div>
									
								</div>
								<div id="headerButtons span9">
									<button type="button" onclick="location.href = '/register'" class="success large" style="margin-right: 10px">Register</button>
									<button type="button" class="warning large" onclick="location.href = '/about'" style="margin-right: 10px">Learn More</button>
									<button type="button" class="primary large" onclick="location.href = '/tutorial'">Take the Tutorial</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				</#if>
				<div class="page-content">
					<div class="row noMargin">
					    <div class="span12">
					    	<div>
								<h4>Find By Category</h4>
							</div>
							<div id="categoryTags">
							</div>
							<div class="row noMargin">
								<div class="input-control text span6">
								    <input id="categories" type="text" value="" placeholder="Comma separated categories"/>
								</div>
								<div class="span6 noMargin">
									<button id="findButton" class="large" style="height: 34px; margin-left: 10px; padding-top: 7px;" disabled>Show Me</button>
								</div>
							</div>
							<div id="filterDiv" class="row noMargin">
							</div>
						</div>
					</div>
				</div>
			</div>								
			<div style="padding-top: 50px" class="row">
				<#include "../includes/footer.ftl">
			</div>
		</div>

	</body>
	<script>
		$('#findButton').click(function() {
			$.ajax({
			    url: '/quiz/categories',
			    type: 'POST',
			    data: JSON.stringify(categories),
				headers: {
					"Content-Type": "application/json"
				},
			    success: function(data) {
			    	console.log(data);
					$('#filterDiv').html(data);
			    },
			    error: function(error) {
			    	$('#filterDiv').html("<h3>No Quizzes Found</h3>");
			    }
			});
		});
	
		//set up auto complete for categories
		$(function() {
		
			var allCategories = [];
			<#if categories??>
			<#list categories as category>
			allCategories.push('${category}');
			</#list>
			</#if>
		
			$('#categories').autocomplete({
				source: allCategories,
				messages: {
			        noResults: '',
			        results: function() {}
			    },
			});
		});
		
		var categories = [];
		
		function setCategoryButtons() {
	
			if(categories.length == 0) {
				$('#findButton').attr('disabled', true);
				$('#findButton').attr('class', 'large')
			}
			else {
				$('#findButton').attr('disabled', false);
				$('#findButton').attr('class', 'success large')
			}
			
			var html = "";
			for(var i = 0; i < categories.length; i++) {
				var cat = categories[i];
				if(cat[0] != '#') {
					cat = '#' + cat;
				}
				html += '<button id="catButton' + i + '" class="default" style="margin: 5px"><i onclick="removeCategory(' + i + ')" class="icon-cancel"></i>  ' + cat + '</button>';
			}
			$('#categoryTags').html(html);
			
			for(var i = 0; i < categories.length; i++) {
				$('#catButton' + i).click(function(event) {
					event.preventDefault();
				});
				
			}
		}
		
		function removeCategory(index) {
			var temp = [];
			for(var j = 0; j < categories.length; j++) {
				if(index != j) {
					temp.push(categories[j]);
				}
			}
			categories = temp;
			setCategoryButtons();
		}
		
		$('#categories').keydown(function(event) {
			if(event.which == 188 || event.which == 13) {
				var cat = $('#categories').val().trim();
				if(cat == ""  || cat == "#") {
					event.preventDefault();
					$('#categories').val("");
					return;
				}
				if($.inArray(cat, categories) == -1)
					categories.push(cat);
				setCategoryButtons();
				$('#categories').val("");
				event.preventDefault();
			}
			
		});
		
		$('#categories').blur(function() {
		
			var cat = $('#categories').val().trim();
			
			if(cat == "" || cat == "#") {
				$('#categories').val("");
				return;
			}
			if($.inArray(cat, categories) == -1)
				categories.push(cat.substring(0));
			setCategoryButtons();
			$('#categories').val("");
			event.preventDefault();
		});

		$('#loginForm').submit(function(event) {
			event.preventDefault();
			
			var username = $('#email').val();
			var password = $('#password').val();
			
			if(!username) {
				doLoginError("Please enter your email");
				return;
			}
			
			if(!password) {
				doLoginError("Please enter your password");
				return;
			}

			$.ajax({
			    url: '/login',
			    username: username,
			    password: password,
			    type: 'POST',
			    success: function(data) {
			    	console.log(data);

			    	window.location='/quiz/categories';
			    },
			    error: function(error) {
			    	doLoginError("Invalid email and/or password");
			    }
			});
		});

		function doLoginError(message) {
			document.getElementById('loginErrorLabel').innerHTML = message;
		}
		
	</script>
</html>