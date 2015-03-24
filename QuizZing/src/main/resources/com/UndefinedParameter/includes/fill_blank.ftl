<div class="span9" id="fillblank-div" hidden>
	<form id="create-question-form">
		<label></label><h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="fb-descriptionText"></textarea>

			</div>
		</label>
		<button id="blankButton" class="success">Add a blank</button>
		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="fb-explanationText"></textarea>
			</div>
		</label>
		<div class="row">	
			<div class="span8">
				<label id="fb-answer-options"><h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
					<div class="input-control text size5">
					    <input type="text" id="fb-qText1"/>
					</div>
	
					<div class="input-control text size5">
					    <input type="text" id="fb-qText2"/>
					</div>
									
					<div class="input-control text size5">
					    <input type="text" id="fb-qText3"/>
					</div>
										
					<div class="input-control text size5">
					    <input type="text" id="fb-qText4"/>
					</div>
					
					<div class="input-control text size5">
					    <input type="text" id="fb-qText5"/>
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
			var quizId = ${quizId};
			var desc = document.getElementById('fb-descriptionText').value;
			var correct = "";
			var type = "FILL_IN_THE_BLANK";
			var answers = [];
			var creatorId = ${user.id};
			var explanation = document.getElementById('fb-explanationText').value;
			var path = "/question/create?quizId=" + quizId;
			
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
			
			document.getElementById('responseLabel').innerHTML = "";
			
			if(!desc) {
				document.getElementById('responseLabel').innerHTML = "The Question must be filled out";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}	
			
			if(hyperlink && !reference) {
				document.getElementById('responseLabel').innerHTML = "Reference must be filled out in conjunction to the hyperlink";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}

			 $.ajax({
				type: 'POST',
				url: path,
				data: JSON.stringify({groupId: ${groupId}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: answers, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink }),
				dataType: "json",
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					if("success" == data["response"]) {
						window.location = data['redirect'];
					}
					else {
						document.getElementById('responseLabel').className = "text-alert";
						document.getElementById('responseLabel').innerHTML = data["message"];
					}
				}
			});
		}
	</script>
</div>