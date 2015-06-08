<#include "/header.ftl">
<body>
<script>
	var head = $("head");
	var headlinkLast = head.find("link[rel='stylesheet']:last");
	var linkElement = "<style>.ui-datepicker-calendar {display: none;}</style>";
	if (headlinkLast.length){
		headlinkLast.after(linkElement);
	}
	else {
	   head.append(linkElement);
	}
</script>
<div id="wrapper">
	<#include "/headerMenu.ftl">
	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
				<#include "/leftContents.ftl">
				<#include "/leftMenu.ftl">
			</div>
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="#none">홈</a> &gt; </li>
						<li><a href="#none">회원업무</a> &gt; </li>
						<li><a href="#none">관리정보</a> &gt; </li>
						<li class="current"><a href="#none">학습현황표</a></li>
					</ul>
				</div>
				 <div class="mgt-20">
				 	<div class="message-top2" style="margin-bottom: 20px;font-size: 13px;">
						<form class="" name="" action="/studyState/pop" id="frm1" method="post">
							<div class="user_info">
								<span>교실명</span>
								<select name="empKey" style="vertical-align: middle;width: 200px;">
									<option value="">전체</option>
									<#list classList as class>
									<option value="${class.empKey }">${class.empKey }
										(${class.empName })</option>
									</#list>
								</select> 
								<span>검색년월</span><input type="text" class="datePicker_yymm" name="searchDay" readonly="readonly" />
								<span class="button"><input size="8px" type="button" name="" id="popBtn" value="검색"></span>
							</div>
						</form>
						</div>
						<div id="mainContent">
						</div>
				</div>  
			</div>
		</div>
	</div>
</div>
<!-- //container -->
<#include "/footer.ftl">
</body>
<script id="template" type="text/x-handlebars-template"> 
<p class="text-r">총 : {{korMemberSearchInfoList.length}}건</p>
{{#xIf korMemberSearchInfoList.length ">=" 10}}
<div class="tbl-type-D" style="width: 960px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 943px;">
{{else}}
<div class="tbl-type-D" style="width: 960px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 960px;">
{{/xIf}}
		<colgroup>
			<col width="5%"/>
			<col width="10%"/>
			<col width="5%"/>
			<col width="4%"/>
			<col width="10%"/>
			<col width="5%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="10%"/>
			<col width="30%"/>
			<col width="5%"/>
		</colgroup>
		<tbody>
			<tr>
				<th>순번</th>
				<th>회원번호</th>
				<th>과목</th>
				<th>상태</th>
				<th>회원명</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>최종퇴회일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>입회</th>
			</tr>
		</tbody>
	</table>
	<div style="overflow: auto;width:960px; height:400px;">
		<table style="border: 0px;border-spacing: 0;width: 100%;">
			<colgroup>
				<col width="5%"/>
				<col width="10%"/>
				<col width="5%"/>
				<col width="4%"/>
				<col width="10%"/>
				<col width="5%"/>
				<col width="8%"/>
				<col width="8%"/>
				<col width="10%"/>
				<col width="30%"/>
				<col width="5%"/>
			</colgroup>
			<tbody>
			{{#each korMemberSearchInfoList}}
				<tr>
					<td>{{inc @index}}</td>
					<td>{{hkey}}</td>
					<td>{{hkwamok}}</td>
					<td>{{#xIf hkwastat "==" "1"}}유지{{else}}퇴회{{/xIf}}</td>
					<td>{{hname}}</td>
					<td>{{sexname}}</td>
					<td>{{hsangil}}</td>
					<td>{{mhthymd}}</td>
					<td>{{htel}}</td>
					<td>{{haddr1}}</td>
					<td>입회</td>
				</tr>
			{{else}}
				<tr>
					<td colspan="11">검색된 결과가 없습니다.</td>
				</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</div>
</script>
</html>
