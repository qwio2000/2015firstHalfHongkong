<#include "/function.ftl">
<#if searchKwamok?exists && searchKwamok != ''>
<#else>
<#assign searchKwamok = memberDetailInfo.kwamok>
</#if>
<select name="searchKwamok">
	<option value="all">전체</option>
<#list kwamokList as kwamok>
	<option value="${kwamok }" ${compare(searchKwamok,kwamok,"selected='selected'","")} >${kwamok }</option>
</#list> 
</select>
