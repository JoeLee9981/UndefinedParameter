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