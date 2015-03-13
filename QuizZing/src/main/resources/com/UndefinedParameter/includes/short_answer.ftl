<div class="span9" id="shortanswer-div" hidden>
	<form id="create-question-form">
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="sa-descriptionText"></textarea>
			</div>

		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="sa-explanationText"></textarea>
			</div>
		</label>
		
		<label id="sa-answer-options"><h5>Answer <a href="#" data-hint="Answer|This is the answer, note the user must provide it exactly, so keep it short" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="sa-answerText"></textarea>
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
		</label>
		<div>
			<input type="button" onclick="addShortAnswer()" value="Submit"></input>
			<p id="sa-responseLabel" />
		</div>
	</form>
	
	<script>
		
	
		function addShortAnswer() {
			
			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var quizId = ${quizId};
			var desc = document.getElementById('sa-descriptionText').value;
			var correct = document.getElementById('sa-answerText').value;
			var type = "SHORT_ANSWER";
			var incorrect = ["", "", "", ""];
			var creatorId = ${user.id};
			var explanation = document.getElementById('sa-explanationText').value;
			var path = "/question/create?quizId=" + quizId;
			
			//TODO: once ready uncomment this code
			//var reference = document.getElementById('referenceText').value;
			//var hyperlink = document.getElementById('referenceLink').value;
			var reference = "";
			var hyperlink = "";
			
			var ordered = true;
			
			document.getElementById('sa-responseLabel').innerHTML = "";

			if(!desc) {
				document.getElementById('sa-responseLabel').innerHTML = "You must enter a question";
				document.getElementById('sa-responseLabel').className = "text-alert";
				return;
			}
			
			if(!correct) {
				document.getElementById('sa-responseLabel').innerHTML = "You must enter an answer";
				document.getElementById('sa-responseLabel').className = "text-alert";
				return;
			}
			
			if(hyperlink && !reference) {
				document.getElementById('sa-responseLabel').innerHTML = "Reference must be filled out in conjunction to the hyperlink";
				document.getElementById('sa-responseLabel').className = "text-alert";
				return;
			}
				
			 $.ajax({
				type: 'POST',
				url: path,
				data: JSON.stringify({groupId: ${groupId}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink}),
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