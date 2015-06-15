<#function attachZero firstArgs>
	<#if firstArgs lt 10>
		<#return 0?string+firstArgs?string>
	<#else>
		<#return firstArgs>
	</#if>
</#function>
<#function currentDayStyle firstArgs, secondArgs>
	<#if (firstArgs?string+'/'+attachZero(mm?number)) = (.now?string("yyyy/MM"))>
		<#return '#fcf3f8'>
	</#if>
</#function>
<#function yearNumberFormat firstArgs>
	<#return firstArgs?string("##0")>
</#function>
<#function compare firstArgs secondArgs trueValue falseValue>
	<#if firstArgs = secondArgs>
		<#return trueValue>
	<#else>
		<#return falseValue>
	</#if>
</#function>
<#function displayOdab firstArgs>
	<#if firstArgs = "01">
		<#return '①'>
	<#elseif firstArgs = "02">
		<#return '②'>
	<#elseif firstArgs = "03">
		<#return '③'>
	<#elseif firstArgs = "04">
		<#return '④'>
	<#elseif firstArgs = "05">
		<#return '⑤'>
	<#elseif firstArgs = "06">
		<#return '⑥'>
	<#elseif firstArgs = "07">
		<#return '⑦'>
	<#elseif firstArgs = "08">
		<#return '⑧'>
	<#elseif firstArgs = "09">
		<#return '⑨'>
	<#elseif firstArgs = "10">
		<#return '⑩'>
	<#elseif firstArgs = "11">
		<#return '⑪'>																				
	<#else>
		<#return '⑫'>						
	</#if>
</#function>