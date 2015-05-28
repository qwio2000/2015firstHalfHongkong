<#include "/popHeader.ftl">
<body>
<#include "/function.ftl">
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
				<li class="first">	<a href="javascript:$.locationGubun('/memberCard/memberOmrInfo','2')"	style="cursor:hand;">진단처방입력</a></li>
				<li class="last active"><a href="javascript:$.locationGubun('/memberCard/memberOmrView','2')"	style="cursor:hand;">진단처방출력</a></li>
			</ul>
		</div>
		<#assign curYY=.now?string("yyyy")>
		<div class="message-top">
			<span>처방년월 : </span>
			<select name="searchYY">
				<option value="${((searchYY?default(curYY))?number-1)?string("##0")}" ${compare(searchYY,((searchYY?default(curYY))?number-1)?string("##0"),"selected='selected'","")}>${((searchYY?default(curYY))?number-1)?string("##0")}년</option>
				<option value="${searchYY?default(curYY)}" ${compare(searchYY,searchYY?default(curYY),"selected='selected'","")}>${searchYY?default(curYY)}년</option>
				<option value="${((searchYY?default(curYY))?number+1)?string("##0")}" ${compare(searchYY,((searchYY?default(curYY))?number+1)?string("##0"),"selected='selected'","")}>${((searchYY?default(curYY))?number+1)?string("##0")}년</option>
			</select>
			<span>과목 : <#include "/memberCard/kwamokInfo.ftl"></span>
			<span class="button"><input id="searchBtn" style="width:80px" type ="button" name="" value="검색"></span>
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