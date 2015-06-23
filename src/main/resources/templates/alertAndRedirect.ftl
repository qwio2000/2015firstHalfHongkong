<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var message = '${message?default("")}';
		var returnUrl = '${url?default("")}';
		var mode = '${mode?default("")}';
		
		alert(message);
		
		if(mode == "close"){
			self.close();		
		}else{
			document.location.href = returnUrl;
		}
	</script>
</body>
</html>