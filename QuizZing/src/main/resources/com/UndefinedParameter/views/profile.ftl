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
		<link rel="stylesheet" href="/assets/css/profile.css">
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>
	<body>
		<#include "../includes/navigation.ftl">
		<div class="page-content">
			<div class="divider1">
				<div class="metro" id="home-page-subsection">
					<div class="grid fluid">
						<#if userProf??>
						
							<div class="row" id="groupImageContainer">
								<div class="row noMargin">
								    <div class="indent30 span12">
								    	<div class="row noMargin">
											<h1 style="text-shadow: 0 0 3px #000000, 0 0 5px #000000;" class="fg-white">
												<strong><i class="icon-user on-left fg-white bg-amber" style="padding-left: 15px; padding-top: 13px; border-radius: 50%; height: 80px; width: 80px"></i> ${displayName?html}</strong> 

											</h1>
											<h2 style="margin-left: 100px; margin-top: -20px; text-shadow: 0 0 3px #000000, 0 0 5px #000000;" class="fg-white"><strong>${userProf.country?html}</strong></h2>
										</div>	
									</div>
							    </div>
							</div>
							<div class="row noMargin">
								<nav class="navigation-bar white white-custom">
								    <nav class="navigation-bar-content">
								        <item id="groupsItem" class="element active"><a href="" id="groupsLink"><i class="icon-link on-right"></i> <strong>Groups (${groupCount})</strong></a></item>
								        <item class="element-divider"></item> 		        
								        <item id="quizzesItem" class="element"><a href="" id="quizzesLink"><i class="icon-clipboard-2 on-right"></i> <strong>Quizzes (${quizCount})</strong></a></item>
								        <item class="element-divider"></item>
								        <item id="badgesItem" class="element"><a href="" id="badgesLink"><i class="icon-medal on-right"></i> <strong>Badges (${badgeCount})</strong></a></item>
								    	<item class="element-divider"></item>
								        <#if editable>	
											<item id="editItem" class="element place-right"><a href="/user/edit?userid=${userProf.id}"><i class="icon-pencil on-left"></i> <strong>Edit Profile</strong></a></item>
											<item class="element-divider place-right"></item>
											<item id="scoresItem" class="element place-right"><a href="/user/scores?userid=${userProf.id}"><i class="icon-chart-alt on-left"></i> <strong>View Scores</strong></a></item>
											<item class="element-divider place-right"></item>
										</#if>
								        
								    </nav>
								</nav>					
							</div>

							<div class="row noMargin">
								<div id="quizzesDiv" hidden>
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
							</div>
						
							<div id="groupsDiv" class="row noMargin">								
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
		
							<#else>
								<h1>User profile unavailable.</h1>
							</#if>
						</div>
						
						<div class="row noMargin">
							<div id="badgesDiv" hidden>
								<div class="span6">
									<h3>Group Badges</h3>
									<#if groupBadges??>
										<#list groupBadges as badge>
											<div class="row noMargin">
												<div class="span2">
													<h1 style="font-size: 75px">
														<#if badge.contribution &gt;= 300>
														<i title="Contribution Score" class="icon-medal on-right fg-amber"></i>
														<#elseif badge.contribution &gt;= 200>
														<i title="Contribution Score" class="icon-medal on-right fg-emerald"></i>
														<#elseif badge.contribution &gt;= 100>
														<i title="Contribution Score" class="icon-medal on-right fg-red"></i>
														<#else>
														<i title="Contribution Score" class="icon-medal on-right"></i>
														</#if>
													</h1>
												</div>
												<div class="span10">
													<h4>Group: <a href="/group?groupId=${badge.groupId}">${badge.groupName}</a></h4>
													<h4 class="fg-steel">Contribution: ${badge.contribution}</h4>
													<h4 class="fg-steel">Badge: 
														<#if badge.contribution &gt;= 300>
														Super User
														<#elseif badge.contribution &gt;= 200>
														Contributor
														<#elseif badge.contribution &gt;= 100>
														Member
														<#else>
														User
														</#if>
													</h4>
												</div>
											</div>
										</#list>
									<#else>
										<h3>No Badges Earned Yet!</h3>
									</#if>
								</div>
								<div class="span6" style="margin-left: 20px">
									<h3>Organization Badges</h3>
									<#if orgBadges??>
										<#list orgBadges as badge>
											<div class="row noMargin">
												<div class="span2">
													<h1 style="font-size: 75px">
														<#if badge.contribution &gt;= 1000>
														<i title="Contribution Score" class="icon-medal on-right fg-amber"></i>
														<#elseif badge.contribution &gt;= 800>
														<i title="Contribution Score" class="icon-medal on-right fg-magenta"></i>
														<#elseif badge.contribution &gt;= 600>
														<i title="Contribution Score" class="icon-medal on-right fg-emerald"></i>
														<#elseif badge.contribution &gt;= 400>
														<i title="Contribution Score" class="icon-medal on-right fg-red"></i>
														<#elseif badge.contribution &gt;= 200>
														<i title="Contribution Score" class="icon-medal on-right fg-olive"></i>
														<#else>
														<i title="Contribution Score" class="icon-medal on-right"></i>
														</#if>
													</h1>
												</div>
												<div class="span10">
													<h4>Group: <a href="/orgs/org?id=${badge.organizationId}">${badge.organizationName}</a></h4>
													<h4 class="fg-steel">Contribution: ${badge.contribution}</h4>
													<h4 class="fg-steel">Badge: 
														<#if badge.contribution &gt;= 1000>
														Master User
														<#elseif badge.contribution &gt;= 800>
														Super User
														<#elseif badge.contribution &gt;= 600>
														Main Contributor
														<#elseif badge.contribution &gt;= 400>
														Contributor
														<#elseif badge.contribution &gt;= 200>
														Member
														<#else>
														User
														</#if>
													</h4>
												</div>
											</div>
										</#list>
									<#else>
										<h3>No Badges Earned Yet!</h3>
									</#if>
								</div>
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

<script>
	$('#quizzesLink').click(function(event) {
		event.preventDefault();
		hideDivs();
		$('#quizzesDiv').show();
		$('#quizzesItem').addClass('active');
	});

	$('#groupsLink').click(function (event) {
		event.preventDefault();
		hideDivs();
		$('#groupsDiv').show();
		$('#groupsItem').addClass('active');
	});

	$('#badgesLink').click(function (event) {
		event.preventDefault();
		hideDivs();
		$('#badgesDiv').show();
		$('#badgesItem').addClass('active');
	});

	function hideDivs() {
		$('#quizzesDiv').hide();
		$('#quizzesItem').removeClass('active');
		$('#groupsDiv').hide();
		$('#groupsItem').removeClass('active')
		$('#badgesDiv').hide();
		$('#badgesItem').removeClass('active');
	}

</script>