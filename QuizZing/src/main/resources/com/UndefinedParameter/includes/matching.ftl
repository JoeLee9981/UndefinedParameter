<div class="span9" id="matching-div">
	<form id="create-question-form">
		<div class="row noMargin">
		    <div class="span12">
		    	<div>
					<h4>Tags For This Question</h4>
				</div>
				<div id="categoryTags">
				</div>
				<div>
					<div class="input-control text">
					    <input id="categories" type="text" value="" placeholder="Comma separated categories"/>
					</div>
				</div>	
			</div>
	    </div>
		<h5>Question <a href="#" data-hint="Question|The text of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-1" maxlength="395" placeholder="Question A"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText1" maxlength="95" placeholder="Match A"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-2" maxlength="395" placeholder="Question B"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText2" maxlength="95" placeholder="Match B"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-3" maxlength="395" placeholder="Question C"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText3" maxlength="95" placeholder="Match C"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-4" maxlength="395" placeholder="Question D"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText4" maxlength="95" placeholder="Match D"/>
			</div>
		</div>
		<div class="row">
			<div class="input-control textarea">
			    <textarea id="match-descriptionText-5" maxlength="395" placeholder="Question E"></textarea>
			</div>
			<div class="input-control text size9">
			    <input type="text" id="matchText5" maxlength="95"  placeholder="Match E"/>
			</div>
		</div>

		<label><h5>Explanation of Answer <a href="#" data-hint="Explanation|A bit of text that explains, or gives background on the answer of the question" data-hint-position="right" data-hint-mode="2"><i class="icon-help fg-blue"></i></a></h5>
			<div class="input-control textarea">
			    <textarea id="match-explanationText"></textarea>
			</div>
		</label>
	
	

		<div class="span8">
			<input type="button" onclick="addMatching()" value="Submit"></input>
			<p id="match-responseLabel" />
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
	
		function addMatching() {
			
			//TODO Prevalidate these fields
			var maxAnswers = 5;
			var quizId = ${quizId};
			var desc = "";
			var correct;
			var type = "MATCHING";
			var incorrect = [];
			var creatorId = ${user.id};
			var explanation = document.getElementById('match-explanationText').value;
			var path = "/question/create?quizId=" + quizId;
			var correctPos = 0;
			
			//TODO: once ready uncomment this code
			//var reference = document.getElementById('referenceText').value;
			//var hyperlink = document.getElementById('referenceLink').value;
			var reference = "";
			var hyperlink = "";
			
			
			var ordered = false;
			
			document.getElementById('responseLabel').innerHTML = "";

			var count = 1;
			for(var i = 1; i <= maxAnswers; i++) {
				if(($('#matchText' + i).val() && !$('#match-descriptionText-' + i).val()) || 
				   (!$('#matchText' + i).val() && $('#match-descriptionText-' + i).val())) {
					document.getElementById('match-responseLabel').innerHTML = "Both Question and its Match must be entered";
					document.getElementById('match-responseLabel').className = "text-alert";
					return;
				}
				else if($('#matchText' + i).val() && $('#match-descriptionText-' + i).val()) {
					desc += "<" + count + ">" + $('#match-descriptionText-' + i).val() + " ";
					if(i == 1) {
						correct = '<A>' + $('#matchText' + i).val();
					}
					else {
						incorrect.push('<' + String.fromCharCode(64 + count) + '>' + $('#matchText' + i).val());
					}
					count++;
				}
				else {
					incorrect.push("");
				}
			}

			if(count < 3) {
				document.getElementById('match-responseLabel').innerHTML = "Matching must at least have two questions to match";
				document.getElementById('match-responseLabel').className = "text-alert";
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