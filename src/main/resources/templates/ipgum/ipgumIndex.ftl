<#include "/header.ftl">
<body>
<div id="wrapper">
	<#include "/headerMenu.ftl">
	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
				<#include "/leftContents.ftl">
				<#include "/leftMenu.ftl">
			</div>
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="#none">홈</a> &gt; </li>
						<li><a href="#none">회원업무</a> &gt; </li>
						<li><a href="#none">회원관리</a> &gt; </li>
						<li class="current"><a href="#none">입금입력</a></li>
					</ul>
				</div>
				<div class="tab-A">
					<ul>
						<li class="first active"><a href="/ipgum">입금입력</a></li>
						<li class="last"><a href="/ipgum/ipgumList">입금내역</a></li>
					</ul>
				 </div>
				 <div class="mgt-20">
					 <p>* 페이지 준비중입니다.</p>
				</div>
			</div> 
		</div>
	</div>
</div>
<!-- //container -->
<#include "/footer.ftl">
</body>
</html>
