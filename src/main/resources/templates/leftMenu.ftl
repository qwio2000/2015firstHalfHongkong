			<#if leftMenuList?has_content>
					<#assign depth = 1 deptemp = 0>
					<#assign menuCodeCnt = menuCode?length>
				<div class="snb">
					<h3>${leftMenuList[0].mMenuName}</h3>
					<#if ((leftMenuList?size)-1 > 0)>
						<ul>
						<#list 1..(leftMenuList?size-1) as i>
								<#if (menuCodeCnt == 7 && (leftMenuList[i].mMenuCode == menuTwoCode)) >
									<li class="active">
								<#elseif (menuCodeCnt == 5 && (leftMenuList[i].mMenuCode == menuTwoCode)) >	
									<li class="active">
								<#elseif (menuCodeCnt == 3 && (leftMenuList[i].mMenuCode == menuCode)) >	
									<li class="active">
								<#else>
									<li>
								</#if>
									<#if ((menuCodeCnt == 7) && (leftMenuList[i].mMenuCode == menuThreeCode)) || leftMenuList[i].mMenuCode == menuCode>
										<a class="on" href="${leftMenuList[i].mMenuLink}">
									<#else>
										<a href="${leftMenuList[i].mMenuLink}">
									</#if>
								<#if (leftMenuList[i].mDepth == 4)>
								-
								<#elseif (leftMenuList[i].mDepth >= 5)>
								*
								</#if>
								${leftMenuList[i].mMenuName}</a>
								<#if leftMenuList[i].mHasChildren == "1">
									<ul>
									<#assign depth = depth+1>
								<#else>
									<#if (depth > 1)>
										<#if (i+1 < leftMenuList?size) >
											<#assign deptemp = leftMenuList[i].mDepth-leftMenuList[i+1].mDepth>
											<#if (deptemp == 0) >
												</li>
											<#else>
												<#list 1..deptemp as j>
													</li></ul>
													<#assign depth = depth-1>
												</#list>
											</#if>
											</li>
										<#else>
											<#list 1..(depth-1) as k >	
												</li></ul>
											</#list>
											</li>
										</#if>
									<#else>
										</li>
									</#if>
								</#if>								
						</#list>
							</ul>
					</#if>
				</div>
				</#if>