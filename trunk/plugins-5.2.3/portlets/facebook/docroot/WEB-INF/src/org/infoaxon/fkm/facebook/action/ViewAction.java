package org.infoaxon.fkm.facebook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ViewAction extends Action {

	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception
	{
		log.info(">>>>>>>> ViewAction.execute()");
		
		return mapping.findForward("facebook.view");
	}
	private static final Log log = LogFactory.getLog(ViewAction.class);
}