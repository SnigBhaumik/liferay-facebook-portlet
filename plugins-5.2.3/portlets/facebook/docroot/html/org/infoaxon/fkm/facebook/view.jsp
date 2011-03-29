
<%@ include file="/html/org/infoaxon/fkm/facebook/init.jsp" %>

<%
	renderURL = renderResponse.createActionURL();
	renderURL.setParameter("struts_action", "/portlet_action/facebook/view"); 

	actionURL = renderResponse.createActionURL();
	actionURL.setParameter("struts_action", "/portlet_action/facebook/view"); 
%>

<%	
	String code = request.getParameter("code");
	String accessToken=request.getParameter("accessToken");
	if((accessToken =="") || (accessToken==null)){
		 accessToken=FacebookLocalServiceUtil.getAccessToken(themeDisplay.getUserId());
	}
	boolean accessTokenValidityStatus = false;
	if((accessToken !="") || (accessToken!=null)){
		accessTokenValidityStatus  = GeneralFacebookUtil.validateAccessToken(accessToken);
	}

	if(!accessTokenValidityStatus){
		if(code!=null){
			accessToken = GetDataFromFacebook.getAccessToken(code, renderURL.toString());
			FacebookLocalServiceUtil.storeAccessToken(themeDisplay.getUserId(), accessToken);
			accessTokenValidityStatus = true;
			request.setAttribute("accessToken",accessToken);
		}
		else{
			if(themeDisplay.isSignedIn()){
%>
				<form name="RoleForm" method="post" action="<%=actionURL.toString()%>">
					<input type="hidden" name="<portlet:namespace />CMD"  id="<portlet:namespace />CMD" value="Login">
					<input type="hidden" name="<portlet:namespace />callback"  id="<portlet:namespace />callback" value='<%=renderURL.toString()%>'>
					<input type="submit" name="<portlet:namespace />Login" value="Login to facebook">
				</form>
			
<%			}
			else{
%>
				<liferay-ui:message key="please-sign-in-to-access-this-application" />
<%

			}
		}
	}
	if(accessTokenValidityStatus){
		String tabs1 = ParamUtil.getString(request, "tabs1", "Home");
		PortletURL portletURL = renderResponse.createRenderURL();
		portletURL.setWindowState(WindowState.NORMAL);
		portletURL.setParameter("struts_action", "/infoaxon/fkm/facebook/viewtabs");
		portletURL.setParameter("tabs1", tabs1);
		String tabNames = "Home,Profile,Wall,Friends";
%>	
		<!--show tabs block -->
<%		if(themeDisplay.isSignedIn()){%>
			<liferay-ui:tabs names="<%= tabNames %>" url="<%= portletURL.toString() %>"/>
			
			<%if (tabs1.equals("Home")){%>
				<%@ include file="/html/org/infoaxon/fkm/facebook/home.jsp" %>
			<%}
			else if (tabs1.equals("Profile")){ %>
				<%@ include file="/html/org/infoaxon/fkm/facebook/profile.jsp" %>
			<%}
			else if (tabs1.equals("Wall")){ %>
				<%@ include file="/html/org/infoaxon/fkm/facebook/wall.jsp" %>
			<%}
			else if (tabs1.equals("Friends")){ %>
				<%@ include file="/html/org/infoaxon/fkm/facebook/friends.jsp" %>
			<%}
		}%>
		
		
<%	}
%>