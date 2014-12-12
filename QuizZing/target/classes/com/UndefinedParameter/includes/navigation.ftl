<link rel="stylesheet" type="text/css" href="/assets/css/navigation.css" />

<div class="metro">
	<nav id="main-nav" class="navigation-bar dark">
	    <nav id="main-nav-content" class="navigation-bar-content page-content">
	    
	    	<a class="element brand" href="/"><i class="icon-home"></i></a>
	    	<span class="element-divider"></span>
	    	<a class="element brand" href="/orgs">Organizations</a>
	    	<span class="element-divider"></span>
	    	<a class="element brand todo" href="#">Stats</a>    	
			<span class="element-divider"></span>
            <div class="element">
                <a class="dropdown-toggle" href="#">
                    Create &nbsp;
                </a>
                <ul class="dropdown-menu dark" data-role="dropdown">
					<li class="menu-title todo">Questions</li>
				    <li><a href="/quiz/create/question">Create a question</a></li>
				    <li class="menu-title todo">Quizzes</li>
				    <li><a href="/quiz/create">Create a quiz</a></li>
				    <li><a href="#" class="todo">Edit a quiz</a></li>
				    <li class="menu-title todo">Group or Organization</li>
				    <li><a class="todo" href="#">Create group</a></li>	
				    <li><a class="todo" href="#">Create organization</a></li>				    
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
	        	
            <a class="element brand place-right todo" href="#">Login</a> 
            <span class="element-divider place-right"></span>
            <a class="element brand place-right todo" href="#">Register</a>   	    
              
	    </nav>
	</nav>
</div>
<div id="nav-buffer">
</div>



<script>	
	ManageNavigationBarPosition();
	
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
</script>