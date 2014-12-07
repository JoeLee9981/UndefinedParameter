<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">		
	</head>
	<body class="metro">
	
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<h3>Create a Question</h3>
			<div class="grid fluid">
				<div class="row">
					<div class="span3">
						<nav class="sidebar light">
							<ul>
						        <li><a href="#">True or False</a></li>
						        <li class="active"><a href="#">Multiple Choice</a></li>
						        <li><a href="#">Fill In The Blank</a></li>
						        <li><a href="#">Essay</a></li>
						        <li><a href="#">Matching</a></li>
						        <li><a href="#">Short Answer</a></li>
							</ul>
						</nav>
					</div>
					<div class="span9">
						<form id="create-question-form">
							<label><h5>Question</h5>
								<div class="input-control textarea">
								    <textarea></textarea>
								</div>
							</label>
							
							<div class="row">	
								<div class="span8">
									<label id="mc-answer-options"><h5>Answer Options</h5>
										<div class="input-control text size5">
										    <input type="text"/>
										</div>
										<div class="input-control checkbox place-right">
										    <label>
										        <input type="checkbox" />
										        <span class="check"></span>
										    </label>
										</div>
										<div class="input-control text size5">
										    <input type="text"/>
										</div>
										<div class="input-control checkbox place-right">
										    <label>
										        <input type="checkbox" />
										        <span class="check"></span>
										    </label>
										</div>								
										<div class="input-control text size5">
										    <input type="text"/>
										</div>
										<div class="input-control checkbox place-right">
										    <label>
										        <input type="checkbox" />
										        <span class="check"></span>
										    </label>
										</div>								
										<div class="input-control text size5">
										    <input type="text"/>
										</div>
										<div class="input-control checkbox place-right">
										    <label>
										        <input type="checkbox" />
										        <span class="check"></span>
										    </label>
										</div>								
									</label>
								</div>
								<div class="span4">
									<div class="panel">
									    <div class="panel-header">
									        Note
									    </div>
									    <div class="panel-content">
									        Use the checkboxes next to your answer to indicate that it is
									        a correct answer.
									    </div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</body>
</html>