<%  
	PortletURL backFromMoreURL = renderResponse.createRenderURL();
	backFromMoreURL.setParameter("struts_action", "/portlet_action/facebook/view");
	tabs1 = ParamUtil.getString(request, "tabs1", "Profile");
	backFromMoreURL.setParameter("tabs1", tabs1);

	portletURL = renderResponse.createRenderURL();
	portletURL.setParameter("struts_action", "/infoaxon/fkm/facebook/viewManageFriends");
	portletURL.setParameter("tabs1", tabs1);

	String moreName= ParamUtil.getString(request, "moreName" );
	int count = 0;
	if(ParamUtil.getString(request, "friendsCount" )!=null){
		count = GetDataFromFacebook.getFriendsCount(accessToken);
		request.setAttribute("friendsCount", count);
	}
%>
<Table width="100%">
	<tr bgcolor="#006B9D">
		<td>
			<font color="white" size="3"><B>Friends</B></font>
		</td>
	</tr>
<% if(ParamUtil.getString(request, "showBack" ).equals("true")){ %>
	<tr>
		<td align="right">
			<!--a href="<%=backFromMoreURL%>">Back to Profile</a-->
			<hr/>
		</td>
	</tr>
<%}%>
</table>


<form name="fm"/>
<%
	int cur1=SearchContainer.DEFAULT_CUR;
	int delta=20;
	int total1=count;
	int maxPages=0;
		
	if(Validator.isNotNull(ParamUtil.getString(request, "cur2"))){
		cur1=Integer.parseInt(ParamUtil.getString(request, "cur2"));

	}
	portletURL.setParameter("cur1", String.valueOf(cur1));

	if(Validator.isNotNull(ParamUtil.getString(request, "delta"))){
		delta=Integer.parseInt(ParamUtil.getString(request, "delta"));
	}
	
	portletURL.setParameter("delta", String.valueOf(delta));					
	maxPages=total1/delta;
	
	IdName tempArray[] = GetDataFromFacebook.getFriends(accessToken,(cur1-1)*delta,cur1*delta);
	List<IdName> list = Arrays.asList(tempArray);

%>

<table border="0" width="100%">
<%for(int i=(cur1-1)*delta;i<list.size();i++){%>
		<tr>
			<td width="50%">
				<table width="100%">
					<tr>
						<td style="padding-top:5px;">
							<img src="<%=GeneralFacebookUtil.getImageURL(list.get(i).getId())%>" align="left"/>
						</td>
						<td>
							<%=StringUtil.shorten(list.get(i).getName(),18)%>
						</td>
					</tr>
				</table>
			</td>
			<%i=i+1;
				if(i==list.size())
					break;
			%>
			<td width="50%">
				<table width="100%">
					<tr>
						<td  style="padding-top:5px;">
							<img src="<%=GeneralFacebookUtil.getImageURL(list.get(i).getId())%>" align="left"/>
						</td>
						<td>
							<%=StringUtil.shorten(list.get(i).getName(),18)%>
						</td>
					</tr>
				</table>
			</td>
<%}%>
		</tr>
		<tr>
			<td colspan="2">
			 <liferay-ui:page-iterator
				formName="fm"
				cur="<%=cur1%>"
				curParam="cur2"
				delta="<%=delta%>"
				deltaParam="delta"
				total="<%=total1%>"
				maxPages="<%=maxPages%>"
				type="regular"
				url="<%=portletURL.toString()%>"/>
		</td>
	</tr>
</table>