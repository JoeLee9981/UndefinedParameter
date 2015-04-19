
<style>
	.ui-autocomplete
	{
		border-style: solid;
		border-width: 1px;
		width: 300px;
		font-size: 15px;
		background-color: white;
	}

	.ui-menu-item
	{
		list-style-type: none;
		
	}

	.ui-helper-hidden-accessible { display:none; }
</style>	

<div class="row noMargin">
    <div class="span12">
    	<div class="row noMargin">
	    	<div class="span4 noMargin">
		    	<div class="row noMargin quizQuestionList" style="width:318px">
					<div class="quizQuestionListContent">
						<div id="catList" class="listview">
							<#if categories??>
							<#list categories as category>
							<a id="${category_index}Button" class="list" href="#" style="margin-bottom: 1px;" onclick="toggleCategory(${category_index}, '${category}')">
								<div class="list-content noMargin">
									<span class="list-title">${category}</span>
								</div>
							</a>
							<script>$('#${category_index}Button').click(function(event) { event.preventDefault(); });</script>
							</#list>
							<#else>
							<a class="list marked" href="#">
								<div class="list-content">   
									<span class="list-title">No tags for this group</span>
								</div>
							</a>
							</#if>
		                               
						</div>
					</div>
				</div>
			</div>
			<div class="span8">
				<div id="filterDiv" class="row noMargin quizQuestionList">

				</div>
			</div>
		</div>
	</div>
</div>

<script>

	var categories = [];
	var quality = 3;
	var difficulty = 3;
	var inflight = false;
	
	function toggleCategory(index, cat) {

		if(inflight) {
			return;
		}
		if($.inArray(cat, categories) == -1) {
			categories.push(cat);
			$('#' + index + 'Button').addClass("selected");
		}
		else {
			removeCategory(cat)
			$('#' + index + 'Button').removeClass("selected");
		}

		filter();
	}
	
	function removeCategory(cat) {
		var temp = [];
		for(var j = 0; j < categories.length; j++) {
			if(cat != categories[j]) {
				temp.push(categories[j]);
			}
		}
		categories = temp;

		filter();
	}

	function filter() {

		inflight = true;
		if(categories.length == '0') {
			$('#filterDiv').html("<h3>No Categories Selected</h3>");
			inflight = false;
			return;
		}
		
		$.ajax({
		    url: '/quiz/top_categories',
		    type: 'POST',
		    data: JSON.stringify(categories),
			headers: {
				"Content-Type": "application/json"
			},
		    success: function(data) {
		    	console.log(data);
				$('#filterDiv').html(data);
				inflight = false;
		    },
		    error: function(error) {
		    	$('#filterDiv').html("<h3>No Quizzes Found</h3>");
		    	inflight = false;
		    }
		});
	}
		
</script>
