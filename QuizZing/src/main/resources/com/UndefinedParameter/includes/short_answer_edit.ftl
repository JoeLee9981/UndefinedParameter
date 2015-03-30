<div class="span9" id="shortanswer-div">
	<form id="create-question-form">
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="sa-descriptionText">${question.questionText}</textarea>
			</div>
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
			    <textarea id="sa-explanationText"><#if question.explanationFormatted??>${question.explanationFormatted}</#if></textarea>
			</div>
		</label>
		
		<label id="sa-answer-options"><h5>Answer <a href="#" data-hint="Answer|This is the answer, note the user must provide it exactly, so keep it short" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="sa-answerText">${question.correctAnswer}</textarea>
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
	
		function addShortAnswer() {
			
			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var desc = document.getElementById('sa-descriptionText').value;
			var correct = document.getElementById('sa-answerText').value;
			var incorrect = ["", "", "", ""];
			var explanation = document.getElementById('sa-explanationText').value;
			var path = "/question/edit?questionId=" + ${question.questionId} + "&groupId=" + ${groupId};
			
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

			var categories = $('#categories').val().split(',');
			for(var i = 0; i < categories.length; i++) {
				categories[i] = categories[i].trim().substring(1);
			}
				
			 $.ajax({
				type: 'PUT',
				url: path,
				data: JSON.stringify({questionId: ${question.questionId}, questionText: desc, correctAnswer: correct, wrongAnswers: incorrect, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, categories: categories}),
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