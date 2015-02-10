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
				<div class="metro">
					<div class="grid fluid">	
						
						<#if userQuizzes??>
							<div class="home-subsection">
								<h3>Your Quizzes</h3>
								<table>
									
									<tr>
										<th>Quiz Name</th>
										<th>Description</th>
										<th># of Questions</th>
										<th>Difficulty</th>
										<th>Rating</th>
										<th>Time</th>
										<th>Edit</th>
									</tr>
									<#list userQuizzes as quiz>	
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
										</tr>
									</#list>
								</table>
							</div>
						</#if>
						
						<#if topQuizzes??>
							<div class="home-subsection">
								<h3>All Quizzes</h3>
								<table>
										
										<tr>
											<th>Quiz Name</th>
											<th>Description</th>
											<th># of Questions</th>
											<th>Difficulty</th>
											<th>Rating</th>
											<th>Time</th>
										</tr>
										
									<#list topQuizzes as quiz>
									
										<tr>
											<td>
												<h3><a href="/quiz?quizId=${quiz.quizId}"><h3 class="text-info">${quiz.name} </h3></a>
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
										</tr>
									</#list>
								</table>
								<br/>

							</div>
						</#if>
					</div>
				<div>			
			<#include "../includes/footer.ftl">
			</div>
		</div>
	</body>
</html>

