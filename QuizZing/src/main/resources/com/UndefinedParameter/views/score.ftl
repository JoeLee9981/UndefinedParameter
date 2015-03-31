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
		
		<style>
			path { 
				stroke: steelblue;
				stroke-width: 2;
				fill: none;
			}

			.axis path,
			.axis line {
				fill: none;
				stroke: grey;
				stroke-width: 1;
				shape-rendering: crispEdges;
			}
		</style>
	</head>

	<body>
		<#include "../includes/navigation.ftl">
		
		<div class="page-content">
			<div class="divider1">
				<div class="metro" id="home-page-subsection">
					<div class="grid fluid">
		
						<h1>${user.firstName}'s Statistics</h1>
						
						<div class="home-subsection">
							
							<select id="quizSelector" onchange="getScores(this.value)">
								<#if quizIds??>
									<#list quizIds as quiz>
										<option value=${quiz.quizId}>${quiz.name}</option>	
									</#list>
								</#if>
							</select>
							
							<div  id="graph-container">
							</div>
							<br><br><br><br><br><br>
							

						</div>
					</div>
					
					
				</div>
			</div>
		</div>
		
		<script type="text/javascript">									
		var dataPoints = {
			"bg_dataset" : [
				{"date" : "2-JAN-10", "score" : "30", "id" : "0"},
				{"date" : "3-JAN-10", "score" : "40", "id" : "1"},
				{"date" : "4-JAN-10", "score" : "18", "id" : "2"},
				{"date" : "5-JAN-10", "score" : "58", "id" : "3"},
				{"date" : "6-JAN-10", "score" : "88", "id" : "4"}], 
			"got_dataset" : [
				{"date" : "2-JAN-10", "score" : "20", "id" : "0"},
				{"date" : "12-JAN-10", "score" : "4", "id" : "1"},
				{"date" : "22-JAN-10", "score" : "55", "id" : "2"},
				{"date" : "2-FEB-10", "score" : "58", "id" : "3"},
				{"date" : "2-JAN-10", "score" : "10", "id" : "4"},
				{"date" : "30-JAN-10", "score" : "49", "id" : "5"},
				{"date" : "25-JAN-10", "score" : "21", "id" : "6"}], 
			"csec_dataset" : [
				{"date" : "2-JAN-10", "score" : "70", "id" : "0"},
				{"date" : "10-JAN-10", "score" : "40", "id" : "1"},
				{"date" : "20-JAN-10", "score" : "10", "id" : "2"},
				{"date" : "12-JAN-10", "score" : "22", "id" : "3"},
				{"date" : "29-JAN-10", "score" : "21", "id" : "4"}], 
			"csys_dataset" : [
				{"date" : "2-JAN-10", "score" : "60", "id" : "0"},
				{"date" : "2-FEB-10", "score" : "21", "id" : "1"}]
		};
		
		//drawQuizScorePlot('bg_dataset');
		drawScorePlot('bg_dataset');
		
		function getScores(quizId) {			
			$.ajax({
				type: 'POST',
				url: "/user/scores?quizid=" + quizId,
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) 
				{
					alert(data["scores"]);
					drawScorePlot(data["scores"]);
						
				},
				error: function(data) {
					alert('An unexpected error occured: Try again later.  Developers: TODO');
				}
			});	
		}
		
		function drawScorePlot(dataset) {
			$("#graph-container").empty();
		
			// Set graph canvas
			var margin = {top: 30, right: 20, bottom: 30, left: 50},
				width = 600 - margin.left - margin.right,
				height = 270 - margin.top - margin.bottom;

			// Parse the date / time
			var parseDate = d3.time.format("%d-%b-%y").parse;

			// Set dataset
			// FIX THIS.
			var data = dataset;
			
			for( var i = 0; i < data.length; i++ ) {
				data[i].date = parseDate(data[i].date);
			}
		
			// Set the ranges
			var x = d3.time.scale().range([0, width]);
			var y = d3.scale.linear().range([height, 0]);

			// Define the axes
			var xAxis = d3.svg.axis().scale(x)
				.orient("bottom").ticks(5);

			var yAxis = d3.svg.axis().scale(y)
				.orient("left").ticks(5);

			// Define the line
			var valueline = d3.svg.line()
				.x(function(d) { return x(d.date); })
				.y(function(d) { return y(d.score); });
				
			// Adds the svg canvas
			var svg = d3.select("#graph-container")
				.append("svg")
					.attr("width", width + margin.left + margin.right)
					.attr("height", height + margin.top + margin.bottom)
				.append("g")
					.attr("transform", 
						  "translate(" + margin.left + "," + margin.top + ")");

				// Scale the range of the data
				x.domain(d3.extent(data, function(d) { return d.date; }));
				y.domain([0, d3.max(data, function(d) { return d.score; })]);

				// Add the valueline path.
				svg.append("path")
					.attr("class", "line")
					.attr("d", valueline(data));

				// Add the scatterplot
				svg.selectAll("dot")
					.data(data)
				  .enter().append("circle")
					.attr("r", 3.5)
					.attr("cx", function(d) { return x(d.date); })
					.attr("cy", function(d) { return y(d.score); });

				// Add the X Axis
				svg.append("g")
					.attr("class", "x axis")
					.attr("transform", "translate(0," + height + ")")
					.call(xAxis);

				// Add the Y Axis
				svg.append("g")
					.attr("class", "y axis")
					.call(yAxis);
		}
	</script>
		
	</body>
</html>