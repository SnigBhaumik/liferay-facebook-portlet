package org.infoaxon.fkm.facebook.pojo;
/**
 * 
 *@author apoorva.prakash
 * 
 */
public class Comment{
	String commentId;
	IdName from;
	String message;
	String time;
	String likeCount;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public IdName getFrom() {
		return from;
	}
	public void setFrom(IdName from) {
		this.from = from;
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
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
}