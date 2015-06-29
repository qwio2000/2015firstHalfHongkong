$(function() {
	$.extend({
		deposit_set:function(pIpheiday,pDepid1,pJisaCD,pSubj,pMKucode,index){
			var pMemGrade = $("#pMemGrade").val();
			var fstDay = $("#fstDay"+index).val();
			if(pMemGrade == ""){
				alert("학년을 선택해주세요.");
				return false;
			}
			
			var pMjGrade = $("#mjGradeId"+index).val();
			
			if(pMjGrade == ""){
				alert("진단 등급을 선택해주세요.");
				return false;
			}
			
			window.open("/iphei/depositPop?pIpheiday="+pIpheiday+"&pMkucode="+pMKucode+"&pJisaCD="+pJisaCD+"&pDepid1="+pDepid1+"&pSubj="+pSubj+"&pMjGrade="+pMjGrade+"&pMemGrade="+pMemGrade+"&pFstDay="+fstDay+"&seqval="+index,"입금선택","width=600,height=460");
			
		},
		
		ipgumTotal:function(){
			var totalIpheibi = 0;
			var totalWolheibi = 0;
			var totalIpgum = 0;
			
			$("input[name='wolheibi']").each(function(index){
				totalIpheibi += parseInt($("input[name='ipheibi']").eq(index).val());
				totalWolheibi += parseInt($("input[name='wolheibi']").eq(index).val());
			});			
			
			totalIpgum = totalIpheibi + totalWolheibi;
			
			
			$("#totalIpheibi").val(totalIpheibi);
			$("#totalWolheibi").val(totalWolheibi);
			$("#totalIpgum").val(totalIpgum);
		},
		
		ipheiSubmit:function(){
			$("#registIpheiBtn").hide();
			$("#cancelBtn").hide();
			$("#loadingImg").show();
			
			var pIpGuide = $("#pIpGuide").val();
			var pFirstName = $.trim($("#pFirstName").val());		
			var pBirthDay = $("#pBirthDay").val();
			var pMemGrade = $("#pMemGrade").val();
			var pAddr = $.trim($("#pAddr").val());
			var pGfirstname = $.trim($("#pGfirstname").val());
			var tel1 = $("#tel1").val();
			var tel2 = $("#tel2").val();
			var tel3 = $("#tel3").val();
			var phone1 = $("#phone1").val();
			var phone2 = $("#phone2").val();
			var phone3 = $("#phone3").val();
			var ePhone1 = $("#ePhone1").val();
			var ePhone2 = $("#ePhone2").val();
			var ePhone3 = $("#ePhone3").val();
			var email1 = $("#email1").val();
			var email2 = $("#email2").val();
			var gEmail1 = $("#gEmail1").val();
			var gEmail2 = $("#gEmail2").val();
			var specialComment = $.trim($("#specialComment").val());
			
			var tel = tel1+"-"+tel2+"-"+tel3;
			var phone = phone1+"-"+phone2+"-"+phone3;
			var ePhone = ePhone1+"-"+ePhone2+"-"+ePhone3;
			var email = email1+"@"+email2;
			var gEmail = gEmail1+"@"+gEmail2;
			
			var allowSubjCnt = 0;
			
			$("input[name='subj']").each(function(){
				if($(this).val() != ""){
					allowSubjCnt++;
				}
			});
			
			if(allowSubjCnt == 0){
				alert("입회가능한 과목이 없습니다.");
				$.showButton();
				return false;
			}
			
			if(pIpGuide == ""){
				alert("입회경로를 선택해 주세요.");
				$.showButton();
				return false;
			}
			
			if(pFirstName == ""){
				alert("회원명을 입력해 주세요.");
				$("#pFirstName").focus();
				$.showButton();
				return false;
			}
			
			if(pBirthDay ==""){
				alert("생년월일을 입력해 주세요.");
				$("#pBirthDay").focus();
				$.showButton();
				return false;
			}
			
			if(pMemGrade == ""){
				alert("학년을 선택해 주세요.");
				$.showButton();
				return false;
			}
			
			if(pAddr == ""){
				alert("주소를 입력해 주세요.");
				$("#pAddr").focus();
				$.showButton();
				return;
			}
			
			if(!$.telCheck(tel,"자택번호")){
				$("#tel1").val("");
				$("#tel2").val("");
				$("#tel3").val("");
				$("#tel1").focus();
				$.showButton();
				return false;
			}
			
			if(!$.telCheck(phone,"휴대전화")){
				$("#phone1").val("");
				$("#phone2").val("");
				$("#phone3").val("");
				$("#phone1").focus();
				$.showButton();
				return false;
			}
			
			if(!$.telCheck(ePhone,"긴급전화")){
				$("#ePhone1").val("");
				$("#ePhone2").val("");
				$("#ePhone3").val("");
				$("#ePhone1").focus();
				$.showButton();
				return false;
			}
			
//			if(!$.emailCheck(email,"회원 이메일")){
//				$("#email1").val("");
//				$("#email2").val("");
//				$("#email1").focus();
//				$.showButton();
//				return false;
//			}
//			
//			if(!$.emailCheck(gEmail,"학부모 이메일")){
//				$("#gEmail1").val("");
//				$("#gEmail2").val("");
//				$("#gEmail1").focus();
//				$.showButton();
//				return false;
//			}
			
//			if(specialComment.length > 300){
//				alert("특이사항은 300자 이내로 입력해주세요.");
//				$("#specialComment").focus();
//				$.showButton();
//				return false;
//			}
			
			var eachFlag = false;
			
			$("input[name='subj']").each(function(index){
				var thisSubj = $(this).val();
				if(thisSubj !== ""){
					var fstClass = $("select[name='fstClass']").eq(index).val();
					var mjGrade = $("select[name='mjGrade']").eq(index).val();
					var mGubun = $("input[name='mGubun']").eq(index).val();
					var enterwol = $("input[name='enterwol']").eq(index).val();
					if(fstClass == ""){
						alert("입회과목"+thisSubj+"의 교실을 선택해주세요.");
						eachFlag = true;
						return false;
					}
					
					if(mjGrade == ""){
						alert("입회과목"+thisSubj+"의 진단등급을 선택해주세요.");
						eachFlag = true;
						return false;
					}
					
					if(mGubun == "" || enterwol == ""){
						alert("입회과목"+thisSubj+"의 입금을 산출해주세요.");
						eachFlag = true;
						return false;
					}
				}
			});
			
			if(eachFlag){
				$.showButton();
				return false;
			}
			
			if(!confirm("입회처리를 시작합니다.\n\n입회처리를 원하시면 확인을 누르십시요.")){
				$.showButton();
				return false;
			}
			
			$.ajax({
				url:"/iphei/ipheiOk.json",
				type:"POST",
				data: $("#ipheiFrm").serialize(),
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert("입회 "+jsonData.successCnt+" , 승인 "+jsonData.successCnt+" 건이 정상적으로 처리되었습니다.\n\n회원명 :"+pFirstName
							+"\n회원번호:"+jsonData.mkey);
					if($("input[name='ipheiType']:checked").val() == "01"){
						location.href="/iphei";
					}else{
						var pop = window.open("/memberCard/memberOmrInfo?mKey="+jsonData.mkey+"&sKey="+jsonData.gotoSkey+"&kwamok="+jsonData.gotoSubj
								,"Window","width=848, height=700, menubar=no,status=yes,scrollbars=no");
						pop.focus();
						location.href="/iphei";
					}		
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert("정상적으로 처리가 되지않았습니다.");
					$.showButton();
				}
			});
		},
		
		showButton:function(){
			$("#loadingImg").hide();
			$("#registIpheiBtn").show();
			$("#cancelBtn").show();
		}
		
		
		
	});
	
	$('#registIpheiBtn').on("click",function(){
		$.ipheiSubmit();
	});
});
