<#include "/header.ftl">
<body>
	<div id="wrapper">
		<#include "/headerMenu.ftl">
		<!-- container -->
		<div id="container">
			<div id="content">
				<div class="side-content"><#include "/leftContents.ftl">
					<#include "/leftMenu.ftl"></div>
				<div id="primary_content" class="primary-content">
					<div class="page-path">
						<ul>
							<li class="home"><a href="#none">홈</a> &gt;</li>
							<li><a href="#none">회원업무</a> &gt;</li>
							<li><a href="#none">관리정보</a> &gt;</li>
							<li class="current"><a href="#none">퇴회자리스트</a></li>
						</ul>
					</div>
					<div class="mgt-20">
						<div class="message-top2" style="margin-bottom: 20px">
						<form class="" name="" action="" id="frm1" method="post">
							<div class="user_info">
								<span>교실명</span> 
								<select name="empKey" style="vertical-align: middle;width: 200px;">
									<option value="">전체</option>
									<#list classList as class>
									<option value="${class.empKey }">${class.empKey }
										(${class.empName })</option>
									</#list>
								</select> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>과목</span> 
								<select name="kwamok" style="vertical-align: middle;width: 80px;text-align: center;">
									<option value="">전체</option>
									<#list kwamokList as kwamok>
									<option value="${kwamok }">${kwamok }</option>
									</#list>
								</select>
								<span>생년월일</span>
								<input type="text" name="startBirthDate" class="datePicker" readonly="readonly"> ~ 
								<input type="text" name="endBirthDate" class="datePicker" readonly="readonly">
								<br/><br/>
								<span>교실번호</span><input type="text" class="text" name="hu_skey" />
								<span>휴회일자</span>
								<input type="text" name="startHuheiDate" class="datePicker" value="${.now?string("yyyy-MM-dd")}" readonly="readonly"> ~ 
								<input type="text" name="endHuheiDate" class="datePicker" value="${.now?string("yyyy-MM-dd")}" readonly="readonly">
								<span class="button"><input size="8px" type="button" name="" id="search" value="검색"></span>
							</div>
						</form>
						</div>
						<div id="mainContent">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
			<!-- //container -->
			<#include "/footer.ftl">
</body>
<script id="template" type="text/x-handlebars-template"> 
<span class="button"><input size="8px" type="button" name="" id="excelHuhei" value="엑셀"></span>
<p class="text-r">총 : {{huheiMemberList.length}}건</p>
<div class="tbl-type-D" style="width: 967px;overflow: auto;overflow-y:hidden; height: 500px;">
	<table style="border: 0px;border-spacing: 0;width: 1300px;">
		<colgroup>
			<col width="30px"/>
			<col width="50px"/>
			<col width="100px"/>
			<col width="80px"/>
			<col width="150px"/>
			<col width="30px"/>
			<col width="30px"/>
			<col width="80px"/>
			<col width="120px"/>
			<col width="80px"/>
			<col width="100px"/>
			<col width="230px"/>
			<col width="120px"/>
			<col width="100px"/>
		</colgroup>
		<tbody>
			<tr>
				<th>번호</th>
				<th>교실번호</th>
				<th>교실명</th>
				<th>회원번호</th>
				<th>회원명</th>
				<th>상태</th>
				<th>과목</th>
				<th>생년월일</th>
				<th>학년</th>
				<th>퇴회일자</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>퇴회사유</th>
				<th>진행과목</th>
			</tr>
		</tbody>
	</table>
	<div style="overflow: auto;width:1300px; height:462px;">
		<table style="border: 0px;border-spacing: 0;width: 1300px;">
			<colgroup>
				<col width="30px"/>
				<col width="50px"/>
				<col width="100px"/>
				<col width="80px"/>
				<col width="150px"/>
				<col width="30px"/>
				<col width="30px"/>
				<col width="80px"/>
				<col width="120px"/>
				<col width="80px"/>
				<col width="100px"/>
				<col width="230px"/>
				<col width="120px"/>
				<col width="100px"/>
			</colgroup>
			<tbody>
			{{#each huheiMemberList}}
				<tr>
					<td>{{inc @index}}</td>
					<td>{{empKey}}</td>
					<td>{{empName}}</td>
					<td>{{mKey}}</td>
					<td>{{mFstName}}</td>
					<td>{{stateName}}</td>
					<td>{{subj}}</td>
					<td>{{birthYMD}}</td>
					<td>{{gradeName}}</td>
					<td>{{huheiYMD}}</td>
					<td>{{tel}}</td>
					<td>{{addr}}</td>
					<td>{{huheiSayuName}}</td>
					<td>{{jin}}</td>
				</tr>
			{{else}}
				<tr>
					<td colspan="14" height="300">검색된 결과가 없습니다.</td>
				</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</div>
</script>
</html>
