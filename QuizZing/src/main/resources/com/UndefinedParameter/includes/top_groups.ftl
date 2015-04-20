<div class="row noMargin">
	<h2><strong>Top Groups</strong></h2>
	<table class="table hovered striped">
        <thead>
            <tr>
                <th class="text-left">Group</th>
			    <th title="Members" class="text-center"><i class="icon-user-3 on-right"></i></th>
			    <th title="Quizzes" class="text-center"><i class="icon-clipboard-2 on-right"></i></th>
			    <th title="Questions" class="text-center"><i class="icon-help-2 on-right"></i></th>
			    <th title="Contribution is how much you've contributed towards the group" class="text-center">Contribution</th>
			    <th class="text-center">Date Created</th>
            </tr>
        </thead>
        <tbody> 
        <#if groups??>
        	<#list groups as group>
				<tr>
					<td><a href="/group?groupId=${group.id}">${group.name?html}</a></td>
					<td class="text-center">${group.memberCount}</td>
					<td class="text-center">${group.quizCount}</td>
					<td class="text-center">${group.questionCount}</td>
					<td class="text-center">35</td>
					<td class="text-center">${group.dateAsString}</td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="3"><h3>No Groups Found</h3></td>
			</tr>
		</#if>
		</tbody>
        <tfoot></tfoot>
    </table>
</div>