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

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="page-content">
				<div class="grid fluid">	
					
					<#if userQuizzes??>
						<div class="home-subsection">
							<h3>Your Quizzes</h3>
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
										<td class="text-left left"><a href="user?userid=${quiz.creatorId}">${quiz.creatorName}</a></td>
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
						</div>
					</#if>
					
					<#if topQuizzes??>
						<div class="home-subsection">
							<h3>All Quizzes</h3>
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
								<#list topQuizzes as quiz>
								
									<tr>
										<td><a href="quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
										<td class="text-left left"><a href="group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a></td>
										<td class="text-left left"><a href="user?userid=${quiz.creatorId}">${quiz.creatorName}</a></td>
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
							<br/>

						</div>
					</#if>
				</div>
			</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>

