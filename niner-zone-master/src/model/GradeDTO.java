package model;

public class GradeDTO {
	
	private int studentRollNumber;
	private int assignmentPostId;
	private String assignmentPostTitle;
	private String grade;
	
	
	@Override
	public String toString() {
		return "GradeDTO [studentRollNumber=" + studentRollNumber
				+ ", assignmentPostId=" + assignmentPostId
				+ ", assignmentPostTitle=" + assignmentPostTitle + ", grade="
				+ grade + "]";
	}
	public GradeDTO(int studentRollNumber, int assignmentPostId,
			String assignmentPostTitle, String grade) {
		super();
		this.studentRollNumber = studentRollNumber;
		this.assignmentPostId = assignmentPostId;
		this.assignmentPostTitle = assignmentPostTitle;
		this.grade = grade;
	}
	public int getStudentRollNumber() {
		return studentRollNumber;
	}
	public void setStudentRollNumber(int studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}
	public int getAssignmentPostId() {
		return assignmentPostId;
	}
	public void setAssignmentPostId(int assignmentPostId) {
		this.assignmentPostId = assignmentPostId;
	}
	public String getAssignmentPostTitle() {
		return assignmentPostTitle;
	}
	public void setAssignmentPostTitle(String assignmentPostTitle) {
		this.assignmentPostTitle = assignmentPostTitle;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
	
	
}
