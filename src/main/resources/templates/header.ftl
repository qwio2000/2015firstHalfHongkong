<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${title?default('')}</title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_center.css" />
<link rel="stylesheet" type="text/css" href="/public/css/jquery-ui.1.11.4.min.css" />
<#if headerCss?has_content>
	<#list headerCss as css>
		<link rel="stylesheet" type="text/css" href="/public/css/${css}.css" />
	</#list>
</#if>
<script type="text/javascript" src="/public/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/public/js/jquery-ui.1.11.4.min.js"></script>
<script type="text/javascript" src="/public/js/handlebars_3_0_3.min.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
<#if headerScript?has_content>
	<#list headerScript as script>
		<script type="text/javascript" src="/public/js/${script}.js"></script>
	</#list>
</#if>
</head>
