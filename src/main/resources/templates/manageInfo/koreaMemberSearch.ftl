<#include "/header.ftl">
<body>
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
						<li class="current"><a href="#none">회원조회</a></li>
					</ul>
				</div>
				<div class="tab-A">
					<ul>
						<li class="first"><a href="/manageInfo">해외회원</a></li>
						<li class="last active"><a href="/manageInfo/korMemberSearch">한국회원</a></li>
					</ul>
				 </div>
				 <div class="mgt-20">
				 	<div class="message-top2" style="margin-bottom: 20px;font-size: 13px;">
						<form class="" name="" action="" id="frm1" method="post">
							<input type="hidden" name="orderby" value=""/>
							<input type="hidden" name="ord" value=""/>
							<div class="user_info">
								<input type="radio" name="type" id="mKey" value="1" style="width: 20px;"><label for="mKey">회원번호</label> 
								<input type="radio" name="type" id="mName" value="2" checked="checked" style="width: 20px;"><label for="mName">이름</label>&nbsp;
								<input type="text" class="text" name="searchWord" />&nbsp;&nbsp;
								<span>생년월일</span><input type="text" class="datePicker_yymm" name="birthDay" readonly="readonly" />
								<span>상태</span>
								<select name="state">
									<option value="all">전체</option>
									<option value="1">유지</option>
									<option value="2">해지</option>
								</select>
								<span class="button"><input size="8px" type="button" name="" id="searchBtn" value="검색"></span>
								<br/><br/>
								<p>회원번호, 이름, 생년월일 : <font color="red">필수</font>항목</p>
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
