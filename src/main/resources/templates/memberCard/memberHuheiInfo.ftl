<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form id="Qry2FormName" name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
	<!-- popHeader -->
	<#include "/popLogo.ftl">
	<!-- popContent -->
	<div id="popContent" style="height:625px">

		<!-- 메뉴 탭 -->
		<#include "/memberCard/memberPopMenu.ftl">
		<p class="mgt-10 mgb-10">${memberDetailInfo.mFstName?default('') } 회원 상세정보 입니다.
		<span id="search" style="float: right;"><#include "/common/kwamokInfo.ftl">
		<span id="searchBtn" class="button btn-type-G"><a class="w-65">조회</a></span>
		</span>
		</p>
		<div id="mainContent" class="tbl-type-D">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="50px">
						<col width="130px">
						<col width="165px">
						<col width="175px">
						<col width="270px">
					</colgroup>
						<tr>
							<th>과목</th>
							<th>퇴회일자</th>
							<th>퇴회구분</th>
							<th>교실</th>
							<th>퇴회사유</th>
						</tr>
					<tbody>
						<#if huheiList?has_content>
							<#list huheiList as huhei>
								<tr>
									<td>${huhei.kwamok }</td>
									<td>${huhei.huheiYMD }</td>
									<td>${huhei.huheiKind }</td>
									<td>${huhei.empName } (${huhei.empKey })</td>
									<td>사유 : ${huhei.huheiSayu }</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td colspan="5">데이터가 없습니다.</td>
							</tr>
						</#if>
					</tbody>
				</table>
		</div>
			<div class="btn-mbox">
				<span class="button btn-type-G"><a class="w-65" href="javascript:self:close();">닫기</a></span>
			</div>
	</div>
	</form>
</div>
</body>
<script id="template" type="text/x-handlebars-template"> 
	<table style="border-spacing: 0;width: 800px;" border="">
		<colgroup>
			<col width="50px">
			<col width="130px">
			<col width="165px">
			<col width="175px">
			<col width="270px">
		</colgroup>
			<tr>
				<th>과목</th>
				<th>퇴회일자</th>
				<th>퇴회구분</th>
				<th>교실</th>
				<th>퇴회사유</th>
			</tr>
		<tbody>
			{{#each huheiList}}
					<tr>
						<td>{{kwamok }}</td>
						<td>{{huheiYMD }}</td>
						<td>{{huheiKind }}</td>
						<td>{{empName }} ({{empKey }})</td>
						<td>사유 : {{huheiSayu }}</td>
					</tr>
			{{else}}
				<tr>
					<td colspan="5">데이터가 없습니다.</td>
				</tr>
			{{/each}}
		</tbody>
	</table>
</script>
</html>