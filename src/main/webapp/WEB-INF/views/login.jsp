<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/public/css/common.css">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
</head>
<body id="login">
<c:if test="${param.error == 'true'}">
<script type="text/javascript">
$(function(){
	alert("계정과 암호가 일치하지 않습니다.");
	$("#loginFrm").attr("action","/login");
	$("#loginFrm").submit();
});
</script>
</c:if>
<div id="loginWrapper">
	<!-- header -->
	<div id="header">
		<h1><a href="#"><img src="/public/img/login/logo.gif" alt="재능Global" /></a></h1>
	</div>
	<!-- //header -->

	<!-- container -->
	<div id="loginContainer">
		<h2><img src="/public/img/login/h2_login.gif" alt="로그인" /><sec:authorize access="isAuthenticated()">&nbsp;&nbsp;<sec:authentication property="principal.memberId"/>님 환영합니다. <a style="float:right;" href="<c:url value='/memberCard' />"><img style="width:100px;height:50px;" src="/public/img/btn_go.gif"/></a></sec:authorize></h2>
			<form name="loginFrm" id="loginFrm" method="post" action="<c:url value='/loginCheck' />" style="margin:0px;">
				<div class="login-box">
				<sec:authorize access="isAnonymous()">
					<dl>
						<dt><img src="/public/img/login/tit_fc_login.gif" alt="재능 Global" /></dt>
					</dl>
						<fieldset>
							<legend>JEI GLOBAL</legend>
							<p>
								<input type="radio" name="loginLang" value="E" checked="checked" id="EnglishId" /><label for="EnglishId">English</label>
								<input type="radio" name="loginLang" value="K" id="koreanId"/><label for="koreanId">Korean</label>
								<input type="radio" name="loginLang" value="C" id="ChineseId" /><label for="ChineseId">Chinese</label>
							</p>
							<div>
								<p><input type="text" placeholder="아이디" name="memberId" maxlength="10" /></p>
								<p><input type="password" placeholder="비밀번호"  name="memberPassword" maxlength="100"/></p>
								<span class="button btn-login"><input type="submit" value="로그인" style="cursor:pointer;"/></span>
							</div>
							<ul>
								<li>비밀번호가 기억이 안나신다구요? 본사로 연락주시기 바랍니다.</li>
								<li>통합관리자 계정 정보를 입력해 주시고 관련 정보는 유출되지 않도록 보안사항을 지켜주세요.</li>
							</ul>
						</fieldset>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<a href="<c:url value='/logout' />"><img src="/public/img/btn_logout.png"/></a>
					</sec:authorize>
					<input type="hidden" name="returl" value="${param.returl}" />
				</div>
			</form>
	</div>
	<!-- //container -->
</div>
</body>
</html>