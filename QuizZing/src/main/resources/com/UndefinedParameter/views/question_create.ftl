
<div class="page-content">
	<h3>Create a Question</h3>
	<div class="grid fluid">
		<div class="row">
			<div class="span3">
				<nav class="sidebar light">
					<ul>
				        <li><a href="#">True or False</a></li>
				        <li class="active"><a href="#">Multiple Choice</a></li>
				        <li><a href="#">Fill In The Blank</a></li>
				        <li><a href="#">Essay</a></li>
				        <li><a href="#">Matching</a></li>
				        <li><a href="#">Short Answer</a></li>
					</ul>
				</nav>
			</div>
			<div class="span9">
				<form id="create-question-form">
					<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
						<div class="input-control textarea">
						    <textarea id="descriptionText"></textarea>
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
								        <input name="question-options" type="radio" id="qCheck1" />
								        <span class="check"></span>
								    </label>
								</div>	
								<div class="input-control text size5">
								    <input type="text" id="qText2"/>
								</div>
								<div class="input-control radio">
								    <label>
								        <input name="question-options" type="radio" id="qCheck2" />
								        <span class="check"></span>
								    </label>
								</div>							
								<div class="input-control text size5">
								    <input type="text" id="qText3"/>
								</div>
								<div class="input-control radio">
								    <label>
								        <input name="question-options" type="radio" id="qCheck3" />
								        <span class="check"></span>
								    </label>
								</div>								
								<div class="input-control text size5">
								    <input type="text" id="qText4"/>
								</div>
								<div class="input-control radio">
								    <label>
								        <input name="question-options" type="radio" id="qCheck4" />
								        <span class="check"></span>
								    </label>
								</div>	
								<div class="input-control text size5">
								    <input type="text" id="qText5"/>
								</div>
								<div class="input-control radio">
								    <label>
								        <input name="question-options" type="radio" id="qCheck5" />
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
									<input type="button" onclick="addQuestion()" value="Submit"></input>
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
			</div>
		</div>
	</div>
</div>

<script>
	var maxAnswers = 5;
	var quizId = ${quizId};
	function addQuestion() {
		
		
		//TODO Prevalidate these fields
		var desc = document.getElementById('descriptionText').value;
		var correct;
		var type = "MULTIPLE_CHOICE";
		var incorrect = [];
		var creatorId = 1;
		var explanation = document.getElementById('explanationText').value;
		var path = "/question/create?quizId=" + quizId;
		
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
			}
			else {
				incorrect.push(document.getElementById('qText' + i).value);
			}
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
		

		 $.ajax({
			type: 'POST',
			url: path,
			data: JSON.stringify({groupId: ${groupId}, questionText: desc, correctAnswer: correct, type: type, wrongAnswers: incorrect, creatorId: creatorId, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink }),
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


