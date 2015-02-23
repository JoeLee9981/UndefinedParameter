<link rel="stylesheet" type="text/css" href="/assets/css/navigation.css" />

<div class="metro">
	<nav id="main-nav" class="navigation-bar light">
	    <nav id="main-nav-content" class="navigation-bar-content page-content">
	    
	    	<a id="home-link" class="element brand" href="/"><i class="icon-home"></i></a>
	    	<span class="element-divider"></span>
	    	<a class="element brand" href="/orgs">Organizations</a>	
			<span class="element-divider"></span>
            <div class="element"  id="category-dropdown">
	            <a class="dropdown-toggle" href="#">
	                Categories &nbsp;
	            </a>
	            <ul class="dropdown-menu page-content" id="category-dropdown-container" data-role="dropdown">
					<div style="height:300px;width:100%;padding:100px;">
						<p> Categories are coming soon! </p>
					</div>			    
	            </ul>          
            </div>

	        <div class="element input-element">
	            <form id="search">
	                <div class="input-control text searchbox">
	                    <input type="text" placeholder="Find what your mind desires..." class="size6">
	                    <button class="btn-search todo"></button>
	                </div>
	            </form>
	        </div>
	        <#if user??>
			  <div class="element place-right">
		            <a class="dropdown-toggle" href="#">
		                <span class="icon-cog"></span> &nbsp;
		            </a>
		            <ul class="dropdown-menu place-right" data-role="dropdown">
		            	<li><a href="#" class="todo">My Groups</a></li>
		                <li><a href="#" class="todo">My Quizzes</a></li>
		                <li><a href="/user?userid=${user.id}">Account Settings</a></li>
		                <li><a href="#" onclick="logoutUser()">Logout</a></li>
		            </ul>
		        </div>
		        <button id="userAndImageBox" class="element image-button image-left place-right">
			        <a href="/user?userid=${user.id}">
			        	<#if user.userName?length &gt; 14>
			        		${user.userName?substring(0, 13)}...
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
		var leftPosition = $("#home-link").position().left;
		var categoryPosition = $("#category-dropdown").position().left;
		$("#category-dropdown-container").css("left","-" + (categoryPosition - leftPosition) + "px");
	}
</script>