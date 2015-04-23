<h5>Group Members<span class="place-right" title="Close"><a href="" id="closeLink"><i class="icon-cancel" style="color: red"></i></a></span></h5>
<div class="row noMargin" id="membersDiv" hidden="true">
	<#if members??>
		<table class="table hovered striped">
			<#list members as member>
				<tr>
					<td class="padding5">
						<a href="/user?userid=${member.userId}">${member.displayName}
						<#if member.moderator><i title="Moderator" class="icon-heart on-right"></i></#if></a>
					</td>
					<td class="padding5"><span class="place-right">${member.contribution}
					<#if member.contribution &gt;= 300>
					<i title="Contribution Score" class="icon-medal on-right fg-amber"></i>
					<#elseif member.contribution &gt;= 200>
					<i title="Contribution Score" class="icon-medal on-right fg-emerald"></i>
					<#elseif member.contribution &gt;= 100>
					<i title="Contribution Score" class="icon-medal on-right fg-red"></i>
					<#else>
					<i title="Contribution Score" class="icon-medal on-right"></i>
					</#if>
					</span></td>
					<td class="padding5"><span class="place-right">${member.quizzes}<i class="icon-clipboard-2 on-right"></i></span></td>
					<td class="padding5"><span class="place-right">${member.questions}<i title="Questions" class="icon-help-2 on-right"></i></span></td>
					<td class="padding5"><span class="place-right" title="Join Date">${member.joinDateString}</span></td>
				</tr>
			</#list>
		</table>
	<#else>
		<h3>There are currently no members of this group</h3>
	</#if>
</div>

<script>
	$('#closeLink').click(function(event) {
		event.preventDefault();
		$('#group-content').html("");
	});
</script>