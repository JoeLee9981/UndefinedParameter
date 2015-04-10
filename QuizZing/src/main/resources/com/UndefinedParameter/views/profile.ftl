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
								<button class="place-right primary" onclick="location.href='/user/scores?userid=${userProf.id}'">View Scores</button>
							</#if>
							
							<div class="home-subsection">
								<h3>${userProf.firstName?html}'s Quizzes</h3>
								<#if userQuizzes??>
								<table class="table hovered striped">
							        <thead>
							            <tr>
							                <th class="text-left">Quiz</th>
							                <th class="text-left">Group</th>
							                <th class="text-left">Creator</th>
							                <th class="text-left">Description</th>
							                <th title="Questions"><i class="icon-help-2 on-right"></i></th>
							                <th style="width: 120px">Difficulty</th>
							                <th style="width: 120px">Rating</th>
							            </tr>
							        </thead>
							        <tbody>                         
						        		<#list userQuizzes as quiz>
										<tr>
											<td><a href="quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
											<td class="text-left left"><a href="group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a></td>
											<td class="text-left left"><a href="user?userId=${quiz.creatorId}">${quiz.creatorName}</a></td>
											<td class="text-left left">
												<#if quiz.description??>
													<#if quiz.description?length &gt; 20>
														${quiz.description?substring(0, 20)}...
													<#else>
														${quiz.description}
													</#if>
												<#else>
													No Description
												</#if>
											</td>
											<td class="text-center center">${quiz.questionCount}</td>
											<td class="text-right right">
												<div id="diff${quiz.quizId}" class="rating small fg-red"></div>
											</td>
											<td class="text-right right">
												<div id="rating${quiz.quizId}" class="rating small"></div>
											</td>
											
											<script>
												//Star rating for quiz quality (entry page)
												$(function() {
													$("#rating${quiz.quizId}").rating({
														static: true,
														score: ${quiz.rating},
														stars: 5,
														showHint: true,
														hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
													});
												});
												
												//Star rating for quiz quality (entry page)
												$(function() {
													$("#diff${quiz.quizId}").rating({
														static: true,
														score: ${quiz.difficulty},
														stars: 5,
														showHint: true,
														hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
													});
												});
											</script>
										</tr>
										</#list>
									</tbody>
							        <tfoot></tfoot>
							    </table>  
							    <#else>
									<tr>
										<td colspan="3">
											<h3>No Quizzes Found</h3>
										</td>
									</tr>
								</#if>
							</div>
						
							<div class="home-subsection">
								<h3>${userProf.firstName?html}'s Groups</h3>								
								<#if userGroups??>
									<table class="table hovered striped">
				                        <thead>
				                        <tr>
				                            <th class="text-left">Group</th>
				                            <th title="Members" class="text-center"><i class="icon-user-3 on-right"></i></th>
				                            <th title="Quizzes" class="text-center"><i class="icon-clipboard-2 on-right"></i></th>
				                            <th title="Questions" class="text-center"><i class="icon-help-2 on-right"></i></th>
				                            <th title="Contribution is how much you've contributed towards the group" class="text-center">Contribution</th>
				                            <th class="text-center">Quizzes Created</th>
				                            <th class="text-center">Date Created</th>
				                        </tr>
				                        </thead>
				
				                        <tbody>
					                        <#list userGroups as group>
												<tr>
													<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
													<td class="text-center">${group.memberCount}</td>
													<td class="text-center">${group.quizCount}</td>
													<td class="text-center">${group.questionCount}</td>
													<td class="text-center">35</td>
													<td class="text-center">3</td>
													<td class="text-center">${group.dateAsString}</td>
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
				
				</div>
			</div>
		</div>
		</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>