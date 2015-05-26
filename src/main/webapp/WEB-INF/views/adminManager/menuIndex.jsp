<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title> </title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_center.css" />
<link rel="stylesheet" type="text/css" href="/public/css/jquery-ui.1.11.4.min.css" />
<link rel="stylesheet" type="text/css" href="/public/css/jquery.treeview.css" />
<link rel="stylesheet" type="text/css" href="/public/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/public/js/jquery-ui.1.11.4.min.js"></script>
<script type="text/javascript" src="/public/js/jquery.form.stylishSelect.js"></script>
<script type="text/javascript" src="/public/js/jquery.treeview.js"></script>
<script type="text/javascript" src="/public/js/globalmenu.js"></script>
</head>

<body>
<a href="#primary_content" class="skip-nav">본문 컨텐츠 바로가기</a>
<div id="wrapper">
	<!-- header -->
	<div id="header">
		<div class="content">
			<h1 class="logo"><a href="/"><img src="/public/img/common/logo_header.gif" alt="재능Global" /></a></h1>
		</div>
	</div>
	<!-- //header -->

	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
			<p class="msg2"><strong><sec:authentication property="principal.memberId"/>님</strong> 환영합니다.</p>
				<div class="banner-section">
					<a href="#none"><img src="/public/img/common/bn_side_02.gif" alt="" /></a>
					<a href="#none"><img src="/public/img/common/bn_side_03.gif" alt="" /></a>
				</div>
			</div>
			<div id="primary_content" class="primary-content">
				<div id="group_warp">
					<div>보기모드
						<select id="viewModeJisaCD">
							<option value="00">본사</option>
							<option value="03">북경</option>
							<option value="06">호주</option>
							<option value="07">뉴질랜드</option>
							<option value="08">홍콩</option>
						</select>
						<select id="viewModeEmpKeyLvCD">
							<option value="MA">본사</option>
							<option value="JA">지사</option>
							<option value="FA">교육원</option>
							<option value="MD">영파</option>
						</select>
						<select id="viewModeDepMngCD">
							<option value="A">관리자</option>
							<option value="F">원장</option>
							<option value="T">선생님</option>
						</select>
						<input type="button" onclick="$.searchMenu();"value="검색"/>
					</div>
					<div id="menu_list"></div>
					<div id="menu_center" class='group_center'></div>
					<div id="menu_content"></div>
				</div>
			</div>
		</div>
	</div>
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
</body>
</html>
