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

package org.infoaxon.fkm.facebook.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="FacebookPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public interface FacebookPersistence extends BasePersistence {
	public void cacheResult(org.infoaxon.fkm.facebook.model.Facebook facebook);

	public void cacheResult(
		java.util.List<org.infoaxon.fkm.facebook.model.Facebook> facebooks);

	public void clearCache();

	public org.infoaxon.fkm.facebook.model.Facebook create(long userid);

	public org.infoaxon.fkm.facebook.model.Facebook remove(long userid)
		throws com.liferay.portal.SystemException,
			org.infoaxon.fkm.facebook.NoSuchFacebookException;

	public org.infoaxon.fkm.facebook.model.Facebook remove(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook update(
		org.infoaxon.fkm.facebook.model.Facebook facebook)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook update(
		org.infoaxon.fkm.facebook.model.Facebook facebook, boolean merge)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook updateImpl(
		org.infoaxon.fkm.facebook.model.Facebook facebook, boolean merge)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook findByPrimaryKey(
		long userid)
		throws com.liferay.portal.SystemException,
			org.infoaxon.fkm.facebook.NoSuchFacebookException;

	public org.infoaxon.fkm.facebook.model.Facebook fetchByPrimaryKey(
		long userid) throws com.liferay.portal.SystemException;

	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> findByaccesstoken(
		java.lang.String accesstoken) throws com.liferay.portal.SystemException;

	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> findByaccesstoken(
		java.lang.String accesstoken, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> findByaccesstoken(
		java.lang.String accesstoken, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public org.infoaxon.fkm.facebook.model.Facebook findByaccesstoken_First(
		java.lang.String accesstoken,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			org.infoaxon.fkm.facebook.NoSuchFacebookException;

	public org.infoaxon.fkm.facebook.model.Facebook findByaccesstoken_Last(
		java.lang.String accesstoken,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			org.infoaxon.fkm.facebook.NoSuchFacebookException;

	public org.infoaxon.fkm.facebook.model.Facebook[] findByaccesstoken_PrevAndNext(
		long userid, java.lang.String accesstoken,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			org.infoaxon.fkm.facebook.NoSuchFacebookException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<org.infoaxon.fkm.facebook.model.Facebook> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByaccesstoken(java.lang.String accesstoken)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByaccesstoken(java.lang.String accesstoken)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}