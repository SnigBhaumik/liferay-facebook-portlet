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

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="FacebookLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface FacebookLocalService {
	public org.infoaxon.fkm.facebook.model.Facebook addFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook createFacebook(long userid);

	public void deleteFacebook(long userid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.infoaxon.fkm.facebook.model.Facebook getFacebook(long userid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> getFacebooks(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFacebooksCount() throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook updateFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook updateFacebook(
		org.infoaxon.fkm.facebook.model.Facebook facebook, boolean merge)
		throws com.liferay.portal.SystemException;

	public void storeAccessToken(long userId, java.lang.String accessToken)
		throws java.lang.Exception;

	public void dropLogin(long userId) throws java.lang.Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getAccessToken(long userId)
		throws java.lang.Exception;
}