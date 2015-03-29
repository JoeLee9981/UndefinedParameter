<div class="span9" id="multichoice-div">
	<form id="create-question-form">
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="descriptionText"></textarea>
			</div>
		<div class="row noMargin">
		    <div class="span12">
		    	<div>
					<h4>Categories for this quiz</h4>
				</div>
				<div>
					<div class="input-control text">
					    <input id="categories" type="text" value="" placeholder="Comma separated categories"/>
					</div>
				</div>	
			</div>
	    </div>
		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="explanationText"></textarea>
			</div>
		</label>
		
		<div class="row">	
			<div class="span8">
				<label id="mc-answer-options"><h5>Answer Options <a href="#" data-hint="Answer Options|These are the possible answers to choose from, use the radio to signify the correct answer" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
					<div class="input-control text size5">
					    <input type="text" id="qText1"/>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="qCheck1"/>
					        <span class="check"></span>
					    </label>
					</div>	
					<div class="input-control text size5">
					    <input type="text" id="qText2"/>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="qCheck2"/>
					        <span class="check"></span>
					    </label>
					</div>							
					<div class="input-control text size5">
					    <input type="text" id="qText3"/>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="qCheck3"/>
					        <span class="check"></span>
					    </label>
					</div>								
					<div class="input-control text size5">
					    <input type="text" id="qText4"/>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="qCheck4"/>
					        <span class="check"></span>
					    </label>
					</div>	
					<div class="input-control text size5">
					    <input type="text" id="qText5"/>
					</div>
					<div class="input-control radio">
					    <label>
					        <input name="question-options" type="radio" id="qCheck5"/>
					        <span class="check"></span>
					    </label>
					</div>
					<div class="input-control checkbox">
						<label>
							<input type="checkbox" id="randomize"/>
							<span class="check"></span>
							Lock Answer Positions
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
						<input type="button" onclick="addMultipleChoice()" value="Submit"></input>
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
	
		function addMultipleChoice() {
			
			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var quizId = ${quizId};
			var desc = document.getElementById('descriptionText').value;
			var correct;
			var type = "MULTIPLE_CHOICE";
			var incorrect = [];
			var creatorId = ${user.id};
			var explanation = document.getElementById('explanationText').value;
			var path = "/question/create?quizId=" + quizId;
			var correctPos = 0;
			
			//TODO: once ready uncomment this code
			//var reference = document.getElementById('referenceText').value;
			//var hyperlink = document.getElementById('referenceLink').value;
			var reference = "";
			var hyperlink = "";
			
			var ordered = document.getElementById('randomize').checked;
			
			document.getElementById('responseLabel').innerHTML = "";
			
			for(var i = 1; i <= maxAnswers; i++) {
				if(document.getElementById('qCheck' + i).checked) {
					correct = document.getElementById('qText' + i).value;
					correctPos = i - 1;
				}
				else {
					incorrect.push(document.getElementById('qText' + i).value);
				}
			}
			
			if(!desc) {
				document.getElementById('responseLabel').innerHTML = "The Question must be filled out";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}
			
			if(!correct) {
				document.getElementById('responseLabel').innerHTML = "You must check a correct answer";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}
			
			
			
			if(hyperlink && !reference) {
				document.getElementById('responseLabel').innerHTML = "Reference must be filled out in conjunction to the hyperlink";
				document.getElementById('responseLabel').className = "text-alert";
				return;
			}

			var categories = $('#categories').val().split(',');
			for(var i = 0; i < categories.lenght; i++) {
				categories[i] = categories[i].trim().substring(1);
			}
				
			
			 $.ajax({
				type: 'POST',
				url: path,
				data: JSON.stringify({groupId: ${groupId}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, correctPosition: correctPos, categories: categories }),
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