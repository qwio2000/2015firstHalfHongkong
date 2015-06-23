$(function() {
	$.extend({
		getDepositSet:function(){
			var seqval = $("#seqval").val();
			var jisaCD = $("#pJisaCD").val();
			var depid1 = $("#pDepid1").val();
			var bulsu = $("#bulsu").val();
			var pSubj = $("#pSubj").val();
			var pMemGrade = $("#pMemGrade").val();
			var pRestYMW = $("#pRestYMW").val();
			var mGubun = $("input[name='SellGubun']:checked").val();
			var mSayu = $("#pMSayu").val();
			
			
			var pData = {"seqval":seqval,"jisaCD":jisaCD,"depid1":depid1,"bulsu":bulsu,"pSubj":pSubj,"pMemGrade":pMemGrade
					,"restymw":pRestYMW,"mGubun":mGubun,"mSayu":mSayu};		
			
			$.ajax({
				url:"/iphei/depositSet.json",
				type:"GET",
				data: pData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var source = $("#depositSetTemplate").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper('checkTotheibi',function(context,options){
						if(mGubun == "03" || mGubun == "04"){
							return 0;
						}else{
							return context;
						}
					});
					$("#dataList").empty();
					$("#dataList").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		
		sellChange:function(idx){
			if(idx == "01"){
				$("#tr02").hide();
				$("#tr03").hide();
				$("#tr04").hide();
				$("#tr05").hide();
				
				$.msayuChange("");
			}else if(idx == "02"){
				$("#tr02").show();
				$("#tr03").hide();
				$("#tr04").hide();
				$("#tr05").hide();
				
				$("input[name='ipGubun']:checked").trigger('click');
			}else if(idx == "03"){
				$("#tr02").hide();
				$("#tr03").show();
				$("#tr04").hide();
				$("#tr05").hide();
				
				$("input[name='monThGubun']:checked").trigger('click');
			}else if(idx == "04"){
				$("#tr02").hide();
				$("#tr03").hide();
				$("#tr04").show();
				$("#tr05").hide();
				
				$("input[name='bothGubun']:checked").trigger('click');
			}else if(idx == "05"){
				$("#tr02").hide();
				$("#tr03").hide();
				$("#tr04").hide();
				$("#tr05").show();
				
				$("input[name='saleGubun']:checked").trigger('click');
			}
		},
		
		msayuChange:function(idx){
			$("#pMSayu").val(idx);
			$.getDepositSet();
		},
		
		depositSet:function(ipheibi,chaheibi,wolheibi,totalheibi,enterWol){
			var seqval = $("#seqval").val();
			var fstYMW = $("#fstYMW").val();
			var restymw = $("#restymw").val();
			var restw = restymw.substring(restymw.length-1);
			var checkFlag = false;
			var mkucode = $("#pMkucode").val();
			var mGubun = $("input[name='SellGubun']:checked").val();
			
			if(fstYMW == ""){
				checkFlag = confirm("월회비금액을 결정합니다.\n\n*잔여주차 : "+restw+" 월회비:"+totalheibi+" 가 정확합니까?");
			}else{
				checkFlag = confirm("월회비금액을 결정합니다.\n\n*첫진도주차: "+fstYMW+"주차\n\n*잔여주차 : "+restw+" 월회비:"+totalheibi+" 가 정확합니까?");
			}
			
			if(checkFlag){
				if(mkucode == "01"){
					if(mGubun == "02" || mGubun == "04"){
						opener.$("input[name='ipheibi']").eq(seqval).val("0");
						opener.$("input[name='orgIpheibi']").eq(seqval).val(ipheibi);
					}else{
						opener.$("input[name='ipheibi']").eq(seqval).val(ipheibi);
						opener.$("input[name='orgIpheibi']").eq(seqval).val(ipheibi);
					}
				}else{
					opener.$("input[name='ipheibi']").eq(seqval).val("0");
					opener.$("input[name='orgIpheibi']").eq(seqval).val("0");
				}
				
				if(mGubun == "03" || mGubun == "04"){
					opener.$("input[name='wolheibi']").eq(seqval).val("0");
				}else{
					opener.$("input[name='wolheibi']").eq(seqval).val(totalheibi);
				}
				
				var oriWolheibi = parseInt(chaheibi) + parseInt(wolheibi);
				
				
				var mSayu = "";
				
				if(mGubun == "01"){
					mSayu = "";
				}else if(mGubun == "02"){
					mSayu = $("input[name='ipGubun']:checked").val();
				}else if(mGubun == "03"){
					mSayu = $("input[name='monThGubun']:checked").val();
				}else if(mGubun == "04"){
					mSayu = $("input[name='bothGubun']:checked").val();
				}else if(mGubun == "05"){
					mSayu = $("input[name='saleGubun']:checked").val();
				}
				
				opener.$("input[name='orgWolheibi']").eq(seqval).val(oriWolheibi);
				
				opener.$("input[name='restymw']").eq(seqval).val(restymw);
				opener.$("input[name='enterwol']").eq(seqval).val(enterWol);
				opener.$("input[name='mGubun']").eq(seqval).val(mGubun);
				opener.$("input[name='mSayu']").eq(seqval).val(mSayu);
				opener.$.ipgumTotal();
				self.close();
			}else{
				return;
			}
		}
	});
	
	$("#restymw").on('change',function(){
		$("#pRestYMW").val($(this).val());
		$.getDepositSet();
	});
	
	
	$("#pRestYMW").val($("#restymw").val());
	
	$.getDepositSet();
	
});
