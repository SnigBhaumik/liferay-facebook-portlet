/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.infoaxon.fkm.facebook.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="FacebookLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public class FacebookLocalServiceUtil {
	public static org.infoaxon.fkm.facebook.model.Facebook addFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException {
		return getService().addFacebook(facebook);
	}

	public static org.infoaxon.fkm.facebook.model.Facebook createFacebook(
		long userid) {
		return getService().createFacebook(userid);
	}

	public static void deleteFacebook(long userid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteFacebook(userid);
	}

	public static void deleteFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException {
		getService().deleteFacebook(facebook);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static org.infoaxon.fkm.facebook.model.Facebook getFacebook(
		long userid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getFacebook(userid);
	}

	public static java.util.List<org.infoaxon.fkm.facebook.model.Facebook> getFacebooks(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getFacebooks(start, end);
	}

	public static int getFacebooksCount()
		throws com.liferay.portal.SystemException {
		return getService().getFacebooksCount();
	}

	public static org.infoaxon.fkm.facebook.model.Facebook updateFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException {
		return getService().updateFacebook(facebook);
	}

	public static org.infoaxon.fkm.facebook.model.Facebook updateFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateFacebook(facebook, merge);
	}

	public static void storeAccessToken(long userId,
		java.lang.String accessToken) throws java.lang.Exception {
		getService().storeAccessToken(userId, accessToken);
	}

	public static void dropLogin(long userId) throws java.lang.Exception {
		getService().dropLogin(userId);
	}

	public static java.lang.String getAccessToken(long userId)
		throws java.lang.Exception {
		return getService().getAccessToken(userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static FacebookLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					FacebookLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new FacebookLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(FacebookLocalService service) {
		_service = service;
	}

	private static FacebookLocalService _service;
}