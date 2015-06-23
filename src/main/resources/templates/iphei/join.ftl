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
				<form id="ipheiFrm"  method="POST">
					<input type="hidden" name="pIpheiKind" value="${pIpheiKind}">
					<input type="hidden" name="pIpheiday" value="${pIpheiday}">
					<input type="hidden" name="pIpheiDepid" value="${pIpheiDepid}">
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="12%">
							<col width="12%">
							<col width="12%">
							<col width="12%">
							<col width="12%">
							<col width="*">
						</colgroup>
						<tr>
							<th>회원번호</th>
							<td>
							<#if pMkey?has_content && pIpheiKind != "01" >
								<input type="text" name="pMkey" value="${pMkey}" readonly="readonly">
							<#else>
								자동부여
								<input type="hidden" name="pMkey" value="">
							</#if>
							</td>
							<th>입회일자</th>
							<td>${pIpheiday}</td>
							<th>입회경로</th>
							<td>
								<select name="pIpGuide" id="pIpGuide">
									<option value="">선택</option>
									<#if ipheiComCodeList?has_content>
										<#list ipheiComCodeList as comCode>
											<option value="${comCode.dtlCD}">${comCode.dtlCDNM}</option>
										</#list>
									</#if>
								</select>
							</td>
						</tr>
						<tr>
							<th>회원명*</th>
							<td colspan="3">
							<#if pIpheiKind == "02" || pIpheiKind == "03">
								<input type="text" name="pFirstName" id="pFirstName" value="${memInfo.mFirstName}" >
							<#else>
								<input type="text" name="pFirstName" id="pFirstName" >
							</#if>
							</td>
							<th>생년월일*</th>
							<td><input type="text" name="pBirthDay" id="pBirthDay" value="${memInfo.mBirthYMD}" class="datePicker" readonly="readonly"><input type="radio" name="pSex" <#if !memInfo.mSex?has_content || memInfo.mSex == "M" > checked="checked" </#if> value="M">남<input type="radio" name="pSex" <#if memInfo.mSex == "F" >  checked="checked" </#if> value="F">여</td>
						</tr>
						<tr>
							<th>학교명</th>
							<td colspan="3"><input name="pSchool" type="text" value="${memInfo.mSchool}"></td>
							<th>학년</th>
							<td>
								<select name="pMemGrade" id="pMemGrade">
									<option value="">선택</option>
									<#if gradeComCodeList?has_content>
										<#list gradeComCodeList as gComCode>
											<option value="${gComCode.dtlCD}"  ${compare(gComCode.dtlCD,memInfo.gradeCD,"selected='selected'","")}  >${gComCode.dtlCDNM}</option>
										</#list>
									</#if>
								</select>
							</td>
						</tr>
						<tr>
							<th>주소*</th>
							<td colspan="3"><input name="pAddr" id="pAddr" type="text" style="width:330px;" value="${memInfo.mAddr}"></td>
							<th>학부모명</th>
							<td><input type="text" name="pGfirstname" id="pGfirstname" value="${memInfo.gFirstName}" ></td>
						</tr>
						<tr>
							<th>자택번호*</th>
							<td colspan="3"><input type="text" name="tel1" id="tel1" style="width:60px;" value="${memInfo.mTel1}" maxlength="3">-<input type="text" name="tel2" id="tel2" style="width:80px;" value="${memInfo.mTel2}" maxlength="4">-<input type="text" name="tel3" id="tel3" style="width:80px;" value="${memInfo.mTel3}" maxlength="4"></td>
							<th>휴대번호*</th>
							<td>
								휴대전화 <input type="text" name="phone1" id="phone1" style="width:60px;" value="${memInfo.gPhone1}" maxlength="3">-<input type="text" name="phone2" id="phone2" style="width:80px;" value="${memInfo.gPhone2}" maxlength="4">-<input type="text" name="phone3" id="phone3" style="width:80px;" value="${memInfo.gPhone3}" maxlength="4"><br/>
								긴급전화 <input type="text" name="ePhone1" id="ePhone1" style="width:60px;" value="${memInfo.ePhone1}" maxlength="3">-<input type="text" name="ePhone2" id="ePhone2" style="width:80px;" value="${memInfo.ePhone2}" maxlength="4">-<input type="text" name="ePhone3" id="ePhone3" style="width:80px;" value="${memInfo.ePhone3}" maxlength="4">
							</td>
						</tr>
						<tr>
							<th>e-Mail</th>
							<td colspan="3">
								회원  : <input type="text" name="email1" style="width:140px;" id="email1" value="${memInfo.email1}">@<input type="text" name="email2" id="email2" style="width:100px;" value="${memInfo.email2}"><br/>
								학부모: <input type="text" name="gEmail1" id="gEmail1" style="width:140px;" value="${memInfo.gEmail1}" >@<input type="text" name="gEmail2" id="gEmail2" style="width:100px;" value="${memInfo.gEmail2}">
							</td>
							<th>특이사항<br/>(300자이내)</th>
							<td>
								<textarea rows="2" cols="1" name="specialComment" id="specialComment" style="width: 300px;height:50px;">${memInfo.specialComment}</textarea>
							</td>
						</tr>
					</table>
					<br/>
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="10%">
							<col width="8%">
							<col width="*">
							<col width="12%">
							<col width="12%">
							<col width="12%">
							<col width="12%">
							<col width="8%">
						</colgroup>
						<tr>
							<th rowspan="2">입회과목</th>
							<th rowspan="2">불출수</th>
							<th colspan="2">관리</th>
							<th rowspan="2">진단등급</th>
							<th colspan="3">입금</th>
						</tr>
						<tr>
							<th>교실</th>
							<th>첫관리방문일</th>
							<th>입회비</th>
							<th>월회비</th>
							<th>산출</th>
						</tr>
						<#if subjInfoList?has_content>
							<#list subjInfoList as subjInfo>
							<tr>
								<td>${subjInfo.ipheiSubj.subj}
									<#if subjInfo.ipheiSubj.daegisubj != "" || subjInfo.ipheiSubj.dongsubj != "" || subjInfo.ipheiSubj.mkucode == "" || (subjInfo.ipheiSubj.agreeNum > 0) >
									불가
									<input type="hidden" name="rejectSubj" value="${subjInfo.ipheiSubj.subj}">
									<input type="hidden" name="subj" value="">
									<#else>
									<input type="hidden" name="rejectSubj" value="">
									<input type="hidden" name="subj" value="${subjInfo.ipheiSubj.subj}">
									</#if>
								</td>
								<td>
									<select>
										<option value="1">1</option>
									</select>
								</td>
								<td>
									<#if classList?has_content>
										<select name="fstClass">
											<option value="">선택</option>
										<#list classList as class>
											<option value="${class.empKey}">${class.empName}</option>
										</#list>
										</select>
									</#if>
								</td>
								<td>
									<#if fstDayList?has_content>
										<select name="fstDay" id="fstDay${subjInfo_index}">
										<#list fstDayList as fstDay>
											<option value="${fstDay}" ${compare(pIpheiday,fstDay,"selected='selected'","")}>${fstDay}</option>
										</#list>
										</select>
									</#if>
								</td>
								<td>
									<#if subjInfo.mjGrade?has_content>
										<select name="mjGrade" id="mjGradeId${subjInfo_index}">
											<option value="">선택</option>
										<#list subjInfo.mjGrade as mj>
											<option value="${mj}">${mj}</option>
										</#list>
										</select>
									</#if>
								</td>
								<td><input type="text" value="0" name="ipheibi" style="width:80px;" readonly="readonly"></td>
								<td><input type="text" value="0" name="wolheibi" style="width:80px;" readonly="readonly"></td>
								<td>
									<#if subjInfo.ipheiSubj.daegisubj != "" || subjInfo.ipheiSubj.dongsubj != "" || subjInfo.ipheiSubj.mkucode == "" || (subjInfo.ipheiSubj.agreeNum > 0) >
									<input type="button" value="+" onclick="alert('입회 불가능한 과목입니다.');">
									<#else>
									<input type="button" value="+" onclick="$.deposit_set('${pIpheiday}','${pIpheiDepid}','${pJisaCD}','${subjInfo.ipheiSubj.subj}','${subjInfo.ipheiSubj.mkucode}','${subjInfo_index}');">
									</#if>
								<input type="hidden" name="orgIpheibi" value="0">
								<input type="hidden" name="orgWolheibi" value="0">
								<input type="hidden" name="mGubun">
								<input type="hidden" name="mSayu">
								<input type="hidden" name="restymw">
								<input type="hidden" name="enterwol">
								<input type="hidden" name="mkucode" value="${subjInfo.ipheiSubj.mkucode}">
								</td>
							</tr>	
							</#list>
						</#if>
					</table>
					<br/>
					<div>
					<span>[입금총계]</span>
					<span>입회비:<input type="text" name="totalIpheibi" id="totalIpheibi" value="0" readonly="readonly"> 월회비:<input type="text" name="totalWolheibi" id="totalWolheibi" value="0" readonly="readonly"> 합계:<input type="text" name="totalIpgum" id="totalIpgum" value="0" readonly="readonly"> </span>
					</div>
					<div>
					<span>[연결업무]</span>
					<span><input type="radio" name="ipheiType" checked="checked" value="01" >입회 <input type="radio" name="ipheiType" value="02" >진도처방</span>
					</div>
				</form>							
				</div>
				<div class="btn-box">
					<span class="button btn-type-J">
						<a href="javascript:;" id="registIpheiBtn" class="w-120">등록</a>
					</span>
					<span id="loadingImg" style="display:none;"><img src="/public/img/common/loading.gif"  /></span>
					<span class="button btn-type-J">
						<a href="/iphei" id="cancelBtn" class="w-120">취소</a>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- //container -->
<#include "/footer.ftl">
</body>
</html>
