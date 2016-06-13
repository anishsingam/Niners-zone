package model;

public class DepartmentDTO {
	int collegeID,departmentId;
	String departmentName;
	public DepartmentDTO(int collegeID, int departmentId, String departmentName) {
		super();
		this.collegeID = collegeID;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public int getCollegeID() {
		return collegeID;
	}
	public void setCollegeID(int collegeID) {
		this.collegeID = collegeID;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "DepartmentDTO [departmentId="
				+ departmentId + ", departmentName=" + departmentName + "]";
	}
	
	
	
}
