package org.infoaxon.fkm.facebook.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.util.ParamUtil;

/**
 * 
 *@author apoorva.prakash
 * 
 */
public class PublishToFacebook {
	private static final Log log = LogFactory.getLog(PublishToFacebook.class);

	static public String updateStatus(String accessToken, String message, String link, String name, String caption, String description) throws Exception{
		String 	publishStatusURL = GeneralFacebookUtil.getPropertyValue("put.update.status.url");
		String	status			 = "false";
		publishStatusURL = publishStatusURL + accessToken;
		
		if (message!=null && message!=""){
			message 			= 	URLEncoder.encode(message, "UTF-8" );
			publishStatusURL 	= 	publishStatusURL + "&message=" + message;
		}
	
		if (link!=null && link!=""){
			link 				= 	URLEncoder.encode(link, "UTF-8" );
			publishStatusURL 	= 	publishStatusURL + "&link=" + link;
		}
		
		if (name!=null && name!=""){
			name 				= 	URLEncoder.encode(name, "UTF-8" );
			publishStatusURL 	= 	publishStatusURL + "&name=" + name;
		}
		
		if (caption!=null && caption!=""){
			caption 			= 	URLEncoder.encode(caption, "UTF-8" );
			publishStatusURL 	= 	publishStatusURL + "&caption=" + caption;
		}
		
		if (description!=null && description!=""){
			description 		= 	URLEncoder.encode(description, "UTF-8" );
			publishStatusURL 	= 	publishStatusURL + "&description=" + description;
		}
		
		URL check = new URL(publishStatusURL);
		log.info(">>>"+check.toString());
		URLConnection checkConn = check.openConnection();
		checkConn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(checkConn.getOutputStream());
		out.close();
	
		BufferedReader temp = new BufferedReader(new InputStreamReader(checkConn.getInputStream()));
		String inputLine = temp.readLine();
		status=inputLine;
		return status;
	}
	
	static public String likeIt(String accessToken, String postId) throws Exception{
		String status="false";
		String likeItURL = GeneralFacebookUtil.getPropertyValue("put.like.post.url");
		likeItURL = likeItURL + postId + "/likes?"+accessToken;
		URL check = new URL(likeItURL);
		URLConnection checkConn = check.openConnection();
		checkConn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(checkConn.getOutputStream());
		out.close();
	
		BufferedReader temp = new BufferedReader(new InputStreamReader(checkConn.getInputStream()));
		String inputLine = temp.readLine();
		status="true";
		return status;
	}
	static public String publishComment (String message, String accessToken, String postId) throws Exception{
		String status="false";
		message = URLEncoder.encode(message, "UTF-8" );
		String publishCommentURL = GeneralFacebookUtil.getPropertyValue("put.comment.url");
		publishCommentURL = publishCommentURL + postId + "/comments?"+accessToken+"&message="+message;
		URL check = new URL(publishCommentURL);
		URLConnection checkConn = check.openConnection();
		checkConn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(checkConn.getOutputStream());
		out.close();
	
		BufferedReader temp = new BufferedReader(new InputStreamReader(checkConn.getInputStream()));
		String inputLine = temp.readLine();
		status="true";
		return status;
	}
}