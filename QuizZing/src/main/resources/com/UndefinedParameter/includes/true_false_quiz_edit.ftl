<div class="row noMargin questionCreateContainer" id="divTRUE_FALSE">
	<div class="row">
		<div class="span8">
			<h4>Create a True or False Question</h4>
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
				    <textarea id="descriptionTextTRUE_FALSE" class="noResize focusOutTrim focusOutValidateNotEmpty"></textarea>
				</div>
			</div>
			<div class="row noMargin">		
				<h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>				
			</div>
			<div class="row noMargin">
				<div class="input-control radio">
					<label>
				        <input name="question-options" type="radio" id="trueCheck" />
				        <span class="check"></span>
				        True
				    </label>
				</div>
			</div>
			<div class="row noMargin">
			    <div class="input-control radio">
				    <label>
				        <input name="question-options" type="radio" id="falseCheck" />
				        <span class="check"></span>
				        False
				    </label>
				</div>
			</div>
		</div>
		<div class="span6">
			<div class="row noMargin">
				<h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				<div class="input-control textarea">
				    <textarea id="explanationTextTRUE_FALSE" class="noResize focusOutTrim"></textarea>
				</div>
			</div>
			<div class="row noMargin">
				<h5>Tags For This Question</h5>
				<div class="input-control text">
				    <input class="categoriesInput" id="categoriesTRUE_FALSE" type="text" value="" placeholder="Comma separated categories"/>
				</div>
					<div id="categoryTagsTRUE_FALSE">
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	<div class="row">
		<div class="span8">
			<button class="success" id="createTRUE_FALSE" onclick="createQuestion('TRUE_FALSE');";>Create Question</button>
		</div>
		<div class="span4">
			<button class="danger place-right" onclick="$('#questionCreateContent').fadeOut(300);">Close</button>
		</div>
	</div>
</div>