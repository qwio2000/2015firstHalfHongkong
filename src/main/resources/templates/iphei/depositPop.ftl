<#include "/popHeader.ftl">
<#include "/function.ftl">
<body>
<div id="popWrapper" class="wrapper">
	<div id="popContent">
				<div>
					<span>▣입금구분</span>
				</div>
				<div class="tbl-type-H">
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="20%">
							<col width="*">
						</colgroup>
						<#if sellList?has_content>
						<tr id="tr01">
							<th>
								판매구분
							</th>
							<td>
								<#list sellList as sell>
								<input type="radio" name="SellGubun" value="${sell.dtlCD}" ${compare("01",sell.dtlCD,"checked='checked'","")} 
								<#if (pMkucode != "01" && (sell.dtlCD == "02" || sell.dtlCD == "04")) || (seqval != "0" && (sell.dtlCD == "02" || sell.dtlCD == "04")) >
								disabled
								<#else>
								onclick="$.sellChange('${sell.dtlCD}');"
								</#if>						
								 >${sell.dtlCDNM}
								</#list>
							</td>
						</tr>
						</#if>
						<#if ipmSayuList?has_content>
						<tr id="tr02" style="display:none;">
							<th>
								입회비면제
							</th>
							<td>
								<#list ipmSayuList as ipmSayu>
								<input type="radio" name="ipGubun" <#if ipmSayu_index == 0>checked='checked'</#if>  onclick="$.msayuChange('${ipmSayu.dtlCD}');" value="${ipmSayu.dtlCD}" >${ipmSayu.dtlCDNM}
								</#list>
							</td>
						</tr>
						</#if>
						<#if monthSayuList?has_content>
						<tr id="tr03" style="display:none;">
							<th>
								월회비면제
							</th>
							<td>
								<#list monthSayuList as monthSayu>
								<input type="radio" name="monThGubun" <#if monthSayu_index == 0>checked='checked'</#if> onclick="$.msayuChange('${monthSayu.dtlCD}');" value="${monthSayu.dtlCD}" >${monthSayu.dtlCDNM}
								</#list>
							</td>
						</tr>
						</#if>
						<#if bothSayuList?has_content>
						<tr id="tr04" style="display:none;">
							<th>
								입회비면제<br/>월회비면제
							</th>
							<td>
								<#list bothSayuList as bothSayu>
								<input type="radio" name="bothGubun" <#if bothSayu_index == 0>checked='checked'</#if> onclick="$.msayuChange('${bothSayu.dtlCD}');" value="${bothSayu.dtlCD}" >${bothSayu.dtlCDNM}
								</#list>
							</td>
						</tr>
						</#if>
						<#if saleSayuList?has_content>
						<tr id="tr05" style="display:none;">
							<th>
								월회비할인
							</th>
							<td>
								<#list saleSayuList as saleSayu>
								<input type="radio" name="saleGubun" <#if saleSayu_index == 0>checked='checked'</#if> onclick="$.msayuChange('${saleSayu.dtlCD}');" value="${saleSayu.dtlCD}" >${saleSayu.dtlCDNM}
								</#list>
							</td>
						</tr>
						</#if>
					</table>
				</div>
				<div>▣잔여주차결정</div>
				<div class="tbl-type-D">
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="20%">
							<col width="*">
						</colgroup>
						<tr>
							<th>잔여주차</th>
							<td>
								<#if fstYMW?has_content>
								<span style="color:blue;">첫진도 주차 :${fstYMW?substring(0,4)}년 ${fstYMW?substring(4,6)}월 ${fstYMW?substring(6)}주차</span>
								</#if>
								<#if juchaList?has_content>
								<select id="restymw">
									<#list	juchaList as jucha>
										<option value="${jucha.restymw}">${jucha.restwk}주차</option>
									</#list>						
								</select>
								</#if>
								<span style="color:red;">(입회일자:${pIpheiday})</span>
							</td>
						</tr>
					</table>
					<div id="dataList"></div>
				</div>
				
				<input type="hidden" id="seqval" value="${seqval}">
				<#if fstYMW?has_content>
				<input type="hidden" id="fstYMW" value="${fstYMW}">
				<#else>
				<input type="hidden" id="fstYMW" value="">
				</#if>
				<input type="hidden" id="pIpheiday" value="${pIpheiday}">
				<input type="hidden" id="pJisaCD" value="${pJisaCD}">
				<input type="hidden" id="pDepid1" value="${pDepid1}">
				<input type="hidden" id="pSubj" value="${pSubj}">
				<input type="hidden" id="pMjGrade" value="${pMjGrade}">
				<input type="hidden" id="pMemGrade" value="${pMemGrade}">
				<input type="hidden" id="bulsu" value="1">
				<input type="hidden" id="pRestYMW">
				<input type="hidden" id="pMSayu">
				<input type="hidden" id="pMkucode" value="${pMkucode}">
	</div>
</div>
<!-- //container -->
<script id="depositSetTemplate" type="text/x-handlebars-template">
	<table style="border-spacing: 0;width: 100%;">
		<colgroup>
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="*">
		</colgroup>
		<tr>
			<th>차액월회비</th>
			<th>익월회비</th>
			<th>입금총액</th>
			<th>등록개월</th>
			<th>적용금액</th>
		</tr>
		{{#each resultList}}
		<tr>
			<td>{{chaheibi}}</td>
			<td>{{wolheibi}}</td>
			<td>{{pwGum}}</td>
			<td><a href="javascript:$.depositSet({{ipheibi}},{{chaheibi}},{{wolheibi}},{{checkTotheibi totheibi}},{{viewWol}})">{{viewWol}} 개월</a></td>
			<td>{{checkTotheibi totheibi}}</td>
		</tr>
		{{else}}
		<tr>
			<td colspan="9">검색된 결과가 없습니다.</td>
		</tr>
		{{/each}}
	</table>
</script>
</body>
</html>
