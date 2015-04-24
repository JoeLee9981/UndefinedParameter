<div class="row noMargin questionCreateContainer" id="divMATCHING">
	<div class="row">
		<div class="span8">
			<h4>Create a Matching Question</h4>
		</div>
		<div class="span4">
			<button onclick="closeCreateQuestionContainer();" class="danger place-right">Close</button>
		</div>
	</div>
	<div class="row">
	
		<div class="row noMargin">
		    <div class="span12">
				<div class="row noMargin">
					<h5>Tags For This Question</h5>
					<div class="input-control text">
					    <input class="categoriesInput" id="categoriesMATCHING" type="text" value="" placeholder="Comma separated categories"/>
					</div>
						<div id="categoryTagsMATCHING">
					</div>
				</div>
			</div>
	    </div>
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-1" maxlength="395" placeholder="Question A"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText1" maxlength="95" placeholder="Match A"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-2" maxlength="395" placeholder="Question B"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText2" maxlength="95" placeholder="Match B"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-3" maxlength="395" placeholder="Question C"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText3" maxlength="95" placeholder="Match C"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-4" maxlength="395" placeholder="Question D"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText4" maxlength="95" placeholder="Match D"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-5" maxlength="395" placeholder="Question E"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText5" maxlength="95"  placeholder="Match E"/>
			</div>
		</div>

		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="explanationTextMATCHING"></textarea>
			</div>
		</label>
	
	

		<div class="span8">
			<p id="match-responseLabel" />
		</div>		
		
	</div>

	<div class="row">
		<p id="errorMATCHING" class="errorFormText1" hidden></p>
	</div>
	<div class="row">
		<div class="span8">
			<button class="success" id="createMATCHING" onclick="createQuestion('MATCHING');";>Create Question</button>
		</div>
		<div class="span4">
			<button class="danger place-right" onclick="$('#questionCreateContent').fadeOut(300);">Close</button>
		</div>
	</div>
</div>

