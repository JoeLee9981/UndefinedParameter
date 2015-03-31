<div class="row">
	<h2>Recently Added Quizzes</h2>
	<table class="table hovered striped">
        <thead>
            <tr>
                <th class="text-left">Quiz</th>
                <th>Rating</th>
                <th class="text-right">Date Created</th>
            </tr>
        </thead>
        <tbody>                         
        	<#if recentQuizzes??>
        		<#list recentQuizzes as quiz>
					<tr>
						<td><a href="quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
						<td class="text-right right">
							<div id="recentRate${quiz.quizId}" class="rating small"></div>
						</td>
						<td class="text-right right">${quiz.dateCreatedAsString}</td>
						
						<script>
							
							//Star rating for quiz quality (entry page)
							$(function() {
								$("#recentRate${quiz.quizId}").rating({
									static: true,
									score: ${quiz.rating},
									stars: 5,
									showHint: true,
									hints: ['wrong', 'poor', 'average', 'good', 'excellent'],
								});
							});
						</script>
					</tr>
				</#list>
			<#else>
				<tr>
					<td colspan="3">
						<h3>No Quizzes Found</h3>
					</td>
				</tr>
			</#if>
		</tbody>
        <tfoot></tfoot>
    </table>  
</div>