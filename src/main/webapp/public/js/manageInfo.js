$(function() {
	$.extend ({
		memberInfoPop:function(mKey, sKey, kwamok){
			var pop_status = window.open("/memberCard/memberInfo?mKey="+mKey+"&sKey="+sKey+"&kwamok="+kwamok,"Window","width=848,height=700,menubar=no,status=yes,scrollbars=no");
			pop_status.focus();
		},
		jindoSearch:function(mKey, kwamok,mFstName){
			var pop_status = window.open("/memberCard/jindoSearch?mKey="+mKey+"&kwamok="+kwamok+"&mFstName="+mFstName,"newWindow","width=665,height=800,marginwidth=0,marginheight=0,toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes");
			pop_status.focus();
		}
	});
		// parameter 정의
		var manageInfo_url = "/manageInfo";
		
		//회원조회
		$(document).on("click","#searchBtn",function(){
			var url = manageInfo_url+window.location.pathname+".json";
			if($("input[name='searchWord']").val() == null || $("input[name='searchWord']").val() == ''){
				alert('검색어를 입력하여 주십시오');
				$("input[name='searchWord']").focus();
				return;
			}
			if(window.location.pathname == '/manageInfo/korMemberSearch'){
				url = window.location.pathname+".json";
				if($("input[name='birthDay']").val() == null || $("input[name='birthDay']").val() == ''){
					alert('생년월일을 입력하여 주십시오');
					$("input[name='birthDay']").focus();
					return;
				}
			}
			var jsonSendData = $("#frm1").serialize();
			console.log(jsonSendData);
			$.ajax({
				url: url,
				type: "POST",
				data:jsonSendData,
				cache: false,
				async: true,
				dataType: "JSON",
				success: function(data, textStatus, XMLHttpRequest) {
					console.log(data);
					var source = $("#template").html();
					var template = Handlebars.compile(source);
					//index값 0부터 시작하기 때문에 1증가
					Handlebars.registerHelper("inc", function(value, options){
						return parseInt(value) + 1;
					});
					//if문
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
					$("#mainContent").append(template(data));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		});
		$(document).on("click","#search",function(){
			var url = manageInfo_url+window.location.pathname+".json";
			var jsonSendData = $("#frm1").serialize();
			console.log(url);
			console.log(jsonSendData);
			$.ajax({
				url: url,
				type: "POST",
				data:jsonSendData,
				cache: false,
				async: true,
				dataType: "JSON",
				success: function(data, textStatus, XMLHttpRequest) {
					console.log(data);
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
					Handlebars.registerHelper('trimString', function(passedString, startstring, endstring) {
					   var theString = passedString.substring( startstring, endstring );
					   return new Handlebars.SafeString(theString)
					});
					$("#mainContent").empty();
					$("#mainContent").append(template(data));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		});
		$(document).on("click","#excel",function(){
			var empKey = $("select[name='empKey']").val();
			var form = "<form action='/manageInfo/emptyHakjuk.xls' method='post'>"; 
			form += "<input type='hidden' name='empKey2' value='"+empKey+"' />"; 
			form += "</form>"; 
			jQuery(form).appendTo("body").submit().remove(); 
		});
		$(document).on("click","#excelHuhei",function(){
			var empKey = $("select[name='empKey']").val();
			var kwamok = $("select[name='kwamok']").val();
			var startBirthDate = $("input[name='startBirthDate']").val();
			var endBirthDate = $("input[name='endBirthDate']").val();
			var hu_skey = $("input[name='hu_skey']").val();
			var startHuheiDate = $("input[name='startHuheiDate']").val();
			var endHuheiDate = $("input[name='endHuheiDate']").val();
			var form = "<form action='/manageInfo/huheiList.xls' method='post'>"; 
			form += "<input type='hidden' name='empKey2' value='"+empKey+"' />"; 
			form += "<input type='hidden' name='kwamok2' value='"+kwamok+"' />"; 
			form += "<input type='hidden' name='startBirthDate2' value='"+startBirthDate+"' />"; 
			form += "<input type='hidden' name='endBirthDate2' value='"+endBirthDate+"' />"; 
			form += "<input type='hidden' name='hu_skey2' value='"+hu_skey+"' />"; 
			form += "<input type='hidden' name='startHuheiDate2' value='"+startHuheiDate+"' />"; 
			form += "<input type='hidden' name='endHuheiDate2' value='"+endHuheiDate+"' />"; 
			form += "</form>"; 
			jQuery(form).appendTo("body").submit().remove(); 
		});
		$(document).on("click","#popBtn",function(){
			if($("input[name='searchDay']").val() == null || $("input[name='searchDay']").val() == ''){
				alert('검색년월을 입력하여 주십시오');
				$("input[name='searchDay']").focus();
				return;
			}
			var pop_status=window.open("","Window","width=848, height=700, menubar=yes,status=yes,scrollbars=yes");
			$("#frm1").attr("target","Window");
			$("#frm1").submit();
			pop_status.focus();
		});
});
