$(function() {
		// parameter 정의
		var memberCard_url = "/memberCard";
		
		// 함수 정의 
    	$.extend ({
			//진단 체크
    		checkSubmit:function(check, dung) {
    			if($("input[name='kwamok']").val() != 'KM' && $("input[name='kwamok']").val() != 'EM'){
    				alert('수학 외 과목은 진단입력을 할 수 없습니다.');
    				return;
    			}
    			if(check){
    				$("input[name='dung']").val(dung);
    				$("#Qry2FormName").attr('action','/memberCard/memberOmrOdabInput');
    				$("#Qry2FormName").submit();
    			}else{
    				alert('이 회원은 처방을 할 수 없습니다.');
    			}
    		},
    		huGubun_chk:function(){
    			alert('test');
    			if(document.Qry2FormName.huGubun.length != null){
    			    if(document.Qry2FormName.huGubun[0].checked==true){
    				    document.Qry2FormName.huSayu[0].disabled=false;
    				    document.Qry2FormName.huSayu[1].disabled=true;
    			    }else{
    				    document.Qry2FormName.huSayu[0].disabled=true;
    				    document.Qry2FormName.huSayu[1].disabled=false;
    			    }
    			}
    		}
    	});
        
       //관리카드 팝업 내 검색
        $(document).on("click","#searchBtn",function(){
        	var jsonSendData = $("#Qry2FormName").serialize();
        	$.ajax({
        		url: window.location.pathname+".json",
        		type: "GET",
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
