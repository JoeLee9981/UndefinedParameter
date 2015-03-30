<div class="span9" id="fillblank-div">
	<form id="create-question-form">
		<label></label><h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="fb-descriptionText"><#if question.questionText??>${question.questionText}</#if></textarea>

			</div>
		</label>
		<button id="blankButton" class="success">Add a blank</button>
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
		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="fb-explanationText"><#if question.explanation??>${question.explanation}</#if></textarea>
			</div>
		</label>
		<div class="row">	
			<div class="span8">
				<label id="fb-answer-options"><h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
					<div class="input-control text size5">
					    <input type="text" id="fb-qText1" <#if question.answers[0]??>value="${question.answers[0]}"</#if>/>
					</div>
	
					<div class="input-control text size5">
					    <input type="text" id="fb-qText2" <#if question.answers[1]??>value="${question.answers[1]}"</#if>/>
					</div>
									
					<div class="input-control text size5">
					    <input type="text" id="fb-qText3" <#if question.answers[2]??>value="${question.answers[2]}"</#if>/>
					</div>
										
					<div class="input-control text size5">
					    <input type="text" id="fb-qText4" <#if question.answers[3]??>value="${question.answers[3]}"</#if>/>
					</div>
					
					<div class="input-control text size5">
					    <input type="text" id="fb-qText5" <#if question.answers[4]??>value="${question.answers[4]}"</#if>/>
					</div>
					
					<!--<label><p>Reference <a href="#" data-hint="Reference|Add the source of the information used to determine the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></p>
						<div class="input-control text size5">
							<input type="text" id="referenceText" />
						</div>
					</label>
					<label><p>Hyperlink <a href="#" data-hint="Hyperlink|Used with reference to provide an html hyperlink to the source referenced" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></p>
						<div class="input-control text size5">
							<input type="text" id="referenceLink" />
						</div>
					</label>-->
					<div class="span8">
						<input type="button" onclick="addFillBlank()" value="Submit"></input>
						<p id="fb-responseLabel" />
					</div>						
				</label>
			</div>
			
			<div class="span4">
				<div class="panel">
				    <div class="panel-header">
				        Note
				    </div>
				    <div class="panel-content">
				        Use the checkboxes next to your answer to indicate that it is
				        a correct answer.
				    </div>
				</div>
			</div>
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
	
		$('#blankButton').click(function(event) {

			event.preventDefault();
			var explanation = $('#fb-descriptionText').val();

			if(countBlanks(explanation) >= 5) {
				document.getElementById('fb-responseLabel').className = "text-alert";
				document.getElementById('fb-responseLabel').innerHTML = "You may only add 5 blanks to the question.";
				return;
			}
			$('#fb-descriptionText').val(explanation + " <blank> ");
			$('#fb-descriptionText').focus();
		});

		function countBlanks(description) {
			return (description.match(/<blank>/g) || []).length;
			return 0;
		}
	
		function addFillBlank() {
			
			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var desc = document.getElementById('fb-descriptionText').value;
			var correct = "";
			var answers = [];
			var explanation = document.getElementById('fb-explanationText').value;
			var path = "/question/edit?groupId=" + ${groupId};
			
			//TODO: once ready uncomment this code
			//var reference = document.getElementById('referenceText').value;
			//var hyperlink = document.getElementById('referenceLink').value;
			var reference = "";
			var hyperlink = "";
			
			var ordered = true;
			var count = countBlanks(desc);
			
			if(count == 0) {
				document.getElementById('fb-responseLabel').className = "text-alert";
				document.getElementById('fb-responseLabel').innerHTML = "You must at least have one blank and answer";
				return;
			}
			else if(count > 5) {
				document.getElementById('fb-responseLabel').className = "text-alert";
				document.getElementById('fb-responseLabel').innerHTML = "You may only have 5 blanks";
				return;
			}

			for(var i = 1; i <= maxAnswers; i++) {
				if(!$('#fb-qText' + i).val() && i <= count) {
					document.getElementById('fb-responseLabel').className = "text-alert";
					document.getElementById('fb-responseLabel').innerHTML = "You must fill in the answer for blank " + i;
				}
				else {
					if(i == 1)
						correct = ($('#fb-qText' + i).val());
					else if(i <= count)
						answers.push($('#fb-qText' + i).val());
					else
						answers.push("");
				}
			}
			
			document.getElementById('fb-responseLabel').innerHTML = "";
			
			if(!desc) {
				document.getElementById('fb-responseLabel').innerHTML = "The Question must be filled out";
				document.getElementById('fb-responseLabel').className = "text-alert";
				return;
			}	
			
			if(hyperlink && !reference) {
				document.getElementById('fb-responseLabel').innerHTML = "Reference must be filled out in conjunction to the hyperlink";
				document.getElementById('fb-responseLabel').className = "text-alert";
				return;
			}

			var categories = $('#categories').val().split(',');
			for(var i = 0; i < categories.length; i++) {
				categories[i] = categories[i].trim().substring(1);
			}

			 $.ajax({
				type: 'PUT',
				url: path,
				data: JSON.stringify({questionId: ${question.questionId}, questionText: desc, correctAnswer: correct, wrongAnswers: answers, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, categories: categories }),
				headers: {
					"Content-Type": "application/json"
				},
				success: function(data) {
					$('#group-content').html(data);
				},
				error: function(error) {
					document.getElementById('fb-responseLabel').className = "text-alert";
					document.getElementById('fb-responseLabel').innerHTML = "An unknown error ocurred while trying to update the question, please try again."
			    }
			});
		}
	</script>
</div>