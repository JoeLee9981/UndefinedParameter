<script src="/assets/scripts/rate.js"></script>

<#if user??>
<div class="grid fluid">
	<div class="row">
		<#if message??><p class="text-success"> *${message}</p></#if>
		<h5>My Questions<span class="place-right" title="Close"><a href="" id="closeLink"><i class="icon-cancel" style="color: red"></i></a></span></h5>
		<p class="text-alert" id="error"  />
		<#if userQuestions??>
			<table class="table hovered striped" width="100%">
				<thead>
					<tr>
						<th>Question</th>
						<th width="25px"></th>
						<th width="120px">Rating</th>
						<th width="120px">Difficulty</th>
						<th>Categories</th>
						<th width="25px"></th>
					</tr>
				</thead>
				<tbody>
				<#list userQuestions as question>
					<tr>
						<td>
							<a id="question${question.questionId}" href="">
								<#if question.questionTextFormatted?length &gt; 75>
									${question.questionTextFormatted?substring(0, 75)}...
								<#else>
									${question.questionTextFormatted}
								</#if>
							</a>
						</td>
						<td>
							<#if question.flagged>
								<a href="" id="qFlagLink${question.questionId}" onclick="unflagQuestion(${question.questionId}, ${groupId}, '${question.flaggedReason}')"><i id="flag${question.questionId}" title="Flagged" class="icon-flag-2 fg-red on-right"></i></a>
							<#else>
								<a href="" id="qFlagLink${question.questionId}" onclick="flagQuestion(${question.questionId})"><i id="flag${question.questionId}" title="Flag Question" class="icon-flag-2 fg-gray on-right"></i></a>
							</#if>
						</td>
						<td width="120px">
							<div id="rating${question.questionId}" class="rating small">
							</div>
						</td>
						<td width="120px">
							<div id="difficulty${question.questionId}" class="rating small fg-red">
							</div>
						</td>
						<td class="text-center"><#if question.categories??><#if question.categoriesString?length &gt; 14>${question.categoriesString?substring(0, 15)}...<#else>${question.categoriesString}</#if></#if></td>
						<td class="padding5">	
							<span class="place-left" title="Edit Question"><a href="" id="qLink${question.questionId}"><i class="icon-pencil join"></i></a></span>
						</td>
						
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

							$('#qFlagLink${question.questionId}').click(function(event) {
								event.preventDefault();
							});
						
							$("#question${question.questionId}").click(function(event) {
								event.preventDefault();
							});
						
							$("#question${question.questionId}").on('click', function() {
			
								var content = "<pre style='white-space: pre-wrap; width: 700px'><strong>Question: </strong>${question.questionTextFormatted}<br/></pre>";
								content += "<strong>Rating:</strong> ${question.rating} <strong>Difficulty: </strong>${question.difficulty}<br/>";
								content += "<strong>Categories: </strong>${question.categoriesString}";
								content += "<pre style='white-space: pre-wrap; width: 700px'><strong>Answers:</strong><br/>  ${question.correctAnswerFormatted}<br/>";
								<#list question.wrongAnswersFormatted as answer>
									content += "  ${answer}<br/>";
								</#list>
								content += "</pre><br/><br/>";

								$.Dialog({
							        shadow: true,
							        overlay: true,
							        flat: true,
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
				</tbody>
				<tfoot></tfoot>
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
			<table class="table hovered striped" width="100%">
				<thead>
					<tr>
						<th>Question</th>
						<th width="25px"></th>
						<th>Rating</th>
						<th width="120px">Difficulty</th>
						<th width="120px">Categories</th>
						<th width="25px"></th>
					</tr>
				</thead>
				<tbody>
				<#list questions as question>
					<tr>
						<td>
							<a id="question${question.questionId}" href="">
								<#if question.questionTextFormatted?length &gt; 75>
									${question.questionTextFormatted?substring(0, 75)}...
								<#else>
									${question.questionTextFormatted}
								</#if>
							</a>
						</td>
						<td>
							<#if question.flagged>
								<#if moderator>
								<a href="" id="qFlagLink${question.questionId}" onclick="unflagQuestion(${question.questionId}, ${groupId}, '${question.flaggedReason}')"><i id="flag${question.questionId}" title="Flagged" class="icon-flag-2 fg-red on-right"></i></a>
								<#else>
								<i id="flag${question.questionId}" title="This question is already flagged" class="icon-flag-2 fg-red on-right"></i>
								</#if>
							<#else>
								
								<a href="" id="qFlagLink${question.questionId}" onclick="flagQuestion(${question.questionId})"><i id="flag${question.questionId}" title="Flag Question" class="icon-flag-2 fg-gray on-right"></i></a>
								
							</#if>
						</td>
						<td width="120px">
							<div id="rating${question.questionId}" class="rating small">
							</div>
						</td>
						<td width="120px">
							<div id="difficulty${question.questionId}" class="rating small fg-red">
							</div>
						</td>
						<td class="text-center"><#if question.categories??><#if question.categoriesString?length &gt; 14>${question.categoriesString?substring(0, 15)}...<#else>${question.categoriesString}</#if></#if></td>
						<#if user?? && user.admin>
						<td class="padding5">
							<span class="place-left" title="Edit Question"><a href="" id="qLink${question.questionId}"><i class="icon-pencil join"></i></a></span>
						</td>
						<#else>
						<td></td>
						</#if>
						
						<script>
							<#if user?? && user.admin??>
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
							</#if>
						
							$('#closeLink').click(function(event) {
								event.preventDefault();
								$('#group-content').html("");
							});

							$('#qFlagLink${question.questionId}').click(function(event) {
								event.preventDefault();
							});
						
							$("#question${question.questionId}").click(function(event) {
								event.preventDefault();
							});
						
							$("#question${question.questionId}").on('click', function() {
			
								var content = "<pre style='white-space: pre-wrap; width: 700px'><strong>Question: </strong>${question.questionTextFormatted}<br/></pre>";
								content += "<strong>Rating:</strong> ${question.rating} <strong>Difficulty: </strong>${question.difficulty}<br/>";
								content += "<strong>Categories: </strong>${question.categoriesString}";
								content += "<pre style='white-space: pre-wrap; width: 700px'><strong>Answers:</strong><br/>  ${question.correctAnswerFormatted}<br/>";
								<#list question.wrongAnswersFormatted as answer>
									content += "  ${answer}<br/>";
								</#list>
								content += "</pre><br/><br/>";

								$.Dialog({
							        shadow: true,
							        overlay: true,
							        flat: true,
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
				</tbody>
				<tfoot></tfoot>
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

	function onFlag() {
		//do nothing here
	}
</script>