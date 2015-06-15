$(function() {
		// parameter 정의
		var memberCard_url = "/ipgum";
		
		// 함수 정의 
		$.extend ({
			//날짜 차이(월)
			dateDiff:function(date1, date2) {
				var datediff = date1.getTime() - date2.getTime();
				return Math.ceil(Math.abs((datediff / (30*24*60*60*1000))));
			}
		});
		
		//관리카드 팝업 내 검색
		$(document).on("click","#searchBtn",function(){
			var startDay = $("#startDay").val();
			var endDay = $("#endDay").val();
			var diffMonth = $.dateDiff(new Date(startDay), new Date(endDay));
			if(diffMonth >= 7){
				alert('6개월 간격으로 선택하여 주십시오.')
				return false;
			}
			var jsonSendData = $("#frm1").serialize();
			console.log(jsonSendData);
			$.ajax({
				url: window.location.pathname+".json",
				type: "POST",
				data:jsonSendData,
				cache: false,
				async: true,
				dataType: "JSON",
				success: function(data, textStatus, XMLHttpRequest) {
					console.log(data);
					var source = $("#template").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper("inc", function(value, options){
						return parseInt(value) + 1;
					});
					$("#mainContent").empty();
					$("#mainContent").append(template(data));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		});
});
