<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_center.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/public/js/jquery-ui-1.8.21.custom.min.js"></script>
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
</head>

<body>
<a href="#primary_content" class="skip-nav">본문 컨텐츠 바로가기</a>
<div id="wrapper">
	<!-- header -->
	<div id="header">
		<div class="content">
			<h1 class="logo"><a href="/"><img src="/public/img/common/logo_header.gif" alt="재능Global" /></a></h1>
			<div class="notice">
			</div>
			<div class="login-info">
				<dl>
				</dl>
				<div class="btn-join-state">
					<a href="/logout"><img src="/public/img/common/btn_gnb_logout.gif" alt="로그아웃" /></a>
				</div>
			</div>
			<ul class="lnb">
				<li><a href="/memberCard"><#if menuFirstCode?exists && menuFirstCode?string  == "1"><img src="/public/img/common/lnb_01_on.gif" alt="회원관리" /><#else><img src="/public/img/common/lnb_01_off.gif" alt="회원관리" /></#if></a></li>
				<li><a href="/subul"><#if menuFirstCode?exists && menuFirstCode?string  == "2"><img src="/public/img/common/lnb_02_on.gif" alt="교재수불" /><#else><img src="/public/img/common/lnb_02_off.gif" alt="교재수불" /></#if></a></li>
				<li><a href="/siljuk"><#if menuFirstCode?exists && menuFirstCode?string  == "3"><img src="/public/img/common/lnb_03_on.gif" alt="실적현황" /><#else><img src="/public/img/common/lnb_03_off.gif" alt="실적현황" /></#if></a></li>
				<li><a href="/board"><#if menuFirstCode?exists && menuFirstCode?string  == "4"><img src="/public/img/common/lnb_04_on.gif" alt="커뮤니티" /><#else><img src="/public/img/common/lnb_04_off.gif" alt="커뮤니티" /></#if></a></li>
			</ul>
		</div>
	</div>
	<!-- //header -->

	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
			<p class="msg2"> <strong><@security.authentication property="principal.empName" ></@security.authentication></strong>님 환영합니다.</p>
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
				<#if leftMenuList?has_content>
					<#assign depth = 1 deptemp = 0>
					<#assign menuCodeCnt = menuCode?length>
				<div class="snb">
					<h3>${leftMenuList[0].mMenuName}</h3>
					<#if ((leftMenuList?size)-1 > 0)>
						<ul>
						<#list 1..(leftMenuList?size-1) as i>
								<#if (menuCodeCnt == 7 && (leftMenuList[i].mMenuCode == menuTwoCode)) >
									<li class="active">
								<#elseif (menuCodeCnt == 5 && (leftMenuList[i].mMenuCode == menuTwoCode)) >	
									<li class="active">
								<#elseif (menuCodeCnt == 3 && (leftMenuList[i].mMenuCode == menuCode)) >	
									<li class="active">
								<#else>
									<li>
								</#if>
									<#if ((menuCodeCnt == 7) && (leftMenuList[i].mMenuCode == menuThreeCode)) || leftMenuList[i].mMenuCode == menuCode>
										<a class="on" href="${leftMenuList[i].mMenuLink}">
									<#else>
										<a href="${leftMenuList[i].mMenuLink}">
									</#if>
								<#if (leftMenuList[i].mDepth == 4)>
								-
								<#elseif (leftMenuList[i].mDepth >= 5)>
								*
								</#if>
								${leftMenuList[i].mMenuName}</a>
								<#if leftMenuList[i].mHasChildren == "1">
									<ul>
									<#assign depth = depth+1>
								<#else>
									<#if (depth > 1)>
										<#if (i+1 < leftMenuList?size) >
											<#assign deptemp = leftMenuList[i].mDepth-leftMenuList[i+1].mDepth>
											<#if (deptemp == 0) >
												</li>
											<#else>
												<#list 1..deptemp as j>
													</li></ul>
													<#assign depth = depth-1>
												</#list>
											</#if>
											</li>
										<#else>
											<#list 1..(depth-1) as k >	
												</li></ul>
											</#list>
											</li>
										</#if>
									<#else>
										</li>
									</#if>
								</#if>								
						</#list>
						</ul>
					</#if>
				</div>
				</#if>
			</div>
			<div id="primary_content" class="primary-content">
				${contents}
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
</body>
</html>
