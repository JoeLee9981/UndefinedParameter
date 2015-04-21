<div class="row noMargin questionCreateContainer" id="divSHORT_ANSWER">
	<div class="row">
		<div class="span8">
			<h4>Create a Short Answer Question</h4>
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
				    <textarea id="descriptionTextSHORT_ANSWER" class="noResize focusOutTrim focusOutValidateNotEmpty"></textarea>
				</div>
			</div>
			<div class="row noMargin">		
				<h5>Answer <a href="#" data-hint="Answer|This is the answer, note the user must provide it exactly, so keep it short" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			</div>
			<div class="row noMargin">
				<div class="input-control textarea">
				    <textarea id="answerTextSHORT_ANSWER" class="noResize focusOutTrim focusOutValidateNotEmpty"></textarea>
				</div>
			</div>
		</div>
		<div class="span6">
			<div class="row noMargin">
				<h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				<div class="input-control textarea">
				    <textarea id="explanationTextSHORT_ANSWER" class="noResize focusOutTrim"></textarea>
				</div>
			</div>
			<div class="row noMargin">
				<h5>Tags For This Question</h5>
				<div class="input-control text">
				    <input class="categoriesInput" id="categoriesSHORT_ANSWER" type="text" value="" placeholder="Comma separated categories"/>
				</div>
					<div id="categoryTagsSHORT_ANSWER">
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	<div class="row">
		<div class="span8">
			<button class="success" id="createSHORT_ANSWER" onclick="createQuestion('SHORT_ANSWER');";>Create Question</button>
		</div>
		<div class="span4">
			<button class="danger place-right" onclick="$('#questionCreateContent').fadeOut(300);">Close</button>
		</div>
	</div>
</div>