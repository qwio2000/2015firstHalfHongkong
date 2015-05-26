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
		<p class="mgt-10 mgb-10">${memberDetailInfo.mFstName } 회원 상세정보 입니다.
		<span id="search" style="float: right;"><#include "/memberCard/kwamokInfo.ftl">
		<span id="searchBtn" class="button btn-type-G"><a class="w-65">조회</a></span>
		</span>
		</p>
		<div id="mainContent" class="tbl-type-D">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="50px">
						<col width="95px">
						<col width="110px">
						<col width="180px">
						<col width="50px">
						<col width="50px">
						<col width="95px">
						<col width="160px">
					</colgroup>
						<tr>
							<th>과목</th>
							<th>입복회 일자</th>
							<th>입회구분</th>
							<th>판매구분</th>
							<th>등급</th>
							<th>불출수</th>
							<th>첫관리</th>
							<th>교실</th>
						</tr>
					<tbody>
						<#if ipheiList?has_content>
							<#list ipheiList as iphei>
									<tr>
										<td>${iphei.kwamok }</td>
										<td>${iphei.ipheiYMD }</td>
										<td>${iphei.ipheiGubun }</td>
										<td>${iphei.ipheiKind }</td>
										<td>${iphei.jGradeCD }</td>
										<td>${iphei.setCnt }</td>
										<td>${iphei.fstYMD }
										<#if iphei.sndYMD?exists && iphei.sndYMD?length gt 0>
											<br/>${iphei.sndYMD }
										</#if>
										</td>
										<td>${iphei.fstEmpName } (${iphei.fstEmpKey })
										<#if iphei.sndYMD?exists && iphei.sndYMD?length gt 0>
											<br/>${iphei.sndEmpName } (${iphei.sndEmpKey })
										</#if>
										</td>
									</tr>
							</#list>
						<#else>
							<tr>
								<td colspan="9">데이터가 없습니다.</td>
							</tr>
						</#if>
					</tbody>
				</table>
		</div>
		</div>
			<div class="btn-mbox">
				<span class="button btn-type-G"><a class="w-65" href="javascript:self:close();">닫기</a></span>
			</div>
	</form>
	</div>
</body>
<script id="template" type="text/x-handlebars-template"> 
	<table style="border-spacing: 0;width: 800px;" border="">
		<colgroup>
			<col width="50px">
			<col width="95px">
			<col width="110px">
			<col width="180px">
			<col width="50px">
			<col width="50px">
			<col width="95px">
			<col width="160px">
		</colgroup>
			<tr>
				<th>과목</th>
				<th>입복회 일자</th>
				<th>입회구분</th>
				<th>판매구분</th>
				<th>등급</th>
				<th>불출수</th>
				<th>첫관리</th>
				<th>교실</th>
			</tr>
		<tbody>
			{{#each ipheiList}}
						<tr>
							<td>{{kwamok}}</td>
							<td>{{ipheiYMD }}</td>
							<td>{{ipheiGubun }}</td>
							<td>{{ipheiKind }}</td>
							<td>{{jGradeCD }}</td>
							<td>{{setCnt }}</td>
							<td>{{fstYMD }}
								{{#if sndYMD}}
									<br/>{{sndYMD }}
								{{/if}}
							</td>
							<td>{{fstEmpName }} ({{fstEmpKey }})
								{{#if sndYMD}}
								<br/>{{sndEmpName }} ({{sndEmpKey }})
								{{/if}}
							</td>
						</tr>
			{{else}}
				<tr>
					<td colspan="9">데이터가 없습니다.</td>
				</tr>
			{{/each}}
		</tbody>
	</table>
</script>
</html>
