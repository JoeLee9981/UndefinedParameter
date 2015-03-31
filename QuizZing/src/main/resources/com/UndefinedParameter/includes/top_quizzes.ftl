<div class="row">
	<h2>Top Quizzes</h2>
	<table class="table hovered striped">
        <thead>
            <tr>
                <th class="text-left">Quiz</th>
                <th>Difficulty</th>
                <th>Rating</th>
            </tr>
        </thead>
        <tbody>                         
        	<#if topQuizzes??>
        		<#list topQuizzes as quiz>
					<tr>
						<td><a href="quiz?quizId=${quiz.quizId}">${quiz.name}</a></td>
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