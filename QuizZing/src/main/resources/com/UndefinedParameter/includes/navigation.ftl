<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
<script src="/assets/scripts/jquery-ui.min.js"></script>
<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
<link href="/assets/css/overrides.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/assets/css/navigation.css" />

<div class="metro">
	<nav id="main-nav" class="navigation-bar dark">
	    <nav id="main-nav-content" class="navigation-bar-content page-content">
	    
	    	<a id="home-link" class="element brand" href="/"><i class="icon-home"></i></a>
	    	<span class="element-divider"></span>
	    	<a class="element brand" href="/orgs">Organizations</a>	
			<span class="element-divider"></span>
            <a class="element brand" href="/quiz/categories">Categories</a>	
			<span class="element-divider"></span>
			<div class="element"  id="">
	            <a class="dropdown-toggle" href="#">
	                Feedback &nbsp;
	            </a>
	            <ul class="dropdown-menu" data-role="dropdown">
	            	<li><a href="/feedback">Leave Feedback</a></li>
	                <li><a href="/feedback/bug">Report a Bug</a></li>
	            </ul>
	      	</div>


	        <div class="element input-element">
                <div class="input-control text searchbox">
                    <input id="searchKeywords" type="text" placeholder="Find what your mind desires..." class="size4">
                    <button class="btn-search" onclick="search();"></button>
                </div>
	        </div>
	        <#if user??>
			  <div class="element place-right">
		            <a class="dropdown-toggle" href="#">
		                <span class="icon-cog"></span> &nbsp;
		            </a>
		            <ul class="dropdown-menu place-right" data-role="dropdown">
		                <li><a href="/user?userid=${user.id}">Account Profile</a></li>
		                <li><a href="#" onclick="logoutUser()">Logout</a></li>
		            </ul>
		        </div>
		        <div class="element place-right">
			        <a href="/user?userid=${user.id}&tab=MESSAGES">
			        	<span id="mailIcon" class="icon-mail fg-white"></span>
		            </a>
	            </div>
		        <button id="userAndImageBox" class="element image-button image-left place-right">
			        <a href="/user?userid=${user.id}">
			        	<#if user.userName?length &gt; 14>
			        		${user.userName?substring(0, 14)}...
			        	<#else>
			        		${user.userName?html}
			        	</#if>      
			            <img src=""/>
		            </a>
		        </button>
            <#else>
	            <a class="element brand place-right" href="/login">Login</a> 
	            <span class="element-divider place-right"></span>
	            <a class="element brand place-right" href="/register">Register</a>              
            </#if>
              
	    </nav>
	</nav>
</div>
<div id="nav-buffer">
</div>



<script>	
	ManageNavigationBarPosition();
	SetCategoryDropdownLocation();
	$(window).resize(function() {
		ManageNavigationBarPosition();
	});
	
	function ManageNavigationBarPosition()
	{
		if ($(window).width() < $("#main-nav-content").width())
		{
			$("#main-nav").removeClass("fixed-top");
			$("#nav-buffer").hide();
		}
		else
		{
			$("#main-nav").addClass("fixed-top");
			$("#nav-buffer").show();
		}
	}
	
	function SetCategoryDropdownLocation()
	{
		//var leftPosition = $("#home-link").position().left;
		//var categoryPosition = $("#category-dropdown").position().left;
		//$("#category-dropdown-container").css("left","-" + (categoryPosition - leftPosition) + "px");
	}
	
	function search()
	{
		var keywords = $("#searchKeywords").val();
		window.location='/search?keywords=' + keywords;
	}
	
	$("#searchKeywords").keypress(function(e) {
		// Enter button
		if (e.keyCode == 13)
		{
			search();
		}
	});

</script>