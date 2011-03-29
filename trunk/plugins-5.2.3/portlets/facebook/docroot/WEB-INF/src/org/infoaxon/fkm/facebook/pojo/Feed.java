package org.infoaxon.fkm.facebook.pojo;
/**
 * 
 *@author apoorva.prakash
 * 
 */
public class Feed{
	String id = "";
	String icon = "";
	String link;
	String name;
	String caption;
	String picture;
	String message;
	String time;
	String type;
	IdName likes[];
	String likesCount;
	String commentsCount;
	IdName from;
	IdName application;
	IdName to;
	String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public IdName[] getLikes() {
		return likes;
	}
	public void setLikes(IdName[] likes) {
		this.likes = likes;
	}
	public String getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(String likesCount) {
		this.likesCount = likesCount;
	}
	public String getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(String commentsCount) {
		this.commentsCount = commentsCount;
	}
	public IdName getFrom() {
		return from;
	}
	public void setFrom(IdName from) {
		this.from = from;
	}
	public IdName getApplication() {
		return application;
	}
	public void setApplication(IdName application) {
		this.application = application;
	}
	public IdName getTo() {
		return to;
	}
	public void setTo(IdName to) {
		this.to = to;
	}
}