<div class="tab-A">
	<ul>
		<li class="first <#if currentUrl == '/memberCard/memberInfo'>active</#if>">	<a href="javascript:$.locationGubun('/memberCard/memberInfo','1')">회원상세정보</a></li>
		<li class="<#if currentUrl == '/memberCard/memberKwamokInfo'>active</#if>">	<a href="javascript:$.locationGubun('/memberCard/memberKwamokInfo','1')">과목정보</a></li>
		<li class="<#if currentUrl == '/memberCard/memberJindoInfo'>active</#if>">	<a href="javascript:$.locationGubun('/memberCard/memberJindoInfo','1')">진도정보</a></li>
		<li class="<#if currentUrl == '/memberCard/memberIpheiInfo'>active</#if>">	<a href="javascript:$.locationGubun('/memberCard/memberIpheiInfo','1')">입복회이력</a></li>
		<li class="<#if currentUrl == '/memberCard/memberIpgumInfo'>active</#if>">	<a href="javascript:$.locationGubun('/memberCard/memberIpgumInfo','1')">입금이력</a></li>
		<li class="last <#if currentUrl == '/memberCard/memberHuheiInfo'>active</#if>">	<a href="javascript:$.locationGubun('/memberCard/memberHuheiInfo','1')">퇴회이력</a></li>
	</ul>
</div>