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
			<h5><a href="/">home</a> >> <a href="/orgs">organizations</a> >> <a href="/orgs/org?id=${organization.id}">${organization.name}</a> >> ${group.name}</h5>
			<div class="divider1">
				<div class="metro" id="home-page-subsection">
					<div class="grid fluid">
						
							<h1>${group.name?html}! <button class="place-right success" onclick="location.href='/quiz/create?groupId=${group.id}'">Create A New Quiz</button></h1>
							<h3>Study for your final here</h3>
							
							<div class="home-subsection">
								<table>
									<#list quizList as quiz>	
										<tr>
											<td>
												<a href="/quiz?quizId=${quiz.quizId}"><h3 class="text-info">${quiz.description}</h3></a>
											</td>
											<td>
												<a href="/quiz/create/question/${quiz.quizId}"><h3 class="text-success">Add a question</h3></a>
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