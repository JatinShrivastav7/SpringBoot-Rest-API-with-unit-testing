package com.springrest.employee.service;
import com.springrest.employee.dao.IDaoEmployeeDetails;
import com.springrest.employee.entity.EmployeeAttributes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplementationTest {

    @Mock
    private IDaoEmployeeDetails daoEmployeeDetails;

    @InjectMocks
    private EmployeeServiceImplementation employeeService;


    @Test
    void employeeDetails() {
        List<EmployeeAttributes> employeeList = new ArrayList<>();
        employeeList.add(new EmployeeAttributes(1,"A", "user1@gmail.com", "Java"));
        employeeList.add(new EmployeeAttributes(2,"B", "user2@gmail.com", "Android"));
        employeeList.add(new EmployeeAttributes(3,"C", "user3@gmail.com", "ML"));

        BDDMockito.given(daoEmployeeDetails.findAll()).willReturn(employeeList);
        List<EmployeeAttributes> expected = employeeService.employeeDetails();

        assertEquals(expected,employeeList);
    }

    @Test
    void employeeDetailsById() {
        final int empId=1;
        final EmployeeAttributes employeeAttributes = new EmployeeAttributes(1,"Jatin","jatin@gmail.com","Java");

        BDDMockito.given(daoEmployeeDetails.findById(empId)).willReturn(Optional.of(employeeAttributes));
        final Optional<EmployeeAttributes> expected = employeeService.employeeDetailsById(empId);
        assertThat(expected).isNotNull();
    }

    @Test
    void addDetails() {
        final EmployeeAttributes employeeAttributes = new EmployeeAttributes(1,"Jatin","jatin@gmail.com","Java");
        BDDMockito.given(daoEmployeeDetails.save(employeeAttributes)).willReturn(employeeAttributes);
        assertEquals(employeeAttributes,employeeService.addDetails(employeeAttributes));
        verify(daoEmployeeDetails).save(any(EmployeeAttributes.class));
    }

    @Test
    void updateDetails() {
        final EmployeeAttributes employeeAttributes = new EmployeeAttributes(1,"Jatin","jatin@gmail.com","Java");
        BDDMockito.given(daoEmployeeDetails.save(employeeAttributes)).willReturn(employeeAttributes);
        final EmployeeAttributes expected = employeeService.updateDetails(employeeAttributes);
        assertThat(expected).isNotNull();
        verify(daoEmployeeDetails).save(any(EmployeeAttributes.class));

    }

    @Test
    void deleteDetails() {
        final int empId=1;
        employeeService.deleteDetails(empId);
        verify(daoEmployeeDetails,times(1)).deleteById(empId);

    }
}