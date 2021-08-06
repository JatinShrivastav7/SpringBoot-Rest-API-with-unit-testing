package com.springrest.employee.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeAttributes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int employee_id;
	private String employee_name;
	private String employee_email;
	private String employee_department;
	public EmployeeAttributes(int employee_id, String employee_name, String employee_email,
			String employee_department) {

		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_email = employee_email;
		this.employee_department = employee_department;
	}

	public EmployeeAttributes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_email() {
		return employee_email;
	}

	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}

	public String getEmployee_department() {
		return employee_department;
	}

	public void setEmployee_department(String employee_department) {
		this.employee_department = employee_department;
	}

	@Override
	public String toString() {
		return "Employee_Attributes [employee_id=" + employee_id + ", employee_name=" + employee_name
				+ ", employee_email=" + employee_email + ", employee_department=" + employee_department + "]";
	}
	
	
	
	
	

}
