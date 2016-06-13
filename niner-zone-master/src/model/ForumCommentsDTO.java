package model;

public class ForumCommentsDTO {
	
	int comment_id, post_id ;
	String owner_type ;
	String owner_userName ;
	String display_type ;
	String commentMade ;
	String dateOfComment;
	
	public ForumCommentsDTO(int comment_id, int post_id, String owner_type,
			String owner_userName, String display_type, String commentMade,
			String dateOfComment) {
		super();
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.owner_type = owner_type;
		this.owner_userName = owner_userName;
		this.display_type = display_type;
		this.commentMade = commentMade;
		this.dateOfComment = dateOfComment;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getOwner_type() {
		return owner_type;
	}
	public void setOwner_type(String owner_type) {
		this.owner_type = owner_type;
	}
	public String getOwner_userName() {
		return owner_userName;
	}
	public void setOwner_userName(String owner_userName) {
		this.owner_userName = owner_userName;
	}
	public String getDisplay_type() {
		return display_type;
	}
	public void setDisplay_type(String display_type) {
		this.display_type = display_type;
	}
	public String getCommentMade() {
		return commentMade;
	}
	public void setCommentMade(String commentMade) {
		this.commentMade = commentMade;
	}
	public String getDateOfComment() {
		return dateOfComment;
	}
	public void setDateOfComment(String dateOfComment) {
		this.dateOfComment = dateOfComment;
	}
	@Override
	public String toString() {
		return "ForumCommentsDTO [comment_id=" + comment_id + ", post_id="
				+ post_id + ", owner_type=" + owner_type + ", owner_userName="
				+ owner_userName + ", display_type=" + display_type
				+ ", commentMade=" + commentMade + ", dateOfComment="
				+ dateOfComment + "]";
	}

}
