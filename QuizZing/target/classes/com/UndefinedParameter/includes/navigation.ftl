<link rel="stylesheet" type="text/css" href="/css/navigation.css" />
<link rel="stylesheet" type="text/css" href="/plugins/unicorn/unicorn_buttons.css" />



<div id="navigation-container">
	<div id="navigation-contents" class="content-width">
		<div id="nav-main-links">
			<a href="/service/home">
				<img src="/images/quizzinglogo.png" alt="some_text">
			</a>
		</div>
		<div id="nav-search-container">
			<form id="searchform" onsubmit="alert('TODO')">
				<input class="search-box-main" type="text" placeholder="Find what your mind desires...">
				<input class="search-button-main" type="submit" value="Search">
			</form>
		</div>
		<div id="nav-extra-links">
			<ul>
				<li><a href="/service/orgs">Organizations</a></li>
				<li><a href="#">Groups</a></li>
				<li><a href="#">Stats</a></li>
				<li><a href="#">Register</a></li>
				<li><a href="#">Sign In</a></li>
			</ul>
		</div>
	</div>
</div>
<div id="navigation-buffer">
</div>



<script>
	
	ManageNavigationBarPosition();
	
	$(window).resize(function() {
		ManageNavigationBarPosition();
	});
	
	function ManageNavigationBarPosition()
	{
		if ($(window).width() < $("#navigation-contents").width())
		{
			$("#navigation-container").css("position", "static");
			$("#navigation-buffer").hide();
		}
		else
		{
			$("#navigation-container").css("position", "fixed");
			$("#navigation-buffer").show();
		}
	}
</script>



