
<%
	actionURL = renderResponse.createRenderURL();
	actionURL.setParameter("struts_action", "/infoaxon/fkm/facebook/view"); 	
%>

<% String pictureUrl="https://graph.facebook.com/me/picture?"+accessToken;%>

<table  border="0" width="100%">
	<tr>
		<td valign="top" rowspan="3" style="padding-right:15px;padding-top:5px;">
			<img src="<%=pictureUrl%>"/>
		</td>
		<td rowspan=2 style="padding-right:15px;padding-top:5px;">
			<form name="<portlet:namespace/>updateStatus" action="<%=actionURL%>" method="post">
				<textarea name="<portlet:namespace/>statusMessage" rows="1" cols="90"></textarea>
				<input type="hidden" name="<portlet:namespace />CMD"  id="<portlet:namespace />CMD" value="updateStatus"/>
				<input type="hidden" name="<portlet:namespace/>accessToken"  id="<portlet:namespace />accessToken" value="<%=accessToken%>"/>
		</td>
				
		<td>    <a href="javascript:void();" onClick="advanced()">Advanced</a>
		</td>
	</tr>
	<tr>
		<td style="padding-right:15px;padding-top:5px;">
			<!--input type="button" name="<portlet:namespace />updateStatus" value="Update Status" onClick="javascript:callupdateStatus()"/-->
			<input type="submit" name="<portlet:namespace />updateStatus" value="Update Status"/>
		</td>
	</tr>
	<tr>
		<td>
			<div id="adv" style="display:none">
			<table border="0">
				<tr>
					<td>Name</td>
					<td> <input type="text" name="<portlet:namespace/>name" size="81"></td>
				</tr>
				<tr>
					<td>Caption</td>
					<td><input type="text" name="<portlet:namespace/>caption" size="81"></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea name="<portlet:namespace/>description" rows="3" cols="78"></textarea></td>
				</tr>
			</table>
			</div>
		</td>
	</tr>

</table>
</form>

<script language="javascript">
	function advanced(){
		var state = document.getElementById("adv").style.display;
		var abc = document.getElementById("adv");
		if (state == 'block') {
			state = 'none';
		}
		else {
			state = 'block';
		} 
		abc.style.display = state;
	}
</script>