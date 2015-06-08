<#include "/popHeader.ftl">
<body>
<script>
	var head = $("head");
	var headlinkLast = head.find("link[rel='stylesheet']:last");
	var linkElement = "<style>body {overflow-x: hidden;}</style>";
	if (headlinkLast.length){
		headlinkLast.after(linkElement);
	}
	else {
	   head.append(linkElement);
	}
</script>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent">

		<p class="mgt-10 mgb-10">* 교육원 : ${depNM }</p>
		<div class="tbl-type-D">
				<table style="border-spacing: 0;width: 800px;" border="">
					<colgroup>
						<col width="5%">
						<col width="15%">
						<col width="7%">
						<col width="10%">
						<col width="20%">
						<col width="8%">
						<col width="7%">
						<col width="7%">
						<col width="7%">
						<col width="7%">
						<col width="7%">
					</colgroup>
						<tr>
							<th>순번</th>
							<th>교실</th>
							<th>과목</th>
							<th>회원번호</th>
							<th>회원성명</th>
							<th>요일</th>
							<th>${month }/1</th>
							<th>${month }/2</th>
							<th>${month }/3</th>
							<th>${month }/4</th>
							<th>${month }/5</th>
						</tr>
					<tbody>
						<#if studyStateList?has_content>
							<#list studyStateList as studyState>
									<tr>
										<td rowspan="2">${studyState_index+1 }</td>
										<td rowspan="2">${studyState.empName }</td>
										<td rowspan="2">${studyState.subj }</td>
										<td rowspan="2">${studyState.mkey }</td>
										<td rowspan="2">${studyState.mFstName }</td>
										<td rowspan="2">${studyState.yoilNM1 }<#if studyState.setCnt?number gt 1><br/>${studyState.yoilNM2 }</#if></td>
										<td>${studyState.jinset1 }${studyState.ind1 }</td>
										<td>${studyState.jinset2 }${studyState.ind2 }</td>
										<td>${studyState.jinset3 }${studyState.ind3 }</td>
										<td>${studyState.jinset4 }${studyState.ind4 }</td>
										<td>${studyState.jinset5 }${studyState.ind5 }</td>
									</tr>
									<tr>
										<td>${studyState.jinset21 }${studyState.ind21 }</td>
										<td>${studyState.jinset22 }${studyState.ind22 }</td>
										<td>${studyState.jinset23 }${studyState.ind23 }</td>
										<td>${studyState.jinset24 }${studyState.ind24 }</td>
										<td>${studyState.jinset25 }${studyState.ind25 }</td>
									</tr>
							</#list>
						<#else>
							<tr>
								<td colspan="11">데이터가 없습니다.</td>
							</tr>
						</#if>
					</tbody>
				</table>
		</div>
			<div class="btn-mbox">
				<span class="button btn-type-G"><a class="w-65" href="javascript:self:close();">닫기</a></span>
			</div>
	</div>
</div>
</body>
</html>
