<%
	PortletURL readFullStoryURL = renderResponse.createActionURL();
	readFullStoryURL.setParameter("_spage", "/portlet_action/facebook/view");
%>
<div align="left" style="height:600px; width:100%; overflow:auto;">
<% 
	String tabValue1 = (String)request.getAttribute("tabValue");
	Feed newFeeds[] = null;
	if(tabValue.equals("Home")){
		newFeeds = GetDataFromFacebook.getUpdates(accessToken, "home");
		readFullStoryURL.setParameter("backPage", "Home");
		request.setAttribute("backPage", "Home");
	}
	else if(tabValue.equals("Wall")){
		newFeeds = GetDataFromFacebook.getUpdates(accessToken, "feed");
		readFullStoryURL.setParameter("backPage", "Wall");
	}
	String label = (String)request.getAttribute("label");
%>

<table border="0" width="100%" cellpadding="10">
	<tr bgcolor="#006B9D">
		<td colspan="2" style="padding-top:3px;padding-bottom:3px;">
			<font color="white" size="3"><B>&nbsp;<%=label%></B></font>
		</td>
	</tr>

	<tr>
		<td valign="top" style="padding-right:15px;padding-top:5px;" colspan="2" >
			<div id="processingBlock" style="display:none">
			<center>
				Please wait...&nbsp;&nbsp;
				<img src="/html/portlet/infoaxon/fkm/facebook/img/processing.gif" alt=" Publishing your post">
			</center>
			</div>
		</td>
	</tr>

<%
if (newFeeds.length==0){
%>
<tr>
		<td valign="top" style="padding-right:15px;padding-top:5px;" colspan="2" >
			there is no update
		</td>
</tr>
<%
}
else{
for( int i=0; i<newFeeds.length; i++){ 
%>
<tr>
	<td valign="top" style="padding-right:15px;padding-top:5px;">
		<%if(newFeeds[i].getFrom()!= null) {%>
			 <img src="<%=GeneralFacebookUtil.getImageURL(newFeeds[i].getFrom().getId())%>"/>
		<%}%>
	</td>
	<td style="padding-bottom:20px;">
		<%if(newFeeds[i].getFrom()!= null) {%>
			 <font color="navy"><b><%= newFeeds[i].getFrom().getName()%></b></font>
		<%}%>
		<%if(newFeeds[i].getTo()!= null && tabValue.equals("Home")) {%>
			 &nbsp;to&nbsp;<font color="navy"><b><%= newFeeds[i].getTo().getName()%></b></font>
		<%}%>
		<table border="0">
			<tr>
				<% if(newFeeds[i].getPicture()!= null){ %>
				<td style="padding-right:10px;">
					<img src="<%= newFeeds[i].getPicture()%>" align="left"/>
				</td>
				<%}%>
				<td>
				<%if(newFeeds[i].getMessage()!= null) {%>
					<i><%= StringUtil.shorten(newFeeds[i].getMessage(),200)%></i><br/>
				<%}%>

				 <% if (newFeeds[i].getName()!= null) {%>
					<%= StringUtil.shorten(newFeeds[i].getName(),150)%><br/>
				<%}%>

				 <% if (newFeeds[i].getDescription()!= null) {%>
					<%= StringUtil.shorten(newFeeds[i].getDescription(),200)%><br/>
				<%}%>

				<% if (newFeeds[i].getCaption()!= null) {%>
						<%if(newFeeds[i].getLink()!= null) {%>
						<a href="<%= newFeeds[i].getLink()%>" target="_blank"> <%}%>
							<%= StringUtil.shorten(newFeeds[i].getCaption(),100)%>
						<%if(newFeeds[i].getLink()!= null) {%>
						</a>
						<%}%><br>
				<%}%>
				</td>
			</tr>	
		</table>
		

		<%if(newFeeds[i].getLikesCount()!= null || 
			newFeeds[i].getCommentsCount()!=null || 
			newFeeds[i].getIcon()!= null) {%>
		<table border="0">
		<tr>
		<td> 
			<% if(newFeeds[i].getApplication()!= null){ %>
				<img src="<%=GeneralFacebookUtil.getImageURL(newFeeds[i].getApplication().getId())%>" height="16" width="16"/>
			<%}else if(newFeeds[i].getIcon()!= null){ %>
				<img src="<%= newFeeds[i].getIcon()%>"/>
			<%}%>
		</td>
		<td> <% if(newFeeds[i].getTime()!= null){ %>
			&nbsp;<%= GeneralFacebookUtil.computeRelativeTime(newFeeds[i].getTime())%>
		<%}%>
		</td>
		<td> <%if(newFeeds[i].getApplication()!= null) {%>
			 &nbsp;&nbsp;<i>via <%= newFeeds[i].getApplication().getName()%></i>
		<%}%>
		</td>
		<td> <% if(newFeeds[i].getLikesCount()!= null){ %>
			&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/html/img/like.png" alt="Likes: ">&nbsp;
			<%=newFeeds[i].getLikesCount()%>&nbsp;&nbsp;
		<%}%>
		</td>
		<td> <% if(newFeeds[i].getCommentsCount()!= null){ %>
			&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/html/img/comment.png" alt="Comments: ">&nbsp;
			<%=newFeeds[i].getCommentsCount()%>&nbsp;&nbsp;
		<%}%>
		</td>
		<%}
		 else{%>
			&nbsp;
		<%}%>
		<td> <%
				readFullStoryURL.setParameter("storyId", newFeeds[i].getId().toString());
				readFullStoryURL.setParameter("name", newFeeds[i].getFrom().getName());
				readFullStoryURL.setParameter("accessToken", accessToken);
			 %>
			&nbsp;&nbsp;<!--a href="<%=readFullStoryURL.toString()%>">read full story...</a-->
		</td>
		</table>
	</td>
</tr>
<%}}%>

</table>
</div>