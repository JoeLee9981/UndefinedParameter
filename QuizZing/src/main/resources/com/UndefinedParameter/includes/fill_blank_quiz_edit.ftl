<div class="row noMargin questionCreateContainer" id="divFILL_IN_THE_BLANK">
	<div class="row">
		<div class="span8">
			<h4>Create a Fill in the Blank Question</h4>
		</div>
		<div class="span4">
			<button onclick="closeCreateQuestionContainer();" class="danger place-right">Close</button>
		</div>
	</div>
	<div class="row">
		<div class="span6">
			<div class="row noMargin">
				<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				<div class="input-control textarea">
				    <textarea id="descriptionTextFILL_IN_THE_BLANK" class="noResize focusOutTrim focusOutValidateNotEmpty"></textarea>
				</div>
			</div>
			<div class="row noMargin">
				<button id="blankButton" class="success">Add a blank</button>
				<p id="fb-responseLabel" />
			</div>
			<div class="row noMargin">		
				<h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				
				<div class="input-control text">
				    <input type="text" id="fb-qText1"/>
				</div>

				<div class="input-control text">
				    <input type="text" id="fb-qText2"/>
				</div>
								
				<div class="input-control text">
				    <input type="text" id="fb-qText3"/>
				</div>
									
				<div class="input-control text">
				    <input type="text" id="fb-qText4"/>
				</div>
				
				<div class="input-control text">
				    <input type="text" id="fb-qText5"/>
				</div>
			</div>
		</div>
		<div class="span6">
			<div class="row noMargin">
				<h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				<div class="input-control textarea">
				    <textarea id="explanationTextFILL_IN_THE_BLANK" class="noResize focusOutTrim"></textarea>
				</div>
			</div>
			<div class="row noMargin">
				<h5>Tags For This Question</h5>
				<div class="input-control text">
				    <input class="categoriesInput" id="categoriesFILL_IN_THE_BLANK" type="text" value="" placeholder="Comma separated categories"/>
				</div>
					<div id="categoryTagsFILL_IN_THE_BLANK">
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	<div class="row">
		<p id="errorFILL_IN_THE_BLANK" class="errorFormText1" hidden></p>
	</div>
	<div class="row">
		<div class="span8">
			<button class="success" id="createFILL_IN_THE_BLANK" onclick="createQuestion('FILL_IN_THE_BLANK');";>Create Question</button>
		</div>
		<div class="span4">
			<button class="danger place-right" onclick="$('#questionCreateContent').fadeOut(300);">Close</button>
		</div>
	</div>
</div>

<script>
	$('#blankButton').click(function(event) {

		event.preventDefault();
		var explanation = $('#descriptionTextFILL_IN_THE_BLANK').val();

		if(countBlanks(explanation) >= 5) {
			document.getElementById('fb-responseLabel').className = "text-alert";
			document.getElementById('fb-responseLabel').innerHTML = "You may only add 5 blanks to the question.";
			return;
		}
		$('#descriptionTextFILL_IN_THE_BLANK').val(explanation + " <blank> ");
		$('#descriptionTextFILL_IN_THE_BLANK').focus();
	});
	
	
	function countBlanks(description) {
		return (description.match(/<blank>/g) || []).length;
		return 0;
	}
</script>