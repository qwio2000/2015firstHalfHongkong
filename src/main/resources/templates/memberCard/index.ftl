<#include "/header.ftl">
<script type="text/javascript">
$(function(){
	//family site
	$('.fmTitle').click(function(e) {
		var target = $(this).attr('href');
		$(target).slideDown();
		$(target).find('.btn-close').click(function() {
			$(target).slideUp();
		});
	});
});
</script>

<body>
<a href="#primary_content" class="skip-nav">본문 컨텐츠 바로가기</a>
<div id="wrapper">
	<!-- header -->
	<#include "/headerMenu.ftl">
	<!-- //header -->

	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
				<#include "/leftContents.ftl">
				<#include "/leftMenu.ftl">
				<div class="banner-section">
					<a href="#none"><img src="/public/img/common/bn_side_02.gif" alt="" /></a>
					<a href="#none"><img src="/public/img/common/bn_side_03.gif" alt="" /></a>
				</div>
			</div>
			<div id="primary_content" class="primary-content">
				<div class="today-visual">
					<p>2015.05.08(금)</p><!-- default -->
					<!-- <p>2012.10.07(일) ~10.13(월)</p> --><!-- 전체 -->
					<span>
						<a href="#none"><img src="/public/img/today/img_date1_off.gif" alt="전체" /></a>
						<a href="#none"><img src="/public/img/today/img_date3_off.gif" alt="월" /></a>
						<a href="#none"><img src="/public/img/today/img_date4_off.gif" alt="화" /></a>
						<a href="#none"><img src="/public/img/today/img_date5_off.gif" alt="수" /></a>
						<a href="#none"><img src="/public/img/today/img_date6_off.gif" alt="목" /></a>
						<a href="#none"><img src="/public/img/today/img_date7_on.gif" alt="금" /></a>
						<a href="#none"><img src="/public/img/today/img_date8_off.gif" alt="토" /></a>
					</span>
				</div>
				<div class="table-info">
					<dl class="today-member">
						<dt>오늘 회원 :</dt>
						<dd><em>6</em>명</dd>
					</dl>
				</div>
				<div class="tbl-type-A">
					<table style="border-spacing: 0;width: 100%;">
						<colgroup>
							<col width="110"/>
							<col width="30"/>
							<col width="30"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="40"/>
							<col width="90"/>
						</colgroup>
						<tr>
							<th rowspan="2"><span class="sort sort-down">과목<br/>회원명</span></th>
							<th rowspan="2"><span class="sort sort-down">학년</span></th>
							<th rowspan="2"><span class="sort sort-down">상태</span></th>
							<th colspan="2"><span class="sort sort-down">4/1</span></th>
							<th colspan="2"><span class="sort sort-down">4/2</span></th>
							<th colspan="2"><span class="sort sort-down">4/3</span></th>
							<th rowspan="2"><span class="length-2">진단처방</span></th>
							<th rowspan="2"><span class="length-2">진도조정</span></th>
							<th rowspan="2"><span class="length-2">월별상담</span></th>
							<th rowspan="2"><span class="length-2">관리사항</span></th>
						</tr>
						<tr>
							<th style="border-top: 1px solid #e4e4e4;border-left: 1px solid #e4e4e4;"><span class="sort sort-down">1</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">2</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">1</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">2</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">1</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">2</span></th>
						</tr>
					
							<tr>
								<td>
									<div class="user-info-cell">
										<p class="number">AA239051<img src="/public/img/subj/img_subj_km.gif" width="17" height="11" border="0"/></p>
										<a href="javascript:$.popupPost('/memberCard/memberInfo','AA309390','05707','KM')" class="link">LEE Serim Abigail</a>
									</div>
								</td>
								<td>
									<a href="#">04</a>
								</td>
								<td><a href="javascript:$.popupPost('/memberCard/memberHuhei','AA309390','05707','KM')"><img src="/public/img/common/img_o.gif"></a></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberOmrInfo','AA309390','05707','KM')" class="w-65">처방</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberJindoUpdateInfo','AA309390','05707','KM')" class="w-65">조정</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">상담</a></span>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="user-info-cell">
										<p class="number">AA242162<img src="/public/img/subj/img_subj_km.gif" width="17" height="11" border="0"/></p>
										<a href="javascript:$.popupPost('/memberCard/memberInfo','AA242162','01266','KM')" class="link">김윤재 Yun Jae</a>
									</div>
								</td>
								<td>
									<a href="#">04</a>
								</td>
								<td><a href="javascript:$.popupPost('/memberCard/memberHuhei','AA242162','01266','KM')"><img src="/public/img/common/img_o.gif"></a></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberOmrInfo','AA242162','01266','KM')" class="w-65">처방</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberJindoUpdateInfo','AA242162','01266','KM')" class="w-65">조정</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">상담</a></span>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="user-info-cell">
										<p class="number">AA260434<img src="/public/img/subj/img_subj_em.gif" width="17" height="11" border="0"/></p>
										<a href="javascript:$.popupPost('/memberCard/memberInfo','AA260434','01266','EM')" class="link">조은 Cho Eun</a>
									</div>
								</td>
								<td>
									<a href="#">05</a>
								</td>
								<td><a href="javascript:$.popupPost('/memberCard/memberHuhei','AA260434','01266','EM')"><img src="/public/img/common/img_o.gif"></a></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberOmrInfo','AA260434','01266','EM')" class="w-65">처방</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberJindoUpdateInfo','AA260434','01266','EM')" class="w-65">조정</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">상담</a></span>
								</td>
								<td>*생일(05-07)</td>
							</tr>
							<tr>
								<td>
									<div class="user-info-cell">
										<p class="number">AA283357<img src="/public/img/subj/img_subj_km.gif" width="17" height="11" border="0"/></p>
										<a href="javascript:$.popupPost('/memberCard/memberInfo','AA283357','01266','KM')" class="link">최주영Choi Joo Young</a>
									</div>
								</td>
								<td>
									<a href="#">03</a>
								</td>
								<td><a href="javascript:$.popupPost('/memberCard/memberHuhei','AA283357','01266','KM')"><img src="/public/img/common/img_o.gif"></a></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberOmrInfo','AA283357','01266','KM')" class="w-65">처방</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberJindoUpdateInfo','AA283357','01266','KM')" class="w-65">조정</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">상담</a></span>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="user-info-cell">
										<p class="number">AA274710<img src="/public/img/subj/img_subj_em.gif" width="17" height="11" border="0"/></p>
										<a href="javascript:$.popupPost('/memberCard/memberInfo','AA274710','01266','EM')" class="link">변혜영 HyeYoung</a>
									</div>
								</td>
								<td>
									<a href="#">04</a>
								</td>
								<td><a href="javascript:$.popupPost('/memberCard/memberHuhei','AA274710','01266','EM')"><img src="/public/img/common/img_o.gif"></a></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberOmrInfo','AA274710','01266','EM')" class="w-65">처방</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="javascript:$.popupPost('/memberCard/memberJindoUpdateInfo','AA274710','01266','EM')" class="w-65">조정</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">상담</a></span>
								</td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	<form name="frm1" action="" method="post">
		<input type="hidden" name="mKey" value=""/>
		<input type="hidden" name="sKey" value=""/>
		<input type="hidden" name="kwamok" value=""/>
	</form>
	</div>
	<!-- //container -->

	<!-- footer -->
	<#include "/footer.ftl">
	<!-- //footer -->
</body>
</html>
