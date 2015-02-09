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
						<div class="row">
							<nav class="breadcrumbs">
		                        <ul>
		                            <li><a href="/"><i class="icon-home"></i></a></li>
		                            <li><a href="/orgs">Organizations</a></li>
		                            <li><a href="/orgs/org?id=${organization.id}">${organization.name}</a></li>
		                            <li class="active"><a>${group.name}</a></li>
		                        </ul>
		                    </nav>
						</div>		
						
							<h1>${group.name?html}! <button class="place-right success" onclick="location.href='/quiz/create?groupId=${group.id}'">Create A New Quiz</button></h1>
							<h3>Study for your final here</h3>
							
							<#if userQuizzes??>
								<div class="home-subsection">
									<h3>Your Group Quizzes</h3>
									<table>
										<#list userQuizzes as quiz>	
											<tr>
												<th>Quiz Name</th>
												<th>Description</th>
												<th># of Questions</th>
												<th>Difficulty</th>
												<th>Rating</th>
												<th>Time</th>
												<th>Edit</th>
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
												<td>
													<a href="/quiz/edit?groupId=${group.id}&quizId=${quiz.quizId}"><h3 class="text-success"><button id="editQuizButton">+</button></h3></a>
												</td>
											</tr>
										</#list>
									</table>
								</div>
							</#if>
							<div class="home-subsection">
								<h3>All Group Quizzes</h3>
								<table>
									<#list quizzes as quiz>	
										<tr>
											<th>Quiz Name</th>
											<th>Description</th>
											<th># of Questions</th>
											<th>Difficulty</th>
											<th>Rating</th>
											<th>Time</th>
										</tr>
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
								<h3 class="text-success">Feel free to add questions - But please be courteous</h3>
								<p class="text-alert">Since this site is still under construction, be courteous to those using these quizzes to study.
										Be sure of the validity of the question before you add it to a quiz - Thank You!</p>
								<p>In the future there will be user controls to manage the content yourself, preventing this problem, but they are not yet implemented</p>
								<br/>

							</div>
	
					</div>
				<div>			
			<#include "../includes/footer.ftl">
			</div>
		</div>
	</body>
</html>

<script>
	
	function createQuiz() {
		
		//TODO Prevalidate these fields
		var desc = document.getElementById('descriptionText').value;
		//TODO Add more fields to the entry - see Group.java file for more info
		if(desc == null || desc == "") {
			document.getElementById('quizCreateResponse').innerHTML = "You must enter a quiz description";
			document.getElementById('quizCreateResponse').className = "text-alert";
			return;
		}

		 $.ajax({
			type: 'POST',
			url: "/quiz/${group.id}/create",
			data: JSON.stringify({ description: desc }),
			dataType: "json",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function(data) {
				if("success" == data["response"]) {

					location.reload();
				}
				else {
					document.getElementById('quizCreateResponse').innerHTML = data["message"];
					document.getElementById('quizCreateResponse').className = "text-alert";
				}
			}
		});
	}
	

	
</script>