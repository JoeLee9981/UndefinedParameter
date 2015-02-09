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
		<div class="page-content">
			<div class="divider1">
				<div class="metro" id="home-page-subsection">
					<div class="grid fluid">
						<#if userProf??>
							<h1>${userProf.firstName?html} ${userProf.lastName?html}</h1>
							<h2>${userProf.country?html}</h2>
							
							<#if editable>
								<button class="place-right success" onclick="location.href='/user/edit?userid=${userProf.id}'">Edit Profile</button>
							</#if>
							
							<div class="home-subsection">
							<h3>${userProf.firstName?html}'s Quizzes</h3>
							<#if userQuizzes??>
								<table>
									<#list userQuizzes as quiz>	
										<tr>
											<th>Quiz Name</th>
											<th>Description</th>
											<th># of Questions</th>
											<th>Difficulty</th>
											<th>Rating</th>
											<th>Time</th>
											<#if editable>
												<th>Edit</th>
											</#if>
										</tr>
										<tr>
											<td>
												<a href="/quiz?quizId=${quiz.quizId}"><h3 class="text-info">${quiz.name} </h3></a>
											</td>
											<td>
												<h3>${quiz.description}</h3>
											</td>
											<td>
												<h3>${quiz.questionCount}</h3>
											</td>
											<td>
												<h3>${quiz.difficulty}</h3>
											</td>
											<td>
												<h3>${quiz.rating}</h3>
											</td>
											<td>
												<h3>${quiz.time}</h3>
											</td>
											<#if editable>
												<td>
													<a href="/"><h3 class="text-success"><button id="editQuizButton">+</button></h3></a>
												</td>
											</#if>
										</tr>
									</#list>
								</table>							
							<#else>
								This user doesn't have any quizzes yet!
							</#if>
						</div>	
						<#else>
							<h1>User profile unavailable.</h1>
						</#if>
					</div>
				<div>			
			<#include "../includes/footer.ftl">
			</div>
		</div>
	</body>
</html>