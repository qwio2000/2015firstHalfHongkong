<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
		<input type="hidden" name="searchKwamok" value=""/>
	</form>
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="height:625px">

		<!-- 로고 및 탭 -->
		<#include "/memberCard/memberPopMenu.ftl">
		<p class="mgt-10 mgb-10">${memberDetailInfo.mFstName?default('') } 회원 상세정보 입니다.</p>
		<div class="tbl-type-D">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="60px">
						<col width="90px">
						<col width="110px">
						<col width="110px">
						<col width="110px">
						<col width="60px">
						<col width="90px">
						<col width="170px">
					</colgroup>
						<tr>
							<th>과목</th>
							<th>상태</th>
							<th>최초입회일</th>
							<th>복회일</th>
							<th>퇴회일</th>
							<th>불출수</th>
							<th>요일</th>
							<th>교실</th>
						</tr>
					<tbody>
						<#if memberKwamokInfo?has_content>
							<#list memberKwamokInfo as kwamokInfo>
									<tr>
										<td>${kwamokInfo.kwamok }</td>
										<td>${kwamokInfo.state }</td>
										<td>${kwamokInfo.ipheiYMD }</td>
										<td>${kwamokInfo.bokheiYMD }</td>
										<td>${kwamokInfo.huheiYMD }</td>
										<td>${kwamokInfo.setCnt }</td>
										<td>${kwamokInfo.fstYoil }
										<#if kwamokInfo.sndYoil?exists && kwamokInfo.sndYoil?length gt 0>
											<br/>${kwamokInfo.sndYoil }										
										</#if>
										</td>
										<td>${kwamokInfo.fstEmpName } (${kwamokInfo.fstEmpKey })
										<#if kwamokInfo.sndYoil?exists && kwamokInfo.sndYoil?length gt 0>
											<br/>${kwamokInfo.sndEmpName } (${kwamokInfo.sndEmpKey })
										</#if>
										</td>
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
</div>
</body>
</html>
