package org.infoaxon.fkm.facebook.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import org.infoaxon.fkm.facebook.pojo.Comment;
import org.infoaxon.fkm.facebook.pojo.Contents;
import org.infoaxon.fkm.facebook.pojo.DetailedPost;
import org.infoaxon.fkm.facebook.pojo.Feed;
import org.infoaxon.fkm.facebook.pojo.IdName;
import org.infoaxon.fkm.facebook.pojo.PersonalDetails;

import org.infoaxon.fkm.facebook.util.GeneralFacebookUtil;

/**
 * 
 *@author apoorva.prakash
 * 
 */

public class GetDataFromFacebook {
	
	private static final Log log = LogFactory.getLog(GetDataFromFacebook.class);
	
	static public PersonalDetails getPersonalDetails(String accessToken, String token, String id)throws Exception{
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		PersonalDetails personalDetails	=	new PersonalDetails();
		Contents		languages[]		=	null;
		Contents		work[]			=	null;
		Contents		education[]		=	null;
		Contents		hometown		=	new Contents();
		Contents		location		=	new Contents();
		String 			profileURL		=	GeneralFacebookUtil.getPropertyValue("get.self.profile.url");
		String 			friendProfileURL=	GeneralFacebookUtil.getPropertyValue("get.friend.profile.url");
		String 			pictureURL		=	GeneralFacebookUtil.getPropertyValue("get.picture.url");
		
		// temp declerations
		JSONArray tempArr = null;
		JSONObject tempObj = null;
		
		log.info("token: "+  token);
		if(token.equals("self"))
			url = new URL(profileURL+"/?"+accessToken);
		else if(token.equals("friend"))
			url = new URL(friendProfileURL+"/"+id+"?"+accessToken);
		
		urlConn = url.openConnection();
        
        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        data = temp.readLine();
        temp.close();
        
        JSONObject profile = new JSONObject(data);
		
		if (!profile.isNull("id"))
			personalDetails.setId(profile.optString("id",""));
		
		if (!profile.isNull("name"))
			personalDetails.setName(profile.optString("name",""));
		
		if (!profile.isNull("first_name"))
			personalDetails.setFirst_name(profile.optString("first_name",""));
		
		if (!profile.isNull("last_name"))
			personalDetails.setLast_name(profile.optString("last_name",""));
		
		if (!profile.isNull("birthday"))
			personalDetails.setBirthday(profile.optString("birthday",""));
		
		if (!profile.isNull("gender"))
			personalDetails.setGender(profile.optString("gender",""));
		
		if (!profile.isNull("link"))
			personalDetails.setLink(profile.optString("link",""));
		
		if (!profile.isNull("bio"))
			personalDetails.setBio(profile.optString("bio",""));
		
		if (!profile.isNull("quotes"))
			personalDetails.setQuote(profile.optString("quotes",""));
		
		if (!profile.isNull("relationship_status"))
			personalDetails.setRelationshipStatus(profile.optString("relationship_status",""));
		
		if (!profile.isNull("religion"))
			personalDetails.setReligion(profile.optString("religion",""));
		
		if (!profile.isNull("political"))
			personalDetails.setPoliticalView(profile.optString("political",""));
		
		
		if (!profile.isNull("hometown")){
			tempObj = profile.getJSONObject("hometown");
			if (tempObj.optString("id","")!=null)
				hometown.setId(tempObj.optString("id"));
			if (tempObj.optString("name","")!=null)
				hometown.setName(tempObj.optString("name"));
			hometown.setCategory(null);
			hometown.setDate(null);
			personalDetails.setHometown(hometown);
			tempObj = null;
		}
		else
			personalDetails.setHometown(null);

		if(!profile.isNull("location")){
			tempObj = profile.getJSONObject("location");
			if (tempObj.optString("id","")!=null)
				location.setId(tempObj.optString("id"));
			if (tempObj.optString("name","")!=null)
				location.setName(tempObj.optString("name"));
			location.setCategory(null);
			location.setDate(null);
			personalDetails.setLocation(location);
			tempObj = null;
		}
		else
			personalDetails.setLocation(null);

		if(!profile.isNull("languages"))
		{
			languages = new Contents[profile.getJSONArray("languages").length()];
			tempArr = profile.getJSONArray("languages");
			for(int i=0; i< tempArr.length(); i++){
				languages[i] = new Contents();
				tempObj = tempArr.getJSONObject(i);
				if (tempObj.optString("id","")!=null)
					languages[i].setId(tempObj.optString("id"));
				if (tempObj.optString("name","")!=null)
					languages[i].setName(tempObj.optString("name"));	
				languages[i].setCategory("");
				languages[i].setDate("");
			}
			personalDetails.setLanguages(languages);
			tempObj = null;
			tempArr = null;
		}
		else
			personalDetails.setLanguages(null);

		if(!profile.isNull("work")){
			work = new Contents[profile.getJSONArray("work").length()];
			tempArr = profile.getJSONArray("work");
			for(int i=0; i < tempArr.length(); i++){
				work[i] = new Contents();
				tempObj = tempArr.getJSONObject(i).getJSONObject("employer");
				if (tempObj.optString("id")!=null)
					work[i].setId(tempObj.optString("id",""));
				if (tempObj.optString("name")!=null)
					work[i].setName(tempObj.optString("name"));
				if (!tempArr.getJSONObject(i).optBoolean("position")){
					tempObj = tempArr.getJSONObject(i).optJSONObject("position");
					if (tempObj!=null && tempObj.optString("name")!=null)
						work[i].setCategory(tempObj.optString("name",""));
					work[i].setDate("");
				}
			}
			personalDetails.setWork(work);
			tempObj = null;
			tempArr = null;
		}
		else
			personalDetails.setLanguages(null);

		if(!profile.isNull("education")){
			education = new Contents[profile.getJSONArray("education").length()];
			tempArr = profile.getJSONArray("education");
			for(int i=0; i< tempArr.length(); i++){
				education[i] = new Contents();
				tempObj = tempArr.getJSONObject(i).getJSONObject("school");
				if (tempObj.optString("id")!=null)
					education[i].setId(tempObj.optString("id",""));
				if (tempObj.optString("name")!=null)
					education[i].setName(tempObj.optString("name",""));	
				tempObj = tempArr.getJSONObject(i);
				if (tempObj.optString("type")!=null)
					education[i].setCategory(tempObj.optString("type",""));
				education[i].setDate("");
			}
			personalDetails.setEducation(education);
			tempObj = null;
			tempArr = null;
		}
		else
			personalDetails.setEducation(null);
		
		personalDetails.setPicture(pictureURL+personalDetails.getId()+"/picture?type=large");
		personalDetails.setMusic(getMisc(accessToken,"music",0,4));
		personalDetails.setMovies(getMisc(accessToken,"movies",0,4));
		personalDetails.setBooks(getMisc(accessToken,"books",0,4));
		personalDetails.setTelevision(getMisc(accessToken,"television",0,4));
		personalDetails.setActivities(getMisc(accessToken,"activities",0,4));
		personalDetails.setInterests(getMisc(accessToken,"interests",0,4));
		
		return personalDetails;
	}
	static public int getMiscCount(String accessToken, String token)throws Exception{
		int 	count	=	0;
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		String 			profileURL		=	GeneralFacebookUtil.getPropertyValue("get.self.profile.url");
		
		// temp declerations
		JSONArray tempArr = null;
		
		url = new URL(profileURL+"/"+token+"?"+accessToken);
		urlConn = url.openConnection();
        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        data = temp.readLine();
        temp.close();
        
        JSONObject mov = new JSONObject(data);
        tempArr = mov.getJSONArray("data");
        count = tempArr.length();
        		
		return count;
	}
	static public Contents[] getMisc(String accessToken, String token, int start, int count)throws Exception{
		Contents		result[]		=	null;
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		String 			profileURL		=	GeneralFacebookUtil.getPropertyValue("get.self.profile.url");
		
		// temp declerations
		JSONArray tempArr = null;
		JSONObject tempObj = null;
		
		url = new URL(profileURL+"/"+token+"?"+accessToken);
		urlConn = url.openConnection();
        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        data = temp.readLine();
        temp.close();
        
        JSONObject mov = new JSONObject(data);
        tempArr = mov.getJSONArray("data");
        if(tempArr.length()<count)
        	count = tempArr.length();
        
		if(count>0){
			result = new Contents[count];
			for(int i=start; i< count; i++){
				result[i] = new Contents();
				tempObj = tempArr.getJSONObject(i);
				if (tempObj.optString("id")!=null)
					result[i].setId(tempObj.optString("id",""));
				if (tempObj.optString("name")!=null)
					result[i].setName(tempObj.optString("name",""));
				if (tempObj.optString("category")!=null)
					result[i].setCategory(tempObj.optString("category",""));
				result[i].setDate("");
			}
			return result;
		}
		else
			return result;
	}
	static public IdName[] getFriends(String accessToken, int start, int count)throws Exception{
		IdName			result[]		=	null;
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		String 			profileURL		=	GeneralFacebookUtil.getPropertyValue("get.self.profile.url");
		
		// temp declerations
		JSONArray tempArr = null;
		JSONObject tempObj = null;
		
		url = new URL(profileURL+"/friends?"+accessToken);
		urlConn = url.openConnection();
        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        data = temp.readLine();
        temp.close();
        
		JSONObject frndz = new JSONObject(data);
	    tempArr = frndz.getJSONArray("data");
	    if(tempArr.length()<count)
	       	count = tempArr.length();
	      
	    result = new IdName[count];
			
		if(count>0){
			for(int i=start; i< count; i++){
				result[i] = new IdName();
				tempObj = tempArr.getJSONObject(i);
				if (tempObj.optString("id")!=null)
					result[i].setId(tempObj.optString("id",""));
				if (tempObj.optString("name")!=null)
					result[i].setName(tempObj.optString("name",""));
			}
			return result;
		}
		else
			return result;
	}
	
	static public int getFriendsCount(String accessToken)throws Exception{
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		String 			profileURL		=	GeneralFacebookUtil.getPropertyValue("get.self.profile.url");
		int 			count 			= 	0;
		
		// temp declerations
		JSONArray tempArr = null;
		
		url = new URL(profileURL+"/friends?"+accessToken);
		urlConn = url.openConnection();
        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        data = temp.readLine();
        temp.close();
        
		JSONObject frndz = new JSONObject(data);
	    tempArr = frndz.getJSONArray("data");
	    count = tempArr.length();
	    return count;
	}
	public static String getAccessToken(String code, String redirectURI) throws Exception{

		URL 			getAccessToken 	= 	null;
		URLConnection 	accessTokenCon	=	null;
		BufferedReader 	temp			=	null;
		String 			inputLine		=	null;


		String accessToken=null;
		String clientSecret = "client_secret=b63177581215b965ecc065d2115c61d6";
		String clientId = "client_id=167165753328221";
		String requestUrl = "https://graph.facebook.com/oauth/access_token?"+clientId+"&redirect_uri="+redirectURI+"&"+clientSecret+"&code="+code;
		try{
			getAccessToken = new URL(requestUrl);
			accessTokenCon = getAccessToken.openConnection();
			temp = new BufferedReader(new InputStreamReader(accessTokenCon.getInputStream()));
			inputLine = temp.readLine();
		    accessToken=inputLine;
			//FacebookLocalServiceUtil.storeLogin(themeDisplay.getUserId(), accessToken);
			log.info("access token" + accessToken);
			//renderRequest.setAttribute("accessToken", accessToken);
		}catch(Exception e){
			// ignored
		}	
		return accessToken;
	}
	static public Feed[] getUpdates (String accessToken, String token) throws Exception{
		Feed 			result[]		=	null;
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		String 			updateURL		=	null;
		
		// temp declerations
		JSONArray tempArr = null;
		JSONObject tempObj = null;
		try{
			if (token.equals("home"))
				updateURL =	GeneralFacebookUtil.getPropertyValue("get.updates.home.url");
			else if (token.equals("feed"))
				updateURL =	GeneralFacebookUtil.getPropertyValue("get.updates.wall.url");
			
			url = new URL(updateURL+"?"+accessToken);
			urlConn = url.openConnection();
	        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	        data = temp.readLine();
	        temp.close();
	        
	        JSONObject updates = new JSONObject(data);
	        tempArr = updates.getJSONArray("data");
			result = new Feed[tempArr.length()];
			if(tempArr.length()>0)
			{
				for(int i=0; i< tempArr.length(); i++){
					result[i] = new Feed();
					tempObj = tempArr.getJSONObject(i);
					if (!tempObj.isNull("id"))
						result[i].setId(tempObj.optString("id",""));
					
					if (!tempObj.isNull("icon"))
						result[i].setIcon(tempObj.optString("icon",""));
					
					if (!tempObj.isNull("link"))
						result[i].setLink(tempObj.optString("link", ""));
					
					if (!tempObj.isNull("name"))
						result[i].setName(tempObj.optString("name",""));
					
					if (!tempObj.isNull("caption"))
						result[i].setCaption(tempObj.optString("caption",""));
					
					if (!tempObj.isNull("picture"))
						result[i].setPicture(tempObj.optString("picture",""));
					
					if (!tempObj.isNull("message"))
						result[i].setMessage(tempObj.optString("message",""));
					
					if (!tempObj.isNull("type"))
						result[i].setType(tempObj.optString("type",""));
					
					if (!tempObj.isNull("created_time"))
						result[i].setTime(tempObj.optString("created_time",""));
					
					if (!tempObj.isNull("likes"))
						result[i].setLikesCount(tempObj.getJSONObject("likes").optString("count",""));
					
					if (!tempObj.isNull("comments"))
						result[i].setCommentsCount(tempObj.getJSONObject("comments").optString("count",""));
					
					if (!tempObj.isNull("description"))
						result[i].setDescription(tempObj.optString("description",""));
					
					if (!tempObj.isNull("from")){
						IdName from = new IdName();
						from.setId(tempObj.getJSONObject("from").optString("id",""));
						from.setName(tempObj.getJSONObject("from").optString("name",""));
						result[i].setFrom(from);
					}
					else
						result[i].setFrom(null);
					
					if (!tempObj.isNull("to")){
						IdName to = new IdName();
						JSONArray jary = tempObj.getJSONObject("to").getJSONArray("data");
						
						to.setId(jary.getJSONObject(0).optString("id", ""));
						to.setName(jary.getJSONObject(0).optString("name", ""));
						result[i].setTo(to);
					}
					else
						result[i].setTo(null);
					
					if (!tempObj.isNull("application")){
						IdName app = new IdName();
						app.setId(tempObj.getJSONObject("application").optString("id",""));
						app.setName(tempObj.getJSONObject("application").optString("name",""));
						result[i].setApplication(app);
					}
					else
						result[i].setApplication(null);
				}
				return result;
			}
			else
				return result;
		}
		catch(Exception e){
			return null;
		}
	}
	static public DetailedPost getDetailedPost (String accessToken, String postId) throws Exception{
		
		DetailedPost	detailedPost	=	new DetailedPost();
		URL 			url 			= 	null;
		URLConnection 	urlConn 		= 	null; 	
		BufferedReader 	temp			=	null;
		String 			data			=	null;
		String 			getDetailedPostURL 		=	GeneralFacebookUtil.getPropertyValue("get.detail.feed.url");
		
		detailedPost.feedDetails = new Feed();
		
		url = new URL(getDetailedPostURL+postId+"?"+accessToken);
		urlConn = url.openConnection();
        temp = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        data = temp.readLine();
        temp.close();
        JSONObject dp = new JSONObject(data);
        
        if (!dp.isNull("id"))
			detailedPost.feedDetails.setId(dp.optString("id",""));
        
        if (!dp.isNull("link"))
			detailedPost.feedDetails.setLink(dp.optString("link",""));
        
        if (!dp.isNull("message"))
			detailedPost.feedDetails.setMessage(dp.optString("message",""));
        
        if (!dp.isNull("picture"))
			detailedPost.feedDetails.setPicture(dp.optString("picture",""));
        
        if (!dp.isNull("type"))
			detailedPost.feedDetails.setType(dp.optString("type",""));
        
        if (!dp.isNull("icon"))
			detailedPost.feedDetails.setIcon(dp.optString("icon",""));
        
        if (!dp.isNull("created_time"))
			detailedPost.feedDetails.setTime(dp.optString("created_time",""));
        
        if (!dp.isNull("name"))
			detailedPost.feedDetails.setName(dp.optString("name",""));
        
        if (!dp.isNull("likes"))
        	detailedPost.feedDetails.setLikesCount(dp.getJSONObject("likes").optString("count",""));
        
        if (!dp.isNull("caption"))
			detailedPost.feedDetails.setCaption(dp.optString("caption",""));
        
        if (!dp.isNull("description"))
			detailedPost.feedDetails.setDescription(dp.optString("description",""));
        
        if (!dp.isNull("application")){
			IdName app = new IdName();
			app.setId(dp.getJSONObject("application").optString("id",""));
			app.setName(dp.getJSONObject("application").optString("name",""));
			detailedPost.feedDetails.setApplication(app);
		}
		else
			detailedPost.feedDetails.setApplication(null);
        
        if (!dp.isNull("to")){
			IdName to = new IdName();
			JSONArray jary = dp.getJSONObject("to").getJSONArray("data");
			
			to.setId(jary.getJSONObject(0).optString("id", ""));
			to.setName(jary.getJSONObject(0).optString("name", ""));
			detailedPost.feedDetails.setTo(to);
		}
		else
			detailedPost.feedDetails.setTo(null);
		
		if (!dp.isNull("application")){
			IdName app = new IdName();
			app.setId(dp.getJSONObject("application").optString("id",""));
			app.setName(dp.getJSONObject("application").optString("name",""));
			detailedPost.feedDetails.setApplication(app);
		}
		else
			detailedPost.feedDetails.setApplication(null);
		
		if (!dp.isNull("comments")){
			String commentCount = dp.getJSONObject("comments").optString("count","");
			detailedPost.feedDetails.setCommentsCount(commentCount);
			
			JSONArray c = dp.getJSONObject("comments").getJSONArray("data");
			int l = c.length();
			if (l > 25)
				l=25;
			Comment comments[] = new Comment[l];
			for(int i=0;i<l;i++){
				JSONObject tempObj = c.getJSONObject(i);
				comments[i] = new Comment();
				comments[i].setCommentId(tempObj.optString("id",""));
				if (!tempObj.isNull("likes"))
					comments[i].setLikeCount(tempObj.optString("likes",""));
				comments[i].setTime(tempObj.optString("created_time",""));
				comments[i].setMessage(tempObj.optString("message",""));
				IdName from = new IdName();
				from.setId(tempObj.getJSONObject("from").optString("id",""));
				from.setName(tempObj.getJSONObject("from").optString("name",""));
				comments[i].setFrom(from);	
			}
			detailedPost.setCommentDetails(comments);
		}
		else
			detailedPost.setCommentDetails(null);
        
		return detailedPost;
	}
}