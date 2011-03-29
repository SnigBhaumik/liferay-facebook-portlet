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

package org.infoaxon.fkm.facebook.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import org.infoaxon.fkm.facebook.model.Facebook;
import org.infoaxon.fkm.facebook.model.FacebookSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="FacebookModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public class FacebookModelImpl extends BaseModelImpl<Facebook> {
	public static final String TABLE_NAME = "fkm_facebook";
	public static final Object[][] TABLE_COLUMNS = {
			{ "userid", new Integer(Types.BIGINT) },
			

			{ "accesstoken", new Integer(Types.VARCHAR) },
			

			{ "code_", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table fkm_facebook (userid LONG not null primary key,accesstoken VARCHAR(200) null,code_ VARCHAR(200) null)";
	public static final String TABLE_SQL_DROP = "drop table fkm_facebook";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.infoaxon.fkm.facebook.model.Facebook"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.infoaxon.fkm.facebook.model.Facebook"),
			true);

	public static Facebook toModel(FacebookSoap soapModel) {
		Facebook model = new FacebookImpl();

		model.setUserid(soapModel.getUserid());
		model.setAccesstoken(soapModel.getAccesstoken());
		model.setCode(soapModel.getCode());

		return model;
	}

	public static List<Facebook> toModels(FacebookSoap[] soapModels) {
		List<Facebook> models = new ArrayList<Facebook>(soapModels.length);

		for (FacebookSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.infoaxon.fkm.facebook.model.Facebook"));

	public FacebookModelImpl() {
	}

	public long getPrimaryKey() {
		return _userid;
	}

	public void setPrimaryKey(long pk) {
		setUserid(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userid);
	}

	public long getUserid() {
		return _userid;
	}

	public void setUserid(long userid) {
		_userid = userid;
	}

	public String getAccesstoken() {
		return GetterUtil.getString(_accesstoken);
	}

	public void setAccesstoken(String accesstoken) {
		_accesstoken = accesstoken;
	}

	public String getCode() {
		return GetterUtil.getString(_code);
	}

	public void setCode(String code) {
		_code = code;
	}

	public Facebook toEscapedModel() {
		if (isEscapedModel()) {
			return (Facebook)this;
		}
		else {
			Facebook model = new FacebookImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setUserid(getUserid());
			model.setAccesstoken(HtmlUtil.escape(getAccesstoken()));
			model.setCode(HtmlUtil.escape(getCode()));

			model = (Facebook)Proxy.newProxyInstance(Facebook.class.getClassLoader(),
					new Class[] { Facebook.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(Facebook.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		FacebookImpl clone = new FacebookImpl();

		clone.setUserid(getUserid());
		clone.setAccesstoken(getAccesstoken());
		clone.setCode(getCode());

		return clone;
	}

	public int compareTo(Facebook facebook) {
		long pk = facebook.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Facebook facebook = null;

		try {
			facebook = (Facebook)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = facebook.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{userid=");
		sb.append(getUserid());
		sb.append(", accesstoken=");
		sb.append(getAccesstoken());
		sb.append(", code=");
		sb.append(getCode());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("org.infoaxon.fkm.facebook.model.Facebook");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userid</column-name><column-value><![CDATA[");
		sb.append(getUserid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accesstoken</column-name><column-value><![CDATA[");
		sb.append(getAccesstoken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>code</column-name><column-value><![CDATA[");
		sb.append(getCode());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userid;
	private String _accesstoken;
	private String _code;
	private transient ExpandoBridge _expandoBridge;
}