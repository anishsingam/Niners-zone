package model;

public class UserApprovalDTO {
	private String userName;
	private String userType;
	private String approvalFlag;
	
	public UserApprovalDTO(String userName, String userType, String approvalFlag) {
		super();
		this.userName = userName;
		this.userType = userType;
		this.approvalFlag = approvalFlag;
	}
	public UserApprovalDTO() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "UserApprovalDTO [userName=" + userName + ", userType="
				+ userType + ", approvalFlag=" + approvalFlag + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getApprovalFlag() {
		return approvalFlag;
	}
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	
	
}
