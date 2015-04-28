<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing - Edit Quiz</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/quiz.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/assets/plugins/unicorn/unicorn_buttons.css" />	
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
		<link rel="stylesheet" type="text/css" href="/assets/css/categories.css" />	
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">
		<div class="grid fluid">
			<div class="page-content">
				<h3>Edit Your Question<span class="place-right" title="Cancel"></span></h3>
				<#if question.type == "MULTIPLE_CHOICE">
					<#include "../includes/multiple_choice_edit.ftl">
				<#elseif question.type == "FILL_IN_THE_BLANK">
					<#include "../includes/fill_blank_edit.ftl">
				<#elseif question.type == "MATCHING">
					<#include "../includes/matching_edit.ftl">
				<#elseif question.type == "SHORT_ANSWER">
					<#include "../includes/short_answer_edit.ftl">
				<#elseif question.type == "TRUE_FALSE">
					<#include "../includes/true_false_edit.ftl">
				</#if>
			</div>
		</div>
		
				
		<script>

		</script>
							
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
	</body>
</html>