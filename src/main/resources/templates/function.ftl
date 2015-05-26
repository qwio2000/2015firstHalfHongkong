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