<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
	
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="height:625px">

		<!-- 로고 및 탭 -->
		<#include "/memberCard/memberPopMenu.ftl">
		<p class="mgt-10 mgb-10">${memberDetailInfo.mFstName?default('') } 회원 상세정보 입니다.
		<span id="search" style="float: right;"><#include "/memberCard/kwamokInfo.ftl">
		<span class="button btn-type-G"><a class="w-65" href="javascript:$.locationGubun('/memberCard/memberJindoInfo','2');">조회</a></span>
		<span class="button btn-type-G"><a class="w-65" href="javascript:jindo_Sch('${memberDetailInfo.mKey?default('')}','${memberDetailInfo.kwamok?default('')}','${memberDetailInfo.mFstName?default('') }');">검색</a></span>
		</span>
		</p>
		<div class="tbl-type-D">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="50px">
						<col width="60px">
						<col width="45px">
						<col width="45px">
						<col width="*">
						<col width="60px">
					</colgroup>
						<tr>
							<th>주차</th>
							<th>과목</th>
							<th>요일</th>
							<th>진도</th>
							<th>중점학습내용 또는 상담포인트</th>
							<th>영역</th>
						</tr>
					<tbody>
					<#assign x=5>
					<#list 1..x as i>
						<#assign j=0>
						<#list jindoList as jindo>	
							<#if jindo.wk?number = i>
								<tr>
									<td>${currentMM}/${jindo.wk}</td>
									<td>${jindo.kwamok}</td>
									<td>${jindo.yoilnm?default('')}</td>
									<td>${jindo.jset?default('')}</td>
									<td style="text-align: left;">${jindo.yetext?default('')}</td>
									<td>${jindo.ed?default('')}</td>
								</tr>
								<#assign j=1>
							</#if>
						</#list>
							<#if j = 0>
								<tr>
									<td>${currentMM}/${i}</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</#if>	
					</#list>
					</tbody>
				</table>
		</div>
			<div class="btn-mbox">
				<span class="button btn-type-G"><a class="w-65" href="javascript:self:close();">닫기</a></span>
			</div>
	</div>
	</form>
</div>
<script type="text/javascript">
function jindo_Sch(mKey, kwamok,mFstName){
	var pop_status = window.open("/memberCard/jindoSearch?mKey="+mKey+"&kwamok="+kwamok+"&mFstName="+mFstName,"newWindow","width=665,height=800,marginwidth=0,marginheight=0,toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes");
	 pop_status.focus();
}
</script>
</body>
</html>