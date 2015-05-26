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
				if($("input[name='huGubun']")[0].checked){
					$("select[name='huSayu']")[0].disabled = false;
					$("select[name='huSayu']")[1].disabled = true;
				}else{
					$("select[name='huSayu']")[0].disabled = true;
					$("select[name='huSayu']")[1].disabled = false;
				}
			},
			odabChk:function(chkObj, OdabStr, MunNum, OdabNum){
				var boo=true;
				var chktemp = true;
				m_tot = parseInt(document.Qry2FormName.ErrTot.value);
				if ( chkObj.checked ){
					for (i=0 ; i < m_tot ; i++)
					{
						if (document.Qry2FormName.selError[i].value == MunNum)
						{
							for ( j = 0 ; j < 10 ; j++ )
								document.Qry2FormName.txt_input[(MunNum - 1) * 10 + j].checked = false;

							chkObj.checked = true;
							document.all.selErrorList.options.remove(i);
							m_tot--;
							document.Qry2FormName.ErrTot.value = m_tot;
						}
					}
					$.errListRefresh(OdabStr, MunNum);
				}else{
					m_tot = m_tot - 1;
					document.Qry2FormName.ErrTot.value = m_tot;
					for (i=0 ; i <= m_tot ; i++){
						if (document.Qry2FormName.selError[i].value == MunNum){
							document.all.selErrorList.options.remove(i);
							break;
						}
					}
				}
			},
			errListRefresh:function(OdabStr, MunNum){
				var tmpValue = 0;
				var tmpText = "";
				var i = 0;
				m_tot = parseInt(document.Qry2FormName.ErrTot.value);
				for ( i = 0 ; i <= m_tot - 1 ; i++ ){
					if (  MunNum < eval(document.Qry2FormName.selError[i].value)){
						tmpValue = document.Qry2FormName.selError[i].value;
						tmpText = document.Qry2FormName.selError[i].text;
						document.Qry2FormName.selError[i].value = MunNum;
						document.Qry2FormName.selError[i].text = OdabStr;
						MunNum = tmpValue;
						OdabStr = tmpText;
					}
				}
				m_tot = m_tot + 1;
				document.Qry2FormName.ErrTot.value = m_tot;
				var monhang=document.createElement("option");
				monhang.text = OdabStr;
				monhang.value = MunNum ;
				document.Qry2FormName.selError.options.add(monhang);
			},
			noOdab:function(isGtC){
				if(document.Qry2FormName.NoOdab.checked){
					for (i = document.Qry2FormName.selError.length - 1 ; i >= 0 ; i--){
						if(isGtC){
							for ( j = 0 ; j < 10 ; j++ )
								document.Qry2FormName.txt_input[(document.Qry2FormName.selError[i].value - 1) * 10 + j].checked = false;
						}else{
							document.Qry2FormName.txt_input[(document.Qry2FormName.selError[i].value - 1)].checked = false;
						}
						document.all.selErrorList.options.remove(i);
					}
					document.all.ts1.style.display="none";
					document.all.ts1_No.style.display="";
					document.Qry2FormName.NoOdab.value = "1";
					document.Qry2FormName.ErrLst.value = "$";
					document.Qry2FormName.ErrTot.value = 0;
				}else{
					document.all.ts1.style.display="";
					document.all.ts1_No.style.display="none";
					document.Qry2FormName.NoOdab.value = "0";
				}
			},
			chkSubmit:function(){
				strTemp = "$";
				for(i = 0 ; i < document.Qry2FormName.ErrTot.value ; i++){
					strTemp = strTemp + document.Qry2FormName.selError.options(i).text + "$";
				}
				strTemp = strTemp + "#";
				document.Qry2FormName.ErrLst.value = strTemp;
				document.Qry2FormName.action = "/memberCard/memberOmrSave";
				document.all.savechk.style.display="none";
				document.all.savechkIng.style.display="";
				document.Qry2FormName.submit();
			},
			jindoSearch:function(mKey, kwamok,mFstName){
				var pop_status = window.open("/memberCard/jindoSearch?mKey="+mKey+"&kwamok="+kwamok+"&mFstName="+mFstName,"newWindow","width=665,height=800,marginwidth=0,marginheight=0,toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes");
				pop_status.focus();
			},
			popupPost:function(url, mKey, sKey, kwamok){
				var pop_status=window.open("","Window","width=848, height=700, menubar=no,status=yes,scrollbars=no");
				document.frm1.action = url;
				document.frm1.mKey.value = mKey;
				document.frm1.sKey.value = sKey;
				document.frm1.kwamok.value = kwamok;
				document.frm1.target = "Window";
				document.frm1.submit();
				pop_status.focus();
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
		//휴회 가능 여부 조회
		$(document).on("click","#saveButton",function(){
			var jsonSendData = $("#Qry2FormName").serialize();
			$.ajax({
				url: window.location.pathname+".json",
				type: "GET",
				data:jsonSendData,
				cache: false,
				async: true,
				dataType: "JSON",
				success: function(data, textStatus, XMLHttpRequest) {
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
