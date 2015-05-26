<%@page import="com.jeiglobal.hk.domain.common.GlobalMenu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<style type="text/css">
		.demo ul { list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px; }
		.demo li { margin: 5px; padding: 5px; width: 150px; }
	</style>

	<script type="text/javascript">
	$(function() {
		$( "#sortable" ).sortable({
			revert: true
		});
		$( "ul, li" ).disableSelection();


		$("#sortsave").on('click',function() {
			var lan = $("#sortable").sortable('toArray');
			var url = "/adminManager/menuChange";
			$.ajax({
				url:url,
				type:"POST",
				data: "lan="+lan+"&_method=PUT",
				cache: false,
				async: true,
				dataType: "text",
				success: function(msg, textStatus, XMLHttpRequest) {
					alert(msg);
					$.menu_list_load();			
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
			
		});
	});
	</script>


<div class="demo">

<ul id="sortable">
	<c:forEach items="${menuList}" var="list">
		<li class="ui-state-default" id="${list.mIdx}">${list.mMenuName}</li>
	</c:forEach>
</ul>
</div>
<input id="sortsave" type="button" value="순서변경저장하기">
