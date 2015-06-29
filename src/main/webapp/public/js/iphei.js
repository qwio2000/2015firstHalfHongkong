$(function() {
	$.extend({
		existsMemberSearch:function(pSearchMKey,pMName,pBirthYY){
			
			var pData = {"mkey":pSearchMKey,"mName":pMName,"birthYY":pBirthYY};		
			
			$.ajax({
				url:"/iphei/existsMember.json",
				type:"GET",
				data: pData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var source = $("#existsTemplate").html();
					var template = Handlebars.compile(source);
					$("#dataList").empty();
					$("#dataList").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		
		mujinMemberSearch:function(pSearchMKey,pMName,pBirthYY){

			var pData = {"mkey":pSearchMKey,"mName":pMName,"birthYY":pBirthYY};		
			
			$.ajax({
				url:"/iphei/mujinMember.json",
				type:"GET",
				data: pData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var source = $("#mujinTemplate").html();
					var template = Handlebars.compile(source);
					$("#dataList").empty();
					$("#dataList").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		
		changeIpkind:function(){
			var ipkind = $("input[name='ipkind']:checked").val();
			
			if(ipkind == "02"){
				$("#trSearch").show();
				$("#dataList").show();
				$("#trKwamok").show();
			}else if(ipkind == "03"){
				$("#trSearch").show();
				$("#dataList").show();
				$("#trKwamok").hide();
			}else{
				$("#trSearch").hide();
				$("#dataList").hide();
				$("#trKwamok").show();
			}
			
			$.resetInputData();
		},
		
		resetInputData:function(){
			//초기화
			$("#pSearchMKey").val("");
			$("#pMName").val("");
			$("#pBirthYY").val("");
			$("#dataList").empty();
		},
		
		changeDepid:function(){
			location.reload();
		},
		
		registSubmit:function(subj,omrDate){
			var submitFlag = false;
			var str = "";
			
			if(subj != ""){
				
				str = subj;
				
			}else{
				$("input[name='subj']:checked").each(function(){
					if(submitFlag){
						str += ",";
					}
					str += $(this).val()
					
					submitFlag = true;
				});
				
				if(!submitFlag){
					alert("입회 과목을 선택하십시오.");
					return;
				}
			}
			
			$("#pIpheiday").val($("#ipheiday").val());
			$("#pSubj").val(str);
			$("#pOmrdate").val(omrDate);
			$("#pIpheiDepid").val($("#ipheiDepid").val());
			$("#pIpheiKind").val($("input[name='ipkind']:checked").val());
			
			$("#ipheiFrm").attr({"action":"/iphei/join","method":"POST"});
			$("#ipheiFrm").submit();
		},
		
		checkForm:function(mkey,subj,omrDate){
			$("#pMKey").val(mkey);
			$.registSubmit(subj,omrDate);
		},
		getIpheiList:function(){
			
			var jsonSendData = $("#frm1").serialize();
			console.log(jsonSendData);
			$.ajax({
				url:"/iphei/list.json",
				type:"POST",
				data: jsonSendData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var source = $("#template").html();
					var template = Handlebars.compile(source);
					//index값 0부터 시작하기 때문에 1증가
					Handlebars.registerHelper("inc", function(value, options){
						return parseInt(value) + 1;
					});
					Handlebars.registerHelper('xIf', function (lvalue, operator, rvalue, options) {
					    var operators, result;
					    if (arguments.length < 3) {
					        throw new Error("Handlerbars Helper 'compare' needs 2 parameters");
					    }
					    if (options === undefined) {
					        options = rvalue;
					        rvalue = operator;
					        operator = "===";
					    }
					    operators = {
					        '==': function (l, r) { return l == r; },
					        '===': function (l, r) { return l === r; },
					        '!=': function (l, r) { return l != r; },
					        '!==': function (l, r) { return l !== r; },
					        '<': function (l, r) { return l < r; },
					        '>': function (l, r) { return l > r; },
					        '<=': function (l, r) { return l <= r; },
					        '>=': function (l, r) { return l >= r; },
					        'typeof': function (l, r) { return typeof l == r; }
					    };

					    if (!operators[operator]) {
					        throw new Error("'xIf' doesn't know the operator " + operator);
					    }

					    result = operators[operator](lvalue, rvalue);
					    if (result) {
					        return options.fn(this);
					    } else {
					        return options.inverse(this);
					    }
					});
					$("#mainContent").empty();
					$("#mainContent").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
	$(document).on("click","#excel",function(){
		$("#frm1").submit();
	});
	
	$(document).on("click","#pSearchBtn",function(){
		var pSearchMKey = $.trim($("#pSearchMKey").val());
		var pMName = $.trim($("#pMName").val());
		var pBirthYY = $.trim($("#pBirthYY").val());
		
		if(pSearchMKey == "" && pMName == "" && pBirthYY == ""){
			alert("검색에 필요한 검색조건을 입력하십시오.");
			return;
		}
		
		if(pSearchMKey != ""){
			if(pSearchMKey.length != 8){
				alert("회원번호는 8자리입니다.");
				$("#pSearchMKey").val("");
				$("#pSearchMKey").focus();
				return;
			}
		}
		
		if(pMName != ""){
			if(pMName.length < 2){
				alert("회원명은 2자리이상 입력하십시오.");
				$("#pMName").val("");
				$("#pMName").focus();
				return;
			}
		}
		
		if(pBirthYY != ""){
			var num_check=/^[0-9]*$/;
			if(!num_check.test(pBirthYY)){
				alert("생일년도에는 숫자만 입력할 수 있습니다.");
				$("#pBirthYY").val("");
				$("#pBirthYY").focus();
				return;
			}
		}
		
		var ipkind = $("input[name='ipkind']:checked").val();
		if(ipkind == "02"){
			$.existsMemberSearch(pSearchMKey,pMName,pBirthYY);
		}else if(ipkind == "03"){
			$.mujinMemberSearch(pSearchMKey,pMName,pBirthYY);
		}
	});
	
	$(document).on("change","input[name='ipkind']",function(){
		$.changeIpkind();
	});
	
	$(document).on("click","input[name='subj']",function(){
		var thisSubj = $(this).val();
		var checkFlag = false;
		
		if($(this).prop("checked")){
			checkFlag = true;
		}else{
			checkFlag = false;
		}
		
		$("input[name='subj']:not(:checked)").each(function(){
				var anotherSubj = $(this).val();
				
				if( (thisSubj == "KK" && anotherSubj == "KG") || (thisSubj == "KG" && anotherSubj == "KK") 
						|| (thisSubj == "KE" && anotherSubj == "KL") || (thisSubj == "KL" && anotherSubj == "KE") 
						|| (thisSubj == "KC" && anotherSubj == "KJ") || (thisSubj == "KJ" && anotherSubj == "KC")
					    || (thisSubj == "CE" && anotherSubj == "CL") || (thisSubj == "CL" && anotherSubj == "CE")){
					$(this).prop("disabled",checkFlag);
				}
		});
	});
});
