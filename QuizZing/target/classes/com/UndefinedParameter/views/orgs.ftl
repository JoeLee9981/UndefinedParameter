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
                            <th class="text-left">Contribution Score <i class="icon-help fg-blue"></i></th>
                            <th class="text-left">Quizzes Participated</th>
                            <th class="text-left">Date Created</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr><td><a href="#">University of Utah</a></td><td class="right">5421</td><td class="right">800</td><td class="right">5000</td><td class="right">79</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Utah State University</a></td><td class="right">950</td><td class="right">700</td><td class="right">4000</td><td class="right">75</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Weber State University</a></td><td class="right">725</td><td class="right">600</td><td class="right">3500</td><td class="right">46</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Utah Valley University</a></td><td class="right">436</td><td class="right">500</td><td class="right">3000</td><td class="right">65</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Snow College</a></td><td class="right">144</td><td class="right">400</td><td class="right">2000</td><td class="right">32</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Harvard</a></td><td class="right">126</td><td class="right">300</td><td class="right">1000</td><td class="right">11</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Chemistry 1210</a></td><td class="right">126</td><td class="right">300</td><td class="right">1000</td><td class="right">11</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">CS 4400</a></td><td class="right">126</td><td class="right">300</td><td class="right">1000</td><td class="right">11</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Neumant</a></td><td class="right">126</td><td class="right">300</td><td class="right">1000</td><td class="right">11</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        <tr><td><a href="#">Brigham Young University</a></td><td class="right">126</td><td class="right">300</td><td class="right">1000</td><td class="right">11</td><td class="right">79</td><td class="right">11/12/14</td></tr>
                        </tbody>

                        <tfoot></tfoot>
                    </table>    
			    </div>
			</div>
		</div>

		<#include "../includes/footer.ftl">

	</body>
</html>