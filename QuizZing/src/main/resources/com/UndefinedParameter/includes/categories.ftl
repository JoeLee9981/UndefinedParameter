<style>
	ul
	{
		border-style: solid;
		border-width: 1px;
		width: 300px;
		font-size: 15px;
	}

	li
	{
		list-style-type: none;
		
	}

	.ui-helper-hidden-accessible { display:none; }
</style>

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

<script>
	//set up auto complete for categories
	$(function() {
	
		var allCategories = [];
		<#if categories??>
		<#list categories as category>
		allCategories.push('${category}');
		</#list>
		</#if>
	
		$('#categories').autocomplete({
			source: allCategories,
			messages: {
		        noResults: '',
		        results: function() {}
		    },
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
		if(event.which == 188 || event.which == 13) {
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
	
</script>