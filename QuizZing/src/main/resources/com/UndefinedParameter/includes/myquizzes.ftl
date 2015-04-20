<div class="row noMargin">
	<h2><strong>My Quizzes</strong></h2>
	<#if quizzes??>
	<table class="table hovered striped">
        <thead>
            <tr>
                <th class="text-left">Quiz</th>
                <th class="text-left">Group</th>
                <th class="text-left">Creator</th>
                <th class="text-left">Description</th>
                <th title="Questions"><i class="icon-help-2 on-right"></i></th>
                <th style="width: 120px">Difficulty</th>
                <th style="width: 120px">Rating</th>
            </tr>
        </thead>
        <tbody>                         
        		<#list quizzes as quiz>
					<tr>
						<td><a href="quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
						<td class="text-left left"><a href="group?groupId=${quiz.parentGroupId}">${quiz.parentGroupName}</a></td>
						<td class="text-left left"><a href="user?userid=${quiz.creatorId}">${quiz.creatorName}</a></td>
						<td class="text-left left">
							<#if quiz.description??>
								<#if quiz.description?length &gt; 20>
									${quiz.description?substring(0, 20)}...
								<#else>
									${quiz.description}
								</#if>
							<#else>
								No Description
							</#if>
						</td>
						<td class="text-center center">${quiz.questionCount}</td>
						<td class="text-right right">
							<div id="diff${quiz.quizId}" class="rating small fg-red"></div>
						</td>
						<td class="text-right right">
							<div id="rating${quiz.quizId}" class="rating small"></div>
						</td>
						
						<script>
							//Star rating for quiz quality (entry page)
							$(function() {
								$("#rating${quiz.quizId}").rating({
									static: true,
									score: ${quiz.rating},
									stars: 5,
									showHint: true,
									hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
								});
							});
							
							//Star rating for quiz quality (entry page)
							$(function() {
								$("#diff${quiz.quizId}").rating({
									static: true,
									score: ${quiz.difficulty},
									stars: 5,
									showHint: true,
									hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
								});
							});
						</script>
					</tr>
				</#list>
		</tbody>
        <tfoot></tfoot>
    </table>  
    <#else>
		<tr>
			<td colspan="3">
				<h3>No Quizzes Found</h3>
			</td>
		</tr>
	</#if>
</div>