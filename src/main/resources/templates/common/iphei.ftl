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
								<select>
									<#list availableDateList as ableDateList>
									<option value="${ableDateList}" ${compare(nowDate,ableDateList,"selected='selected'","")} >${ableDateList}</option>
									</#list>
								</select>
								</#if>
							</td>
							<th>입회교육원</th>
							<td>
								<#if depInfoList?has_content>
								<select>
									<#list depInfoList as deptList>
									<option value="${deptList.depid1}"  >${deptList.depid1} (${deptList.depidNM})</option>
									</#list>
								</select>
								</#if>
							</td>
						</tr>
						<tr>
							<th>입회종류</th>
							<td colspan="3">
								<input type="radio" name="ipkind" value="01">New Enrollment
								<input type="radio" name="ipkind" value="02">Existing Member
								<input type="radio" name="ipkind" value="03">Free diagnostic test
							</td>
						</tr>
						<tr>
							<th>과목선택</th>
							<td colspan="3">체크박스들</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="btn-box">
					<span class="button btn-type-J">
						<a href="#none" class="w-120">등록</a>
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
<!-- //container -->
<#include "/footer.ftl">
</body>
</html>
>>>>>>> refs/remotes/origin/master
