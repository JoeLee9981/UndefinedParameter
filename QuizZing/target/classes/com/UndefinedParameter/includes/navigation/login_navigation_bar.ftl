<div id="login-nav" class="full-width">
	<div id="login-nav-content" class="content-width center full-height">
		<div>
			<ul id="home-nav">
				<li><a href="/service/home">QuizZing</a></li>
				<li>Random</li>
				<li>Stats</li>
			</ul>
		</div>
		<div id="login-nav-form" class="full-height go-right">
			<form class="vertical-middle">
				<table id="nav-login-table">
					<tr>
						<td><input type="text" name="email" placeholder="Email"></td>
						<td><input type="password" name="password" placeholder="Password"></td>
						<td><button type="submit">Login</button></td>
					</tr>
					<tr>
						<td><label><input type="checkbox" name="staySignedIn">Stay signed in?</label></td>
						<td>Forgot your password?</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<div id="login-nav-buffer">
</div>

<script>
	
	var loginNavStartingWidth = $("#login-nav-content").width()
	var minWidth = $("#home-nav").width() + $("#login-nav-form").width();
	function ManageLoginNavigationSize()
	{			
		var windowSize = $(window).width();
		if (windowSize < minWidth)
		{
			$("#login-nav-content").width(minWidth);
		}
		else if (windowSize > loginNavStartingWidth)
		{
			$("#login-nav-content").width(loginNavStartingWidth);
		}
		else
		{
			$("#login-nav-content").width(windowSize);
		}
	}
	
	ManageLoginNavigationSize();
	
	$(window).resize(function() {
		ManageLoginNavigationSize();
	});
</script>