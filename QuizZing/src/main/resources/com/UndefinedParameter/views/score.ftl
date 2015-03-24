<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/home.css" />
		<script src="/assets/scripts/d3.min.js"></script>
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link href="/assets/css/question.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/assets/plugins/unicorn/unicorn_buttons.css" />
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<div class="divider1">
				<div class="metro" id="home-page-subsection">
					<div class="grid fluid">
		
						<h1>${user.firstName?html}'s Quizzes</h1>
						
						<div class="home-subsection" id="graph-container">
							<select id="quizSelector" onchange="updateGraphs(this.value)">
								<option value="bg_dataset">Board Games</option>
								<option value="got_dataset">Game of Thrones</option>
								<option value="csec_dataset">Computer Security</option>
								<option value="csys_dataset">Computer Systems</option>
							</select>
							<br><br><br><br><br><br>
							
							<script type="text/javascript">
					
								//Width and height
								var w = 500;
								var h = 200;
								var xScale = 1;
								var barPadding = 1;
								var	margin = {top: 30, right: 50, bottom: 30, left: 30};
								
								var dataPoints = {
								    "bg_dataset" : [ 1, 2, 3, 4, 5, 6, 12, 24, 48 ],
									"got_dataset" : [ 5, 10, 13, 19, 21, 25, 22, 18, 15, 13 ],
									"csec_dataset" : [ 33, 21, 9, 1, 58, 20, 12, 55 ],
									"csys_dataset" : [ 5, 10, 15, 20, 25, 30, 35, 40, 45, 50 ]
								};
								
								/*var xAxis = d3.svg.axis()
									.scale(xScale)
									.tickFormat(function(d) { return dataPoints['got_dataset'][d]; })
									.orient("bottom");*/
									
								var xAxis = d3.svg.axis().scale(xScale).tickSize(-h).tickSubdivide(true);
																
								//Create SVG element
								var svg = d3.select("#graph-container")
											.append("svg")
												.attr("width", w + margin.left + margin.right)
        										.attr("height", h + margin.top + margin.bottom)
											.append("g")
        										.attr("transform", "translate(" + margin.left + "," + margin.top + ")");    
        										    										
								/*svg.append("g")
								    .attr("class", "x axis")
								    .attr("transform", "translate(0," + h + ")")
								    .call(xAxis);*/
        										
        						updateGraphs('bg_dataset');								  
								
								function updateGraphs(dataset)
								{  					
							        svg.selectAll("rect").remove();
									svg.selectAll("text").remove();
								
									svg.selectAll("rect")
									   .data(dataPoints[dataset])
									   .enter()
									   .append("rect")
									   .attr("x", function(d, i) {
									   		return i * (w / dataPoints[dataset].length);
									   })
									   .attr("y", function(d) {
									   		return h - (d * 4);
									   })
									   .attr("width", w / dataPoints[dataset].length - barPadding)
									   .attr("height", function(d) {
									   		return d * 4;
									   })
									   .attr("fill", function(d) {
											return "rgb(0, 0, " + (d * 10) + ")";
									   });
						
									svg.selectAll("text")
									   .data(dataPoints[dataset])
									   .enter()
									   .append("text")
									   .text(function(d) {
									   		return d;
									   })
									   .attr("text-anchor", "middle")
									   .attr("x", function(d, i) {
									   		return i * (w / dataPoints[dataset].length) + (w / dataPoints[dataset].length - barPadding) / 2;
									   })
									   .attr("y", function(d) {
									   		return h - (d * 4) + 14;
									   })
									   .attr("font-family", "sans-serif")
									   .attr("font-size", "11px")
									   .attr("fill", "white");
								}
							</script>
						</div>
					</div>
					
					
				</div>
			</div>
		</div>
		
	</body>
</html>