<link rel="stylesheet" type="text/css" href="../css/navigation.css" />

<div id="navigation-container">
	<div id="navigation-contents" class="content-width">
		<div id="nav-main-links">
			<a href="#">
				<img src="../images/quizzinglogo.png" alt="some_text">
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
				<li><a href=/service/orgs>Organization</a></li>
				<li>Statistics</li>
				<li>Sign In</li>
				<li>Register</li>
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