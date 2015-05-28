$(function() {
		// ajax load 를 위한 시간값
		var jeinow = new Date(); 
		var time = jeinow.getTime(); 
		// parameter 정의
		var jeiadmin_url = "/adminManager";
		
		// 함수 정의 
    	$.extend ({
		
			// 좌측 메뉴 load
    		menu_list_load:function() {
    			var mJisaCD = $("#viewModeJisaCD").val();
    			var mEmpKeyLvCD = $("#viewModeEmpKeyLvCD").val();
    			var mDepMngCD = $("#viewModeDepMngCD").val();
    			$("#menu_list").load(jeiadmin_url + "/menuList?mJisaCD="+mJisaCD+"&mEmpKeyLvCD="+mEmpKeyLvCD+"&mDepMngCD="+mDepMngCD+"&"+time);
    		},
    		// 우측 내용 load
    		menu_content_load:function() {
    			var mJisaCD = $("#viewModeJisaCD").val();
    			var mEmpKeyLvCD = $("#viewModeEmpKeyLvCD").val();
    			var mDepMngCD = $("#viewModeDepMngCD").val();
    			$("#menu_content").load(jeiadmin_url + "/menuContent?mJisaCD="+mJisaCD+"&mEmpKeyLvCD="+mEmpKeyLvCD+"&mDepMngCD="+mDepMngCD+"&"+time,function(){
    				$("#mJisaCD").val(mJisaCD);
    				$("#mEmpKeyLvCD").val(mEmpKeyLvCD);
    				$("#mDepMngCD").val(mDepMngCD);
    			});
    		},
    		//좌측 그림에서 추가아이콘 눌렀을때
    		menu_add:function(mid) {
    			var mJisaCD = $("#viewModeJisaCD").val();
    			var mEmpKeyLvCD = $("#viewModeEmpKeyLvCD").val();
    			var mDepMngCD = $("#viewModeDepMngCD").val();
    			$("#menu_content").load(jeiadmin_url + "/menuContent?mJisaCD="+mJisaCD+"&mEmpKeyLvCD="+mEmpKeyLvCD+"&mDepMngCD="+mDepMngCD+"&"+time,function(){
    				$("#mParentIdx").val(mid);
        			$("#mIdx").val("");
    				$("#mMenuName").val("");
    				$("#mMenuCode").val("");
    				$("#mJisaCD").val(mJisaCD);
    				$("#mEmpKeyLvCD").val(mEmpKeyLvCD);
    				$("#mDepMngCD").val(mDepMngCD);
    				$("#mUseState").val("1");
    				$("#mMenuLink").val("");
    				$("#mAntPattern").val("");
    				$("#mCon").val("");
    				$("#mHasChildren").val("0");
    				$("#mDepth").val("0");
    				$("#insertBut").show();
    				$("#updateBut").hide();
    				$("#deleteBut").hide();
    			});
    		},
    		//좌측 그림에서 삭제아이콘 눌렀을때
    		menu_delete:function(mIdx) {
    			if(confirm("정말 삭제하시겠습니까?")){
    				$.ajax({
        				url: jeiadmin_url+"/menuDelete/"+mIdx,
        				type: "DELETE",
        				data: '',
        				cache: false,
        				async: true,
        				dataType: "text",
        				success: function(msg, textStatus, XMLHttpRequest) {
        					alert(msg);
        					$.menu_list_load();
        					$.menu_content_load();
        				},
        				error:function (xhr, ajaxOptions, thrownError){	
        					alert(thrownError);
        				}
    				});		
    			}
    		},
    		//좌측 그림에서 수정아이콘 눌렀을때
    		menu_modify:function(mid) {
    			var mJisaCD = $("#viewModeJisaCD").val();
    			var mEmpKeyLvCD = $("#viewModeEmpKeyLvCD").val();
    			var mDepMngCD = $("#viewModeDepMngCD").val();
    			$("#menu_content").load(jeiadmin_url + "/menuContent?mJisaCD="+mJisaCD+"&mEmpKeyLvCD="+mEmpKeyLvCD+"&mDepMngCD="+mDepMngCD+"&"+time,function(){
    				$.ajax({
            			url: jeiadmin_url+"/menuContent/"+mid,
            			type: "GET",
            			data: '' ,
            			cache: false,
            			async: true,
            			dataType: "JSON",
            			success: function(JsonData, textStatus, XMLHttpRequest) {
            				$("#mParentIdx").val(JsonData.mParentIdx);
                			$("#mIdx").val(JsonData.mIdx);
            				$("#mMenuName").val(JsonData.mMenuName);
            				$("#mMenuCode").val(JsonData.mMenuCode);
            				$("#mJisaCD").val(JsonData.mJisaCD);
            				$("#mEmpKeyLvCD").val(JsonData.mEmpKeyLvCD);
            				$("#mDepMngCD").val(JsonData.mDepMngCD);
            				$("#mUseState").val(JsonData.mUseState);
            				$("#mMenuLink").val(JsonData.mMenuLink);
            				$("#mAntPattern").val(JsonData.mAntPattern);
            				$("#mCon").val(JsonData.mCon);
            				$("#mHasChildren").val(JsonData.mHasChildren);
            				$("#mDepth").val(JsonData.mDepth);
            				$("#mSort").val(JsonData.mSort);
            				$("#insertBut").hide();
            				$("#updateBut").show();
            				$("#deleteBut").show();
            			},
            			error:function (xhr, ajaxOptions, thrownError){	
            				alert(thrownError);
            			}
            		});
    			});
    		},
    		
    		menu_change:function(mid) {
    			$("#menu_content").load(jeiadmin_url + "/menuChange/"+mid+"?"+time);
    		},
    		
    		searchMenu:function(){
    	    	$.menu_list_load();
    	        $.menu_content_load();
    		}
    	
    	});
    	
    	$("#menu_center").toggle(
    		function() {
    			$("#menu_list")
    				.animate({
    					width:"0px"
    				},500);
    			$("#menu_content").animate({
    				width:"691px"
    			},500);
    			$("#menu_center").removeClass("category_center");
    			$("#menu_center").addClass("category_center2");
       	   },
       	   function() {
       		  $("#menu_list").animate({
       			   width:"240px"
       			},500);
       		  $("#menu_content").animate({
       			   width:"451px"
       			},500);
       		  $("#menu_center").removeClass("category_center2");
       		  $("#menu_center").addClass("category_center");

       	   }
       	   
        );
    	
    	// 메뉴 리스트 불러오기
    	$.menu_list_load();
    	// 메뉴 컨텐츠 불러오기
        $.menu_content_load();
        
        
        //another_content의 등록버튼
        $(document).on("click","#insertBut",function(){
        	
        	$("#_method").val("POST");
        	
        	var jsonSendData = $("#menuContentFrm").serialize();
        	
        	$.ajax({
    			url: jeiadmin_url+"/menuSave.json",
    			type: "POST",
    			data:jsonSendData ,
    			cache: false,
    			async: true,
    			dataType: "text",
    			success: function(msg, textStatus, XMLHttpRequest) {
    				alert(msg);
    				$.menu_list_load();
    				$.menu_content_load();
    			},
    			error:function (xhr, ajaxOptions, thrownError){	
    				alert(thrownError);
    			}
    		});		
        });
        
       //another_content의 삭제버튼
       $(document).on("click","#deleteBut",function(){
    	   var mid = $("#mIdx").val();
    	   $.menu_delete(mid);
       });
        
     //another_content의 수정버튼
     $(document).on("click","#updateBut",function(){
    	
    	$("#_method").val("PUT");
    	
 		var jsonSendData = $("#menuContentFrm").serialize();
 		
     	$.ajax({
 			url: jeiadmin_url+"/menuSave.json",
 			type: "POST",
 			data:jsonSendData ,
 			cache: false,
 			async: true,
 			dataType: "text",
 			success: function(msg, textStatus, XMLHttpRequest) {
 				alert(msg);
 				$.menu_list_load();
 				$.menu_content_load();
 			},
 			error:function (xhr, ajaxOptions, thrownError){	
 				alert(thrownError);
 			}
 		});
     });
     
     
});
