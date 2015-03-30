<div class="span9" id="matching-div">
	<form id="create-question-form">
	
		<div class="row noMargin">
		    <div class="span12">
		    	<div>
					<h4>Categories For This Quiz</h4>
				</div>
				<div>
					<div class="input-control text">
					    <input id="categories" type="text" placeholder="Comma separated categories" value="${question.categoriesString}"/>
					</div>
				</div>	
			</div>
	    </div>
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>

		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-1" maxlength="395" placeholder="Question A"><#if question.matchingQuestions[0]??>${question.matchingQuestions[0]}</#if></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText1" maxlength="95" placeholder="Match A" value="${question.correctAnswer?substring(9)}"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-2" maxlength="395" placeholder="Question B"><#if question.matchingQuestions[1]??>${question.matchingQuestions[1]}</#if></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText2" maxlength="95" placeholder="Match B" <#if question.wrongAnswers[0]??>value="${question.wrongAnswers[0]?substring(9)}"</#if>/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-3" maxlength="395" placeholder="Question C"><#if question.matchingQuestions[2]??>${question.matchingQuestions[2]}</#if></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText3" maxlength="95" placeholder="Match C" <#if question.wrongAnswers[1]??>value="${question.wrongAnswers[1]?substring(9)}"</#if>/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-4" maxlength="395" placeholder="Question D"><#if question.matchingQuestions[3]??>${question.matchingQuestions[3]}</#if></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText4" maxlength="95" placeholder="Match D" <#if question.wrongAnswers[2]??>value="${question.wrongAnswers[2]?substring(9)}"</#if>/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-5" maxlength="395" placeholder="Question E"><#if question.matchingQuestions[4]??>${question.matchingQuestions[4]}</#if></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText5" maxlength="95"  placeholder="Match E" <#if question.wrongAnswers[3]??>value="${question.wrongAnswers[3]?substring(9)}"</#if>/>
			</div>
		</div>

		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="match-explanationText"><#if question.explanationFormatted??>${question.explanationFormatted}</#if></textarea>
			</div>
		</label>
	
	

		<div class="span8">
			<input type="button" onclick="addMatching()" value="Submit"></input>
			<p id="match-responseLabel" />
		</div>						
	</form>
	
	
	<script>
		$('#categories').keydown(function(event) {
			if(event.which == 188) {
				var replc = "";
				var hTags = $('#categories').val().split(',');
				for(var i = 0; i < hTags.length; i++) {
					if(hTags[i].trim() && hTags[i].trim().substring(0, 1) != "#") {
						replc += "#" + hTags[i].trim() + ", ";
					}
					else if(hTags[i].trim() != ""){
						replc += hTags[i].trim() + ", ";
					}
				}
				$('#categories').val(replc);
				event.preventDefault();
			}
			
		});
	
		$('#categories').blur(function() {
	
			var replc = "";
			var hTags = $('#categories').val().split(',');
			for(var i = 0; i < hTags.length; i++) {
				if(hTags[i].trim() && hTags[i].trim().substring(0, 1) != "#") {
					replc += "#" + hTags[i].trim() + ", ";
				}
				else if(hTags[i].trim() != ""){
					replc += hTags[i].trim() + ", ";
				}
			}
			$('#categories').val(replc.substring(0, replc.length-2));
			event.preventDefault();
		});
	
		function addMatching() {
			
			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var desc = "";
			var correct;
			var incorrect = [];
			var explanation = document.getElementById('match-explanationText').value;
			var path = "/question/edit?groupId=" + ${groupId};
			var correctPos = 0;
			
			//TODO: once ready uncomment this code
			//var reference = document.getElementById('referenceText').value;
			//var hyperlink = document.getElementById('referenceLink').value;
			var reference = "";
			var hyperlink = "";
			
			
			var ordered = false;
			
			document.getElementById('match-responseLabel').innerHTML = "";

			var count = 1;
			for(var i = 1; i <= maxAnswers; i++) {
				if(($('#matchText' + i).val() && !$('#match-descriptionText-' + i).val()) || 
				   (!$('#matchText' + i).val() && $('#match-descriptionText-' + i).val())) {
					document.getElementById('match-responseLabel').innerHTML = "Both Question and its Match must be entered";
					document.getElementById('match-responseLabel').className = "text-alert";
					return;
				}
				else if($('#matchText' + i).val() && $('#match-descriptionText-' + i).val()) {
					desc += "<" + count + ">" + $('#match-descriptionText-' + i).val() + " ";
					if(i == 1) {
						correct = '<A>' + $('#matchText' + i).val();
					}
					else {
						incorrect.push('<' + String.fromCharCode(64 + count) + '>' + $('#matchText' + i).val());
					}
					count++;
				}
				else {
					incorrect.push("");
				}
			}

			if(count < 3) {
				document.getElementById('match-responseLabel').innerHTML = "Matching must at least have two questions to match";
				document.getElementById('match-responseLabel').className = "text-alert";
				return;
			}
			
			if(hyperlink && !reference) {
				document.getElementById('responseLabel').innerHTML = "Reference must be filled out in conjunction to the hyperlink";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}

			var categories = $('#categories').val().split(',');
			for(var i = 0; i < categories.length; i++) {
				categories[i] = categories[i].trim().substring(1);
			}

			 $.ajax({
				type: 'PUT',
				url: path,
				data: JSON.stringify({questionId: ${question.questionId}, questionText: desc, correctAnswer: correct, wrongAnswers: incorrect, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, correctPosition: correctPos, categories: categories }),
				headers: {
					"Content-Type": "application/json"
				},
				success: function(data) {
					$('#group-content').html(data);
				},
				error: function(error) {
					document.getElementById('responseLabel').className = "text-alert";
					document.getElementById('responseLabel').innerHTML = "An unknown error ocurred while trying to update the question, please try again."
			    }
			});
		}
	</script>
</div>