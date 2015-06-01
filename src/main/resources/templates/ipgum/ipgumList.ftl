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
							<div class="union">
								<input id="" type="radio"><label for="">우리지역국</label> <input
									id="" type="radio"><label for="">우리사업국</label> <input
									id="" type="radio"><label for="">전사</label>
							</div>
							<div class="user_info">
								<span>회원번호</span><input size="6px" type="text" class="text" />
								<span>회원명</span><input size="6px" type="text" class="text" /> <span>주민번호</span><input
									type="text" size="10px" class="text" />&nbsp;-&nbsp;<input
									type="text" size="10px" class="text" /> <span class="button"><input
									size="8px" type="submit" name="" value="검색"></span>
							</div>
						</div>
						<div class="tbl-type-D" style="overflow: auto; height: 600px;">
							<form class="" name="" action="" method="post">
								<table cellspacing="0" cellpadding="0" border="0" width="100%" style="height: 300px;">
									<colgroup>
										<col width="5%">
										<col width="10%">
										<col width="10%">
										<col width="15%">
										<col width="15%">
										<col width="8%">
										<col width="*">
									</colgroup>
									<tbody>
										<tr>
											<th>번호</th>
											<th><span class="sort sort-down">과목</span></th>
											<th><span class="sort sort-down">회원명</span></th>
											<th><span class="sort sort-down">회원번호</span></th>
											<th><span class="sort sort-down">학교</span></th>
											<th><span class="sort sort-down">학년</span></th>
											<th>전화번호</th>
											<th>패키지</th>
										</tr>
										<tr>
											<td>105</td>
											<td>수학</td>
											<td>유재헌</a></td>
											<td>ACC00290<a href="#none"></td>
											<td>길음</td>
											<td>초1</td>
											<td>010-3899-7696</td>
											<td></td>
										</tr>
										<tr>
											<td>105</td>
											<td>수학</td>
											<td>유재헌</td>
											<td>ACC00290<a href="#none"></a></td>
											<td>길음</td>
											<td>초1</td>
											<td>010-3899-7696</td>
											<td></td>
										</tr>
										<tr>
											<td>105</td>
											<td>수학</td>
											<td>유재헌</td>
											<td>ACC00290<a href="#none"></a></td>
											<td>길음</td>
											<td>초1</td>
											<td>010-3899-7696</td>
											<td></td>
										</tr>
										<tr>
											<td>105</td>
											<td>수학</td>
											<td>유재헌</td>
											<td>ACC00290<a href="#none"></a></td>
											<td>길음</td>
											<td>초1</td>
											<td>010-3899-7696</td>
											<td></td>
										</tr>
										<tr>
											<td>105</td>
											<td>수학</td>
											<td>유재헌</td>
											<td>ACC00290<a href="#none"></a></td>
											<td>길음</td>
											<td>초1</td>
											<td>010-3899-7696</td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- //container -->
			<#include "/footer.ftl">
</body>
</html>
