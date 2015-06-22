<#include "/header.ftl">
<#include "/function.ftl">
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
				<h1>입복회</h1>
				<div class="tbl-type-D">	
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="15%">
							<col width="40%">
							<col width="15%">
							<col width="*">
						</colgroup>
						<tr>
							<th>입회일자</th>
							<td>
								<#if availableDateList?has_content>
								<select id="ipheiday">
									<#list availableDateList as ableDateList>
									<option value="${ableDateList}" ${compare(nowDate,ableDateList,"selected='selected'","")} >${ableDateList}</option>
									</#list>
								</select>
								</#if>
							</td>
							<th>입회교육원</th>
							<td>
								<#if depInfoList?has_content>
								<select id="ipheiDepid">
									<#list depInfoList as deptList>
										<#if mujinDepid != "">
										<option value="${deptList.depid1}" onchange="$.changeDepid();" ${compare(mujinDepid,deptList.depid1,"selected='selected'","")} >${deptList.depid1} (${deptList.depidNM})</option>
										<#else>
										<option value="${deptList.depid1}" onchange="$.changeDepid();"  ${compare(depid1,deptList.depid1,"selected='selected'","")} >${deptList.depid1} (${deptList.depidNM})</option>
										</#if>
									</#list>
								</select>
								</#if>
							</td>
						</tr>
						<tr>
							<th>입회종류</th>
							<td colspan="3">
								<#if ipheiKindList?has_content>
									<#list ipheiKindList as ipheiKind>
									<input type="radio" name="ipkind"  id="ipkind${ipheiKind_index}" value="${ipheiKind.dtlCD}" 
									<#if ipheiKind.dtlCD == ipKind || ipheiKind.dtlCD == "01" > checked="checked" </#if> >
									<label for="ipkind${ipheiKind_index}">${ipheiKind.dtlCDNM}</label>
									</#list>
								</#if>
							</td>
						</tr>
						<tr id="trKwamok">
							<th>과목선택</th>
							<td colspan="3">
								<#if kwamokList?has_content>
									<#list kwamokList as kwList>
										<#if kwList_index == 6>
											<br/>
										</#if>
										<input type="checkbox" name="subj" id="subj${kwList}" value="${kwList}"><label for="subj${kwList}">${kwList}</label>
									</#list>
								</#if>
							</td>
						</tr>
						<tr id="trSearch" style="display:none;">
							<th>회원검색</th>
							<td colspan="3">회원명<input id="pMName" type="text" /> 회원번호<input id="pSearchMKey" type="text" maxlength="8"/> 생일년도<input id="pBirthYY" type="text" maxlength="4"/> <input type="button" id="pSearchBtn" value="검색"/></td>
						</tr>
					</table>
				</div>
				<br/>
				<div id="dataList">
				</div>
				<div class="btn-box">
					<span class="button btn-type-J">
						<a href="javascript:$.registSubmit();" class="w-120">등록</a>
					</span>
					<span class="button btn-type-J">
						<a href="#none" class="w-120">취소</a>
					</span>
					<span class="button btn-type-I">
						<a href="#none" class="w-120">목록</a>
					</span>
				</div>		
			</div>
		</div>
	</div>
	<form id="ipheiFrm">
		<input type="hidden" name="pIpheiday" id="pIpheiday">
		<input type="hidden" name="pSubj" id="pSubj">
		<input type="hidden" name="pIpheiDepid" id="pIpheiDepid">
		<input type="hidden" name="pIpheiKind" id="pIpheiKind">
		<input type="hidden" name="pMkey" id="pMKey">
		<input type="hidden" name="pOmrdate" id="pOmrdate">
	</form>
</div>
<!-- //container -->
<#include "/footer.ftl">
<script id="existsTemplate" type="text/x-handlebars-template">
	<span>▣ 기존회원 검색</span>
	<table style="border-spacing: 0;width: 100%;" border="1">
		<colgroup>
			<col width="8%">
			<col width="6%">
			<col width="6%">
			<col width="14%">
			<col width="6%">
			<col width="10%">
			<col width="8%">
			<col width="14%">
			<col width="*">
		</colgroup>
		<thead>
		<tr>
			<th>회원번호</th>
			<th>과목</th>
			<th>상태</th>
			<th>회원명</th>
			<th>성별</th>
			<th>생년월일</th>
			<th>지사</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
		</thead>
	</table>
	<div style="height:305px;overflow-y:scroll;">
		<table style="border-spacing: 0;width: 100%;" border="1">
			<colgroup>
				<col width="8%">
				<col width="6%">
				<col width="6%">
				<col width="14%">
				<col width="6%">
				<col width="10%">
				<col width="8%">
				<col width="14%">
				<col width="*">
			</colgroup>
			<tbody>
			{{#each existsMemberList}}
			<tr>
				<td>{{mKey}}<a href="javascript:$.checkForm('{{mKey}}','','');"><img src="/public/img/common/ico_arw02.gif"></a></td>
				<td>{{subj}}</td>
				<td>{{stateCD}}</td>
				<td>{{mFstName}} {{mLstName}}</td>
				<td>{{sexCD}}</td>
				<td>{{birthYMD}}</td>
				<td>{{jisaCD}}</td>
				<td>{{tel}}</td>
				<td>{{addr}}</td>
			</tr>
			{{else}}
			<tr>
				<td colspan="9">검색된 결과가 없습니다.</td>
			</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</script>
<script id="mujinTemplate" type="text/x-handlebars-template">
	<span>▣ 무료진단 회원 검색</span>
	<table style="border-spacing: 0;width: 100%;" border="1">
		<colgroup>
			<col width="9%">
			<col width="9%">
			<col width="6%">
			<col width="12%">
			<col width="6%">
			<col width="14%">
			<col width="8%">
			<col width="8%">
			<col width="*">
		</colgroup>
		<thead>
		<tr>
			<th>처방일자</th>
			<th>회원번호</th>
			<th>과목</th>
			<th>교실</th>
			<th>종류</th>
			<th>회원명</th>
			<th>등급</th>
			<th>요일</th>
			<th>주소</th>
		</tr>
		</thead>
	</table>
	<div style="height:305px;overflow-y:scroll;">
		<table style="border-spacing: 0;width: 100%;" border="1">
			<colgroup>
				<col width="9%">
				<col width="9%">
				<col width="6%">
				<col width="12%">
				<col width="6%">
				<col width="14%">
				<col width="8%">
				<col width="8%">
				<col width="*">
			</colgroup>
			<tbody>
			{{#each mujinMemberList}}
			<tr>
				<td>{{omrDate}}</td>
				<td>{{hkey}}<a href="javascript:$.checkForm('{{hkey}}','{{kwamok}}','{{omrDate}}');"><img src="/public/img/common/ico_arw02.gif"></a></td>
				<td>{{kwamok}}</td>
				<td>{{sname}}({{skey}})</td>
				<td>{{omrKind}}</td>
				<td>{{mFirstName}} {{mLastName}}</td>
				<td>{{omrGrd}}</td>
				<td>{{omrDay}}</td>
				<td>{{omrAddr}}</td>
			</tr>
			{{else}}
			<tr>
				<td colspan="9">검색된 결과가 없습니다.</td>
			</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</script>
</body>
</html>
