<h3>Edit Your Question<span class="place-right" title="Cancel"><a href="" id="closeLink"><i class="icon-cancel" style="color: red"></i></a></span></h3>
<#if question.type == "MULTIPLE_CHOICE">
	<#include "../includes/multiple_choice_edit.ftl">
<#elseif question.type == "FILL_IN_THE_BLANK">
	<#include "../includes/fill_blank_edit.ftl">
<#elseif question.type == "MATCHING">
	<#include "../includes/matching_edit.ftl">
<#elseif question.type == "SHORT_ANSWER">
	<#include "../includes/short_answer_edit.ftl">
<#elseif question.type == "TRUE_FALSE">
	<#include "../includes/true_false_edit.ftl">
</#if>

<script>
	$('#closeLink').click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/group/questions?groupId=" + ${groupId},
			success: function(data) {
				$('#group-content').html(data);
			},
			error: function(error) {
		    	$('#group-content').html("<h3>No Questions Found</h3>");
		    }
		});
	});
</script>