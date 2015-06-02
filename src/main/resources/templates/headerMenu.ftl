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