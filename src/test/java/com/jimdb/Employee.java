package com.jimdb;

public class Employee {
	
	private String id;
	
	private String name;
	
	private String gender;
	
	private float salary;
	
	private String department;
	
	private String address;
	
	private String mailId;
	
	public Employee(float salary, String address, String mailId) {
		this.salary = salary;
		this.address = address;
		this.mailId = mailId;
	}
	
	public Employee(String id, String name, String gender, float salary, String department, String address, String mailId) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.department = department;
		this.address = address;
		this.mailId = mailId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	
	@Override
	public String toString() {
		return "{ id: " + id + ", name: " + name + ", gender: " + gender + ", salary: " + salary + ", department: " + department + ", address: " + address + ", mailId: " + mailId + " }";
	}
}
