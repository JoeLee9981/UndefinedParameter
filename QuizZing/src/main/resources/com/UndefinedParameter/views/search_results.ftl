<!DOCTYPE html>
<html lang="en">
	<head>
		<title>QuizZing</title>
		<link rel="stylesheet" href="/assets/plugins/metro_ui/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
		<script src="/assets/scripts/jquery-2.1.1.min.js"></script>
		<script src="/assets/scripts/jquery-ui.min.js"></script>
		<script src="/assets/plugins/metro_ui/js/metro-accordion.js"></script>
		<script src="/assets/plugins/metro_ui/min/metro.min.js"></script>
		<link href="/assets/plugins/metro_ui/min/iconFont.min.css" rel="stylesheet">
		<link href="/assets/css/organizations.css" rel="stylesheet">	
		<link href="/assets/css/overrides.css" rel="stylesheet">
		<link rel="shortcut icon" type="image/x-icon" href="/assets/images/qlogo_32.jpg">
	</head>

	<body class="metro">
		<#include "../includes/navigation.ftl">		
			<div class="metro">
				<div class="grid fluid">	
					<div class="page-content">
						<div class="row">
							<div class="span8">
								<div class="row noMargin">
									<div class="input-control text">
									    <input id="inPageSearch" type="text" value="${searchString}" placeholder="Search QuizZing..."/>
									    <button class="btn-search" onclick="inPageSearch();"></button>
									</div>
								</div>
								
								<div class="row" id="resultTabs">
									<button id="quizResultsButton" class="span4 fg-white bg-black" onclick="showResults('quizResults')">
									   	Quiz Results (${quizResults?size})
									</button>
									<button id="orgResultsButton" class="span4 fg-white bg-cyan"  onclick="showResults('orgResults')">
									    Organization Results (${orgResults?size})
									</button>
									<button id="groupResultsButton" class="span4 fg-white bg-cyan"  onclick="showResults('groupResults')">
									    Group Results (${groupResults?size})
									</button>									
							    </div>
								<div id="resultDivs">
									<div class="row" id="quizResults">					
										<#if quizResults?size == 0>
											<strong>No quizzes were found for this search.</strong>
										<#else>
											<strong>${quizResults?size} quiz results found.</strong>
											<div class="row">
												<div class="listview-outlook" data-role="listview">
													<#list quizResults as quiz>
					                                    <a class="list" href="#" onclick="window.location='/quiz?groupId=${quiz.parentGroupId}&quizId=${quiz.quizId}'">
					                                        <div class="list-content">
					                                            <span class="list-title">${quiz.name}</span>
					                                            <span class="list-title questionText" style="display: none">
					                                            	
					                                            </span>
					                                          	<span class="list-remark">
																	${quiz.description}
					                   							</span>
					                   							<span class="list-subtitle questionCategories">
					                                          		<div class="row noMargin">
					                                          			<i class="icon-star on-left"> ${quiz.rating}</i>
					                                          			<i class="icon-clock on-left"> ${quiz.timeMinutes}:${quiz.timeSeconds}</i>
					                                          			${quiz.parentGroupName}
					                                          		</div>
					                                          	</span>
					                                        </div>
					                                    </a>
					                            	</#list>
				                                </div>
				                           	</div>
		                                </#if>
									</div>
									
									<div class="row" id="orgResults" style="display:none;">					
										<#if orgResults?size == 0>
											<strong>No organizations were found for this search.</strong>
										<#else>
											<strong>${orgResults?size} organization results found.</strong>
											<div class="row">
												<div class="listview-outlook" data-role="listview">
													<#list orgResults as org>
					                                    <a class="list" href="#" onclick="window.location='/orgs/org?id=${org.id}'">
					                                        <div class="list-content">
					                                            <span class="list-title">${org.name}</span>
					                                            <span class="list-title questionText" style="display: none">
					                                            	
					                                            </span>
					                                          	<span class="list-remark">
																	${org.description}
					                   							</span>
					                   							<span class="list-subtitle questionCategories">
					                                          		<div class="row noMargin">
					                                          			<i class="icon-user-3 on-left"> ${org.memberCount}</i>
					                                          			<i class="icon-star on-left"> ${org.rating}</i>
					                                          			Type: <strong>${org.type}</strong></i>
					                                          		</div>
					                                          	</span>
					                                        </div>
					                                    </a>
					                            	</#list>
				                                </div>
				                           	</div>
		                                </#if>
									</div>
									
									<div class="row" id="groupResults" style="display:none;">					
										<#if groupResults?size == 0>
											<strong>No groups were found for this search.</strong>
										<#else>
										
											<strong>${groupResults?size} group results found.</strong>
											<div class="row">
												<div class="listview-outlook" data-role="listview">
													<#list groupResults as group>
					                                    <a class="list" href="#" onclick="window.location='/group?groupId=${group.id}'">
					                                        <div class="list-content">
					                                            <span class="list-title">${group.name}</span>
					                                            <span class="list-title questionText" style="display: none">
					                                            	
					                                            </span>
					                                          	<span class="list-remark">
																	${group.description}
					                   							</span>
					                   							<span class="list-subtitle questionCategories">
					                                          		<div class="row noMargin">
					                                          			<i class="icon-user-3 on-left"> ${group.memberCount}</i>
					                                          			
					                                          		</div>
					                                          	</span>
					                                        </div>
					                                    </a>
					                            	</#list>
				                                </div>
				                           	</div>
		                                </#if>
									</div>
									
								</div>
								
								
							</div>
							<div class="span4">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<div style="padding-top: 50px" class="row">
			<#include "../includes/footer.ftl">
		</div>

		<script>
			$("#inPageSearch").keypress(function(e) {
				// Enter button
				if (e.keyCode == 13)
				{
					inPageSearch();
				}
			});
			
			function inPageSearch()
			{
				var keywords = $("#inPageSearch").val();
				window.location='/search?keywords=' + keywords;
			}
			
			function showResults(type)
			{
				// Manage which tab to show as active
				$('#resultTabs').children('button').each(function ()
				{
				    if ($(this).attr('id') == (type + 'Button'))
				    {
				    	$(this).removeClass('bg-cyan').addClass('bg-black');
				    }
				    else
				    {
				    	$(this).removeClass('bg-black').addClass('bg-cyan');
				    }
				});
				
				// Show the correct result div
				$('#resultDivs >div').each(function ()
				{
					if ($(this).attr('id') == type)
				    {
				    	$(this).show();
				    }
				    else
				    {
				    	$(this).hide();
				    }
				});
			}
			
			<#if destination??>
				showResults('orgResults');
			</#if>
		</script>
	</body>
</html>