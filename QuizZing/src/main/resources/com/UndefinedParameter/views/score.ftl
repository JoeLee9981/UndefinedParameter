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
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
		<style>
			path { 
				stroke: #60a917;
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
							
							<div id="graph-container">
							</div>
							
							<br><br><br><br><br><br>
						</div>
					</div>					
				</div>
			</div>
		</div>
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>
		
		<script type="text/javascript">
		
		function loadStartingGraph() {
			
		}	
												
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
					drawScorePlot(data["scores"]);						
				},
				error: function(data) {
					alert('An unexpected error occurred: Try again later.  Developers: TODO');
				}
			});	
		}
		
		function drawScorePlot(dataset) {
			$("#graph-container").empty();
		
			// Set graph canvas
			var margin = {top: 30, right: 20, bottom: 30, left: 50},
				width = 600 - margin.left - margin.right,
				height = 300 - margin.top - margin.bottom;

			// Parse the date / time
			var parseDate = d3.time.format("%d-%b-%y").parse;

			// Set dataset
			// FIX THIS.
			var data = dataset;
			var i = 0;
			
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
				.x(function(d) { return x(d.dateTime); })
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
				x.domain(d3.extent(data, function(d) { return d.dateTime; }));
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
					.attr("cx", function(d) { return x(d.dateTime); })
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