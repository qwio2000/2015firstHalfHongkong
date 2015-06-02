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

		<!-- 로고 및 탭 -->
		<#include "/memberCard/memberPopMenu.ftl">
		<p class="mgt-10 mgb-10">${memberDetailInfo.mFstName } 회원 상세정보 입니다.
		<span id="search" style="float: right;"><#include "/common/kwamokInfo.ftl">
		<span id="searchBtn" class="button btn-type-G"><a class="w-65">조회</a></span>
		</span>
		</p>
		<div id="mainContent" class="tbl-type-D">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="50px">
						<col width="90px">
						<col width="140px">
						<col width="150px">
						<col width="90px">
						<col width="90px">
						<col width="90px">
						<col width="90px">
					</colgroup>
						<tr>
							<th>과목</th>
							<th>입금일자</th>
							<th>입금구분</th>
							<th>교실</th>
							<th>입회비(면제)</th>
							<th>월회비(면제)</th>
							<th>실입금계</th>
							<th>차기등록일</th>
						</tr>
					<tbody>
						<#if ipgumList?has_content>
							<#list ipgumList as ipgum>
								<tr>
									<td>${ipgum.kwamok?default('') }</td>
									<td>${ipgum.ipgumYMD?default('') }</td>
									<td>${ipgum.existCD?default('') }</td>
									<td>${ipgum.empName?default('') } (${ipgum.empKey?default('') })</td>
									<td style="text-align: right;padding-right: 5px;">${ipgum.ipheibi?default('') } (${ipgum.ipheibiFree?default('') })</td>
									<td style="text-align: right;padding-right: 5px;">${ipgum.wolheibi?default('') } (${ipgum.wolheibiFree?default('') })</td>
									<td style="text-align: right;padding-right: 5px;">${ipgum.totalHeibi?default('') }</td>
									<td>${ipgum.expireYMD?default('') }</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td colspan="8">데이터가 없습니다.</td>
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
			<col width="90px">
			<col width="140px">
			<col width="150px">
			<col width="90px">
			<col width="90px">
			<col width="90px">
			<col width="90px">
		</colgroup>
			<tr>
				<th>과목</th>
				<th>입금일자</th>
				<th>입금구분</th>
				<th>교실</th>
				<th>입회비(면제)</th>
				<th>월회비(면제)</th>
				<th>실입금계</th>
				<th>차기등록일</th>
			</tr>
		<tbody>
			{{#each ipgumList}}
					<tr>
						<td>{{kwamok}}</td>
						<td>{{ipgumYMD}}</td>
						<td>{{existCD}}</td>
						<td>{{empName}} ({{empKey}})</td>
						<td style="text-align: right;padding-right: 5px;">{{ipheibi }} ({{ipheibiFree }})</td>
						<td style="text-align: right;padding-right: 5px;">{{wolheibi }} ({{wolheibiFree }})</td>
						<td style="text-align: right;padding-right: 5px;">{{totalHeibi}}</td>
						<td>{{expireYMD }}</td>
					</tr>
			{{else}}
				<tr>
					<td colspan="8">데이터가 없습니다.</td>
				</tr>
			{{/each}}
		</tbody>
	</table>
</script>
</html>
