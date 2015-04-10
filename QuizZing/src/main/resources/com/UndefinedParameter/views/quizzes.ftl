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

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="page-content">
				<div class="grid fluid">	
					
					<#if userQuizzes??>
						<div class="home-subsection">
							<h3>Your Quizzes</h3>
							<table class="table hovered">
								<thead>
								<tr>
									<th>Quiz Name</th>
									<th>Description</th>
									<th># of Questions</th>
									<th>Difficulty</th>
									<th>Rating</th>
									<th>Time</th>
									<!-- <th>Edit</th>  -->
								</tr>
								</thead>
								<tbody>
								<#list userQuizzes as quiz>	
									<tr>
										<td>
											<a href="/quiz?quizId=${quiz.quizId}"><p class="text-info">${quiz.name} </p></a>
										</td>
										<td>
											<p>${quiz.description}</p>
										</td>
										<td>
											<p>${quiz.questionCount}</p>
										</td>
										<td>
											<p>${quiz.difficulty}</p>
										</td>
										<td>
											<p>${quiz.rating}</p>
										</td>
										<td>
											<p>${quiz.time}</p>
										</td>
									</tr>
								</#list>
								</tbody>
								<tfoot></tfoot>
							</table>
						</div>
					</#if>
					
					<#if topQuizzes??>
						<div class="home-subsection">
							<h3>All Quizzes</h3>
							<table class="table hovered">
								<thead>
									<tr>
										<th>Quiz Name</th>
										<th>Description</th>
										<th># of Questions</th>
										<th>Difficulty</th>
										<th>Rating</th>
										<th>Time</th>
									</tr>
								</thead>
								<tbody>
								<#list topQuizzes as quiz>
								
									<tr>
										<td>
											<a href="/quiz?quizId=${quiz.quizId}"><p class="text-info">${quiz.name} </p></a>
										</td>
										<td>
											<p>${quiz.description}</p>
										</td>
										<td>
											<p>${quiz.questionCount}</p>
										</td>
										<td>
											<p>${quiz.difficulty}</p>
										</td>
										<td>
											<p>${quiz.rating}</p>
										</td>
										<td>
											<p>${quiz.time}</p>
										</td>
									</tr>
								</#list>
								</tbody>
								<tfoot></tfoot>
							</table>
							<br/>

						</div>
					</#if>
				</div>
			</div>
		<#include "../includes/footer.ftl">
	</body>
</html>

