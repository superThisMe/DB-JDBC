package com.example.jdbc;

// EMPLOYEE Table에 데이터를 저장하기 위한 클래스
public class EmployeeVO {

	// EMPLOYEE Table의 Column을 Field로 구성
	private int employeeNo;
	private String firstName;
	private String lastName;
	private String email;

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("[%d][%s][%s][%s]", employeeNo, firstName, lastName, email);
	}

}
