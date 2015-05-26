<#include "/popHeader.ftl">
<body>
<#include "/function.ftl">
<div id="popWrapper" class="wrapper" style="width:850px;">

	<!-- popHeader -->
	<#include "/popLogo.ftl">
	<!-- popContent -->
	<div id="popContent" style="height:625px">
	<form name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('')}"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
		<input type="hidden" name="searchKwamok" value=""/>
	</form>
		<!-- 로고 및 탭 -->
		<#include "/memberCard/memberPopMenu.ftl">

		<p class="mgt-10 mgb-10">${memberDetailInfo.mFstName?default('') } 회원 상세정보 입니다.</p>
		<div class="tbl-type-H">
			<form method="post" action="" name="frm1" class="">
			<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
			<input type="hidden" name="fstEmpKey" value="${memberDetailInfo.fstEmpKey?default('') }"/>
			<input type="hidden" name="fstEmpName" value="${memberDetailInfo.fstEmpName?default('') }"/>
			<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
			<input type="hidden" name="sndEmpKey" value="${memberDetailInfo.sndEmpKey?default('') }"/>
			<input type="hidden" name="sndEmpName" value="${memberDetailInfo.sndEmpName?default('') }"/>
			<input type="hidden" name="fstYoil" value="${memberDetailInfo.fstYoil?default('') }"/>
			<input type="hidden" name="sndYoil" value="${memberDetailInfo.sndYoil?default('') }"/>
			<input type="hidden" name="sKey" value="${memberDetailInfo.sKey?default('') }"/>
			<input type="hidden" name="sName" value="${memberDetailInfo.sName?default('') }"/>
			<input type="hidden" name="depid1" value="${memberDetailInfo.depid1?default('') }"/>
			<input type="hidden" name="depid2" value="${memberDetailInfo.depid2?default('') }"/>
				<table style="border-spacing: 0;width: 800px;">
					<colgroup>
						<col width="80px">
						<col width="196px">
						<col width="80px">
						<col width="180px">
						<col width="80px">
						<col width="180px">
					</colgroup>
					<tr>
						<th>과목</th>
							<td>${memberDetailInfo.kwamok } / ${compare(memberDetailInfo.stateCD,"1","유지","퇴회")}</td>
						<th>교실[1]</th>
							<td>
								<#if memberDetailInfo.fstEmpKey?exists>
									${memberDetailInfo.fstEmpKey }(${memberDetailInfo.fstEmpName })
								<#else>
									없음
								</#if>
							</td>
						<th>관리요일[1]</th>
							<td>
								<#if memberDetailInfo.fstYoil?exists>
									
									<select name="cngFstYoil">
										<option value="1" ${compare(memberDetailInfo.fstYoil,"1","selected='selected'","")}>SUN</option>
										<option value="2" ${compare(memberDetailInfo.fstYoil,"2","selected='selected'","")}>MON</option>
										<option value="3" ${compare(memberDetailInfo.fstYoil,"3","selected='selected'","")}>TUE</option>	
										<option value="4" ${compare(memberDetailInfo.fstYoil,"4","selected='selected'","")}>WED</option>
										<option value="5" ${compare(memberDetailInfo.fstYoil,"5","selected='selected'","")}>THU</option>
										<option value="6" ${compare(memberDetailInfo.fstYoil,"6","selected='selected'","")}>FRI</option>
										<option value="7" ${compare(memberDetailInfo.fstYoil,"7","selected='selected'","")}>SAT</option>
									</select>
								<#else>
									없음
								</#if>	
							</td>
					</tr>
					<tr>
						<th>회원번호</th>
							<td>${memberDetailInfo.mKey }</td>
						<th>교실[2]</th>
							<td>
								<#if memberDetailInfo.sndEmpKey?exists &&memberDetailInfo.sndEmpKey?length gt 0 >
									${memberDetailInfo.sndEmpKey }(${memberDetailInfo.sndEmpName })
								<#else>
									없음
								</#if>
							</td>
						<th>관리요일[2]</th>
							<td>
								<#if memberDetailInfo.sndYoil?exists>
									없음
								<#else>
									<select name="cngSndYoil">
										<option value="1" ${compare(memberDetailInfo.sndYoil,"1","selected='selected'","")}>SUN</option>
										<option value="2" ${compare(memberDetailInfo.sndYoil,"2","selected='selected'","")}>MON</option>
										<option value="3" ${compare(memberDetailInfo.sndYoil,"3","selected='selected'","")}>TUE</option>
										<option value="4" ${compare(memberDetailInfo.sndYoil,"4","selected='selected'","")}>WED</option>
										<option value="5" ${compare(memberDetailInfo.sndYoil,"5","selected='selected'","")}>THU</option>
										<option value="6" ${compare(memberDetailInfo.sndYoil,"6","selected='selected'","")}>FRI</option>
										<option value="7" ${compare(memberDetailInfo.sndYoil,"7","selected='selected'","")}>SAT</option>
									</select>
								</#if>	
							</td>
					</tr>
					<tr>
						<th>회원명</th>
							<td><input type="text" name="mFstName" value="${memberDetailInfo.mFstName }"/></td>
						<th>생년월일</th>
							<td><input type="text" name="birthYMD" class="datePicker" value="${memberDetailInfo.birthYMD }" title="생년월일" readonly></td>
						<th>성별</th>
							<td>
							<input type="radio" name="sexCD" value="M" ${compare(memberDetailInfo.sexCD,"M","checked='checked'","")}>
							<label for="">남</label>
							<input type="radio" name="sexCD" value="F" ${compare(memberDetailInfo.sexCD,"F","checked='checked'","")}>
							<label for="">여</label></td>
					</tr>
					<tr>
						<th>학교</th>
							<td><input type="text" name="schoolName"  value="${memberDetailInfo.schoolName?default('') }" title="학교"></td>
						<th>학년</th>
							<td colspan="3">
								<select name="gradeCD" style="width: 200px;">
									<#list dtlCD as gradeCD>
										<#if gradeCD.mstCD = '0003'>
											<option value="${gradeCD.dtlCD }" ${compare(gradeCD.dtlCD,memberDetailInfo.gradeCD,"selected='selected'","")}>${gradeCD.dtlCDNM }</option>
										</#if>
									</#list>
								</select>
							</td>
					</tr>
					<tr>
						<th>주소</th>
							<td colspan="5">
							<input type="text" name="addr" width="500px" value="${memberDetailInfo.addr?default('') }" style="width: 500px;"/> 
							</td>
					</tr>
					<tr>
						<th>자택번호</th>
							<td>
								<p class="mgt-5">
								<input type="text" class="text1" name="tel1" value="${memberDetailInfo.tel?split('-')[0]?default('')}" style="width: 30px;" maxlength="4" >
								-
								<input type="text" class="text1" name="tel2" value="${memberDetailInfo.tel?split('-')[1]?default('')}" style="width: 30px;" maxlength="4" >
								-
								<input type="text" class="text1" name="tel3" value="${memberDetailInfo.tel?split('-')[2]?default('')}" style="width: 30px;" maxlength="4" >
								</p>
							</td>
						<th>휴대번호</th>
							<td colspan="3">
								<p>
									<label class="label-space" for="">휴대전화 :</label>
									<input type="text" class="text1" name="gPhone1" value="${memberDetailInfo.gPhone?split('-')[0]?default('')}"  maxlength="3" >
									-
									<input type="text" class="text1" name="gPhone2" value="${memberDetailInfo.gPhone?split('-')[1]?default('')}"  maxlength="4" >
									-
									<input type="text" class="text1" name="gPhone3" value="${memberDetailInfo.gPhone?split('-')[2]?default('')}"  maxlength="4" >
								</p>
								<p class="mgt-5">
									<label class="label-space" for="">긴급전화 :</label>
									<input type="text" class="text1" name="ePhone1" value="${memberDetailInfo.ePhone?split('-')[0]?default('')}"  maxlength="3" >
									-
									<input type="text" class="text1" name="ePhone2" value="${memberDetailInfo.ePhone?split('-')[1]?default('')}"  maxlength="4" >
									-
									<input type="text" class="text1" name="ePhone3" value="${memberDetailInfo.ePhone?split('-')[2]?default('')}"  maxlength="4" >
								</p>
							</td>
					</tr>
					<tr>
						<th>학부모 성명</th>
							<td>
								<input type="text" style="width: 96px" class="text" name="gFstName" value="${memberDetailInfo.gFstName?default('') }" >
							</td>
						<th>입회경로</th>
							<td colspan="3">
								<select name="ipheiPath" style="width: 300px;">
									<#list dtlCD as path>
										<#if path.mstCD = '0009'>
											<option value="${path.dtlCD }" ${compare(path.dtlCDNM,memberDetailInfo.ipheiPath,"selected='selected'","")}>${path.dtlCDNM }</option>
										</#if>
									</#list>
								</select>
							</td>
					</tr>
					<tr>
						<th>이메일</th>
							<td colspan="5">
							<p>
								<label class="label-space" for="">회  원 :</label>
								<input type="text" class="text1" name="mEmail1" value="${memberDetailInfo.mEmail?split('@')[0]?default('')}" style="width: 150px;" >
								@
								<input type="text" class="text1" name="mEmail2" value="${memberDetailInfo.mEmail?split('@')[1]?default('')}" style="width: 150px;" >
							</p>
							<p>
								<label class="label-space" for="">학부모 :</label>
								<input type="text" class="text1" name="gEmail1" value="${memberDetailInfo.gEmail?split('@')[0]?default('')}" style="width: 150px;" >
								@
								<input type="text" class="text1" name="gEmail2" value="${memberDetailInfo.gEmail?split('@')[1]?default('')}" style="width: 150px;" >
							</p>
							</td>
					</tr>
					<tr>
					</tr>
					<tr>
					</tr>
					<tr>
						<th>상담내용<br>및<br>특이사항</th>
							<td colspan="5">
								<input type="text" name="specialComment" value="${memberDetailInfo.specialComment?default('') }" style="width: 600px;height: 50px;"/>
							</td>
					</tr>
				</table>
				<div class="btn-box">
					<span class="button btn-type-G"><a href="javascript:checkSubmit();" class="w-65">변경</a></span>
					<span class="button btn-type-E"><a class="w-65" href="javascript:self:close();">닫기</a></span>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
	function checkSubmit(){
		var form = document.frm1;
		var selectSexCD = '';
		for(var i=0; i<form.sexCD.length; i++){
			if(form.sexCD[i].checked == true){
				selectSexCD = form.sexCD[i].value;
			}
		}
		var mFstName = '${memberDetailInfo.mFstName?default('') }';
		var fstYoil = '${memberDetailInfo.fstYoil?default('')}';
		var sndYoil = '${memberDetailInfo.sndYoil?default('')}';
		var birthYMD = '${memberDetailInfo.birthYMD?default('') }';
		var sexCD = '${memberDetailInfo.sexCD?default('')}';
		var schoolName = '${memberDetailInfo.schoolName?default('')}';
		var gradeCD = '${memberDetailInfo.gradeCD?default('')}';
		var addr = '${memberDetailInfo.addr?default('') }';
		var tel1 = '${memberDetailInfo.tel?split('-')[0]?default('')}';
		var tel2 = '${memberDetailInfo.tel?split('-')[1]?default('')}';
		var tel3 = '${memberDetailInfo.tel?split('-')[2]?default('')}';
		var gPhone1 = '${memberDetailInfo.gPhone?split('-')[0]?default('')}';
		var gPhone2 = '${memberDetailInfo.gPhone?split('-')[1]?default('')}';
		var gPhone3 = '${memberDetailInfo.gPhone?split('-')[2]?default('')}';
		var ePhone1 = '${memberDetailInfo.ePhone?split('-')[0]?default('')}';
		var ePhone2 = '${memberDetailInfo.ePhone?split('-')[1]?default('')}';
		var ePhone3 = '${memberDetailInfo.ePhone?split('-')[2]?default('')}';
		var gFstName = '${memberDetailInfo.gFstName?default('') }';
		var mEmail1 = '${memberDetailInfo.mEmail?split('@')[0]?default('')}';
		var mEmail2 = '${memberDetailInfo.mEmail?split('@')[1]?default('')}';
		var gEmail1 = '${memberDetailInfo.gEmail?split('@')[0]?default('')}';
		var gEmail2 = '${memberDetailInfo.gEmail?split('@')[1]?default('')}';
		var specialComment = '${memberDetailInfo.specialComment?default('') }';
		if(form.mFstName.value==""){
			alert('회원명은 공백을 입력할 수 없습니다.');
			form.mFstName.focus();
			return;
		}
		if(!((form.tel1.value!=''&&form.tel2.value!=''&&form.tel3.value!='')||(form.tel1.value==''&&form.tel2.value==''&&form.tel3.value==''))){
			alert('자택번호가 올바르지 않습니다.');
			form.tel1.focus();
			return;
		}
		if(!((form.gPhone1.value!=''&&form.gPhone2.value!=''&&form.gPhone3.value!='')||(form.gPhone1.value==''&&form.gPhone2.value==''&&form.gPhone3.value==''))){
			alert('휴대전화가 올바르지 않습니다.');
			form.gPhone1.focus();
			return;
		}
		if(!((form.ePhone1.value!=''&&form.ePhone2.value!=''&&form.ePhone3.value!='')||(form.ePhone1.value==''&&form.ePhone2.value==''&&form.ePhone3.value==''))){
			alert('긴급전화가 올바르지 않습니다.');
			form.ePhone1.focus();
			return;
		}
		if(!((form.mEmail1.value!=''&&form.mEmail2.value!='')||(form.mEmail1.value==''&&form.mEmail2.value==''))){
			alert('회원 이메일이 올바르지 않습니다.');
			form.mEmail1.focus();
			return;
		}
		if(!((form.gEmail1.value!=''&&form.gEmail2.value!='')||(form.gEmail1.value==''&&form.gEmail2.value==''))){
			alert('학부모 이메일이 올바르지 않습니다.');
			form.gEmail1.focus();
			return;
		}
		if(form.mFstName.value==mFstName&&
				form.fstYoil.value==fstYoil&&
				form.sndYoil.value==sndYoil&&
				form.birthYMD.value==birthYMD&&
				selectSexCD==sexCD&&
				form.schoolName.value==schoolName&&
				form.gradeCD.value==gradeCD&&
				form.addr.value==addr&&
				form.tel1.value==tel1&&
				form.tel2.value==tel2&&
				form.tel3.value==tel3&&
				form.gPhone1.value==gPhone1&&
				form.gPhone2.value==gPhone2&&
				form.gPhone3.value==gPhone3&&
				form.ePhone1.value==ePhone1&&
				form.ePhone2.value==ePhone2&&
				form.ePhone3.value==ePhone3&&
				form.gFstName.value==gFstName&&
				form.mEmail1.value==mEmail1&&
				form.mEmail2.value==mEmail2&&
				form.gEmail1.value==gEmail1&&
				form.gEmail2.value==gEmail2&&
				form.specialComment.value==specialComment){
			alert('변경된 사항이 없습니다.');
			return;
		}else{
			if(confirm("수정하시겠습니까?")){
				form.action = '/memberCard/memberInfoUpdate';
				form.submit();
			}
		}
	}
</script>
</body>
</html>
