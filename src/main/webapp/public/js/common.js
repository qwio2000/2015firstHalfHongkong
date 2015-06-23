$(function(){
	$.extend({
				paging : function(thisPage, pageNum, blockSize) {
					var pageUnit = blockSize;// 블럭 단위
					var totPages = pageNum; // 총페이지수
					var thisBlock = Math.ceil(thisPage / pageUnit); // 현재 페이징블럭
					var startPage, endPage; // 현재 페이징블럭 처음, 마지막 페지이
					var preBlock, nextBlock; // 이전, 다음 페이징 블럭
					var html = "";

					if (pageNum > 0) {
						// 현재 페이지블럭의 시작페이지번호, 전페이지번호
						if (thisBlock > 1) {
							startPage = (thisBlock - 1) * pageUnit + 1;
							preBlock = startPage - 1;
						} else {
							startPage = preBlock = 1;
						}

						// 현재 페이지블럭의 끝페이지번호, 다음페이지번호
						if ((thisBlock * pageUnit) >= totPages) {
							endPage = totPages;
							nextBlock = endPage;
						} else {
							endPage = thisBlock * pageUnit;
							nextBlock = endPage + 1
						}

						if (thisPage > 1) {
							html += "<a href='javascript:;' pageNo='1' class='naviPage'>처음</a> "; // 맨처음으로
							// 가기
							if (preBlock > 1) {
								html += "<a href='javascript:;' pageNo='"
										+ preBlock
										+ "' class='naviPage'>이전</a> "; // 현재블럭의
								// 전페이지
							}
						}

						for (i = startPage; i <= endPage; i++) {
							if (i != thisPage) {
								html += " <a href='javascript:;' pageNo='" + i
										+ "' class='naviPage'>" + i + "</a> ";
							} else {
								html += "<a href='javascript:;' pageNo='"
										+ i
										+ "' class='naviPage' style='color:red;'>"
										+ i + "</a>";
							}
						}

						if (thisPage != totPages) {
							html += "<a href='javascript:;' pageNo='"
									+ nextBlock + "' class='naviPage'>다음</a> "; // 현재
							// 블럭의
							// 다음페이지
							html += "<a href='javascript:;' pageNo='"
									+ totPages + "' class='naviPage'>끝</a> "; // 맨끝으로
							// 가기
						}
					}
					return html;
				},
				locationGubun:function(url, gubun) {
					if ((url=='/memberCard/memberJindoInfo' && document.Qry2FormName.searchKwamok.value=='all'&&gubun=='2')||
							(url=='/memberCard/jindoSearch'&& document.Qry2FormName.searchKwamok.value=='all'&&gubun=='2')) {
						alert('진도는 전체를 선택할 수 없습니다.');
						return;
					}
					if(gubun == '1'){
						document.Qry2FormName.searchKwamok.value ='';
					}
					document.Qry2FormName.action = url;
					document.Qry2FormName.submit();
	    		},
	    		
	    		//전화번호, 핸드폰 번호 형식 체크
				telCheck:function(msg,str) {
					var pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
					if(!pattern.test(msg)){
					    alert(str + " 형식이 올바르지 않습니다.\n\n{2,3}-{3,4}-{4}자리로 입력해 주십시오.\n");
					    return false;
					}
					return true;
				},

				//email 형식 체크
				emailCheck:function(msg,str) {
					var pattern = /([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)\.([0-9a-zA-Z_-]+)/; //pattern = /(\S+)@(\S+)\.(\S+)/; 이메일주소에 한글 사용시
					if(!pattern.test(msg)){
					    alert(str + " 형식을 맞춰주세요. 예)hong@korea.com");
					    return false;
					}
					return true;
				}
	});
	$(".datePicker").datepicker(
			{
				changeYear : true,
				changeMonth : true,
				dateFormat : 'yy-mm-dd',
				prevText : '이전 달',
				nextText : '다음 달',
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
						'9월', '10월', '11월', '12월' ],
				monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ],
				dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
				dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
				showMonthAfterYear : true,
				yearSuffix : '년'
	});
	$(".datePicker_yymm").datepicker(
		{
			changeMonth: true,
			changeYear: true,
			dateFormat : 'yy-mm',
			showButtonPanel: true,
			yearRange: "-100:+0",
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
							'9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
			yearSuffix : '년',
			showMonthAfterYear : true,
			closeText : "확인",
			currentText : "오늘",
			onSelect: function()
			{ 
				var dateObject = $(this).datepicker('getDate'); 
			},
			onClose: function(dateText, inst){
				var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
				var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
				$(this).datepicker('setDate', new Date(year, month, 1));
			},
		});
	 
	//family site
	$('.fmTitle').click(function(e) {
		var target = $(this).attr('href');
		$(target).slideDown();
		$(target).find('.btn-close').click(function() {
			$(target).slideUp();
		});
	});
});
