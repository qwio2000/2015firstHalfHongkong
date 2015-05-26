<#include "/popHeader.ftl">
<body>
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form name="Qry2FormName" action="" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="mFstName" value="${memberDetailInfo.mFstName?default('') }"/>
		<input type="hidden" name="sKey" value="${memberDetailInfo.sKey?default('') }"/>
	<!-- popHeader -->
	<#include "/popLogo.ftl">

	<!-- popContent -->
	<div id="popContent" style="width:800px;height:625px;overflow: scroll;">

		<!-- 로고 및 탭 -->
		<div class="tab-A">
		<ul>
			<li class="first active">	<a href="javascript:$.locationGubun('/memberCard/memberOmrInfo','2')" style="cursor:hand;">진단처방입력</a></li>
			<li class="last">	<a href="javascript:$.locationGubun('/memberCard/memberOmrView','2')"	style="cursor:hand;">진단처방출력</a></li>
		</ul>
		</div>
		<div class="clear">
			<div class="wrong-right">
				<div class="tbl-type-C mgt-10">
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="17%" />
							<col width="17%" />
							<col width="17%" />
							<col width="17%" />
							<col width="*" />
							<col width="16%" />
						</colgroup>
						<tr>
							<th>과목</th>
							<td>스스로수학(M)</td>
							<th>회원번호</th>
							<td>${memberDetailInfo.mKey }</td>
							<th>회원명</th>
							<td>${memberDetailInfo.mFstName}</td>
						</tr>
						<tr>
							<th>진단등급</th>
							<td>${dung }</td>
							<th>진단종류</th>
							<td>총괄</td>
							<th>입력일자</th>
							<td>${.now?string("yyyy-MM-dd")}</td>
						</tr>
						<tr>
							<th>실시일자</th>
							<td colspan="3">
							<div class="td-left">
							<input type="text" value="${(((.now?time)?long - (1000 * 60 * 60 * 24 * 7)?long)?number_to_date)?string("yyyy-MM-dd")}" name="Silsi" size='10' maxlength='10' readonly />
							</div>
							</td>
							<th>오답없음</th>
							<td><input type="checkbox" value="" name="NoOdab" onclick="$.noOdab(${isGtC?string});" /></td>
						</tr>
					</table>
				</div>

	<input type="hidden" name=JungDabLst value="">
	<input type="hidden" name=OdabNumLst value="">
	<input type='hidden' name='TotMun' value='${tot }'>
	<input type='hidden' name='dung' value='${dung }'>

	<div id="sWin">
				<div class="two-tbl-type">
					<div class="float-l" style="">
						<div class="tbl-top">
							<h3 class="bul-title">오답입력</h3>
						</div>
						<div class="tbl-type-B" >
							<table style="border-spacing: 0;width: 100%;">
								<tr id="ts1_No" style="display:none">
									<td colspan="3" bgcolor="ffffff" align="center"><font color="blue"><b>오답 없음</b></font></td>
								</tr>
								<tr id="ts1" >
								<td>
								<table style="border-spacing: 0;width: 100%;height: 450px;">
								<#if isGtC>
									<tr>
										<th width="28" rowspan="2">문항<br/>번호</th>
										<th width="294" colspan="10">오답입력</th>
									</tr>
									<tr>
										<th width="28">1</th>
										<th width="28">2</th>
										<th width="28">3</th>
										<th width="28">4</th>
										<th width="28">5</th>
										<th width="28">6</th>
										<th width="28">7</th>
										<th width="28">8</th>
										<th width="28">9</th>
										<th width="40">10</th>
									</tr>
								<#else>
									<tr>
										<th width="42px">문항<br/>번호</th>
										<th width="" colspan="10">오답입력</th>
									</tr>
								</#if>
									<tr>
										<td colspan="12" class="in-td">
											<div class="wrong">
												<table style="border-spacing: 0;">
												<#if isGtC>
													<#list jungDabList as jungDab>
														<tr class="fst">
															<td width="41px" bgcolor="ffffef" align='right'><font color="#496492">${(jungDab.jungKey?substring(1))?number }</font></td>
															<#list 1..10 as i>
																<td  width="38px" align="center">
																<#if jungDab.jungDab?number = i>
																	<input type="checkbox" name="txt_input" value="${i?string("00") }" disabled>
																<#elseif jungDab.jungHang?number lt i>
																	<input type="hidden" name="txt_input" value="">
																<#else>
																	<input type="checkbox" name="txt_input" value="${i?string("00") }" onclick="$.odabChk(this, '${jungDab.jungKey?substring(1) }  |  ${i?string("00")}', ${(jungDab.jungKey?substring(1))?number }, ${(i?string("00"))?number })">
																</#if>
																</td>
															</#list>
														</tr>
													</#list>
												<#else>
													<#list 1..tot as i>
													<tr class="fst">
														<td width="41px" bgcolor="ffffef" align='right'><font color="#496492">${i }</font></td>
														<td width="409px" align="center" width="28">
															<input type="checkbox" name="txt_input" value="${i }"   onclick="$.odabChk(this, '${i?string("000") }', ${i }, 0)">
														</td>
													</tr>
													</#list>
												</#if>
												</table>
											</div>
									</tr>
									</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="float-r">
						<h3 class="bul-title">평가 결과</h3>
							<select multiple="multiple" size="6" class="multiselect" id="selError" name="selErrorList">
							</select>
							<input  type="hidden" name=txt_totQs value="">
							<input type="hidden" name="txtErrList" value="$">
					</div>
					<div class="tbl-bottom">
						오답 합계 <input type="text" value="0" name="ErrTot" class="text" readonly/> 개
						<input name="ErrLst" type='hidden' value=''>
					</div>
				</div>
			</div>
		</div>

	</div>

		<!-- 버튼박스 -->
		<div class="btn-box">
			<div id="savechk">
				<span class="button btn-type-G"><a href="javascript:$.chkSubmit();" class="w-65" style="width: 65px;">저장</a></span>
				<span class="button btn-type-E"><a href="javascript:self:close();" class="w-65" style="width: 65px;">취소</a></span>
			</div>
			<div id="savechkIng"  style="display:none">
				<b><font color="blue">저장 중입니다. 조금만 기다려 주세요</font></b>
			</div>
		</div>
		<!-- //버튼박스 -->
	</div>
	</form>
</div>
</body>
</html>