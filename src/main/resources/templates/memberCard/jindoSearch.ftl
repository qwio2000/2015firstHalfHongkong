<#include "/popHeader.ftl">
<body>
<#include "/function.ftl">
<div id="popWrapper" class="wrapper" style="width:100%;">
	<!-- popHeader -->
	<div id="popHeader">
		<h1><img width="139" src="/public/img/common/logo_header.gif" alt="JEI Global.com" /></h1>
		<h2>진도정보</h2>
	</div>
	<!-- popContent -->
	<div id="popContent" style="height:700px">
		<form action="" method="post" name="Qry2FormName">
			<input type="hidden" name="mKey" value="${memberDetailInfo.mKey}">
			<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok}">
			<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName}">
		<div style="float: right;">
		과목 : <#include "/memberCard/kwamokInfo.ftl">&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="searchYY">
				<option value="${yearNumberFormat(searchYY?number-1) }">${yearNumberFormat(searchYY?number-1) }</option>
				<option value="${yearNumberFormat(searchYY?number) }" selected="selected">${yearNumberFormat(searchYY?number) }</option>
				<option value="${yearNumberFormat(searchYY?number+1) }">${yearNumberFormat(searchYY?number+1) }</option>
			</select>년
			<select name="searchMM">
				<#list 1..12 as i>
					<option value="${attachZero(i)}" ${compare(searchMM,attachZero(i)?string,"selected='selected'","")}>${attachZero(i)}</option>
				</#list>
			</select>월
			<span class="button btn-type-G"><a class="w-65" href="javascript:$.locationGubun('/memberCard/jindoSearch','2');">검색</a></span>
		</div>
		<p class="mgt-10 mgb-10">*과목 : ${searchKwamok}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		*${memberDetailInfo.mFstName?default('') }회원</p>
		<div class="tbl-type-D">
			<table style="border-spacing: 0;width: 620px;" border="">
				<colgroup>
					<col width="60px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
					<col width="54px">
				</colgroup>
				<tbody>
				<#if memberJindoSearch?has_content>
					<tr>
						<th rowspan="2">년/월</th>
						<th colspan="2">1주</th>
						<th colspan="2">2주</th>
						<th colspan="2">3주</th>
						<th colspan="2">4주</th>
						<th colspan="2">5주</th>
					</tr>
					<tr>
						<#list 0..4 as i>
							<td>${memberJindoSearch.fstYoilNm?default('') }</td>
							<td>${memberJindoSearch.sndYoilNm?default('') }</td>
						</#list>
					</tr>
						<#assign yyyy=startYYYY>
						<#assign mm=startMM>
					<#list 0..11 as i>
					<tr style="background-color: ${currentDayStyle(yyyy,mm)};">
						<td>${yyyy}/${attachZero(mm?number)}</td>
						<#list 0..4 as j>
							<#list 0..1 as j2>
								<td>${bsArray[i][j][j2]?default('') }&nbsp;${indArray[i][j][j2]?default('') }</td>
							</#list>
						</#list>
					</tr>
						<#if mm?number+1 = 13>
							<#assign yyyy = yearNumberFormat(yyyy?number+1)>
							<#assign mm = 1>
						<#else>
							<#assign mm = mm?number+1>
							<#assign mm = mm>
						</#if>
					</#list>
				<#else>
					<tr>
						<td colspan="11">데이터가 존재하지 않습니다.</td>
					</tr>
					</#if>
				</tbody>
			</table>
		</div>
		<div class="btn-mbox">
			<span class="button btn-type-G"><a class="w-65" href="javascript:self:close();">닫기</a></span>
		</div>
		</form>
	</div>
</div>
</body>
</html>