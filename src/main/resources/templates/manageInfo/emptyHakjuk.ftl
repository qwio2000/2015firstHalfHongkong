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
						<li class="current"><a href="#none">학적미발생회원</a></li>
					</ul>
				</div>
				 <div class="mgt-20">
				 	<div class="message-top2" style="margin-bottom: 20px;font-size: 13px;">
						<form class="" name="" action="" id="frm1" method="post">
							<div class="user_info">
								<span>교실명</span>
								<select name="empKey" style="vertical-align: middle;width: 200px;">
									<option value="">전체</option>
									<#list classList as class>
									<option value="${class.empKey }">${class.empKey }
										(${class.empName })</option>
									</#list>
								</select> 
								<span class="button"><input size="8px" type="button" name="" id="search" value="검색"></span>
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
<p class="text-r">총 : {{emptyHakjukInfo.length}}건</p>
{{#xIf emptyHakjukInfo.length ">=" 10}}
<div class="tbl-type-D" style="width: 960px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 943px;">
{{else}}
<div class="tbl-type-D" style="width: 960px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 960px;">
{{/xIf}}
		<colgroup>
			<col width="5%"/>
			<col width="10%"/>
			<col width="15%"/>
			<col width="8%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="5%"/>
			<col width="17%"/>
			<col width="10%"/>
		</colgroup>
		<tbody>
			<tr>
				<th>순번</th>
				<th>교실번호</th>
				<th>교실명</th>
				<th>관리요일</th>
				<th>회원번호</th>
				<th>회원명</th>
				<th>과목</th>
				<th>최종진도<br/>(년/월/주)</th>
				<th>최종진도</th>
			</tr>
		</tbody>
	</table>
	<div style="overflow: auto;width:960px; height:400px;">
		<table style="border: 0px;border-spacing: 0;width: 100%;">
			<colgroup>
				<col width="5%"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="8%"/>
				<col width="10%"/>
				<col width="20%"/>
				<col width="5%"/>
				<col width="17%"/>
				<col width="10%"/>
			</colgroup>
			<tbody>
			{{#each emptyHakjukInfo}}
				<tr>
					<td>{{inc @index}}</td>
					<td>{{sKey}}</td>
					<td>{{sName}}</td>
					<td>{{yoilNM}}</td>
					<td><a href="javascript:$.jindoSearch('{{mkey}}','{{subj}}','{{mFstName}}')">{{mkey}}</a></td>
					<td>{{mFstName}}</td>
					<td>{{subj}}</td>
					<td>{{#if finalYMW}}{{trimString finalYMW 0 4}}/{{trimString finalYMW 4 6}}/{{trimString finalYMW 6 7}}{{else}}{{/if}}</td>
					<td>{{finalJindo}}</td>
				</tr>
			{{else}}
				<tr>
					<td colspan="9">검색된 결과가 없습니다.</td>
				</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</div>
</script>
</html>
