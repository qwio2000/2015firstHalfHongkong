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
	<#if firstArgs == secondArgs>
		<#return trueValue>
	<#else>
		<#return falseValue>
	</#if>
</#function>

<#function displayOdab firstArgs>
	<#if firstArgs == "01">
		<#return '①'>
	<#elseif firstArgs == "02">
		<#return '②'>
	<#elseif firstArgs == "03">
		<#return '③'>
	<#elseif firstArgs == "04">
		<#return '④'>
	<#elseif firstArgs == "05">
		<#return '⑤'>
	<#elseif firstArgs == "06">
		<#return '⑥'>
	<#elseif firstArgs == "07">
		<#return '⑦'>
	<#elseif firstArgs == "08">
		<#return '⑧'>
	<#elseif firstArgs == "09">
		<#return '⑨'>
	<#elseif firstArgs == "10">
		<#return '⑩'>
	<#elseif firstArgs == "11">
		<#return '⑪'>																				
	<#else>
		<#return '⑫'>						
	</#if>
</#function>

<#function displayMonth firstArgs>
	<#if firstArgs == "01">
		<#return 'JAN'>
	<#elseif firstArgs == "02">
		<#return 'FEB'>
	<#elseif firstArgs == "03">
		<#return 'MAR'>
	<#elseif firstArgs == "04">
		<#return 'APR'>
	<#elseif firstArgs == "05">
		<#return 'MAY'>
	<#elseif firstArgs == "06">
		<#return 'JUN'>
	<#elseif firstArgs == "07">
		<#return 'JUL'>
	<#elseif firstArgs == "08">
		<#return 'AUG'>
	<#elseif firstArgs == "09">
		<#return 'SEP'>
	<#elseif firstArgs == "10">
		<#return 'OCT'>
	<#elseif firstArgs == "11">
		<#return 'NOV'>
	<#elseif firstArgs == "12">
		<#return 'DEC'>																						
	<#else>
		<#return ''>						
	</#if>	
</#function>

<#function displayJinSet firstArgs secondArgs>
	<#if secondArgs == "Z999">
		<#return "<font style='font-size:11px;'>COM</font>">
	<#elseif secondArgs?substring(1) == "000">
		<#return secondArgs?substring(0,1) + "-Dig.">
	<#elseif secondArgs?substring(1) == "999">
		<#return secondArgs?substring(0,1) + "-Ach.">
	<#elseif secondArgs?substring(1) == "992">
		<#return secondArgs?substring(0,1) + "-Rev.">
	<#elseif secondArgs?substring(1) == "000">
		<#return secondArgs?substring(0,1) + "-Prep.">
	<#else>
		<#return secondArgs>	
	</#if>											
</#function>