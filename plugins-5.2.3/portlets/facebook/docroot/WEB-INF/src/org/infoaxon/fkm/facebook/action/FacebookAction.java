package org.infoaxon.fkm.facebook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infoaxon.fkm.facebook.util.PublishToFacebook;
import org.infoaxon.fkm.facebook.util.GeneralFacebookUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.infoaxon.fkm.facebook.service.FacebookLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;

/**
 * 
 *@author apoorva.prakash
 * 
 */
public class FacebookAction extends Action {
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest actionRequest,
			HttpServletResponse actionResponse)
		throws Exception {
		String callbackUrl = ParamUtil.getString(actionRequest, "callback");
		
		String CMD = ParamUtil.getString(actionRequest, "CMD");
		if(CMD.equals("Login")){
			String client_id				=	GeneralFacebookUtil.getPropertyValue("client.id");
			String getProfileAccessCodeURL 	= 	GeneralFacebookUtil.getPropertyValue("get.profile.login.code.url");
			String permissionScope  		= 	GeneralFacebookUtil.getPropertyValue("permission.scope");
			String requestUrl				=	getProfileAccessCodeURL+client_id+"&redirect_uri="+ callbackUrl+ "/oauth_redirect&"+permissionScope;
			actionResponse.sendRedirect(requestUrl);
		}
		else if(CMD.equals("Logout")){
			try{
				long userId = ParamUtil.getLong(actionRequest, "userid");
				FacebookLocalServiceUtil.dropLogin(userId);
				log.info(userId+"'s accessToken dropped");
			}
			catch(Exception e){
				log.info("some exception caught in dropping");
			}
		}
		else if(CMD.equals("updateStatus")){
			try{
				String accessToken 		=	ParamUtil.getString(actionRequest, "accessToken");
				String statusMessage 	=	ParamUtil.getString(actionRequest, "statusMessage");
				String name 			=	ParamUtil.getString(actionRequest, "name");
				String caption 			=	ParamUtil.getString(actionRequest, "caption");
				String link 			=	null;
				String description 		=	ParamUtil.getString(actionRequest, "description");
				String status 			=	PublishToFacebook.updateStatus(accessToken, statusMessage, link, name, caption, description);
			}
			catch(Exception e){
				log.info("some exception in updating status");
			}
		}
		return mapping.findForward("facebook.view");
	}

	public String render(HttpServletRequest req) throws Exception
	{
		return "facebook.view";
	}
	private static final Log log = LogFactory.getLog(FacebookAction.class);
}