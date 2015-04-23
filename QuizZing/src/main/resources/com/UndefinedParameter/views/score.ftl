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
				stroke: #4390df;
				stroke-width: 2;
				fill: none;
			}
			
			circle {
				fill: #2F659C;
			}
			
			circle:hover {
				fill: #1B3A59;
			}

			.axis path,
			.axis line {
				fill: none;
				stroke: grey;
				stroke-width: 1;
				shape-rendering: crispEdges;
			}
			
			.area {
				fill: #C7DEF5;
				stroke-width: 0px;
			}
			
			.label {
				text-transform: uppercase;
				fill: grey;
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
							<div class="grid">
							
							    <div class="row">
							    	<h2>Statistics by Quiz</h2>							    	
							        <div class="span4">
										<div class="input-control select">									
											<select multiple id="quizSelector" onchange="getScores(this.value)" style="height: 215px">
												<#if quizIds??>
													<#list quizIds as quiz>
														<option value=${quiz.quizId}>${quiz.name}</option>	
													</#list>
												</#if>
											</select>										
										</div>
									</div>								
									<div class="span6">				
										<div id="quizScoreGraph"></div>
									</div>							
								</div>
								
								<div class="row">
									<div class="span2 text-right">
										<h2>Overall<br>Average<br>Score</h2>
									</div>
									<div class="span2 notice bg-amber fg-white" style="height: 110px">
										    <h1>${averageScore}%</h1>
									</div>
									<div class="span2 text-right">
										<h2>Best<br>Categories</h2>
									</div>
									<div class="span2 notice bg-green fg-white" style="height: 110px">
										    <#if bestCategories??>
										    	<#list bestCategories as category>
										    		<h4>${category}</h4>
										    	</#list>
										    </#if>
									</div>
								</div>
								
								<div class="row">
								
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
		
		<script type="text/javascript">
		
		$(document).ready(function(){
			loadStartingGraph();
		});
		
		function loadStartingGraph() {
			<#if quizIds??>
				var firstQuizScore = "${quizIds[0].quizId}";
				getScores(firstQuizScore);
			</#if>
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
				}
			});	
		}
		
		function drawScorePlot(dataset) {
			$("#quizScoreGraph").empty();
		
			// Set graph canvas.
			var margin = {top: 10, right: 20, bottom: 40, left: 50},
				width = 600 - margin.left - margin.right,
				height = 250 - margin.top - margin.bottom;

			// Parse the date / time.
			var parseDate = d3.time.format("%d-%b-%y").parse;

			// Set dataset.
			var data = dataset;
			var i = 0;
			
			// Set start map.
			var startData = data.map( function( d ) {
                    return {
                      score : 0,
                      value : 0
                    };
                  } );
			
			// Set the ranges.
			var x = d3.time.scale().range([0, width]);
			var y = d3.scale.linear().range([height, 0]);

			// Define the axes.
			var xAxis = d3.svg.axis().scale(x)
				.orient("bottom").ticks(0);

			var yAxis = d3.svg.axis().scale(y)
				.orient("left").ticks(5);

			// Define the line.
			var valueline = d3.svg.line()
				.x(function(d, i) { return x(i); })
				.y(function(d) { return y(d.score); });
				
			// Define area.
			var area = d3.svg.area()
                .interpolate( 'linear' )
                .x( function( d, i )  { return x( i ); } )
                .y0( height )
                .y1( function( d ) { return y( d.score ); } );
				
			// Add the svg canvas.
			var svg = d3.select("#quizScoreGraph")
				.append("svg")
					.attr("width", width + margin.left + margin.right)
					.attr("height", height + margin.top + margin.bottom)
				.append("g")
					.attr("transform", 
						  "translate(" + margin.left + "," + margin.top + ")");

				// Scale the range of the data.
				x.domain(d3.extent(data, function(d, i) { return i; }));
				y.domain([0, 100]);
				
				// Add the X axis.
				svg.append("g")
					.attr("class", "x axis")
					.attr("transform", "translate(0," + height + ")")
					.call(xAxis);

				// Add the Y axis.
				svg.append("g")
					.attr("class", "y axis")
					.call(yAxis);

				// Add the valueline path.
				var path = svg.append("path")
							.attr("class", "line")
							.attr("d", valueline(startData));
					
				// Add X axis label.
				svg.append("text")
				    .attr("class", "x label")
				    .attr("text-anchor", "end")
				    .attr("x", width)
				    .attr("y", height + 35)
				    .text("Time");
									
				// Add Y axis label.
				svg.append("text")
				    .attr("class", "y label")
				    .attr("text-anchor", "end")
				    .attr("y", 4)
				    .attr("dy", "-3.5em")
				    .attr("transform", "rotate(-90)")
				    .text("Percentage Correct");		
				  
				path.transition().duration(1000).attr("d", valueline(data));	  				
		}
	</script>
		
	</body>
</html>