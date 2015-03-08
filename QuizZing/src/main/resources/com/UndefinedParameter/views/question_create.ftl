
<div class="page-content">
	<h3>Create a Question</h3>
	<div class="grid fluid">
		<div class="row">
			<div class="span3">
				<nav class="sidebar light">
					<ul>
				        <li id="truefalse-li"><a id="truefalse" href="">True or False</a></li>
				        <li  id="multichoice-li" class="active"><a id="multichoice" href="#">Multiple Choice</a></li>
				        <li id="fillblank-li"><a id="fillblank" href="#" class="todo">Fill In The Blank</a></li>
				        <li id="matching-li"><a id="matching" href="#" class="todo">Matching</a></li>
				        <li id="shortanswer-li"><a id="shortanswer" href="#" class="todo">Short Answer</a></li>
					</ul>
				</nav>
			</div>
			
			<#include "../includes/multiple_choice.ftl">
			<#include "../includes/true_false.ftl">
			<#include "../includes/fill_blank.ftl">
			<#include "../includes/matching.ftl">
			<#include "../includes/short_answer.ftl">
			
		</div>
	</div>
</div>

<script>
	
	$("#truefalse").click(function(event) {
		event.preventDefault();
		
		removeActiveFromAll();
		hideAll();
		$("#truefalse-li").addClass("active");
		$("#truefalse-div").show();
	});
	
	$("#multichoice").click(function(event) {
		event.preventDefault();
		
		removeActiveFromAll();
		hideAll();
		$("#multichoice-li").addClass("active");
		$("#multichoice-div").show();
	});
	
	$("#fillblank").click(function(event) {
		event.preventDefault();
		
		removeActiveFromAll();
		hideAll();
		$("#fillblank-li").addClass("active");
		$("#fillblank-div").show();
	});
	
	$("#matching").click(function(event) {
		event.preventDefault();
		
		removeActiveFromAll();
		hideAll();
		$("#matching-li").addClass("active");
		$("#matching-div").show();
	});
	
	$("#shortanswer").click(function(event) {
		event.preventDefault();
		
		removeActiveFromAll();
		hideAll();
		$("#shortanswer-li").addClass("active");
		$("#shortanswer-div").show();
	});
	
	function removeActiveFromAll() {
		$("#truefalse-li").removeClass("active");
		$("#multichoice-li").removeClass("active");
		$("#fillblank-li").removeClass("active");
		$("#matching-li").removeClass("active");
		$("#shortanswer-li").removeClass("active");
	}
	
	function hideAll() {
		$("#truefalse-div").hide();
		$("#multichoice-div").hide();
		$("#fillblank-div").hide();
		$("#matching-div").hide();
		$("#shortanswer-div").hide();
	}
	
</script>


