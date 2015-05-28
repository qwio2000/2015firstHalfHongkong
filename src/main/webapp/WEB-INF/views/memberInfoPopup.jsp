<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title> </title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_popup.css" />
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
<div id="popWrapper" class="wrapper" style="width:850px;">

	<!-- popHeader -->
	<div id="popHeader">
		<h1><img src="/public/img/popup/pop_logo.gif" alt="재능스스로러닝센터" /></h1>
		<h2>회원정보</h2>
		
	</div>

	<!-- popContent -->
	<div id="popContent" style="height:650px">

		<!-- 로고 및 탭 -->
		<div class="tab-A">
		<ul>
			<li class="first active">	<a href="#" style="cursor:hand;">회원상세정보</a></li>
			<li class="">	<a href="#"	style="cursor:hand;">과목정보</a></li>
			<li class="">	<a href="#"	style="cursor:hand;">진도/평가이력</a></li>
			<li class="">	<a href="#"	style="cursor:hand;">입복회이력</a></li>
			<li class="last">	<a href="#"	style="cursor:hand;">퇴회이력</a></li>
		</ul>
		</div>

		<p class="mgt-10 mgb-10">홍길동 회원 상세정보 입니다.</p>
		<div class="tbl-type-H">
			<form method="post" action="" name="frm1" class="">
			<input type="hidden" name="Hkey" value="">
			<input type="hidden" name="Hname" value="">

				<table style="border-spacing: 0;">
					<colgroup>
						<col width="60px">
						<col width="325px">
						<col width="70px">
						<col width="345px">
					</colgroup>
					<tr>
						<th>회원명</th>
							<td>홍길동</td>
						<th>패키지여부</th>
							<td>N</td>
					</tr>
					<tr>
						<th>생년월일</th>
							<td>
								<input type="text" name="Birth"  value="2008-05-05" title="생년월일" readonly>
								<input type="radio" name="Lunar" value="G" checked="checked">
								<label for="">양력</label>
								<input type="radio" name="Lunar" value="L">
								<label for="">음력</label>
							</td>
						<th>성별</th>
							<td>남자</td>
					</tr>
					<tr>
						<th>회원번호</th>
							<td >AA123456</td>
						<th>학년</th>
							<td>
								<select>
									<option>초등 1</option>
								</select>
							</td>
					</tr>
					<tr>
						<th>주소</th>
							<td colspan="3">
								<div class="text-mgl">
									<p>
										<input type="text" style="width: 96px" class="text" name="zipcd1" value="138" readonly>
										-
										<input type="text" style="width: 96px" class="text" name="zipcd2" value="200" readonly>
										<span class="button">
											<button type="button" onClick="">우편번호 검색</button>
										</span>
									</p>
									<p class="mgt-5">
										<input type="text" style="width: 354px" class="text" name="Addr1" value="서울 송파구 문정동 123-123" >
									</p>
									<input type="hidden" name="TempAddr" value="">
									<input type="hidden" name="TempZipcd" value="">
								</div>
							</td>
					</tr>
					<tr>
						<th rowspan="3">전화번호</th>
							<td rowspan="3">
								<div class="text-mgl">
									<p>
										<label class="label-space" for="">회원 C.P:</label>
										<select>
											<option>010</option>
										</select>
										<input type="text" class="text1" name="Hphone2" value="1132"  maxlength="4" >
										-
										<input type="text" class="text1" name="Hphone3" value="1132"  maxlength="4" >
									</p>
									<p class="mgt-5">
										<label class="label-space" for="">학부모 C.P:</label>
										<select>
											<option>010</option>
										</select>
										<input type="text" class="text1" name="ParentHphone2" value="1132"  maxlength="4" >
										-
										<input type="text" class="text1" name="ParentHphone3" value="1132"  maxlength="4" >
									</p>
									<p class="mgt-5">
										<label class="label-space" for="">
										집전화 H.P:
										</label>
										<select>
											<option>02</option>
										</select>
										<input type="text" class="text1" name="Tel2" value=""  maxlength="4" >
										-
										<input type="text" class="text1" name="Tel3" value=""  maxlength="4" >
									</p>
								</div>
							</td>
						<th rowspan="3">이메일</th>
							<td rowspan="3">
								<div class="text-mgl">
									<p>
										회&nbsp;&nbsp;원 :
										<input size="8" type="text" class="text" name="Email1" value="qwio2000" maxlength="19">
										@
										<input size="10" type="text" class="text" name="Email2" value="naver.com" maxlength="19">
										<select name="Email3">
											<option>naver.com</option>
										</select>

									</p>
								</div>
								<br>
								<div class="text-mgl">
									<p>
										학부모:
										<input size="8" type="text" class="text" name="ParentEmail1" value=""  maxlength="19">
										@
										<input size="10" type="text" class="text" name="ParentEmail2" value=""  maxlength="19">
										<select name="ParentEmail3">
													<option></option>
										</select>
									</p>
								</div>
							</td>
					</tr>
					<tr>
					</tr>
					<tr>
					</tr>
					<tr>
						<th>상담내용<br>및<br>특이사항</th>
							<td colspan="3">특이사항</td>
					</tr>
				</table>
				<div class="btn-box">
					<span class="button btn-type-G"><a href="#" class="w-65">정보변경</a></span>
					<span class="button btn-type-E"><a class="w-65" href="javascript:self:close();">닫기</a></span>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
//<![CDATA[
	$('.formSelect').sSelect();
	$('.formSelect-140').sSelect({
		listWidth: 140
	});
</script>
</body>
</html>
