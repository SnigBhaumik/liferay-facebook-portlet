<div align="left" style="height:600px; width:100%; overflow:auto;">
<%  
	PortletURL moreURL = renderResponse.createRenderURL();
	moreURL.setParameter("struts_action", "/infoaxon/fkm/facebook/more");

	PortletURL viewManageFriendsURL = renderResponse.createRenderURL();
	viewManageFriendsURL.setParameter("struts_action", "/portlet_action/facebook/view"); 
	viewManageFriendsURL.setParameter("tabs1", "Friends");
	

	if(GeneralFacebookUtil.validateAccessToken(accessToken)){
		PersonalDetails personalDetails = GetDataFromFacebook.getPersonalDetails(accessToken,"self","id");
		request.setAttribute("personalDetails", personalDetails);
		request.setAttribute("accessToken", accessToken);%>
		
		<table border="0" cellpadding="10" cellspacing="10" width="100%">
			<tr>
				<td rowspan="10" valign="top" width="25%">
					<img src="<%=personalDetails.getPicture()%>"/>
					<br/><center>
					<%IdName friends[] = GetDataFromFacebook.getFriends(accessToken,0,6);%>
					<%if(friends!= null){%>
					<br/>
						<table border="0" width="100%">
							<tr bgcolor="#006B9D">
								<td colspan="2">
									<font color="white" size="3"><B>Friends</B></font>
								</td>
							</tr>
							<tr>
								<%for( int i=0; i<friends.length; i++){ %>
								<td width="60" style="padding-top:5px;">
									<img src="<%=GeneralFacebookUtil.getImageURL(friends[i].getId())%>"/>
								</td>
								<td style="padding-top:5px;">
									<%=StringUtil.shorten(friends[i].getName(),15)%>
								</td>
							</tr>
								<%}%>
						<%if(friends.length==6)
						{%>
							<tr>
								<td colspan="2">
									<% 
										 viewManageFriendsURL.setParameter("profileId", friends[0].getId()); 
										 viewManageFriendsURL.setParameter("accessToken", accessToken); 
										 viewManageFriendsURL.setParameter("showBack", "true"); 
									%>
									<!--a href="<%=viewManageFriendsURL%>"> &nbsp; <%=GetDataFromFacebook.getFriendsCount(accessToken)-6%> More >>></a-->
									<a href="<%=viewManageFriendsURL%>">Total&nbsp; <%=GetDataFromFacebook.getFriendsCount(accessToken)%> Friends</a>
								</td>
							</tr>
						<%}%>
						</table>
					<%}%>
				</td>
			</tr>

			<tr>
				<td>
					<font size="5">&nbsp;&nbsp;<%=personalDetails.getName()%></font>
					<a href="<%=personalDetails.getLink()%>" target="_blank">
							<img align="right" src="<%=request.getContextPath()%>/html/img/facebook.png" alt="Go to FB">
					</a>
				</td>
			</tr>

			<tr>
				<td valign="top">
				<div style="margin:5px">
					<table border="0" width="100%">
						<%if((personalDetails.getWork()!= null) || 
							(personalDetails.getEducation()!= null)) {%>
						<tr bgcolor="#006B9D">
							<td colspan="2">
								<font color="white" size="3"><B>Work and Education</B></font>
							</td>
						</tr>
						
						<%if(personalDetails.getWork()!= null) {
							request.setAttribute("label", "work");
							request.setAttribute("showCategory", "true");%>
							<%@ include file="grid.jsp" %>
						<%}%>
						
						<%if(personalDetails.getEducation()!= null) {%>
						<tr>
							<td>
								<b>Education</b>
							</td>

							<td>
								<table border="0" cellpadding="3" width="100%">
									<tr>
										<% Contents education[] = personalDetails.getEducation();
											int length=0;
											if(education.length <= 2)
												length = education.length;
											else
												length = 2;

											for( int i=0; i<length; i++){ %>
										<td valign="top" width="50%" style="padding-top:5px; padding-bottom:2px;">
											<img src="<%=GeneralFacebookUtil.getImageURL(education[i].getId())%>" align="left" />
											&nbsp;<%=StringUtil.shorten(education[i].getName(),15)%>
										</td>
											<%}%>
									</tr>
								</table>
							</td>
						</tr>
						<%}%>
					<%}%>

					<%if((personalDetails.getReligion()!= "") || 
						(personalDetails.getPoliticalView()!= "") || 
						(personalDetails.getQuote()!= "")) {%>

						<tr bgcolor="#006B9D">
							<td colspan="2">
								<font color="white" size="3"><B>Philosophy</B></font>
							</td>
						</tr>
							
						<%if(personalDetails.getReligion()!= "") {%>
						<tr>
							<td>
								<b>Religion</b>
							</td>
							<td>
								<%=personalDetails.getReligion()%>
							</td>
						</tr>
						<%}%>
						
						<%if(personalDetails.getPoliticalView()!= "") {%>
						<tr>
							<td>
								<b>Political View</b>
							</td>
							<td>
								<%=personalDetails.getPoliticalView()%>
							</td>
						</tr>
						<%}%>
						
						<%if(personalDetails.getQuote()!= "") {%>
						<tr>
							<td>
								<b>Favourite Quote</b>
							</td>
								
							<td>
								<%=StringUtil.shorten(personalDetails.getQuote(),250)%>
							</td>
						</tr>
						<%}%>
					<%}%>

					<%if((personalDetails.getBio()!= "") || 
						(personalDetails.getGender()!= "") || 
						(personalDetails.getRelationshipStatus()!= "")|| 
						(personalDetails.getBirthday()!= "")||
						(personalDetails.getLanguages()!= null)||
						(personalDetails.getLocation()!= null)||
						(personalDetails.getHometown()!= null)) {%>
					<tr bgcolor="#006B9D">
						<td colspan="2">
							<font color="white" size="3"><B>Basic Information</B></font>
						</td>
					</tr>

						<%if(personalDetails.getBio()!= "") {%>
						<tr>
							<td valign="top">
								<b>About</b>
							</td>
							<td>
								<%=personalDetails.getBio()%>
							</td>
						</tr>
						<%}%>

						<%if(personalDetails.getGender()!= "") {%>
						<tr>
							<td>
								<b>Sex</b>
							</td>
							<td>
								<%=personalDetails.getGender()%>
							</td>
						</tr>
						<%}%>
						
						<%if(personalDetails.getRelationshipStatus()!= "") {%>
						<tr>
							<td>
								<b>Relationship Status</b>
							</td>
							<td>
								<%=personalDetails.getRelationshipStatus()%>
							</td>
						</tr>
						<%}%>

						<%if(personalDetails.getBirthday()!= "") {%>
						<tr>
							<td>
								<b>Birthday</b>
							</td>
							<td>
								<%=personalDetails.getBirthday()%>
							</td>
						</tr>
						<%}%>

						<%if(personalDetails.getLanguages()!= null) {%>
						<tr>
							<td>
								<b>Languages</b>
							</td>
							<td>
								<table border="0" cellpadding="3" width="100%">
									<tr>
										<% Contents languages[] = personalDetails.getLanguages();
											for( int i=0; i<2; i++){ %>
										<td valign="top" style="padding-top:5px; padding-bottom:3px;"> 
											<img src="<%=GeneralFacebookUtil.getImageURL(languages[i].getId())%>" align="left">
											&nbsp;<%=languages[i].getName()%>
										</td>
											<%}%>
									</tr>
								</table>
							</td>
						</tr>
						<%}%>

						<%if(personalDetails.getLocation()!= null) {%>
							<tr>
								<td>
									<b>Location</b>
								</td>
								<td valign="middle" style="padding-top:5px; padding-bottom:3px;">
									<% Contents location = personalDetails.getLocation();%>
									 <img src="<%=GeneralFacebookUtil.getImageURL(location.getId())%>" align="left">
									&nbsp;<%=location.getName()%>
								</td>
							</tr>
						<%}%>
					
						<%if(personalDetails.getHometown()!= null) {%>
							<tr>
								<td>
									<b>Hometown</b>
								</td>
								<td valign="middle" style="padding-top:5px; padding-bottom:3px;">
									<% Contents hometown = personalDetails.getHometown();%>
									<img src="<%=GeneralFacebookUtil.getImageURL(hometown.getId())%>" align="left">
									&nbsp;<%=hometown.getName()%>
								</td>
							</tr>
						<%}%>
					<%}%>

					<%if((personalDetails.getMusic()!=null) || 
						(personalDetails.getBooks()!=null) || 
						(personalDetails.getMovies()!=null)|| 
						(personalDetails.getTelevision()!=null)) {%>
						<tr bgcolor="#006B9D">
							<td colspan="2">
								<font color="white" size="3"><B>Arts and Entertainment</B></font>
							</td>
						</tr>
						
						<%if(personalDetails.getMusic()!=null) {
							request.setAttribute("label", "music");
							request.setAttribute("showCategory", "false");%>
							<%@ include file="grid.jsp" %>
							<tr>
								<td colspan="2">
									<hr/>
								</td>
							</tr>
						<%}%>

						<%if(personalDetails.getBooks()!=null) {
							request.setAttribute("label", "books");
							request.setAttribute("showCategory", "false");%>
							<%@ include file="grid.jsp" %>
							<tr>
								<td colspan="2">
									<hr/>
								</td>
							</tr>
						<%}%>

						<%if(personalDetails.getMovies()!=null) {
							request.setAttribute("label", "movies");
							request.setAttribute("showCategory", "false");%>
							<%@ include file="grid.jsp" %>
							<tr>
								<td colspan="2">
									<hr/>
								</td>
							</tr>
						<%}%>

						<%if(personalDetails.getTelevision()!=null) {
							request.setAttribute("label", "television");
							request.setAttribute("showCategory", "false");%>
							<%@ include file="grid.jsp" %>
							<tr>
								<td colspan="2">
									<hr/>
								</td>
							</tr>
						<%}%>

					<%}%>
					
					<%if((personalDetails.getActivities()!=null) || 
						(personalDetails.getInterests()!=null)) {%>
						<tr bgcolor="#006B9D">
							<td colspan="2">
								<font color="white" size="3"><B>Activities and Interests</B></font>
							</td>
						</tr>

						<%if(personalDetails.getActivities()!=null) {
							request.setAttribute("label", "activities");
							request.setAttribute("showCategory", "false");%>
							<%@ include file="grid.jsp" %>
							<tr>
								<td colspan="2">
									<hr/>
								</td>
							</tr>
						<%}%>

						<%if(personalDetails.getInterests()!=null) {
							request.setAttribute("label", "interests");
							request.setAttribute("showCategory", "false");%>
							<%@ include file="grid.jsp" %>
							<tr>
								<td colspan="2">
									<hr/>
								</td>
							</tr>
						<%}%>
					<%}%>

				</table>
				</div>
			</td>
		</tr>
	</table>
	<%}%>
</div>