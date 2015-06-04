$(function() {
	$.extend ({
		memberInfoPop:function(mKey, sKey, kwamok){
			var pop_status = window.open("/memberCard/memberInfo?mKey="+mKey+"&sKey="+sKey+"&kwamok="+kwamok,"Window","width=848,height=700,menubar=no,status=yes,scrollbars=no");
			pop_status.focus();
		}
	});
		var head = $("head");
		var headlinkLast = head.find("link[rel='stylesheet']:last");
		var linkElement = "<style>.ui-datepicker-calendar {display: none;}</style>";
		if (headlinkLast.length){
			headlinkLast.after(linkElement);
		}
		else {
		   head.append(linkElement);
		}
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
});
