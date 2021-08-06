package com.springrest.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.employee.entity.EmployeeAttributes;

public interface IDaoEmployeeDetails extends JpaRepository<EmployeeAttributes, Integer>{

}
