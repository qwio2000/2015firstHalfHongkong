<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form id="Qry2FormName" name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
		<input type="hidden" name="dung" value=""/>
		<input type="hidden" name="sKey" value="${memberDetailInfo.sKey?default('') }"/>
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="width:800px;height:625px">

		<!-- 로고 및 탭 -->
		<div class="tab-A">
		<ul>
			<li class="first active">	<a href="javascript:$.locationGubun('/memberCard/memberOmrInfo','2')" style="cursor:hand;">진단처방입력</a></li>
			<li class="last">	<a href="javascript:$.locationGubun('/memberCard/memberOmrView','2')"	style="cursor:hand;">진단처방출력</a></li>
		</ul>
		</div>
		<br/>
		<p class="text">
			※
			<strong> ${memberDetailInfo.kwamok}</strong> ${memberDetailInfo.mFstName}(${memberDetailInfo.mKey}) 회원
			<br/>
			<#if omrInfo?exists>
			<em>${omrInfo.yy}</em>년 <em>${omrInfo.mm}</em>월 <em>${omrInfo.wk}</em>주 <span class="tcolor">${omrInfo.jset?substring(0,1)}</span>등급 <span class="tcolor">${omrInfo.pan}</span> 예정입니다. <!--  현재 가능한 진단은 재진입니다.-->
			</#if>
		</p>
		
			<div class="btn-box">
				<span class="button btn-type-G"><a class="w-65" style="width: 65px;" href="javascript:$.checkSubmit(<#if omrInfo?exists>'true','${omrInfo.jset?substring(0,1)}'<#else>'false',''</#if>);">진단입력</a></span>
				<span class="button btn-type-E"><a class="w-65" style="width: 65px;" href="javascript:self:close();">닫기</a></span>
			</div>
	</div>
	</form>
</div>
</body>
</html>