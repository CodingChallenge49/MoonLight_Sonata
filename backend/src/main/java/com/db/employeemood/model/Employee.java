package com.db.employeemood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "manager_email")
	private String managerEmail;
	public Employee() {
		
	}
	public Employee(String email, String name, String managerEmail) {
		super();
		this.email = email;
		this.name = name;
		this.managerEmail = managerEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager_email() {
		return managerEmail;
	}
	public void setManager_email(String managerEmail) {
		this.managerEmail = managerEmail;
	}
}
