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
							<h1>${displayName?html}</h1>
							<h2>${userProf.country?html}</h2>
							
							<#if editable>
								<button class="place-right success" onclick="location.href='/user/edit?userid=${userProf.id}'">Edit Profile</button>
							</#if>
							<button class="place-right success" onclick="location.href='/user/scores?userid=${userProf.id}'">View Scores</button>
							
							<div class="home-subsection">
								<h3>${userProf.firstName?html}'s Quizzes</h3>
								<#if userQuizzes??>
									<table class="table hovered">
										<thead>
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
												<#if editable>
													<td>
														<a href="/"><p class="text-success"><button id="editQuizButton">+</button></p></a>
													</td>
												</#if>
											</tr>
										</#list>
										</tbody>
									
										<tfoot></tfoot>
									</table>							
								<#else>
									This user doesn't have any quizzes yet!
								</#if>
							</div>
						
							<div class="home-subsection">
								<h3>${userProf.firstName?html}'s Groups</h3>
								<#if userGroups??>
									<table class="table hovered">
				                        <thead>
				                        <tr>
				                            <th class="text-left">Group</th>
				                            <th class="text-left">Members</th>
				                            <th class="text-left">Quizzes</th>
				                            <th class="text-left">Questions</th>
				                            <th class="text-left">Contribution Score <a href="#" data-hint="Contribution Score|A contribution score is something that we must figure out later. It will be super cool" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></th>
				                            <th class="text-left">Quizzes Participated</th>
				                            <th class="text-left">Date Created</th>
				                        </tr>
				                        </thead>
				
				                        <tbody>
					                        <#list userGroups as group>
												<tr>
													<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
													<td class="right">${group.memberCount}</td><td class="right">${group.quizCount}</td>
													<td class="right">${group.questionCount}</td><td class="right">35</td>
													<td class="right">3</td>
													<td class="right">${group.dateAsString}</td>
												</tr>
											</#list>     
				                        </tbody>
				
				                        <tfoot></tfoot>
				                    </table>							
								<#else>
									This user doesn't have any groups yet!
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