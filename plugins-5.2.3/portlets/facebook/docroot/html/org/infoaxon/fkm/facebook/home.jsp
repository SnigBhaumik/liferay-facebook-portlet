<%
	portletURL = renderResponse.createRenderURL();
	String tabValue = ParamUtil.getString(request, "tabs1", "Home");
%>
<%@ include file="/html/org/infoaxon/fkm/facebook/updateStatus.jsp" %>
<hr/>
<%
	request.setAttribute("tabValue",tabValue); 
	request.setAttribute("label", "Recent Updates");
%>
<%@ include file="/html/org/infoaxon/fkm/facebook/feeds.jsp" %>

