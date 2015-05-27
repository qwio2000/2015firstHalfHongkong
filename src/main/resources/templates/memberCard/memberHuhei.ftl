<#include "/popHeader.ftl">
<body>
<#include "/function.ftl">
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form id="Qry2FormName" name="Qry2FormName" action="/memberCard/memberHuheiSave" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
		<input type="hidden" name="sKey" value="${memberDetailInfo.sKey?default('') }"/>
	
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="height:625px">
		<div class="tbl-type-D">
			<table style="border-spacing: 0;width: 800px;" border="">
				<colgroup>
					<col width="130px">
					<col width="80px">
					<col width="130px">
					<col width="100px">
					<col width="*">
				</colgroup>
					<tr>
						<th>회원</th>
						<th>과목</th>
						<th>교실</th>
						<th>퇴회일자</th>
						<th>퇴회사유</th>
					</tr>
				<tbody>
					<tr>
						<td>${memberDetailInfo.mFstName } ${memberDetailInfo.mLstName?default('') }</td>
						<td>${kwamokName} (${memberDetailInfo.kwamok })</td>
						<td>${memberDetailInfo.sName } (${memberDetailInfo.sKey })</td>
						<td>
							<select name="huheiDay">
								<#assign curDate = .now?string("yyyy-MM-dd")>
								<#list huheiDayList as day>
									<option value="${day }" ${compare(day,curDate,"selected='selected'","")}>${day }</option>
								</#list>
							</select>
						</td>
						<td style="text-align: left;">
							<div>
								<input name="huGubun" type="radio" checked="checked" value="01" onclick="javascript:$.huGubun_chk()"/>
								일반퇴회 : 
								<select name="huSayu" style="vertical-align: middle;">
									<#list huheiSayuList as huheiSayu>
										<#if huheiSayu.mstCD = '0021'>
											<option value="${huheiSayu.dtlCD }">${huheiSayu.dtlCDNM }</option>
										</#if>
									</#list>
								</select>
							</div>
							<div>
								<input name="huGubun" type="radio" value="02" onclick="javascript:$.huGubun_chk()"/>
								퇴회면제 : 
								<select name="huSayu" style="width: 273px;vertical-align: middle;" disabled="disabled">
									<#list huheiSayuList as huheiSayu>
										<#if huheiSayu.mstCD = '0201'>
											<option value="${huheiSayu.dtlCD }">${huheiSayu.dtlCDNM }</option>
										</#if>
									</#list>
								</select>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btn-mbox">
			<span id="saveButton" class="button btn-type-G"><a class="w-65" style="width: 65px" href="#">저장</a></span>
			<span class="button btn-type-G"><a class="w-65" style="width: 65px" href="javascript:self:close();">닫기</a></span>
		</div>
	</div>
	</form>
</div>
</body>
</html>
