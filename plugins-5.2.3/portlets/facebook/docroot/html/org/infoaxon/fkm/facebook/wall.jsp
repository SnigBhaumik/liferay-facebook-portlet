<%

actionURL = renderResponse.createActionURL();
actionURL.setParameter("struts_action", "/infoaxon/fkm/facebook/updateStatus"); 

portletURL = renderResponse.createRenderURL();
String tabValue = ParamUtil.getString(request, "tabs1", "wall");

%>
	<%@ include file="/html/org/infoaxon/fkm/facebook/updateStatus.jsp" %>
	<hr>

	<%if(tabValue.equals("Wall")) {
		request.setAttribute("tabValue",tabValue); 
		request.setAttribute("label", "Wall");%>
		<%@ include file="/html/org/infoaxon/fkm/facebook/feeds.jsp" %>
	<%}%>