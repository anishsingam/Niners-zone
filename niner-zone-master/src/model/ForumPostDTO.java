package model;

public class ForumPostDTO {
	
	int board_id, post_id ;
	String owner_type ;
	String owner_userName ;
	String display_type ;
	String title ;
	String content ;
	String postDate;
	
	public ForumPostDTO ()
	{
		
	}
	public ForumPostDTO(int post_id, int board_id, String owner_type,
			String owner_userName, String display_type, String title,
			String content) {
		this.board_id =  board_id;
	    this.post_id =	post_id ;
		this.owner_type = owner_type ;
		this.owner_userName = owner_userName ;
		this.display_type = display_type ;
		this.title = title ;
		this.content = content ;
		
	}
	
	
	public ForumPostDTO(int post_id, int board_id,String owner_type,
			String owner_userName, String display_type, String title,
			String content, String postDate) {
		super();
		this.board_id = board_id;
		this.post_id = post_id;
		this.owner_type = owner_type;
		this.owner_userName = owner_userName;
		this.display_type = display_type;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	@Override
	public String toString() {
		return "ForumPostDTO [board_id=" + board_id + ", post_id=" + post_id
				+ ", owner_type=" + owner_type + ", owner_userName="
				+ owner_userName + ", display_type=" + display_type
				+ ", title=" + title + ", content=" + content + "]";
	}
	

}
