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
 * <a href="FacebookServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public class FacebookServiceUtil {
	public static java.lang.String updateStatus(java.lang.String accessToken,
		java.lang.String message, java.lang.String link, java.lang.String name,
		java.lang.String caption, java.lang.String description)
		throws java.lang.Exception {
		return getService()
				   .updateStatus(accessToken, message, link, name, caption,
			description);
	}

	public static java.lang.String likeIt(java.lang.String accessToken,
		java.lang.String postId) throws java.lang.Exception {
		return getService().likeIt(accessToken, postId);
	}

	public static java.lang.String publishComment(java.lang.String message,
		java.lang.String accessToken, java.lang.String postId)
		throws java.lang.Exception {
		return getService().publishComment(message, accessToken, postId);
	}

	public static void clearService() {
		_service = null;
	}

	public static FacebookService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					FacebookServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new FacebookServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(FacebookService service) {
		_service = service;
	}

	private static FacebookService _service;
}