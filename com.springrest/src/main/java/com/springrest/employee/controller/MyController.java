package com.springrest.employee.controller;

import com.springrest.employee.entity.EmployeeAttributes;
import com.springrest.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {
	@Autowired
	private IEmployeeService employeeService;
	
	//Get method to get all employee details
	
	@GetMapping("/employee_details")
	public ResponseEntity<List<EmployeeAttributes>> getEmployeeDetails()
	{
		
		List<EmployeeAttributes> employeesList=this.employeeService.employeeDetails();
		if(employeesList.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(employeesList));
		
	}
	
	//Get method to get Employee details by id
	
	@GetMapping("/employee_details/{employee_id}")
    public ResponseEntity<EmployeeAttributes> getEmployeeDetailsById(@PathVariable String employee_id)
    {
//    	EmployeeAttributes empAttributes=this.employeeService.employeeDetailsById(Integer.parseInt(employee_id));
//    	if(empAttributes==null)
//    	{
//    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    	}
//    	return ResponseEntity.of(Optional.of(empAttributes));

		return employeeService.employeeDetailsById(Integer.parseInt(employee_id)).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

	//Post method to add new employee details
	
	@PostMapping("/add_details")
	public ResponseEntity<EmployeeAttributes> addEmployeeDetails(@RequestBody EmployeeAttributes employeeAttributeBody)
	{
	  EmployeeAttributes empAttributes=null;
		
		try {
			empAttributes = this.employeeService.addDetails(employeeAttributeBody);
			System.out.println(empAttributes);
			return ResponseEntity.of(Optional.of(empAttributes));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		
	}
	
	//Put method to update existing employee details
	
	@PutMapping("/update_details")
	public ResponseEntity<EmployeeAttributes> updateEmployeeDetails(@RequestBody EmployeeAttributes employeeAttributeBody)
	{
		EmployeeAttributes empAttributes=null;
		try{
			empAttributes=employeeService.updateDetails(employeeAttributeBody);
			return ResponseEntity.ok().body(empAttributes);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//Delete method to delete employee details
	
	@DeleteMapping("/delete_details/{employee_id}")

	public ResponseEntity<HttpStatus> deleteEmployeeDetails(@PathVariable String employee_id)
	{
		try {
			this.employeeService.deleteDetails(Integer.parseInt(employee_id));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
