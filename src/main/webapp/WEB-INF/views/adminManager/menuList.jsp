<%@page import="com.jeiglobal.hk.domain.common.GlobalMenu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" charset="${_response_encoding}">
		$(function() {
			$("#tree").treeview({
				collapsed: true,
				animated: "medium",
				control:"#sidetreecontrol",
				persist: "location"
			});
		})
</script>

<div id="sidetree">
	<div class="treeheader">&nbsp;</div>
	<div id="sidetreecontrol">
		<a href="?#">Collapse All</a> | <a href="?#">Expand All</a>
	</div>
		<% 	List<GlobalMenu> menuList = (List<GlobalMenu>)request.getAttribute("menuList");
			int cnt = menuList.size();
			int depth = 1;
			int deptemp = 0;
		%>
		<ul id='tree'>ROOT&nbsp;
			<a onclick='$.menu_add(1);' style='cursor:pointer'><img src='/public/img/menu/icons_insert.gif'></a>
			&nbsp;
			<% if(cnt > 1){ %>
			<a onclick='$.menu_change(1);' style='cursor:pointer'><img src='/public/img/menu/icons_sort.gif'></a>
			<%} %>
			<ul>
			<%	
				for(int i = 1; i < cnt; i++){
			%>
					<li><span>
				<%
					if("0".equals(menuList.get(i).getmUseState())){
				%>
						<del><%=menuList.get(i).getmMenuName()%></del>			
				<%
					}else{
				%>
						<%=menuList.get(i).getmMenuName()%>
				<%	}	%>
					</span>
					&nbsp;<a onclick='$.menu_modify("<%=menuList.get(i).getmIdx()%>");' style='cursor:pointer'><img src='/public/img/menu/icons_modify.gif'></a>
					&nbsp;<a onclick='$.menu_add("<%=menuList.get(i).getmIdx()%>");' style='cursor:pointer'><img src='/public/img/menu/icons_insert.gif'></a>
					&nbsp;<a onclick='$.menu_delete("<%=menuList.get(i).getmIdx()%>");' style='cursor:pointer'><img src='/public/img/menu/icons_delete.gif'></a>
			<%
					if("1".equals(menuList.get(i).getmHasChildren())){ %>
						&nbsp;<a onclick='$.menu_change("<%=menuList.get(i).getmIdx()%>");' style='cursor:pointer'><img src='/public/img/menu/icons_sort.gif'></a>
						<ul>
			<%		
						depth = depth+1;
					}else{
						if (depth > 1) {
							if (i+1 < cnt) {
								deptemp = menuList.get(i).getmDepth()-menuList.get(i + 1).getmDepth();
								if (deptemp == 0) {  %>
									</li>
							<%	}else{
									for (int j = 0; j < deptemp; j++) { %>
										</li></ul>
							<%			depth = depth-1;
									}
								}
							}else{
								for (int k = 0; k < depth-1; k++) { %>
									</li></ul>
							<%	} %>
								</li>
							<%
							}
						}else { %>
							</li>
					<%	}
					}
				} %>
				</ul>
</div>
