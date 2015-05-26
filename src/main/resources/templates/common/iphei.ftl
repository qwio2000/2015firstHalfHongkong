<#include "/header.ftl">
<body>
<div id="wrapper">
	<#include "/headerMenu.ftl">
	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
				<#include "/leftContents.ftl">
				<#include "/leftMenu.ftl">
			</div>
			<div id="primary_content" class="primary-content">
				${contents}
			</div>
		</div>
	</div>
</div>
<!-- //container -->
<#include "/footer.ftl">
</body>
</html>
