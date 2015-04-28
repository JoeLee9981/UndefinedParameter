<div class="span9" id="fillblank-div">
	<form id="create-question-form">
		<label></label><h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="fb-descriptionText"><#if question.questionText??>${question.questionTextBlankFormatted}</#if></textarea>

			</div>
		</label>
		<button id="blankButton" class="success">Add a blank</button>
		<div class="row noMargin">
		    <div class="span12">
		    	<div>
					<h4>Tags For This Question</h4>
				</div>
				<div id="categoryTags">
					<#if question.categories??>
					<#list question.categories as category>
					<button id="catButton${category_index}" class="default" style="margin: 5px"><i onclick="removeCategory(${category_index})" class="icon-cancel"></i>  #${category}</button>
					</#list>
					</#if>
				</div>
				<div>
					<div class="input-control text">
					    <input id="categories" type="text" placeholder="Comma separated categories"/>
					</div>
				</div>	
			</div>
	    </div>
		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="fb-explanationText"><#if question.explanationFormatted??>${question.explanationFormatted}</#if></textarea>
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

		//set up auto complete for categories
		$(function() {
	
			var allCategories = [];
			<#if allCategories??>
			<#list allCategories as category>
			allCategories.push('${category}');
			</#list>
			</#if>
	
			$('#categories').autocomplete({
				source: allCategories
			});
		});
	
		var categories = [];
		<#if question.categories??>
		<#list question.categories as category>
		categories.push('${category}');
	
		$('#catButton${category_index}').click(function(event) {
			event.preventDefault();
		});
		</#list>
		</#if>
		
		function setCategoryButtons() {
			var html = "";
			for(var i = 0; i < categories.length; i++) {
				var cat = categories[i];
				if(cat[0] != '#') {
					cat = '#' + cat;
				}
				html += '<button id="catButton' + i + '" class="default" style="margin: 5px"><i onclick="removeCategory(' + i + ')" class="icon-cancel"></i>  ' + cat + '</button>';
			}
			$('#categoryTags').html(html);
			
			for(var i = 0; i < categories.length; i++) {
				$('#catButton' + i).click(function(event) {
					event.preventDefault();
				});
				
			}
		}
		
		function removeCategory(index) {
			var temp = [];
			for(var j = 0; j < categories.length; j++) {
				if(index != j) {
					temp.push(categories[j]);
				}
			}
			categories = temp;
			setCategoryButtons();
		}
		
		$('#categories').keydown(function(event) {
			if(event.which == 188) {
				var cat = $('#categories').val().trim();
				if(cat == ""  || cat == "#") {
					event.preventDefault();
					$('#categories').val("");
					return;
				}
				if($.inArray(cat, categories) == -1)
					categories.push(cat);
				setCategoryButtons();
				$('#categories').val("");
				event.preventDefault();
			}
			
		});
	
		$('#categories').blur(function() {
	
			var cat = $('#categories').val().trim();
			
			if(cat == "" || cat == "#") {
				$('#categories').val("");
				return;
			}
			if($.inArray(cat, categories) == -1)
				categories.push(cat.substring(0));
			setCategoryButtons();
			$('#categories').val("");
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

			 $.ajax({
				type: 'PUT',
				url: path,
				data: JSON.stringify({questionId: ${question.questionId}, questionText: desc, correctAnswer: correct, wrongAnswers: answers, explanation: explanation, ordered: ordered, reference: reference, referenceLink: hyperlink, categories: categories }),
				headers: {
					"Content-Type": "application/json"
				},
				success: function(data) {
					<#if goBackToQuizEdit>
						window.location = "/quiz/edit?groupId=${groupId}&quizId=${quizId}";
					</#if>
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