<#include "/header.ftl">
<body>
<#include "/function.ftl">
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
				<div class="page-path">
					<ul>
						<li class="home"><a href="#none">홈</a> &gt; </li>
						<li><a href="#none">회원업무</a> &gt; </li>
						<li><a href="#none">관리정보</a> &gt; </li>
						<li class="current"><a href="#none">회원조회</a></li>
					</ul>
				</div>
				<div class="tab-A">
					<ul>
						<li class="first active"><a href="/manageInfo">해외회원</a></li>
						<li class="last"><a href="/manageInfo/korMemberSearch">한국회원</a></li>
					</ul>
				 </div>
				 <div class="mgt-20">
				 	<div class="message-top2" style="margin-bottom: 20px;font-size: 13px;">
						<form class="" name="" action="" id="frm1" method="post">
							<input type="hidden" name="orderby" value=""/>
							<input type="hidden" name="ord" value=""/>
							<div class="user_info">
								<input type="radio" name="type" id="mKey" value="1" style="width: 20px;"><label for="mKey">회원번호</label> 
								<input type="radio" name="type" id="mName" value="2" checked="checked" style="width: 20px;"><label for="mName">이름</label>&nbsp;
								<input type="text" class="text" name="searchWord" />&nbsp;&nbsp;
								<span>생년월일</span><input type="text" class="datePicker_yymm" name="birthDay" readonly="readonly" />
								<span class="button"><input size="8px" type="button" name="" id="searchBtn" value="검색"></span>
								<span>
									<input type="checkbox" name="check" value="1" checked="checked" style="width: 20px;"/>
									MY ${compare(authMemberInfo.empKeyLvCD,"JA","지사",compare(authMemberInfo.empKeyLvCD,"FA","교육원",""))}
								</span><br/><br/>
								<p>회원번호, 이름 : <font color="red">필수</font>항목, 생년월일 : 선택항목</p>
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
<p class="text-r">총 : {{memberSearchInfoList.length}}건</p>
<div class="tbl-type-D" style="width: 967px;overflow: auto;overflow-y:hidden; height: 435px;">
	<table style="border: 0px;border-spacing: 0;width: 1300px;">
		<colgroup>
			<col width="2%"/>
			<col width="5%"/>
			<col width="3%"/>
			<col width="4%"/>
			<col width="10%"/>
			<col width="3%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="2%"/>
			<col width="8%"/>
			<col width="39%"/>
		</colgroup>
		<tbody>
			<tr>
				<th>순번</th>
				<th>회원번호</th>
				<th>과목</th>
				<th>상태</th>
				<th>회원명</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>최종입복회일</th>
				<th>최종퇴회일</th>
				<th>지사</th>
				<th>전화번호</th>
				<th>주소</th>
			</tr>
		</tbody>
	</table>
	<div style="overflow: auto;width:1300px; height:400px;">
		<table style="border: 0px;border-spacing: 0;width: 1300px;">
			<colgroup>
			<col width="2%"/>
			<col width="5%"/>
			<col width="3%"/>
			<col width="4%"/>
			<col width="10%"/>
			<col width="3%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="2%"/>
			<col width="8%"/>
			<col width="39%"/>
		</colgroup>
			<tbody>
			{{#each memberSearchInfoList}}
				<tr>
					<td>{{inc @index}}</td>
					<td>{{mKey}}</td>
					<td>{{kwamok}}</td>
					<td>{{state}}</td>
					<td><a href="javascript:$.memberInfoPop('{{mKey}}','{{sKey}}','{{kwamok}}')">{{mFstName}}</a></td>
					<td>{{sex}}</td>
					<td>{{birthDay}}</td>
					<td>{{lastIpBokheiDay}}</td>
					<td>{{huheiDay}}</td>
					<td>{{jisa}}</td>
					<td>{{tel}}</td>
					<td>{{addr}}</td>
				</tr>
			{{else}}
				<tr>
					<td colspan="12">검색된 결과가 없습니다.</td>
				</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</div>
</script>
</html>
