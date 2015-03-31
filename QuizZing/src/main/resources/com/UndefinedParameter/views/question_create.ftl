
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
			
			<div id="question-create">
				<#include "../includes/multiple_choice.ftl">
			</div>		
		</div>
	</div>
</div>

<script>
	
	$("#truefalse").click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/question/create/type?groupId=" + ${groupId} + "&quizId=" + ${quizId} + "&type=TRUE_FALSE",
			success: function(data) {
				removeActiveFromAll();
				$("#truefalse-li").addClass("active");
				$('#question-create').html(data);
			},
			error: function(error) {
		    	$('#question-create').html("<h3>There was an unexpected error communicating to server, please refresh and try again.</h3>");
		    }
		});
	});
	
	$("#multichoice").click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/question/create/type?groupId=" + ${groupId} + "&quizId=" + ${quizId} + "&type=MULTIPLE_CHOICE",
			success: function(data) {
				removeActiveFromAll();
				$("#multichoice-li").addClass("active");
				$('#question-create').html(data);
			},
			error: function(error) {
		    	$('#question-create').html("<h3>There was an unexpected error communicating to server, please refresh and try again.</h3>");
		    }
		});
	});
	
	$("#fillblank").click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/question/create/type?groupId=" + ${groupId} + "&quizId=" + ${quizId} + "&type=FILL_IN_THE_BLANK",
			success: function(data) {
				removeActiveFromAll();
				$("#fillblank-li").addClass("active");
				$('#question-create').html(data);
			},
			error: function(error) {
		    	$('#question-create').html("<h3>There was an unexpected error communicating to server, please refresh and try again.</h3>");
		    }
		});
	});
	
	$("#matching").click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/question/create/type?groupId=" + ${groupId} + "&quizId=" + ${quizId} + "&type=MATCHING",
			success: function(data) {
				removeActiveFromAll();
				$("#matching-li").addClass("active");
				$('#question-create').html(data);
			},
			error: function(error) {
		    	$('#question-create').html("<h3>There was an unexpected error communicating to server, please refresh and try again.</h3>");
		    }
		});
	});
	
	$("#shortanswer").click(function(event) {
		event.preventDefault();
		$.ajax({
			type: 'GET',
			url: "/question/create/type?groupId=" + ${groupId} + "&quizId=" + ${quizId} + "&type=SHORT_ANSWER",
			success: function(data) {
				removeActiveFromAll();
				$("#shortanswer-li").addClass("active");
				$('#question-create').html(data);
			},
			error: function(error) {
		    	$('#question-create').html("<h3>There was an unexpected error communicating to server, please refresh and try again.</h3>");
		    }
		});
	});
	
	function removeActiveFromAll() {
		$("#truefalse-li").removeClass("active");
		$("#multichoice-li").removeClass("active");
		$("#fillblank-li").removeClass("active");
		$("#matching-li").removeClass("active");
		$("#shortanswer-li").removeClass("active");
	}
	
</script>


