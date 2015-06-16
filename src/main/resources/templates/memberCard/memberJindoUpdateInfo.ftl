<#include "/popHeader.ftl">
<body>
<script>
	var head = $("head");
	var headlinkLast = head.find("link[rel='stylesheet']:last");
	var linkElement = "<style>.ui-datepicker-calendar {display: none;}</style>";
	if (headlinkLast.length){
		headlinkLast.after(linkElement);
	}
	else {
	   head.append(linkElement);
	}
</script>
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
		<ul class="message-top">
			<li>예시</li>
			<li>＊복습은 근무일 기준 9일 이후부터 진도 추가가 가능하며, 월 2회 허용됩니다.</li>
			<li>＊최근2주 이내에 요일 변경(월→목 이후, 화→금 이후, 수→토요일 로 변경)  또는 입회 후 3주 이내에만 당김이 가능합니다.</li>
			<li>＊복회무진을 선택하지 않은 복회회원은 처방 입력시까지 진도 조정이 불가능합니다.</li>
			<li>＊수학만 생략 가능합니다.</li>
			<li>＊쿠키북은 진도 조정이 불가능합니다.</li>
		</ul>
		<div class="message-top">
			<span>진도조정월 : </span>
			<input type="text" class="datePicker_yymm" name="updateYM" readonly="readonly" value="${.now?string("yyyy-MM")}" />
			<span>조정사유 : </span>
			<select name = "cngOpt">
				<#if dtlCDList?has_content>
				<#list dtlCDList as dtlCD>
					<option value="${dtlCD.dtlCD }">${dtlCD.dtlCDNM }</option>
				</#list>
				</#if>
			</select>
			<span class="button"><input style="width:80px" type ="button" name="" value="검색" onClick="javascript:document.frm1.submit();"></span>
		</div>
		<div style="padding:0 0 30px 0"> </div>
		<div class="btn-area-center">
			<fieldset class="board-search">
				<span class="button btn-type-I"><a href="javascript:window.close()" class="w-65" style="width: 65px;">닫기</a></span>
			</fieldset>
		</div>
	</div>
	</form>
</div>
</body>
</html>