<div class="row noMargin questionCreateContainer" id="divMULTIPLE_CHOICE">
	<div class="row">
		<div class="span8">
			<h4>Create a Multiple Choice Question</h4>
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
				    <textarea id="descriptionTextMULTIPLE_CHOICE" class="noResize focusOutTrim focusOutValidateNotEmpty"></textarea>
				</div>
			</div>
			<div class="row noMargin">
			
				<h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				
				<div class="row noMargin">
					<div class="input-control text span11">
					    <input type="text" class="focusOutTrim" id="qText1"/>
					</div>
					<div class="input-control radio span1 place-right">
					    <label>
					        <input name="question-options" type="radio" id="qCheck1"/>
					        <span class="check"></span>
					    </label>
					</div>	
				</div>
				
				<div class="row noMargin">
					<div class="input-control text span11">
					    <input type="text" class="focusOutTrim" id="qText2"/>
					</div>
					<div class="input-control radio span1 place-right">
					    <label>
					        <input name="question-options" type="radio" id="qCheck2"/>
					        <span class="check"></span>
					    </label>
					</div>	
				</div>
				
				<div class="row noMargin">
					<div class="input-control text span11">
					    <input type="text" class="focusOutTrim" id="qText3"/>
					</div>
					<div class="input-control radio span1 place-right">
					    <label>
					        <input name="question-options" type="radio" id="qCheck3"/>
					        <span class="check"></span>
					    </label>
					</div>	
				</div>
				
				<div class="row noMargin">
					<div class="input-control text span11">
					    <input type="text" class="focusOutTrim" id="qText4"/>
					</div>
					<div class="input-control radio span1 place-right">
					    <label>
					        <input name="question-options" type="radio" id="qCheck4"/>
					        <span class="check"></span>
					    </label>
					</div>	
				</div>
				
				<div class="row noMargin">
					<div class="input-control text span11">
					    <input type="text" class="focusOutTrim" id="qText5"/>
					</div>
					<div class="input-control radio span1 place-right">
					    <label>
					        <input name="question-options" type="radio" id="qCheck5"/>
					        <span class="check"></span>
					    </label>
					</div>	
				</div>
			
			</div>
			<div class="input-control checkbox">
				<label>
					<input type="checkbox" id="randomize"/>
					<span class="check"></span>
					Lock Answer Positions
				</label>
			</div>
		</div>
		<div class="span6">
			<div class="row noMargin">
				<h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
				<div class="input-control textarea">
				    <textarea id="explanationTextMULTIPLE_CHOICE" class="noResize focusOutTrim"></textarea>
				</div>
			</div>
			<div class="row noMargin">
				<h5>Tags For This Question</h5>
				<div class="input-control text">
				    <input class="categoriesInput" id="categoriesMULTIPLE_CHOICE" type="text" value="" placeholder="Comma separated categories"/>
				</div>
					<div id="categoryTagsMULTIPLE_CHOICE">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<p id="errorMULTIPLE_CHOICE" class="errorFormText1" hidden></p>
	</div>
	<div class="row">
		<div class="span8">
			<button class="success" id="createMULTIPLE_CHOICE" onclick="createQuestion('MULTIPLE_CHOICE');";>Create Question</button>
		</div>
		<div class="span4">
			<button class="danger place-right" onclick="$('#questionCreateContent').fadeOut(300);">Close</button>
		</div>
	</div>
</div>