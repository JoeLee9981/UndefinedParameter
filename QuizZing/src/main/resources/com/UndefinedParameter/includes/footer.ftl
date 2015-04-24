<link href="/assets/css/footer.css" rel="stylesheet">	
<div style="height:200px;">
</div>
<div id="footer" class="noMargin noPadding">
	<div id="footer-content" class="page-content metro">
		<div id="bottom-links" class="grid fluid">
			<nav class="horizontal-menu compact">
			    <ul>
			        <li><a href="/about" class="">About</a></li>
			        <#if user??>
			        <#else>
				        <li><a href="/register" class="">Register</a></li>
				        <li><a href="/login" class="">Login</a></li>
			        </#if>
			        <li><a href="/orgs">Organizations</a></li>
			        <li><a href="/group/top" class="">Groups</a></li>
			        <!--<li class="place-right todo"><a href="#" class="place-right"><i class="icon-google-plus"></i></a></li>			        
			        <li class="place-right todo"><a href="#" class="place-right"><i class="icon-twitter"></i></a></li>
			        <li class="place-right todo"><a href="#" class="place-right"><i class="icon-facebook"></i></a></li>-->
			    </ul>
			</nav>

		</div>
	</div>
</div>

<script src="/assets/scripts/main.js"></script>