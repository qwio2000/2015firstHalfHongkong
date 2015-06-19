<#include "/popHeader.ftl">
<body>
<#include "/function.ftl">
<div id="popWrapper" class="wrapper" style="width:850px;">
	<form id="frm1" name="Qry2FormName" action="/memberCard/memberJindoUpdate" method="post">
		<input type="hidden" name="mKey" value="${memberDetailInfo.mKey?default('') }"/>
		<input type="hidden" name="kwamok" value="${memberDetailInfo.kwamok?default('') }"/>
		<input type="hidden" name="sKey" value="${memberDetailInfo.sKey?default('') }"/>
		<input type="hidden" name="cngGubun" value="1"/>
		<input type="hidden" name="cngOpt" value="${cngOpt?default('') }"/>
		<input type="hidden" name="fstYoil" value="${memberInfoCheck.fstYoil?default('')}"/>
		<input type="hidden" name="sndYoil" value="${memberInfoCheck.sndYoil?default('')}"/>
		<input type="hidden" name="setCnt" value="${memberInfoCheck.setCnt?default('')}"/>
		
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
		<ul class="message-top1">
			<li>예시</li>
			<li>＊당김, 복습을 원하는 월 주차를 클릭후 당김, 복습을 하시면 됩니다.</li>
			<li><span class="c-point">＊최종 불출 된 SET만 주차당김이 가능합니다.</span></li>
		</ul>
		<div class="table-top-msg1">
		</div>
		<div class="tbl-type-F bor-none tbl-review${compare(cngOpt,'1',' float-l','')}">
			<table style="border-spacing: 0; width: 100%;">
			<colgroup>
				<col width="60px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
				<col width="53px" />
			</colgroup>
			<!--테이블의 셀들의 너비 지정//-->
			<!-- 게시판 테이블 시작//-->
			<tr>
				<th rowspan="2" align="center" >년/월</th>
				<th colspan="2" align="center" >1주</th>
				<th colspan="2" align="center" >2주</th>
				<th colspan="2" align="center" >3주</th>
				<th colspan="2" align="center" >4주</th>
				<th colspan="2" align="center" >5주</th>
			</tr>
			<tr>
			<#list 0..4 as i>
				<td align="center" >${memberInfoCheck.fstYoilNM?default('')}</td>
				<td align="center" >${memberInfoCheck.sndYoilNM?default('')}</td>
			</#list>
			</tr>
			<#assign yyyy=startYYYY>
			<#assign mm=startMM>
			<#assign count = 0>
			<#list 0..5 as i>
					<tr>
						<td>${yyyy}/${attachZero(mm?number)}</td>
						<#list 0..4 as j>
							<#list 0..1 as j2>
								<td><#if chkArray[i][j][j2]?default('') == '1'>
								<input type="radio" name="ymw" value="${yyyy+attachZero(mm?number)+(j+1)+'1'+bkArray[i][j][j2]?default('')+bsArray[i][j][j2]?default('') }" 
								onClick="javascript:$.onChangeWk('${count}','${compare(bsArray[i][j][j2]?default('')?length,3,'0','1')}');" ID="Radio1"><#assign count = count +1></#if>
								${bsArray[i][j][j2]?default('') }</td>
							</#list>
						</#list>
					</tr>
						<#if mm?number+1 = 13>
							<#assign yyyy = yearNumberFormat(yyyy?number+1)>
							<#assign mm = 1>
						<#else>
							<#assign mm = mm?number+1>
							<#assign mm = mm>
						</#if>
					</#list>
			</table>
		</div>
		<#if cngOpt == '1'>
		<div class="float-r tbl-type-F bor-none tbl-review" style="width:180px;height: 345px;overflow-y: scroll;">
		<table style="width:100%;border-spacing: 0">
			<colgroup>
				<col width="64px" />
			</colgroup>
			<#if set1?has_content>
				<tr><th>${set1dung?default('') }등급</th></tr>
				<tr>
					<td class="pd-0">
					<#list set1 as set>
						<a href="javascript:onclick=$.setBan('${set.casKey}','${set.casNset }')">${set.casNset }</a><#if ((set_index+1) % 6) == 0><br/></#if>
					</#list>
					</td>
				</tr>
			</#if>
			<#if set2?has_content>
				<tr><th>${set2dung?default('') }등급</th></tr>
				<tr>
					<td class="pd-0">
					<#list set2 as set>
						<a href="javascript:onclick=$.setBan('${set.casKey}','${set.casNset }')">${set.casNset }</a>
					</#list>
					</td>
				</tr>
			</#if>
			<#if set3?has_content>
				<tr><th>${set3dung?default('') }등급</th></tr>
				<tr>
					<td class="pd-0">
					<#list set3 as set>
						<a href="javascript:onclick=$.setBan('${set.casKey}','${set.casNset }')">${set.casNset }</a>
					</#list>
					</td>
				</tr>
			</#if>
		</table>
		</div>
		<div style="padding:0 0 5px 0"> </div>
		<div style="padding:0 0 10px 0"> </div>
		<div class="prev-choice">
		<#else>
		<div class="prev-choice1">
		</#if>
		<span>
				<input type="text"  name="jo_yy" class="text" size="4" readonly/> 년
				<input type="text"  name="jo_mm" class="text" size="1" readonly/> 월
				<input type="text"  name="jo_wk" class="text" size="1" readonly/> 주
				<input name="jo_sort" type="hidden" class="textbox1" size="1" readonly>
				<input type="text"  name="jo_set" class="text" size="4" readonly/> Set
				<input type="hidden" name="jo_key" size="4">
		</span>
		<#if cngOpt == '1'>
		<span>
				<label for="choice01" class="label">
					<input type="radio" value="B" name="cngSayu" onclick="javascript:$.onChangeBok()"> 범위복습</label>
					<input type="text" name="setb1_k" class="text" readonly/>
					<input type="hidden" name="setb1">
					~
					<input type="text" name="setb2_k" class="text" readonly/>
					<input type="hidden" name="setb2">
				</span>
				<span>
					<label for="choice02" class="label"><input type="radio" value="S" name="cngSayu" onclick="javascript:$.onChangeBok()" checked> 선택복습</label>
					<input type="text"  name="sets1_k" class="text" readonly/> ,
					<input type="text"  name="sets2_k" class="text" readonly/> ,
					<input type="text"  name="sets3_k" class="text" readonly/> ,
					<input type="text"  name="sets4_k" class="text" readonly/> ,
					<input type="text"  name="sets5_k" class="text" readonly />
					<input type="hidden" name="sets1">
					<input type="hidden" name="sets2">
					<input type="hidden" name="sets3">
					<input type="hidden" name="sets4">
					<input type="hidden" name="sets5">
				</span>
		</#if>
		</div>
		<div class="btn-box">
		<div id="savechk" >
			<span class="button btn-type-G"><a href="javascript:$.save();" class="w-65" style="width: 65px;">저장</a></span>
			<span class="button btn-type-E"><a href="javascript:if(confirm('정말 종료하시겠습니까?')){self.close();}" class="w-65" style="width: 65px;">취소</a></span>
		</div>
		<div id="savechkIng" style="display:none">
			<b><font color="blue">저장 중입니다. 조금만 기다려 주세요</font></b>
		</div>
	</div>
	</div>
	</div>
	</form>
</div>
</body>
<script>
$(function() {
	var frm = document.Qry2FormName;
	// 함수 정의 
	$.extend ({
		onChangeWk:function(count, chk){
			var rChk = '${count}';
			if (rChk=="1"){
				frm.jo_yy.value = frm.ymw.value.substring(0,4);
				frm.jo_mm.value = frm.ymw.value.substring(4,6);
				frm.jo_wk.value = frm.ymw.value.substring(6,7);
				frm.jo_sort.value = frm.ymw.value.substring(7,8);
				frm.jo_key.value = frm.ymw.value.substring(8,11);
				if(chk == "1"){
					frm.jo_set.value = frm.ymw[count].value.substring(12);
				}else{
					frm.jo_set.value = frm.ymw[count].value.substring(11,15);
				}
			}
			else{
				frm.jo_yy.value = frm.ymw[count].value.substring(0,4);
				frm.jo_mm.value = frm.ymw[count].value.substring(4,6);
				frm.jo_wk.value = frm.ymw[count].value.substring(6,7);
				frm.jo_sort.value = frm.ymw[count].value.substring(7,8);
				frm.jo_key.value = frm.ymw[count].value.substring(8,11);
				if(chk == "1"){
					frm.jo_set.value = frm.ymw[count].value.substring(12);
				}else{
					frm.jo_set.value = frm.ymw[count].value.substring(11,15);
				}
			}
			var cngOpt = '${cngOpt}';
			if(cngOpt == "1"){
				frm.setb1.value="";
				frm.setb2.value="";
				frm.setb1_k.value="";
				frm.setb2_k.value="";

				frm.sets1_k.value="";
				frm.sets2_k.value="";
				frm.sets3_k.value="";
				frm.sets4_k.value="";
				frm.sets5_k.value="";
				frm.sets1.value="";
				frm.sets2.value="";
				frm.sets3.value="";
				frm.sets4.value="";
				frm.sets5.value="";
			}
		},
		setBan:function(bKey, bSet){
			if (frm.jo_set.value.length>0){
				if (frm.cngSayu[0].checked == true){
					if (frm.setb1.value.length>0){
						if (frm.setb1.value >= bKey){
							alert('첫번째 세트와 작거나 같은 세트 입니다. 다시 선택하세요.1');
							return;
						}else{
							if (frm.jo_key.value <= bKey){
								alert('해당주차의 세트와 같거나 큰 세트 입니다. 다시 선택하세요.2');
								return;
							}
							else{
								frm.setb2.value=bKey;
								frm.setb2_k.value=bSet;
								return;
							}
						}
					}else{
						if (frm.jo_key.value <= bKey){
							alert('해당주차의 세트와 같거나 큰 세트 입니다. 다시 선택하세요.3');
							return;
						}else{
							frm.setb1.value=bKey;
							frm.setb1_k.value=bSet;
							return;
						}
					}
				}else{
					if (frm.jo_key.value <= bKey){
						alert('해당주차의 세트와 같거나 큰 세트 입니다. 다시 선택하세요.');
						return;
					}else{
						if (frm.sets1.value.length>0){
							if (frm.sets2.value.length>0){
								if (frm.sets3.value.length>0){
									if (frm.sets4.value.length>0){
										if (frm.sets5.value.length>0){
											alert('모두 선택 하셨습니다.');
											return;
										}else{
											frm.sets5.value	= bKey;
											frm.sets5_k.value = bSet;
											return;
										}
									}else{
										frm.sets4.value	= bKey;
										frm.sets4_k.value = bSet;
										return;
									}
								}else{
									frm.sets3.value	= bKey;
									frm.sets3_k.value = bSet;
									return;
								}
							}else{
								frm.sets2.value	= bKey;
								frm.sets2_k.value = bSet;
								return;
							}
						}else{
							frm.sets1.value	= bKey;
							frm.sets1_k.value = bSet;
							return;
						}
					}
				}
			}else{
				alert('진도 조정 주차를 선택하세요');
				return;
			}
		},
		onChangeBok:function(){
			if (frm.cngSayu[0].checked == true){
				frm.sets1_k.value="";
				frm.sets2_k.value="";
				frm.sets3_k.value="";
				frm.sets4_k.value="";
				frm.sets5_k.value="";
				frm.sets1.value="";
				frm.sets2.value="";
				frm.sets3.value="";
				frm.sets4.value="";
				frm.sets5.value="";
			}else{
				frm.setb1_k.value="";
				frm.setb2_k.value="";
				frm.setb1.value="";
				frm.setb2.value="";
			}
		},
		save:function(){
			var cngOpt = '${cngOpt}';
			if(confirm("입력한 내용이 정확한지 다시 한번 확인바랍니다.\n확인을 누르면 진도조정을 시작합니다")){
				if(cngOpt=="1"){
					if (frm.jo_set.value==''){
						alert('년/월/주/정렬순서/세트를 선택하세요');
						return;
					}
					if (frm.cngSayu[0].checked){
						if (frm.setb1.value=='' || frm.setb2.value==''){
							alert('범위 복습 세트를 선택해 주세요');
							return;
						}
					}else{
						if(frm.sets1.value==''){
							alert('선택 복습 세트를 선택해 주세요');
							return;
						}
					}
				}else{
					if (frm.jo_set.value==''){
						alert('년/월/주/정렬순서/세트를 선택하세요');
						return;
					}
				}
				frm.submit();
			}
		}
	});
});
</script>
</html>