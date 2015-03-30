<div class="grid fluid">

	<div class="row">

		<h3>Add A Question</h3>
		<p class="text-alert" id="error"  />
		<br/>
		<#if questions??>
			<table>
				<tr>
					<th>Question</th>
					<th>Rating</th>
					<th>Difficulty</th>
					<th>Categories</th>
					<th>Add</th>
				</tr>
				<#list questions as question>
					<tr>
						<td><a id="question${question.questionId}" href="">${question.questionText}</a></td>
						<td width="105px">
							<div id="rating${question.questionId}" class="rating small">
							</div>
						</td>
						<td width="105px">
							<div id="difficulty${question.questionId}" class="rating small fg-red">
							</div>
						</td>
						<td>Computer Science, Computer Secur...</td>
						<td><button id="add${question.questionId}" class="success">+</button>
						
						<script>
							
							$("#add${question.questionId}").click(function() {

								$.ajax({
									type: 'POST',
									url: '/question/add?questionId=${question.questionId}&groupId=${groupId}&quizId=${quizId}',
									success: function(data) {
										window.location.href = '/quiz/edit?groupId=${groupId}&quizId=${quizId}';
									},
									error: function() {
										$("#error").html("* Question has already been added to the quiz.");
									}
								});
							});
						
							$("#question${question.questionId}").click(function(event) {
								event.preventDefault();
							});
						
							$("#question${question.questionId}").on('click', function() {
			
								var content = "<pre style='white-space: pre-wrap; width: 700px'><strong>Question: </strong>${question.questionText}<br/></pre>";
								content += "<strong>Rating:</strong> ${question.rating} <strong>Difficulty: </strong>${question.difficulty}<br/>";
								content += "<strong>Categories: </strong>${question.categoriesString}";
								content += "<pre style='white-space: pre-wrap; width: 700px'><strong>Answers:</strong><br/>  ${question.correctAnswer}<br/>";
								<#list question.wrongAnswers as answer>
									content += "  ${answer}<br/>";
								</#list>
								content += "</pre><br/><br/>";

								$.Dialog({
							        shadow: true,
							        overlay: true,
							        icon: '<span class="icon-power"></span>',
							        title: 'Question',
							        width: 500,
							        padding: 20,
							        content: content
							    });
							});
						
							$(function() {
								$("#difficulty${question.questionId}").rating({
									static: false,
									score: ${question.difficulty},
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
</div>