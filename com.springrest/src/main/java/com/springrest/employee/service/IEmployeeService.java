package com.springrest.employee.service;

import com.springrest.employee.entity.EmployeeAttributes;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

	public List<EmployeeAttributes> employeeDetails();
	public Optional<EmployeeAttributes> employeeDetailsById(int employee_id);
	public EmployeeAttributes addDetails(EmployeeAttributes employeeAttribute);
	public EmployeeAttributes updateDetails(EmployeeAttributes employeeAttributes);
	public void deleteDetails(int employee_id);
}

