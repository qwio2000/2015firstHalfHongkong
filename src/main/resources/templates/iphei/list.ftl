<#include "/header.ftl">
<body>
	<div id="wrapper">
		<#include "/headerMenu.ftl">
		<!-- container -->
		<div id="container">
			<div id="content">
				<div class="side-content"><#include "/leftContents.ftl">
					<#include "/leftMenu.ftl"></div>
				<div id="primary_content" class="primary-content">
					<div class="page-path">
						<ul>
							<li class="home"><a href="#none">홈</a> &gt;</li>
							<li><a href="#none">회원관리</a> &gt;</li>
							<li><a href="#none">입회</a> &gt;</li>
							<li class="current"><a href="#none">입회목록</a></li>
						</ul>
					</div>
					<div class="tab-A">
					<ul>
						<li class="first active"><a href="/iphei/list">입회목록</a></li>
					</ul>
				 	</div>
					<div class="mgt-20">
						<div class="message-top2" style="margin-bottom: 20px">
						<form class="" name="" action="/iphei/list.xls" id="frm1" method="post">
							<div class="user_info">
								<span>교실명</span> 
								<select name="empKey" style="vertical-align: middle;width: 200px;">
									<option value="">전체</option>
									<#list classList as class>
									<option value="${class.empKey }">${class.empKey }
										(${class.empName })</option>
									</#list>
								</select> 
								<span style="margin-left: 50px;">입회종류</span> 
								<select name="ipheiGubun" style="vertical-align: middle;width: 80px;text-align: center;">
									<option value="">전체</option>
									<#list ipheiKindList as iphei>
									<option value="${iphei.dtlCD }">${iphei.dtlCDNM }</option>
									</#list>
								</select>
								<span style="margin-left: 50px;">과목</span> 
								<select name="kwamok" style="vertical-align: middle;width: 80px;text-align: center;">
									<option value="">전체</option>
									<#list kwamokList as kwamok>
									<option value="${kwamok }">${kwamok }</option>
									</#list>
								</select>
								<br/><br/>
								<select name="type" style="width: 100px;">
									<option value="1">입복회일</option>
									<option value="2">작업일</option>
								</select>
								<input type="text" name="startDate" class="datePicker" readonly="readonly" value="${.now?string("yyyy-MM-dd")}" style="margin-left: 30px;"> ~ 
								<input type="text" name="endDate" class="datePicker" readonly="readonly" value="${.now?string("yyyy-MM-dd")}">
								<span class="button"><input size="8px" type="button" name="" id="search" onclick="javascript:$.getIpheiList();" value="검색"></span>
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
<span class="button"><input size="8px" type="button" name="" id="excel" value="엑셀"></span>
<p class="text-r">총 : {{ipheiList.length}}건</p>
{{#xIf ipheiList.length ">=" 10}}
<div class="tbl-type-D" style="width: 960px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 943px;">
{{else}}
<div class="tbl-type-D" style="width: 960px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 960px;">
{{/xIf}}
		<colgroup>
			<col width="5%"/>
			<col width="8%"/>
			<col width="10%"/>
			<col width="8%"/>
			<col width="5%"/>
			<col width="15%"/>
			<col width="15%"/>
			<col width="5%"/>
			<col width="6%"/>
			<col width="10%"/>
			<col width="13%"/>
		</colgroup>
		<tbody>
			<tr>
				<th>순번</th>
				<th>입복회일</th>
				<th>교실명</th>
				<th>회원번호</th>
				<th>과목</th>
				<th>회원명</th>
				<th>학년</th>
				<th>등급</th>
				<th>관리요일</th>
				<th>구분</th>
				<th>작업일</th>
			</tr>
		</tbody>
	</table>
	<div style="overflow: auto;width:960px; height:400px;">
		<table style="border: 0px;border-spacing: 0;width: 100%;">
			<colgroup>
				<col width="5%"/>
				<col width="8%"/>
				<col width="10%"/>
				<col width="8%"/>
				<col width="5%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="5%"/>
				<col width="6%"/>
				<col width="10%"/>
				<col width="13%"/>
			</colgroup>
			<tbody>
			{{#each ipheiList}}
				<tr>
					<td>{{inc @index}}</td>
					<td>{{ipheiYMD}}</td>
					<td>{{fstEmpName}}{{#if sndEmpName}}{{sndEmpName}}{{/if}}</td>
					<td>{{mkey}}</td>
					<td>{{subj}}</td>
					<td>{{mFstName}}{{mLstName}}</td>
					<td>{{gradeCDNM}}</td>
					<td>{{#xIf jGradeCD "==" "X"}}무진단{{else}}{{jGradeCD}}{{/xIf}}</td>
					<td>{{fstYoilNM}}{{#if sndYoilNM}}{{sndYoilNM}}{{/if}}</td>
					<td>{{ipheiGubunCDNM}}</td>
					<td>{{regDate}}</td>
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
