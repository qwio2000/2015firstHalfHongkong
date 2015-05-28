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
	    		}
	});
	$(".datePicker").datepicker(
			{
				changeYear : true,
				showAnim : "slide",
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
	
	
	//family site
	$('.fmTitle').click(function(e) {
		var target = $(this).attr('href');
		$(target).slideDown();
		$(target).find('.btn-close').click(function() {
			$(target).slideUp();
		});
	});
});