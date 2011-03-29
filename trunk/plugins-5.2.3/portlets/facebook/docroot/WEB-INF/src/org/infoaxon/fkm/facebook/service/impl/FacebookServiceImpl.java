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

package org.infoaxon.fkm.facebook.service.impl;


import org.infoaxon.fkm.facebook.service.base.FacebookServiceBaseImpl;
import org.infoaxon.fkm.facebook.util.PublishToFacebook;

/**
 * <a href="FacebookServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		Apoorva Prakash
	
 *
 */
public class FacebookServiceImpl extends FacebookServiceBaseImpl {
	public String updateStatus (String accessToken, String message, String link, String name, String caption, String description)  throws Exception{
		String status = "false";
		status = PublishToFacebook.updateStatus(accessToken, message, link, name, caption, description);
		return status;
	}
	public String likeIt(String accessToken, String postId) throws Exception{
		String status = "false";
		status = PublishToFacebook.likeIt(accessToken, postId);
		return status;
	}
	public String publishComment (String message, String accessToken, String postId)  throws Exception{
		String status = "false";
		status = PublishToFacebook.publishComment(message,accessToken, postId);
		return status;
	}
}