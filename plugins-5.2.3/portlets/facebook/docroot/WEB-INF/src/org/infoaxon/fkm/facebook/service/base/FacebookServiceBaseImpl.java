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

package org.infoaxon.fkm.facebook.service.base;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;

import org.infoaxon.fkm.facebook.service.FacebookLocalService;
import org.infoaxon.fkm.facebook.service.FacebookService;
import org.infoaxon.fkm.facebook.service.persistence.FacebookPersistence;

/**
 * <a href="FacebookServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public abstract class FacebookServiceBaseImpl extends PrincipalBean
	implements FacebookService {
	public FacebookLocalService getFacebookLocalService() {
		return facebookLocalService;
	}

	public void setFacebookLocalService(
		FacebookLocalService facebookLocalService) {
		this.facebookLocalService = facebookLocalService;
	}

	public FacebookService getFacebookService() {
		return facebookService;
	}

	public void setFacebookService(FacebookService facebookService) {
		this.facebookService = facebookService;
	}

	public FacebookPersistence getFacebookPersistence() {
		return facebookPersistence;
	}

	public void setFacebookPersistence(FacebookPersistence facebookPersistence) {
		this.facebookPersistence = facebookPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			PortalUtil.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "org.infoaxon.fkm.facebook.service.FacebookLocalService.impl")
	protected FacebookLocalService facebookLocalService;
	@BeanReference(name = "org.infoaxon.fkm.facebook.service.FacebookService.impl")
	protected FacebookService facebookService;
	@BeanReference(name = "org.infoaxon.fkm.facebook.service.persistence.FacebookPersistence.impl")
	protected FacebookPersistence facebookPersistence;
}