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

package org.infoaxon.fkm.facebook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="FacebookSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public class FacebookSoap implements Serializable {
	public static FacebookSoap toSoapModel(Facebook model) {
		FacebookSoap soapModel = new FacebookSoap();

		soapModel.setUserid(model.getUserid());
		soapModel.setAccesstoken(model.getAccesstoken());
		soapModel.setCode(model.getCode());

		return soapModel;
	}

	public static FacebookSoap[] toSoapModels(Facebook[] models) {
		FacebookSoap[] soapModels = new FacebookSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FacebookSoap[][] toSoapModels(Facebook[][] models) {
		FacebookSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FacebookSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FacebookSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FacebookSoap[] toSoapModels(List<Facebook> models) {
		List<FacebookSoap> soapModels = new ArrayList<FacebookSoap>(models.size());

		for (Facebook model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FacebookSoap[soapModels.size()]);
	}

	public FacebookSoap() {
	}

	public long getPrimaryKey() {
		return _userid;
	}

	public void setPrimaryKey(long pk) {
		setUserid(pk);
	}

	public long getUserid() {
		return _userid;
	}

	public void setUserid(long userid) {
		_userid = userid;
	}

	public String getAccesstoken() {
		return _accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		_accesstoken = accesstoken;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	private long _userid;
	private String _accesstoken;
	private String _code;
}