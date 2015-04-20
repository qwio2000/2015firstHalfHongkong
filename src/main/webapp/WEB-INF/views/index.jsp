<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title> </title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_center.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="/public/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="/public/js/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" src="/public/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="/public/js/jquery.form.stylishSelect.js"></script>
<script type="text/javascript">
$(function(){
	$('.scroll-pane').jScrollPane();
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
</head>

<body>
<a href="#primary_content" class="skip-nav">본문 컨텐츠 바로가기</a>
<div id="wrapper">
	<!-- header -->
	<div id="header">
		<div class="content">
			<h1 class="logo"><a href="#none"><img src="/public/img/common/logo_header.gif" alt="재능Global" /></a></h1>
			<div class="notice">
				<h2>공지사항</h2>
				<a href="#none">월별진도기록부를작성해주세요.</a>
				<img src="/public/img/common/icon_new.gif" alt="신규등록" class="new" />
			</div>
			<div class="login-info">
				<dl>
					<dt class="letter">쪽지 :</dt>
					<dd class="letter"><a href="#none">0</a>건</dd>
				</dl>
				<div class="btn-join-state">
					<a href="#none"><img src="/public/img/common/btn_gnb_mypage.gif" alt="마이페이지" /></a>
					<a href="#none"><img src="/public/img/common/btn_gnb_logout.gif" alt="로그아웃" /></a>
				</div>
			</div>
			<ul class="lnb">
				<li><a href="#none"><img src="/public/img/common/lnb_01_off.gif" alt="회원관리" /></a></li>
				<li><a href="#none"><img src="/public/img/common/lnb_02_off.gif" alt="교재수불" /></a></li>
				<li><a href="#none"><img src="/public/img/common/lnb_03_off.gif" alt="실적현황" /></a></li>
				<li><a href="#none"><img src="/public/img/common/lnb_04_off.gif" alt="커뮤니티" /></a></li>
			</ul>
		</div>
	</div>
	<!-- //header -->

	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
			<p class="msg2">동인천센터 <strong>선생님</strong> 환영합니다.</p>
				<div class="mb-state">
					<h3><img src="/public/img/today/tit_mb_state.gif" alt="센터현황" /></h3>
					<a href="#none" class="more"><img src="/public/img/common/icon_more.gif" alt="더보기" /></a>
					<ul>
						<li>
							<a href="#none">- 금월 입회과목 <em>00</em> 명</a> <img src="/public/img/common/icon_new_white.gif" alt="NEW" />
						</li>
						<li>
							<a href="#none">- 금월 퇴회과목 <em>00</em> 명</a>
						</li>
						<li>
							<a href="#none">- 금월 생일회원 <em>00</em> 명</a>
						</li>
						<li>
							<a href="#none">- 금월 미납과목 <em>00</em> 명</a>
						</li>
					</ul>
				</div>
				<div class="banner-section">
					<a href="#none"><img src="/public/img/common/bn_side_02.gif" alt="" /></a>
					<a href="#none"><img src="/public/img/common/bn_side_03.gif" alt="" /></a>
				</div>
				<div class="connection-box">
					<dl>
						<dt>접속 IP :</dt>
						<dd>203.234.173.254</dd>
						<dt>최종로그인시간 : </dt>
						<dd>2012.02.03 09:21:22</dd>
					</dl>
				</div>
			</div>
			<div id="primary_content" class="primary-content">
				<div class="today-visual">
					<p>2012.10.07(일)</p><!-- default -->
					<p>2012.10.07(일) ~10.13(월)</p><!-- 전체 -->
					<span>
						<a href="#none"><img src="/public/img/today/img_date1_off.gif" alt="전체" /></a>
						<a href="#none"><img src="/public/img/today/img_date3_off.gif" alt="월" /></a>
						<a href="#none"><img src="/public/img/today/img_date4_on.gif" alt="화" /></a>
						<a href="#none"><img src="/public/img/today/img_date5_off.gif" alt="수" /></a>
						<a href="#none"><img src="/public/img/today/img_date6_off.gif" alt="목" /></a>
						<a href="#none"><img src="/public/img/today/img_date7_off.gif" alt="금" /></a>
						<a href="#none"><img src="/public/img/today/img_date8_off.gif" alt="토" /></a>
					</span>
				</div>
				<div class="table-info">
					<dl class="today-member">
						<dt>오늘 회원 :</dt>
						<dd><em>1</em>명</dd>
					</dl>
				</div>
				<div class="tbl-type-A">
					<table cellspacing="0" width="100%">
						<colgroup>
							<col width="90"/>
							<col width="40"/>
							<col width="40"/>
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
							<th rowspan="3"><span class="sort sort-down">과목<br/>회원명</span></th>
							<th rowspan="3"><span class="sort sort-down">학년</span></th>
							<th rowspan="3"><span class="sort sort-down">상태</span></th>
							<th colspan="6"><span class="sort sort-down">학습진도</span></th>
							<th rowspan="3"><span class="length-2">진단처방</span></th>
							<th rowspan="3"><span class="length-2">진도조정</span></th>
							<th rowspan="3"><span class="length-2">월별상담</span></th>
							<th rowspan="3"><span class="length-2">관리사항</span></th>
						</tr>
						<tr>
							<th colspan="2" style="border-top: 1px solid #e4e4e4;border-left: 1px solid #e4e4e4;"><span class="sort sort-down">4/1</span></th>
							<th colspan="2" style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">4/2</span></th>
							<th colspan="2" style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">4/3</span></th>
						</tr>
						<tr>
							<th style="border-top: 1px solid #e4e4e4;border-left: 1px solid #e4e4e4;"><span class="sort sort-down">1</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">2</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">1</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">2</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">1</span></th>
							<th style="border-top: 1px solid #e4e4e4;"><span class="sort sort-down">2</span></th>
						</tr>
					</table>	
					<div class="tbl-type-A">
						<table cellspacing="0" width="100%">
						<colgroup>
							<col width="90"/>
							<col width="40"/>
							<col width="40"/>
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
								<td>
									<div class="user-info-cell">
										<p class="number">AA239051(KM)</p>
										<a href="javascript:popup_post('AA239051','01266','KK')" class="link">구민준</a>
									</div>
								</td>
								<td>
									<a href="#">04</a>
								</td>
								<td><a href="#none">유지</a></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">처방</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">조정</a></span>
								</td>
								<td>
									<span class="button btn-type-G"><a href="#" class="w-65">상담</a></span>
								</td>
								<td>*생일(04-06)</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form name="frm1" action="/memberCard/memberInfo" method="post">
		<input type="hidden" name="mKey" value=""/>
		<input type="hidden" name="sKey" value=""/>
		<input type="hidden" name="kwamok" value=""/>
	</form>
	<!-- //container -->

	<!-- footer -->
	<div id="footer">
		<div class="foot-content">
			<p class="logo"><img src="/public/img/login/footer_logo.gif" alt="JEI 재능교육" /></p>
			<ul class="util-link">
				<li class="fst"><a href="#"><img src="/public/img/login/btn_util_01.gif" alt="회사소개" /></a></li>
				<li><a href="#"><img src="/public/img/login/btn_util_02.gif" alt="이용약관" /></a></li>
				<li><a href="#"><img src="/public/img/login/btn_util_03.gif" alt="개인정보 취급방침" /></a></li>
				<li><a href="#"><img src="/public/img/login/btn_util_04.gif" alt="사이트맵" /></a></li>
			</ul>
			<p class="copyright"><img src="/public/img/login/txt_copyright.gif" alt="copyright (c) JEI eAcademy corporation. All rights reserved." /></p>
			<div id="family-site">
				<a href="#fmList" class="fmTitle"><img src="/public/img/common/family_site.gif" alt="Family site" /></a>
				<div class="fml-wrap" style="display:none" id="fmList">
					<a href="#family-site" class="btn-close">창 닫기</a>
					<dl class="first-child">
						<dt><span>교육출판</span></dt>
						<dd><a href="#none" class="n-0101">Jei.com</a></dd>
						<dd><a href="#none" class="n-0102">생각하는쿠키북</a></dd>
						<dd><a href="#none" class="n-0103">재능교육</a></dd>
						<dd><a href="#none" class="n-0104">스스로학습시스템2.0</a></dd>
						<dd><a href="#none" class="n-0105">재능에듀닷컴</a></dd>
						<dd><a href="#none" class="n-0106">JEI 리멤버</a></dd>
						<dd><a href="#none" class="n-0107">재능스스로펜</a></dd>
						<dd><a href="#none" class="n-0108">인천재능대학교</a></dd>
						<dd><a href="#none" class="n-0109">스스로북</a></dd>
						<dd><a href="#none" class="n-0110">재능셀프러닝</a></dd>
						<dd><a href="#none" class="n-0111">재능교육 위즈</a></dd>
						<dd class="last"><a href="#none" class="n-0112">한국한자한문능력개발원</a></dd>
					</dl>
					<dl class="nth-child-2">
						<dt><span>방송&amp;IT</span></dt>
						<dd><a href="#none" class="n-0201">재능TV</a></dd>
						<dd><a href="#none" class="n-0202">JEI English TV</a></dd>
					</dl>
					<dl class="nth-child-3">
						<dt><span>문화&amp;생활</span></dt>
						<dd><a href="#none" class="n-0301">Mom대로 키워라</a></dd>
						<dd><a href="#none" class="n-0302">재능동화구연협회재능동화구연협회</a></dd>
						<dd><a href="#none" class="n-0303">재능시낭송협회</a></dd>
						<dd><a href="#none" class="n-0304">수국작가촌</a></dd>
					</dl>
					<dl class="nth-child-4">
						<dt><span>인쇄&amp;유통</span></dt>
						<dd><a href="#none" class="n-0401">JEI PLATZ</a></dd>
						<dd><a href="#none" class="n-0402">재능인쇄</a></dd>
					</dl>
					<dl class="last-child">
						<dt><span>글로벌</span></dt>
						<dd><a href="#none" class="n-0501">미주지사</a></dd>
						<dd><a href="#none" class="n-0502">중국지사</a></dd>
						<dd><a href="#none" class="n-0503">홍콩지사</a></dd>
						<dd><a href="#none" class="n-0504">호주지사</a></dd>
						<dd><a href="#none" class="n-0505">뉴질랜드지사</a></dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<!-- //footer -->
</div>
<script type="text/javascript">
//<![CDATA[
	$('.formSelect').sSelect();
	$('.formSelect-140').sSelect({
		listWidth: 140
	});
//]]>
	function popup_post(mKey, sKey, kwamok){
		 window.open("","Window","width=848, height=728, menubar=no,status=yes,scrollbars=no");
		 document.frm1.mKey.value = mKey;
		 document.frm1.sKey.value = sKey;
		 document.frm1.kwamok.value = kwamok;
		 document.frm1.target = "Window";
		 document.frm1.submit();
	}
	</script>
</body>
</html>
