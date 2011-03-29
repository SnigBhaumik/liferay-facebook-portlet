package org.infoaxon.fkm.facebook.pojo;
/**
 * 
 *@author apoorva.prakash
 * 
 */
public class DetailedPost{
	public Feed feedDetails;
	public Comment[] commentDetails;
	public Feed getFeedDetails() {
		return feedDetails;
	}
	public void setFeedDetails(Feed feedDetails) {
		this.feedDetails = feedDetails;
	}
	public Comment[] getCommentDetails() {
		return commentDetails;
	}
	public void setCommentDetails(Comment[] commentDetails) {
		this.commentDetails = commentDetails;
	}
}