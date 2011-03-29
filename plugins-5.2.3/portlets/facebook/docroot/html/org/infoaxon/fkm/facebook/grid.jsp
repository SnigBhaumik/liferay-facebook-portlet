
<%  
	PortletURL moreURL1 = renderResponse.createRenderURL();
	moreURL1.setParameter("struts_action", "/infoaxon/fkm/facebook/more");

	String label = (String)request.getAttribute("label");
	int stringSize = 40;

	String showCategory = (String)request.getAttribute("showCategory");
	PersonalDetails personalDetails1= (PersonalDetails)request.getAttribute("personalDetails");
	request.setAttribute("accessToken", (String)request.getAttribute("accessToken"));

%>

<tr>
	<td valign="top">
		<b><%=StringUtil.upperCaseFirstLetter(label)%></b>
	</td>
		
	<td valign="middle">
		<table border="0" cellpadding="3" width="100%">
		<% Contents temp[] = null;
		if (label.equals("education")){
			 temp = personalDetails1.getEducation();
			 stringSize = 20;
		}
		else if (label.equals("music")){
			 temp = personalDetails1.getMusic();
		}
		else if (label.equals("interests")){
			 temp = personalDetails1.getInterests();
		}
		else if (label.equals("movies")){
			 temp = personalDetails1.getMovies();
		}
		else if (label.equals("activities")){
			 temp = personalDetails1.getActivities();
		}
		else if (label.equals("television")){
			 temp = personalDetails1.getTelevision();
		}
		else if (label.equals("books")){
			 temp = personalDetails1.getBooks();
		}
		else if (label.equals("work")){
			 temp = personalDetails1.getWork();
			 stringSize = 25;
		}%>
	</td>
</tr>
		
<%for( int i=0; i<temp.length; i++){ %>
<tr>
	<td width="50%">
		<table border="0">
			<tr>
				<td style="padding-top:5px; padding-bottom:3px;">
					<img src="<%=GeneralFacebookUtil.getImageURL(temp[i].getId())%>" align="left">
				</td>
				<td  valign="top">&nbsp;<%=StringUtil.shorten(temp[i].getName(),stringSize)%>
					<%if(showCategory.equals("true")&& temp[i].getCategory()!=null){%>
						<br/>&nbsp;<%=temp[i].getCategory()%>
					<%}%>
				</td>
			</tr>
		</table>
		<% if (i==2 && temp.length!=3) {
				moreURL1.setParameter("moreName",(String)request.getAttribute("label"));%>
	<td>
		<!--a href="<%=moreURL1%>">&nbsp;<%=GetDataFromFacebook.getMiscCount((String)request.getAttribute("accessToken"),label)-3%>&nbsp;More >>></a-->
		<table border="0">
			<tr>
				<td>
					<img src="<%=GeneralFacebookUtil.getImageURL(temp[i+1].getId())%>" align="left">
				</td>
				<td  valign="top">
					&nbsp;<%=StringUtil.shorten(temp[i+1].getName(),stringSize)%>
					<%if(showCategory.equals("true") && temp[i+1].getCategory()!=null){%>
						<br/>
						&nbsp;<%=temp[i+1].getCategory()%>
					<%}%>
				</td>
			</tr>
		</table>
		<!---->
	</td>
				 <% break;}
				i=i+1;
			if(temp.length>1){%>
	<td width="50%">
		<table border="0">
			<tr>
				<td>
					<img src="<%=GeneralFacebookUtil.getImageURL(temp[i].getId())%>" align="left">
				</td>
				<td  valign="top">
					&nbsp;<%=StringUtil.shorten(temp[i].getName(),stringSize)%>
					<%if(showCategory.equals("true") && temp[i].getCategory()!=null){%>
						<br/>
						&nbsp;<%=temp[i].getCategory()%>
					<%}%>
				</td>
			</tr>
		</table>
	</td>
</tr>
<%}}%>
</table>
</td>
</tr>