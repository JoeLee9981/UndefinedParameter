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