package org.infoaxon.fkm.facebook.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoaxon.fkm.facebook.model.Facebook;
import org.infoaxon.fkm.facebook.service.FacebookLocalServiceUtil;
import org.infoaxon.fkm.facebook.service.persistence.FacebookUtil;
import org.infoaxon.fkm.facebook.util.GeneralFacebookUtil;


public class GeneralFacebookUtil{
	public static String getPropertyValue(String propKey) throws Exception{
		String propValue	=	null;
		try{
			ResourceBundle urlBundle= ResourceBundle.getBundle("portal-fusionkm-config");
			propValue = urlBundle.getString(propKey);
			return propValue;
		}
		catch(Exception e){
			log.info("Some exception in getting property value");
			return null;
		}
	}
	
	public static boolean validateAccessToken(String accessToken) throws Exception{
		boolean status	=	false;
		String likeURL=GeneralFacebookUtil.getPropertyValue("get.all.likes.url");
		try{
			URL check = new URL(likeURL+accessToken);
			URLConnection checkConn = check.openConnection();
			BufferedReader temp = new BufferedReader(new InputStreamReader(checkConn.getInputStream()));
		    String data = temp.readLine();
			status  = true;
		}
		catch(Exception e){
			status  = false;
		}
		return status;	
	}
	
	public static String computeRelativeTime(String strDate){
		// input 2011-01-18T06:03:40+0000
		long 				diff			=	0;
		long 				diffSeconds		=	0; 
		long 				diffMinutes		=	0; 
		long 				diffHours		=	0; 
		long 				diffDays		=	0; 
		SimpleDateFormat 	formatter1		= 	new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		SimpleDateFormat 	df 				= 	new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); 
		Date 				now; 
		String 				s;
		Date 				cdate; 
		
		try{
			now = new Date(); 
			s = df.format(now);
			String oldDate = strDate;
			Date ddate = formatter1.parse(oldDate);
			
			cdate = formatter1.parse(s);
			diff = cdate.getTime() - ddate.getTime();
			
			// Calculate difference in seconds
			diffSeconds = diff / 1000;
			// Calculate difference in minutes
			diffMinutes = diff / (60 * 1000);
			// Calculate difference in hours
			diffHours = diff / (60 * 60 * 1000);
			// Calculate difference in days
			diffDays = diff / (24 * 60 * 60 * 1000);

			if (diffDays > 0 )
				strDate = String.valueOf(diffDays)+" Days ago";
			else if (diffHours > 0 )
				strDate = String.valueOf(diffHours)+" Hours ago";
			else if (diffMinutes > 0 )
				strDate = String.valueOf(diffMinutes)+" Minutes ago";
			else if (diffSeconds > 0 )
				strDate = String.valueOf(diffSeconds)+" Seconds ago";	
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return strDate;
	}
	
	public static String getImageURL(String id) throws Exception{
		String 	imageURL	=	GeneralFacebookUtil.getPropertyValue("get.picture.url");
		imageURL = imageURL + id + "/picture";
		return imageURL;
	}

	public static String getLikeUrl(String id, String accessToken) throws Exception{
		String 	likeURL	=	GeneralFacebookUtil.getPropertyValue("get.like.url");
		likeURL = likeURL + id + "/likes?" + accessToken;
		return likeURL;
	}
	private static final Log log = LogFactory.getLog(GeneralFacebookUtil.class);
}