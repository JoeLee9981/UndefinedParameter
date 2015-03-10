<div class="grid fluid">

	<div class="row">

		<h3>Current Questions</h3>
		<p class="text-alert" id="error"  />
		<br/>
		<#if quiz.questions??>
			<table>
				<tr>
					<th>Question</th>
					<th>Rating</th>
					<th>Difficulty</th>
					<th>Categories</th>
					<th>Add</th>
				</tr>
				<#list quiz.questions as question>
					<tr>
						<td><a id="question${question.questionId}" href="">${question.questionText}</a></td>
						<td>
							<div id="rating${question.questionId}" class="rating small">
							</div>
						</td>
						<td>
							<div id="difficulty${question.questionId}" class="rating small fg-red">
							</div>
						</td>
						<td>Computer Science, Computer Secur...</td>
						<td><button class="danger" onclick="removeQuestion(${question.questionId})">-</button></td>
						
						<script>
						
							$("#question${question.questionId}").click(function(event) {
								event.preventDefault();
							});
						
							$("#question${question.questionId}").on('click', function() {
			
								var content = "<pre><strong>Question: </strong>${question.questionText}<br/>";
								content += "<strong>Rating:</strong> ${question.rating} <strong>Difficulty: </strong>${question.questionDifficulty}<br/><strong>Answers:</strong><br/>";
								content += "  ${question.correctAnswer}<br/>";
								<#list question.wrongAnswers as answer>
									content += "  ${answer}<br/>";
								</#list>
								content += "</pre>";

								$.Dialog({
							        shadow: true,
							        overlay: true,
							        icon: '<span class="icon-power"></span>',
							        title: 'Question',
							        width: 500,
							        padding: 10,
							        content: content
							    });
							});
						
							$(function() {
								$("#difficulty${question.questionId}").rating({
									static: false,
									score: ${question.questionDifficulty},
									stars: 5,
									showHint: true,
									hints: ['cake', 'easy', 'average', 'hard', 'impossible'],
									click: function(value, rating) {
										alert("Not yet implemented");
									}
								});		
							});
							
							$(function() {
								$("#rating${question.questionId}").rating({
									static: false,
									score: ${question.rating},
									stars: 5,
									showHint: true,
									hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
									click: function(value, rating) {
										alert("Not yet implemented");
									}
								});
							});
						</script>
					</tr>
				</#list>
			</table>
		<#else>
			<h4>No Questions Found</h4>
		</#if>

	</div>
	
	<script>
	
		function removeQuestion(questionId) {
			
			$.ajax({
				type: 'DELETE',
				url: '/question/remove?quizId=${quiz.quizId}&questionId=' + questionId,
				success: function(data) {
					$("#questionDiv").html(data);
				},
				error: function() {
					$("#error").html("* An error occurred attempting to remove the question.");
				}
			});
		}
	
	</script>
</div>