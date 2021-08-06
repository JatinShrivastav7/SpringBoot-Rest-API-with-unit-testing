package com.springrest.employee;

import com.springrest.employee.dao.IDaoEmployeeDetails;
import com.springrest.employee.entity.EmployeeAttributes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private IDaoEmployeeDetails daoEmployeeDetails;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("To check whether the data is being added to the database")
	public void testCreate()
	{
		EmployeeAttributes attributes = new EmployeeAttributes();
		attributes.setEmployee_id(1000);
		attributes.setEmployee_name("Alex");
		attributes.setEmployee_email("alex@gmail.com");
		attributes.setEmployee_department("Java");
		daoEmployeeDetails.save(attributes);
		Assertions.assertNotNull(daoEmployeeDetails.findById(1000).get());
	}

	@Test
	@DisplayName("To check getAll employee method")
	public void testReadAll() {
		List<EmployeeAttributes> list=daoEmployeeDetails.findAll();
		assertThat(list).size().isGreaterThan(0);

	}
	

}
