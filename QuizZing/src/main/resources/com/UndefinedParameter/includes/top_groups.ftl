<div class="row">
	<h2>Top Groups</h2>
	<table class="table hovered">
		
        <thead>
            <tr>
                <th class="text-left">Group</th>
                <th class="text-right">Members</th>
                <th class="text-right">Quizzes</th>
            </tr>
        </thead>
        <tbody> 
        <#if topGroups??>
        	<#list topGroups as group>
				<tr>
					<td><a href="group?groupId=${group.id}">${group.name}</a></td>
					<td class="text-right right">${group.memberCount}</td>
					<td class="text-right right">${group.quizCount}</td>
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