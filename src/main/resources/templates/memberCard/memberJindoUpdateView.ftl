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
	<div id="popContent" style="width: 800px; height: 624px;">
		<div class="tab-A">
		<ul>
			<li class="first">	<a href="javascript:$.locationGubun('/memberCard/memberJindoUpdateInfo','2')" style="cursor:hand;">진도조정</a></li>
			<li class="last active">	<a href="javascript:$.locationGubun('/memberCard/memberJindoUpdateView','2')"	style="cursor:hand;">진도조정내역</a></li>
		</ul>
		</div>
		<div class="message-top2" style="margin-bottom: 20px">
				<div class="user_info">
					<span>교실명</span> 
					<select name="empKey" style="vertical-align: middle;width: 200px;">
						<option value="">전체</option>
						<#list classList as class>
						<option value="${class.empKey }">${class.empKey }(${class.empName })</option>
						</#list>
					</select> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span>회원번호</span><input type="text" class="text" name="searchMKey" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span>과목</span> 
					<select name="searchKwamok" style="vertical-align: middle;width: 80px;text-align: center;">
						<option value="">전체</option>
						<#list kwamokList as kwamok>
						<option value="${kwamok }">${kwamok }</option>
						</#list>
					</select>
					<span>조정사유 : </span>
					<select name = "cngOpt">
						<#if dtlCDList?has_content>
						<#list dtlCDList as dtlCD>
							<option value="${dtlCD.dtlCD }">${dtlCD.dtlCDNM }</option>
						</#list>
						</#if>
					</select>
					<br/><br/>
					<span>검색일자</span>
					<input type="text" name="startHuheiDate" class="datePicker" value="${.now?string("yyyy-MM-dd")}" readonly="readonly"> ~ 
					<input type="text" name="endHuheiDate" class="datePicker" value="${.now?string("yyyy-MM-dd")}" readonly="readonly">
					<span class="button"><input size="8px" type="button" name="" id="search" value="검색"></span>
				</div>
		</div>
		 <p class="mgt-20 mgb-20"></p>
			 <div id="mainContent" class="tbl-type-D">
					<table style="border-spacing: 0;border: 0;width: 100%;">
						<colgroup>
							<col width="4%">
							<col width="9%">
							<col width="4%">
							<col width="4%">
							<col width="12%">
							<col width="9%">
							<col width="9%">
							<col width="12%">
							<col width="12%">
							<col width="7%">
							<col width="7%">
							<col width="9%">
						</colgroup>
						<tbody>
							<tr>
								<th>번호</th>
								<th>처방일자</th>
								<th>과목</th>
								<th>등급</th>
								<th>학년</th>
								<th>생년월일</th>
								<th>회원번호</th>
								<th>회원이름</th>
								<th>그래프</th>
								<th>평균</th>
								<th>미리보기</th>
								<th>진단종류</th>
							</tr>
							<#if omrGichoList?has_content>
							<#list omrGichoList as omrGicho>
							<tr>
								<td>${omrGicho_index+1}</td>
								<td>${omrGicho.omrDate}</td>
								<td>${omrGicho.kwamok}</td>
								<td>${omrGicho.omrGrd}</td>
								<td>${omrGicho.omrHak}</td>
								<td>${omrGicho.omrBirth }</td>
								<td>${omrGicho.hkey}</td>
								<td>${omrGicho.mFirstName}</td>
								<td>
									<select name="graph">
											<option value="B">막대그래프</option>
											<option value="R">방사형</option>
											<option value="L">선</option>
									</select>
								</td>
								<td>
									<select name="average">
											<option value="Y">Yes</option>
											<option value="N">No</option>
									</select>
								</td>
								<td>
									<a href="javascript:$.ippr20EM('${omrGicho.team}', '${omrGicho.omrDate}', '${omrGicho.hkey}', '${omrGicho.kwamok}', '${omrGicho.omrPath}', '${lang}');">
											<img src="/public/img/common/btn_detail_view.gif" width="23" height="19" border=0>
									</a>
								</td>
								<td>${omrGicho.omrKind }</td>
							</tr>
							</#list>
							<#else>
							<tr>
								<td class="line01" colspan='15'>☞ 검색 조건에 대한 데이타가 없습니다</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
					<div class="btn-box">
						<span class="button btn-type-E"><a class="w-65" style="width: 65px;" href="javascript:self:close();">닫기</a></span>
					</div>
		</div>
	</form>
</div>
</body>
<script id="template" type="text/x-handlebars-template"> 
	<table style="border-spacing: 0;border: 0;width: 100%;">
		<colgroup>
			<col width="4%">
			<col width="9%">
			<col width="4%">
			<col width="4%">
			<col width="12%">
			<col width="9%">
			<col width="9%">
			<col width="12%">
			<col width="12%">
			<col width="7%">
			<col width="7%">
			<col width="9%">
		</colgroup>
		<tbody>
			<tr>
				<th>번호</th>
				<th>처방일자</th>
				<th>과목</th>
				<th>등급</th>
				<th>학년</th>
				<th>생년월일</th>
				<th>회원번호</th>
				<th>회원이름</th>
				<th>그래프</th>
				<th>평균</th>
				<th>미리보기</th>
				<th>진단종류</th>
			</tr>
				{{#each omrGichoList}}
					<tr>
						<td>{{inc @index}}</td>
						<td>{{omrDate}}</td>
						<td>{{kwamok}}</td>
						<td>{{omrGrd}}</td>
						<td>{{omrHak}}</td>
						<td>{{omrBirth }}</td>
						<td>{{hkey}}</td>
						<td>{{mFirstName}}</td>
						<td>
						<select name="graph">
								<option value="B">막대그래프</option>
								<option value="R">방사형</option>
								<option value="L">선</option>
						</select>
						</td>
						<td>
						<select name="average">
								<option value="Y">Yes</option>
								<option value="N">No</option>
						</select>
						</td>
						<td>
							<a href="javascript:$.ippr20EM('{{team}}', '{{omrDate}}', '{{hkey}}', '{{kwamok}}', '{{omrPath}}', '{{../lang}}');">
								<img src="/public/img/common/btn_detail_view.gif" width="23" height="19" border=0>
							</a>
						</td>
						<td>{{omrKind }}</td>
					</tr>
			{{else}}
				<tr>
					<td class="line01" colspan='15'>☞ 검색 조건에 대한 데이타가 없습니다</td>
				</tr>
			{{/each}}
		</tbody>
	</table>
</script>
</html>