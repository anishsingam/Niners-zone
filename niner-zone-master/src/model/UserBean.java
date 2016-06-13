package model;

import java.util.Date;

public class UserBean {
	String userName,userType, firstName,lastName,emailId,Gender;
	Date dateOfBirth;
	int userRollNumber;
	
	public UserBean(){
		
	}
	
	public UserBean(String userName, String userType, String firstName,
			String lastName, String emailId, String gender, Date dateOfBirth) {
		super();
		this.userName = userName;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		Gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	

	public int getUserRollNumber() {
		return userRollNumber;
	}

	public void setUserRollNumber(int userRollNumber) {
		this.userRollNumber = userRollNumber;
	}

	public UserBean(String userName, int userRollNumber) {
		super();
		this.userName = userName;
		this.userRollNumber = userRollNumber;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "UserBean [userName=" + userName + ", userRollNumber="
				+ userRollNumber + "]";
	}

	
	
	
}
