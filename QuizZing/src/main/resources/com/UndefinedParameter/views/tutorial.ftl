<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/home.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/quiz.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/home.css" rel="stylesheet">
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>
	<body>
		
		<#include "../includes/navigation.ftl">


		<div id="home-content" class=" metro">
			<div class="grid fluid">	
				<div class="page-content">
					<div class="row">
						<h3>Welcome to QuizZing - Let's get started.</h3><br/>
					</div>
					<!-- Register Accordion -->
					<div class="accordion with-marker" data-role="accordion" data-closeany="true">
						<div class="accordion-frame <#if !user??>active</#if>">
							<a href="#" class="heading bg-lightBlue fg-white">Step 1: Register an Account</a>
							<div class="content">
								<h2>Step 1: Register</h2>
								<p>In order to take full advantage of the features of QuizZing, you need to register your account. Simply fill out the fields marked in red, accept the terms and click 'Create Account'</p>
								<p>Click to <button class="link" onclick="window.open('/register', '_blank')">Register a new account</button> or if you Already have an account click to <button class="link" onclick="window.open('/login', '_blank')">login.</button></p>
								<div class="row">
									<img src="/assets/images/register.png" title="register image">
								</div>
							</div>
						</div>

					
						<!-- Organization Accordion -->
						<div id="organizationAccordion" class="accordion-frame <#if user??>active</#if>">
							<a href="#" class="heading bg-lightBlue fg-white">Step 2: Find Your Organization</a>
							<div class="content">
								<h2>Step 2: Find an Organization</h2>
								<p class="text-alert"><strong>Note:</strong> If you wish to create your own organization, please proceed to step 3, or else continue below</p>
								<p>From the menu bar on the<button class="link" onclick="window.open('/', '_blank')">Home Page</button> select 'Find an Organization,' as shown in red below</p>
								<div class="row">
									<img src="/assets/images/menu.png" title="menu bar image">
								</div><br/>
								<p>Then from the<button class="link" onclick="window.open('/orgs', '_blank')">Organizations Page</button> search for an organization using the 'Find an Organization' menu shown in green, or select one from the 'Recommended Organizations' list shown in red.
								<div class="row">
									<img src="/assets/images/orgs.png" title="organizations image">
								</div>
							</div>
						</div>
						
						<!-- Organization Accordion -->
						<div id="organizationCreateAccordion" class="accordion-frame">
							<a href="#" class="heading bg-lightBlue fg-white">Step 3: Create Your Organization</a>
							<div class="content">
								<h2>Step 3: Create an Organization</h2>
								<p class="text-alert"><strong>Note:</strong> If you have already joined an existing organization, proceed to step 4, or else continue below</p>
								<p>From the menu bar on the<button class="link" onclick="window.open('/', '_blank')">Home Page</button> select 'Find an Organization,' as shown in red below</p>
								<div class="row">
									<img src="/assets/images/menu.png" title="menu bar image">
								</div><br/>
								<p>Then from the<button class="link" onclick="window.open('/orgs', '_blank')">Organizations Page</button>click the green 'Create Organization' button highlighted below in red.</p>
								<div class="row">
									<img src="/assets/images/create_org.png" title="create button image">
								</div><br/>
								<p>Then from the<button class="link" onclick="window.open('/orgs/create', '_blank')">Create Organization Page</button> fill out the required fields highlighted in red, accept the terms and click the 'Create Organization' button highlighted in green.</p>
								<div class="row">
									<img src="/assets/images/org_create.png" title="create organization image">
								</div><br/>
							</div>
						</div>
					
						<!-- Group Accordion -->
						<div id="groupAccordion" class="accordion-frame">
							<a href="#" class="heading bg-lightBlue fg-white">Step 4: Find or Create Your Group</a>
							<div class="content">
								<h2>Step 4: Find or Create Your Group</h2>
								<p>After completion of step 2 or step 3, you should now be at your organizations page. If this is your first visit to the organization, you may wish to choose to join the organization by clicking the green 'Join Organization' button highlighted in red below.</p>
								<p>Once you have joined your organization, you can join a group by selecting it from the 'Recommended Groups' table, or you can create a group by selecting 'Create Group'. Both are highlighted in green below.
								<div class="row">
									<img src="/assets/images/organization.png" title="group image">
								</div><br/>
								<p>If you have chosen to create a group, fill out the required fields ,shown below highlighted in red, and then accept the terms and click the 'Create Group' button highlighted below in green.</p>
								<div class="row">
									<img src="/assets/images/group_create.png" title="group create image">
								</div>
							</div>
						</div>

					
						<!-- Quiz Accordion -->
						<div id="quizAccordion" class="accordion-frame">
							<a href="#" class="heading bg-lightBlue fg-white">Step 5: Create Your Quiz</a>
							<div class="content">
								<h2>Step 5: Create Your Quiz</h2>
								<p>After step 4, if you have not yet joined your group, you must click 'Join Group,' shown in red below, before you may create a quiz. If you created the group, you should already be a member.</p>
								<div class="row">
									<img src="/assets/images/join_group.png" title="join group">
								</div><br/>
								<p>Once you have joined the group, you may now click the 'Create Quiz' button shown below in red.</p>
								<div class="row">
									<img src="/assets/images/create_quiz.png" title="create quiz button">
								</div><br/>
								<p>On the Quiz Creator Page, fill out the required fields shown below in red.</p>
								<p>You can use the Organization and Group fields, shown below in blue, to create a quiz for a different group or organization you belong to.</p>
								<p>If the Auto-generate quiz, shown in green, is selected, the system will auto select questions from the existing question pool. Un-check this box if you wish to pick all the questions yourself.</p>
								<p>The optional fields on the right, shown in orange, are used if you select to Auto-generate quiz. These include the number of questions on the quiz, the difficulty and rating you prefer, and the tags for the quiz</p>
								<p>Once you have selected your options, click the 'Continue To Editor' button highlighted in purple.</p>
								<div class="row">
									<img src="/assets/images/quiz_create.png" title="quiz creator">
								</div>
							</div>
						</div>
						
						<!-- Quiz Edit Accordion -->
						<div id="quizEditAccordion" class="accordion-frame">
							<a href="#" class="heading bg-lightBlue fg-white">Step 6: Edit Your Quiz</a>
							<div class="content">
								<h2>Step 6: Edit Your Quiz</h2>
								<p>On the Quiz Edit Page, you can easily add and remove questions from your quiz.</p>
								<p>You are also able to create new questions as well as edit your quiz settings.</p>
								<div class="row">
									<img src="/assets/images/quiz_edit.png" title="quiz editor">
								</div>
							</div>
						</div>
						
						<!-- Quiz Taking Accordion -->
						<div id="quizTakingAccordion" class="accordion-frame">
							<a href="#" class="heading bg-lightBlue fg-white">Step 7: Take Your Quiz</a>
							<div class="content">
								<h2>Step 7: Take Your Quiz</h2>
								<p>Now that you have created your quiz, you can take it and test your knowledge.</p>
								<div class="row">
									<img src="/assets/images/take_quiz.png" title="take quiz">
								</div>
							</div>
						</div>
				</div>
			</div>	
		</div>

					
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
	
</html>
