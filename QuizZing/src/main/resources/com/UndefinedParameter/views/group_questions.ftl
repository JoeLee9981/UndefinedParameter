<#if user??>
<div class="grid fluid">
	<div class="row">
		<#if message??><p class="text-success"> *${message}</p></#if>
		<h5>My Questions<span class="place-right" title="Close"><a href="" id="closeLink"><i class="icon-cancel" style="color: red"></i></a></span></h5>
		<p class="text-alert" id="error"  />
		<#if userQuestions??>
			<table width="100%">
				<tr>
					<th></th>
					<th>Question</th>
					<th>Rating</th>
					<th>Difficulty</th>
					<th>Categories</th>
				</tr>
				<#list userQuestions as question>
					<tr>
						<td class="padding5">
							<span class="place-left" title="Edit Question"><a href="" id="qLink${question.questionId}"><i class="icon-pencil join"></i></a></span>
						</td>
						<td><a id="question${question.questionId}" href="">
							<#if question.questionText?length &gt; 75>
								${question.questionText?substring(0, 75)}...
							<#else>
								${question.questionText}
							</#if>
						</a></td>
						<td width="105px">
							<div id="rating${question.questionId}" class="rating small">
							</div>
						</td>
						<td width="105px">
							<div id="difficulty${question.questionId}" class="rating small fg-red">
							</div>
						</td>
						<td><#if question.categories??><#if question.categoriesString?length gt 35>${question.categoriesString?substring(0, 35)}...<#else>${question.categoriesString}</#if></#if></td>
						<script>

							$('#qLink${question.questionId}').click(function(event) {
								event.preventDefault();
								$.ajax({
									type: 'GET',
									url: "/question/edit?groupId=" + ${groupId} + "&questionId=" + ${question.questionId},
									success: function(data) {
										$('#group-content').html(data);
									},
									error: function(error) {
								    	$('#group-content').html("<h3>You do not have access to edit this question</h3>");
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
			<p style="padding-left:30px">No Questions Found</p>
		</#if>
	</div>
</div>
</#if>

<div class="grid fluid">
	<div class="row">
		<h5>Group Questions<#if user??><#else><span class="place-right" title="Close"><a href="" id="closeLink"><i class="icon-cancel" style="color: red"></i></a></span></#if></h5>
		<p class="text-alert" id="error"  />
		<#if questions??>
			<table width="100%">
				<tr>
					<th>Question</th>
					<th>Rating</th>
					<th>Difficulty</th>
					<th>Categories</th>
				</tr>
				<#list questions as question>
					<tr>
						<td><a id="question${question.questionId}" href="">
							<#if question.questionText?length &gt; 75>
								${question.questionText?substring(0, 75)}...
							<#else>
								${question.questionText}
							</#if>
						</a></td>
						<td width="105px">
							<div id="rating${question.questionId}" class="rating small">
							</div>
						</td>
						<td width="105px">
							<div id="difficulty${question.questionId}" class="rating small fg-red">
							</div>
						</td>
						<td><#if question.categories??><#if question.categoriesString?length gt 35>${question.categoriesString?substring(0, 35)}...<#else>${question.categoriesString}</#if></#if></td>
						
						<script>

							$('#closeLink').click(function(event) {
								event.preventDefault();
								$('#group-content').html("");
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
			<p style="padding-left:30px">No Questions Found</p>
		</#if>

	</div>
</div>

<script>
	$('#closeLink').click(function(event) {
		event.preventDefault();
		$('#group-content').html("");
	});
</script>