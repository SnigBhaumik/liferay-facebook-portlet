<%@ include file="init.jsp" %>
<tiles:useAttribute id="tilesPortletContent" name="portlet_content" classname="java.lang.String" ignore="true" />

<div>
	<jsp:include page='<%= "/html" + tilesPortletContent %>' flush="true" />
</div>