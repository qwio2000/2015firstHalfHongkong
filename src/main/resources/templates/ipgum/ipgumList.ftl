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
							<li><a href="#none">회원관리</a> &gt;</li>
							<li class="current"><a href="#none">입금내역</a></li>
						</ul>
					</div>
					<div class="tab-A">
						<ul>
							<li class="first"><a href="/ipgum">입금입력</a></li>
							<li class="last active"><a href="/ipgum/ipgumList">입금내역</a></li>
						</ul>
					</div>
					<div class="mgt-20">
						<div class="message-top2" style="margin-bottom: 20px">
						<form class="" name="" action="" id="frm1" method="post">
							<div class="user_info">
								<span>교실</span> 
								<select name="empKey" style="vertical-align: middle;width: 200px;">
									<option value="all">전체</option>
									<#list classList as class>
									<option value="${class.empKey }">${class.empKey }
										(${class.empName })</option>
									</#list>
								</select> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>과목</span> 
								<select name="kwamok" style="vertical-align: middle;width: 80px;text-align: center;">
									<option value="all">전체</option>
									<#list kwamokList as kwamok>
									<option value="${kwamok }">${kwamok }</option>
									</#list>
								</select> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>입금종류</span> 
								<select name="existCD" style="vertical-align: middle;width: 80px;">
									<option value="all">전체</option>
									<option value="1">신입</option>
									<option value="2">기존</option>
								</select><br/><br/>
								<span>회원번호</span><input type="text" class="text" name="mKey" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<select name="chkVal" style="vertical-align: middle;width: 80px;">
									<option value="1">입금일</option>
									<option value="2">작업일</option>
								</select>&nbsp;&nbsp;
								<input type="text" class="datePicker" name="startDay" value="${.now?string("yyyy-MM-dd")}"/>&nbsp;~&nbsp;
								<input type="text" size="10px" class="datePicker" name="endDay" value="${.now?string("yyyy-MM-dd")}"/> 
								<span class="button"><input size="8px" type="button" name="" id="searchBtn" value="검색"></span>
							</div>
						</form>
						</div>
						<div id="mainContent" class="tbl-type-D">
							<div style="width: 967px;overflow: auto;overflow-y:hidden; height: 500px;">
								<table style="border: 0px;border-spacing: 0;width: 1300px;">
									<colgroup>
										<col width="50px"/>
										<col width="150px"/>
										<col width="80px"/>
										<col width="120px"/>
										<col width="50px"/>
										<col width="100px"/>
										<col width="150px"/>
										<col width="105px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
									</colgroup>
									<tbody>
										<tr>
											<th rowspan="2">번호</th>
											<th rowspan="2"><span class="sort sort-down">교육원</span></th>
											<th rowspan="2"><span class="sort sort-down">입금일</span></th>
											<th rowspan="2"><span class="sort sort-down">교실명</span></th>
											<th rowspan="2"><span class="sort sort-down">과목</span></th>
											<th rowspan="2"><span class="sort sort-down">회원번호</span></th>
											<th rowspan="2">회원명</th>
											<th rowspan="2">입금구분</th>
											<th colspan="3">입회비</th>
											<th colspan="3">월회비</th>
											<th colspan="3">계</th>
										</tr>
										<tr>
											<th>일반</th>
											<th>면제</th>
											<th>합계</th>
											<th>일반</th>
											<th>면제</th>
											<th>합계</th>
											<th>일반</th>
											<th>면제</th>
											<th>합계</th>
										</tr>
										<tr>
											<td colspan="8">합계</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
											<td>0</td>
										</tr>
									</tbody>
								</table>
								<div style="overflow: auto;width:1300px; height:400px;">
								<table style="border: 0px;border-spacing: 0;width: 1300px;">
									<colgroup>
										<col width="50px"/>
										<col width="150px"/>
										<col width="80px"/>
										<col width="120px"/>
										<col width="50px"/>
										<col width="100px"/>
										<col width="150px"/>
										<col width="105px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
										<col width="55px"/>
									</colgroup>
									<tbody>
									<tr>
										<td colspan="17" height="300">검색된 결과가 없습니다.</td>
									</tr>
									</tbody>
								</table>
							</div>
							</div>
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
<div style="width: 967px;overflow: auto;overflow-y:hidden; height: 500px;">
	<table style="border: 0px;border-spacing: 0;width: 1300px;">
		<colgroup>
			<col width="50px"/>
			<col width="150px"/>
			<col width="80px"/>
			<col width="120px"/>
			<col width="50px"/>
			<col width="100px"/>
			<col width="150px"/>
			<col width="105px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
			<col width="55px"/>
		</colgroup>
		<tbody>
			<tr>
				<th rowspan="2">번호</th>
				<th rowspan="2"><span class="sort sort-down">교육원</span></th>
				<th rowspan="2"><span class="sort sort-down">입금일</span></th>
				<th rowspan="2"><span class="sort sort-down">교실명</span></th>
				<th rowspan="2"><span class="sort sort-down">과목</span></th>
				<th rowspan="2"><span class="sort sort-down">회원번호</span></th>
				<th rowspan="2">회원명</th>
				<th rowspan="2">입금구분</th>
				<th colspan="3">입회비</th>
				<th colspan="3">월회비</th>
				<th colspan="3">계</th>
			</tr>
			<tr>
				<th>일반</th>
				<th>면제</th>
				<th>합계</th>
				<th>일반</th>
				<th>면제</th>
				<th>합계</th>
				<th>일반</th>
				<th>면제</th>
				<th>합계</th>
			</tr>
			<tr>
				<td colspan="8">합계</td>
				<td>{{totHeibi.sumIpheibi}}</td>
				<td>{{totHeibi.sumIpheibiFree}}</td>
				<td>{{totHeibi.sumTotIpheibi}}</td>
				<td>{{totHeibi.sumWolheibiFree}}</td>
				<td>{{totHeibi.sumWolheibi}}</td>
				<td>{{totHeibi.sumTotWolheibi}}</td>
				<td>{{totHeibi.sumTotIlbanHeibi}}</td>
				<td>{{totHeibi.sumTotHeibiFree}}</td>
				<td>{{totHeibi.sumTotHeibi}}</td>
			</tr>
		</tbody>
	</table>
	<div style="overflow: auto;width:1300px; height:400px;">
		<table style="border: 0px;border-spacing: 0;width: 1300px;">
			<colgroup>
				<col width="50px"/>
				<col width="150px"/>
				<col width="80px"/>
				<col width="120px"/>
				<col width="50px"/>
				<col width="100px"/>
				<col width="150px"/>
				<col width="105px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
				<col width="55px"/>
			</colgroup>
			<tbody>
			{{#each ipgumInfoList}}
				<tr>
					<td>{{inc @index}}</td>
					<td>{{depidNM}}</td>
					<td>{{ipgumYMD}}</td>
					<td>{{empName}}</td>
					<td>{{subj}}</td>
					<td>{{mkey}}</td>
					<td>{{mName}}</td>
					<td>{{existCDNM}}</td>
					<td>{{ipheibi}}</td>
					<td>{{ipheibiFree}}</td>
					<td>{{sumIpheibi}}</td>
					<td>{{wolheibi}}</td>
					<td>{{wolheibiFree}}</td>
					<td>{{sumWolheibi}}</td>
					<td>{{sumHeibi}}</td>
					<td>{{sumFreeHeibi}}</td>
					<td>{{totHeibi}}</td>
				</tr>
			{{else}}
				<tr>
					<td colspan="17" height="300">검색된 결과가 없습니다.</td>
				</tr>
			{{/each}}
			</tbody>
		</table>
	</div>
</div>
</script>
</html>
