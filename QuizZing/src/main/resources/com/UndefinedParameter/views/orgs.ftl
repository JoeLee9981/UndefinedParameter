<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		<script src="/scripts/jquery-2.1.1.min.js"></script>
		<script src="/scripts/jquery-ui.min.js"></script>
		<script src="/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/css/organizations.css" rel="stylesheet">	
		<link href="/css/overrides.css" rel="stylesheet">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">		
		
		<div class="page-content">
			<div class="grid fluid">
				<div class="row">
					<div>
						<h2>Organizations and Groups</h2>
					</div>
				</div>
			    <div class="row">
					<button class="shortcut primary span3">
					    <i class="icon-bookmark-4"></i>
					    University
					</button>
					<button class="shortcut info span3">
					    <i class="icon-book"></i>
					    Class
					</button>
					<button class="shortcut success span3">
					    <i class="icon-lab"></i>
					    Subject
					</button>
					<button class="shortcut warning span3">
					    <i class="icon-comments-2"></i>
					    Group
					</button>															
			    </div>
			    
			    <div class="row">
			    	<div>
						<h4>Top Organizations and Groups</h4>
					</div>
				</div>
			    <div class="row">
					<table class="table hovered">
                        <thead>
                        <tr>
                            <th class="text-left">Organization/Group</th>
                            <th class="text-left">Members</th>
                            <th class="text-left">Quizes</th>
                            <th class="text-left">Questions</th>
                            <th class="text-left">Contribution Score <a href="#" data-hint="Contribution Score|A contribution score is something that we must figure out later. It will be super cool" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></th>
                            <th class="text-left">Quizzes Participated</th>
                            <th class="text-left">Date Created</th>
                        </tr>
                        </thead>

                        <tbody>
                        <#list getOrganizations("Salt Lake") as org>
                        	<tr><td><a href="/service/orgs/${org.id}">${org.name}</a></td><td class="right">${org.memberCount}</td><td class="right">${org.quizCount}</td><td class="right">${org.questionCount}</td><td class="right">${getContributionScore(org.id)}</td><td class="right">${getQuizzesParticipated(org.id)}</td><td class="right">11/12/14</td></tr>
                        </#list>            
                        </tbody>

                        <tfoot></tfoot>
                    </table>    
			    </div>
			</div>
		</div>

		<#include "../includes/footer.ftl">

	</body>
</html>