package model;

public class AssignmentPostDTO {
	private int postId;
	private int academicBoardId;
	private int assignmentMaterialId;
	private String title;
	private String description;
	private String creationDate;
	private String dueDate;
	private String materialLink;
	public AssignmentPostDTO(int postId, int academicBoardId,
			int assignmentMaterialId, String title, String description,
			String creationDate, String dueDate, String materialLink) {
		super();
		this.postId = postId;
		this.academicBoardId = academicBoardId;
		this.assignmentMaterialId = assignmentMaterialId;
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.materialLink = materialLink;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getAcademicBoardId() {
		return academicBoardId;
	}
	public void setAcademicBoardId(int academicBoardId) {
		this.academicBoardId = academicBoardId;
	}
	public int getAssignmentMaterialId() {
		return assignmentMaterialId;
	}
	public void setAssignmentMaterialId(int assignmentMaterialId) {
		this.assignmentMaterialId = assignmentMaterialId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getMaterialLink() {
		return materialLink;
	}
	public void setMaterialLink(String materialLink) {
		this.materialLink = materialLink;
	}
	@Override
	public String toString() {
		return "AssignementPostDTO [postId=" + postId + ", academicBoardId="
				+ academicBoardId + ", assignmentMaterialId="
				+ assignmentMaterialId + ", title=" + title + ", description="
				+ description + ", creationDate=" + creationDate + ", dueDate="
				+ dueDate + ", materialLink=" + materialLink + "]";
	}
	
 
}
