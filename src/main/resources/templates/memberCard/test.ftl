<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form id="frm1" name="Qry2FormName" action="/memberCard/memberJindoUpdateInput" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="sKey" value="${memberDetailInfo.sKey?default('') }"/>
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="width:800px;height:625px;overflow: scroll;">

		<!-- 로고 및 탭 -->
		<div class="tab-A">
		<ul>
			<li class="first active">	<a href="javascript:$.locationGubun('/memberCard/memberJindoUpdateInfo','2')" style="cursor:hand;">진도조정</a></li>
			<li class="last">	<a href="javascript:$.locationGubun('/memberCard/memberJindoUpdateView','2')"	style="cursor:hand;">진도조정내역</a></li>
		</ul>
		</div>
		<div>${test.test }</div>
	</div>
	</form>
</div>
</body>
</html>