<div class="span9" id="truefalse-div">
	<form id="create-question-form">
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="tf-descriptionText"><#if question.questionText??>${question.questionText}</#if></textarea>
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
			    <textarea id="tf-explanationText"><#if question.explanation??>${question.explanation}</#if></textarea>
			</div>
		</label>
		
		<div class="row">	
			<div class="span8">
				<label id="mc-answer-options"><h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
					<div class="input-control text size5">
					    <h3>True</h3>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="trueCheck" <#if question.correctAnswer == "True">checked</#if>/>
					        <span class="check"></span>
					    </label>
					</div>	
					<div class="input-control text size5">
					    <h3>False</h3>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="falseCheck" <#if question.correctAnswer == "False">checked</#if>/>
					        <span class="check"></span>
					    </label>
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
						<input type="button" onclick="addTrueFalse()" value="Submit"></input>
						<p id="responseLabel" />
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
	
		function addTrueFalse() {

			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var desc = document.getElementById('tf-descriptionText').value;
			var correct;
			var incorrect = [];
			var correctPos = 0;
			var explanation = document.getElementById('tf-explanationText').value;
			var path = "/question/edit?groupId=" + ${groupId};
			
			//TODO: once ready uncomment this code
			//var reference = document.getElementById('referenceText').value;
			//var hyperlink = document.getElementById('referenceLink').value;
			var reference = "";
			var hyperlink = "";
			
			var ordered = true;
			
			document.getElementById('responseLabel').innerHTML = "";
			
			if($('#trueCheck').is(':checked')) {
				correct = "True";
				incorrect.push("False");
				correctPos = 0;
			}
			else if($('#falseCheck').is(':checked')) {
				correct = "False";
				incorrect.push("True");
				correctPos = 1;
			}
			else {
				document.getElementById('responseLabel').innerHTML = "You must check a correct answer";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}
			
			incorrect.push("");
			incorrect.push("");
			incorrect.push("");
			incorrect.push("");
			
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