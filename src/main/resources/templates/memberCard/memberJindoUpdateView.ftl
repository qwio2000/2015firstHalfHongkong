<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
		<form id="Qry2FormName" name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="width: 800px; height: 624px;">
		<div class="tab-A">
		<ul>
			<li class="first">	<a href="javascript:$.locationGubun('/memberCard/memberJindoUpdateInfo','2')" style="cursor:hand;">진도조정</a></li>
			<li class="last active">	<a href="javascript:$.locationGubun('/memberCard/memberJindoUpdateView','2')"	style="cursor:hand;">진도조정내역</a></li>
		</ul>
		</div>
		<div class="message-top2" style="margin-bottom: 20px">
				<div class="user_info">
					<span>검색일자</span>
					<input type="text" name="startDate" class="datePicker" value="${.now?string("yyyy-MM-dd")}" readonly="readonly"> ~ 
					<input type="text" name="endDate" class="datePicker" value="${.now?string("yyyy-MM-dd")}" readonly="readonly">
					<span class="button"><input size="8px" type="button" name="" id="searchBtn" value="검색"></span>
				</div>
		</div>
		 <p class="mgt-20 mgb-20"></p>
			 <div id="mainContent" class="tbl-type-D">
					<table style="border-spacing: 0;border: 0;width: 100%;">
						<colgroup>
							<col width="5%">
							<col width="8%">
							<col width="12%">
							<col width="8%">
							<col width="10%">
							<col width="5%">
							<col width="8%">
							<col width="15%">
							<col width="5%">
							<col width="8%">
							<col width="4%">
							<col width="4%">
							<col width="4%">
							<col width="4%">
						</colgroup>
						<tbody>
							<tr>
								<th rowspan="2">순서</th>
								<th rowspan="2">변경구분</th>
								<th rowspan="2">조정구분</th>
								<th rowspan="2">조정옵션</th>
								<th rowspan="2">조정일자</th>
								<th rowspan="2">과목</th>
								<th rowspan="2">회원번호</th>
								<th rowspan="2">회원이름</th>
								<th colspan="6">Set선택정보</th>
							</tr>
							<tr>
								<td>Set</td>
								<td>년</td>
								<td>월</td>
								<td>주</td>
								<td>요일</td>
								<td>Sort</td>
							</tr>
							<#if jindoUpdateViewList?has_content>
							<#list jindoUpdateViewList as jindoUpdateView>
							<tr>
								<td>${jindoUpdateView_index+1}</td>
								<td>${jindoUpdateView.cngGubunNM}</td>
								<td>${jindoUpdateView.cngOptNM}</td>
								<td>${jindoUpdateView.cngSayuNM}</td>
								<td><a href="javascript:$.jindoHisView('${jindoUpdateView.mkey}','${jindoUpdateView.subj}',
								'${jindoUpdateView.cngYMD}','${jindoUpdateView.cngGubunCD}','${jindoUpdateView.cngOptCD}',
								'${jindoUpdateView.cngSayuCD}','${jindoUpdateView.yy }','${jindoUpdateView.mm }')">
								${jindoUpdateView.cngYMD}</a>
								</td>
								<td>${jindoUpdateView.subj }</td>
								<td>${jindoUpdateView.mkey}</td>
								<td>${jindoUpdateView.mFstName}</td>
								<td>${jindoUpdateView.jset }</td>
								<td>${jindoUpdateView.yy }</td>
								<td>${jindoUpdateView.mm }</td>
								<td>${jindoUpdateView.wk }</td>
								<td>${jindoUpdateView.yoil }</td>
								<td>${jindoUpdateView.sort }</td>
							</tr>
							</#list>
							<#else>
							<tr>
								<td class="line01" colspan='14'>☞ 검색 조건에 대한 데이타가 없습니다</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
					<div class="btn-box">
						<span class="button btn-type-E"><a class="w-65" style="width: 65px;" href="javascript:self:close();">닫기</a></span>
					</div>
		</div>
	</form>
</div>
</body>
<script id="template" type="text/x-handlebars-template"> 
	<table style="border-spacing: 0;border: 0;width: 100%;">
		<colgroup>
			<col width="3%">
			<col width="10%">
			<col width="8%">
			<col width="8%">
			<col width="10%">
			<col width="5%">
			<col width="8%">
			<col width="15%">
			<col width="5%">
			<col width="8%">
			<col width="5%">
			<col width="5%">
			<col width="5%">
			<col width="5%">
		</colgroup>
		<tbody>
			<tr>
				<th rowspan="2">순서</th>
				<th rowspan="2">변경구분</th>
				<th rowspan="2">조정구분</th>
				<th rowspan="2">조정옵션</th>
				<th rowspan="2">조정일자</th>
				<th rowspan="2">과목</th>
				<th rowspan="2">회원번호</th>
				<th rowspan="2">회원이름</th>
				<th colspan="6">Set선택정보</th>
			</tr>
			<tr>
				<td>Set</td>
				<td>년</td>
				<td>월</td>
				<td>주</td>
				<td>요일</td>
				<td>Sort</td>
			</tr>
				{{#each jindoUpdateViewList}}
					<tr>
						<td>{{inc @index}}</td>
						<td>{{cngGubunNM}}</td>
						<td>{{cngOptNM}}</td>
						<td>{{cngSayuNM}}</td>
						<td><a href="javascript:$.jindoHisView('{{mkey}}','{{subj}}',
								'{{cngYMD}}','{{cngGubunCD}}','{{cngOptCD}}',
								'{{cngSayuCD}}','{{yy}}','{{mm}}')">
							{{cngYMD}}</a></td>
						<td>{{subj }}</td>
						<td>{{mkey}}</td>
						<td>{{mFstName}}</td>
						<td>{{jset }}</td>
						<td>{{yy }}</td>
						<td>{{mm }}</td>
						<td>{{wk }}</td>
						<td>{{yoil }}</td>
						<td>{{sort }}</td>
					</tr>
			{{else}}
				<tr>
					<td class="line01" colspan='14'>☞ 검색 조건에 대한 데이타가 없습니다</td>
				</tr>
			{{/each}}
		</tbody>
	</table>
</script>
</html>