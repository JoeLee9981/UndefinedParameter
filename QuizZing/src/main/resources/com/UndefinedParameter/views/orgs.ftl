<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/organizations.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">		
		
		<div class="page-content">
			<div class="grid fluid">
				<div class="row">
					<nav class="breadcrumbs">
                        <ul>
                            <li><a href="/"><i class="icon-home"></i></a></li>
                            <li class="active"><a>Organizations</a></li>
                        </ul>
                    </nav>			
				</div>
						
				<div class="row">
					<div class="span8">
						<div class="row noMargin">
							<div class="">




										<#list organizationTypes as orgType>
											<tr>
												<td></td>
												<td class="right">${orgType.typeName},</td>
											</tr>
										</#list>






							</div>
						</div>
					</div>
					<div class="span4">
						<div class="row noMargin containerFill">
							<div>
								<strong>Create A New Organization</strong>
							</div>
							<div>
								Not finding the organization you are looking for?  Create an organization that focuses
								on what you are interested in.
							</div>
							<div>
								<div class="place-right marginTop">
									<button class="success">Create Organization</button>
								</div>
							</div>
						</div>
						<div class="row">
							<h5>Largest</h5>
							<div class="container">
								<table class="table hovered">
									<tbody>
										<#list largestOrganizations as org>
											<tr>
												<td><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
												<td class="right">${org.memberCount}</td>
											</tr>
										</#list>
									</tbody>
								</table>		
							</div>
						</div>
						<div class="row noMargin">
							<h5>New</h5>
							<div class="container">	
								<table class="table hovered">
									<tbody>
										<#list newestOrganizations as org>
											<tr>
												<td><a href="/orgs/org?id=${org.id}">${org.name}</a></td>
												<td class="right">${org.dateAsString}</td>
											</tr>
										</#list>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		
		<#include "../includes/footer.ftl">

		<script>
			
			function register(orgId) {
				$.ajax({
				    url: '/orgs/register?orgId=' + orgId,
				    type: 'POST',
				    success: function(data) {
				    	console.log(data);
				    	window.location='/orgs/org?id=' + orgId;
				    },
				    error: function(error) {
				    	displayError("Unable to register for organization");
				    }
				});
			}
			
			function leave(orgId) {
				$.ajax({
				    url: '/orgs/leave?orgId=' + orgId,
				    type: 'DELETE',
				    success: function(data) {
				    	console.log(data);
				    	window.location='/orgs';
				    },
				    error: function(error) {
				    	displayError("There was an error when attempting to leave organization");
				    }
				});
			}
			
			function displayError(message) {
				alert(message);
			}
			
		</script>
	</body>
</html>