package com.springrest.employee.service;

import com.springrest.employee.dao.IDaoEmployeeDetails;
import com.springrest.employee.entity.EmployeeAttributes;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImplementation implements IEmployeeService  {
	@Autowired	
	private IDaoEmployeeDetails daoEmployeeDetails;
	
	public EmployeeServiceImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<EmployeeAttributes> employeeDetails() {

		return this.daoEmployeeDetails.findAll();
	}

	@Override
	public Optional<EmployeeAttributes> employeeDetailsById(int employee_id) {
		
//		Optional<EmployeeAttributes> result = daoEmployeeDetails.findById(employee_id);
//
//		EmployeeAttributes attributes=null;
//
//		if(result.isPresent()) {
//			attributes=result.get();
//		}
//		else {
//			return null;
//		}
//		return attributes;

		return daoEmployeeDetails.findById(employee_id);
		
	}

	@Override
	public EmployeeAttributes addDetails(EmployeeAttributes employeeAttribute) {

		return daoEmployeeDetails.save(employeeAttribute);
	}

	@Override
	public EmployeeAttributes updateDetails(EmployeeAttributes employeeAttributes) {

		daoEmployeeDetails.save(employeeAttributes);
		return employeeAttributes;
	}

	@Override
	public void deleteDetails(int employee_id) {

		daoEmployeeDetails.deleteById(employee_id);
	}

}
