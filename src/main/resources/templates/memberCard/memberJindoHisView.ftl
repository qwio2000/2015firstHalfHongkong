<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
<#include "/function.ftl">	
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="height:625px">
	<h1 class="page-title" style="margin-top: 0px;">${cngGubunNM }[${cngOptNM }] 
	회원번호 : ${jindoUpdateView.mkey }, 과목 : ${jindoUpdateView.subj }</h1>
		<!-- 로고 및 탭 -->
		<div style="margin-top: 20px;"></div>
		<p><font color="red"><b>* Before Progress Schedule</b></font></p>
		<div class="tbl-type-D" style="margin-top: 10px;">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
					</colgroup>
						<tr>
							<th rowspan="2">년/월</th>
							<th colspan="2">1주</th>
							<th colspan="2">2주</th>
							<th colspan="2">3주</th>
							<th colspan="2">4주</th>
							<th colspan="2">5주</th>
						</tr>
						<tr>
							<#list 1..5 as i>
							<td>${fstYoilNM }</td>
							<td></td>
							</#list>
						</tr>
					<tbody>
					<#assign yyyy = startYY>
					<#assign mm = startMM>
					<#if beforeJin1?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list beforeJin1 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if beforeJin2?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list beforeJin2 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if beforeJin3?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list beforeJin3 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if beforeJin4?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list beforeJin4 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if beforeJin5?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list beforeJin5 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if beforeJin6?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list beforeJin6 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					</tbody>
				</table>
		</div>
		<div style="margin-top: 30px;"></div>
		<p><font color="red"><b>* After Progress Schedule</b></font></p>
		<div class="tbl-type-D" style="margin-top: 10px;">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
					</colgroup>
						<tr>
							<th rowspan="2">년/월</th>
							<th colspan="2">1주</th>
							<th colspan="2">2주</th>
							<th colspan="2">3주</th>
							<th colspan="2">4주</th>
							<th colspan="2">5주</th>
						</tr>
						<tr>
							<#list 1..5 as i>
							<td>${fstYoilNM }</td>
							<td></td>
							</#list>
						</tr>
					<tbody>
					<#assign yyyy = startYY>
					<#assign mm = startMM>
					<#if afterJin1?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list afterJin1 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if afterJin2?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list afterJin2 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if afterJin3?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list afterJin3 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if afterJin4?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list afterJin4 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if afterJin5?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list afterJin5 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
						</tr>
					</#if>
					<#if mm?number+1 = 13>
						<#assign yyyy = yearNumberFormat(yyyy?number+1)>
						<#assign mm = 1>
					<#else>
						<#assign mm = mm?number+1>
						<#assign mm = mm>
					</#if>
					<#if afterJin6?has_content>
						<tr>
							<td>${yyyy}/${attachZero(mm?number)}</td>
							<#list 1..5 as i>
								<#assign j=0>
								<#list afterJin6 as jin>
									<#if jin.wk?number = i>
									<td>${jin.jset }</td><td></td>
									<#assign j=1>
									</#if>
								</#list>
								<#if j=0>
									<td></td><td></td>
								</#if>
							</#list>
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