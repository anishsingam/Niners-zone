package model;

public class CourseDTO {
	private String courseId,courseName,courseLevel;
	private int departmentId;
	private int facultyId;
	private String facultyUserName;
	
	
	public CourseDTO(String courseName, String courseId) {
		super();
		this.courseName = courseName;
		this.courseId = courseId;
	}
	
	public CourseDTO(String courseId, String courseName, String courseLevel,
			int departmentId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseLevel = courseLevel;
		this.departmentId = departmentId;
	}
	
	public CourseDTO(String courseId, String courseName, String courseLevel,
			int facultyId, String facultyUserName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseLevel = courseLevel;
		this.facultyId = facultyId;
		this.facultyUserName = facultyUserName;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyUserName() {
		return facultyUserName;
	}


	public void setFacultyUserName(String facultyUserName) {
		this.facultyUserName = facultyUserName;
	}

	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	@Override
	public String toString() {
		return "CourseDTO [courseId=" + courseId + ", courseName=" + courseName
				+ ", courseLevel=" + courseLevel + ", departmentId="
				+ departmentId + ", facultyId=" + facultyId
				+ ", facultyUserName=" + facultyUserName + "]";
	}
	
}
