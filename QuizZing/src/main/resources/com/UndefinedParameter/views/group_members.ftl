<h5>Group Members<span class="place-right" title="Close"><a href="" id="closeLink"><i class="icon-cancel" style="color: red"></i></a></span></h5>
<div class="row noMargin" id="membersDiv" hidden="true">
	<#if members??>
		<table class="table hovered striped">
			<#list members as member>
				<tr>
					<td class="padding5"><a href="/user?userid=${member.userId}">${member.displayName}</a></td>
					<td class="padding5"><span class="place-right">${member.contribution}<i class="icon-clipboard-2 on-right"></i></span></td>
					<td class="padding5"><span class="place-right">${member.joinDateString}<i class="icon-clipboard-2 on-right"></i></span></td>
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