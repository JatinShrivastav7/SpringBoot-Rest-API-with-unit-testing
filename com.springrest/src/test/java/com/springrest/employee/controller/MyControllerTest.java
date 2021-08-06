package com.springrest.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.employee.entity.EmployeeAttributes;
import com.springrest.employee.service.IEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MyController.class)
@ActiveProfiles("test")
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    //To convert employee object to json string
    @Autowired
    ObjectMapper objectMapper ;

    @MockBean
    private IEmployeeService employeeService;

    private List<EmployeeAttributes> employeeList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.employeeList = new ArrayList<>();
        this.employeeList.add(new EmployeeAttributes(1,"A", "user1@gmail.com", "Java"));
        this.employeeList.add(new EmployeeAttributes(2,"B", "user2@gmail.com", "Android"));
        this.employeeList.add(new EmployeeAttributes(3,"C", "user3@gmail.com", "ML"));
    }


    @Test
    void getEmployeeDetails() throws Exception{
        when(employeeService.employeeDetails()).thenReturn(employeeList);

        this.mockMvc.perform(get("/employee_details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(employeeList.size())));
    }

    @Test
    void getEmployeeDetailsById() throws Exception{
         int empId=1;
//        EmployeeAttributes attributes = new EmployeeAttributes(1,"A","abc@gmail.com","Java");
//        BDDMockito.given(employeeService.employeeDetailsById(attributes.getEmployee_id())).willReturn(Optional.of(attributes));
//        this.mockMvc.perform(get("/employee_details{employee_id}" , empId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.employee_name", is(attributes.getEmployee_name())))
//                .andExpect(jsonPath("$.employee_email", is(attributes.getEmployee_email())))
//                .andExpect(jsonPath("$.employee_department", is(attributes.getEmployee_department())));

                EmployeeAttributes attributes = new EmployeeAttributes(empId,"A","abc@gmail.com",
                                                "Java");
                Mockito.when(employeeService.employeeDetailsById(empId)).thenReturn(Optional.of(attributes));
//                String URI = "/employee_details/1";
                RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee_details/{employee_id}",empId)
                                                .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                String expectedJson = objectMapper.writeValueAsString(attributes);
                String outputJson = result.getResponse().getContentAsString();
                assertThat(outputJson).isEqualTo(expectedJson);
    }

    @Test
    void addEmployeeDetails() throws Exception {
        EmployeeAttributes attributes = new EmployeeAttributes();
        attributes.setEmployee_id(202);
        attributes.setEmployee_name("ArjTrio");
        attributes.setEmployee_email("arjtrio@gmail.com");
        attributes.setEmployee_department("ML");

        //converting EmployeeAttributes object to Json String
        //by calling writeValueAsString() method of ObjectMapper class
        String jsonRequest = objectMapper.writeValueAsString(attributes);
        Mockito.when(employeeService.addDetails(Mockito.any(EmployeeAttributes.class))).thenReturn(attributes);
        //To hit the controller and get the result in the form of Mvc result
//        mockMvc.perform(post("/add_details")
//                        .content(jsonRequest)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.employee_id" , is(attributes.getEmployee_id())))
//                .andExpect(jsonPath("$.employee_name" , is(attributes.getEmployee_name())))
//                .andExpect(jsonPath("$.employee_email" , is(attributes.getEmployee_email())))
//                .andExpect(jsonPath("$.employee_department" , is(attributes.getEmployee_department())));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/add_details")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String jsonResponse = response.getContentAsString();

        assertThat(jsonResponse).isEqualTo(jsonRequest);
        assertEquals(HttpStatus.OK.value() , response.getStatus());
    }

    @Test
    void updateEmployeeDetails() throws Exception {
        EmployeeAttributes attributes = new EmployeeAttributes();
        attributes.setEmployee_id(202);
        attributes.setEmployee_name("ArjTrio");
        attributes.setEmployee_email("arjtrio@gmail.com");
        attributes.setEmployee_department("ML");

        //converting EmployeeAttributes object to Json String
        //by calling writeValueAsString() method of ObjectMapper class
        String jsonRequest = objectMapper.writeValueAsString(attributes);
        Mockito.when(employeeService.updateDetails(Mockito.any(EmployeeAttributes.class))).thenReturn(attributes);
        //To hit the controller and get the result in the form of Mvc result
        mockMvc.perform(put("/update_details")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee_id" , is(attributes.getEmployee_id())))
                .andExpect(jsonPath("$.employee_name" , is(attributes.getEmployee_name())))
                .andExpect(jsonPath("$.employee_email" , is(attributes.getEmployee_email())))
                .andExpect(jsonPath("$.employee_department" , is(attributes.getEmployee_department())));
    }

    @Test
    void deleteEmployeeDetails() throws Exception {
        int empId=1;
        EmployeeAttributes attributes = new EmployeeAttributes(empId,"A","abc@gmail.com","Java");

        doNothing().when(employeeService).deleteDetails(empId);
        mockMvc.perform(delete("/delete_details{employee_id}",attributes.getEmployee_id()));
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.employee_id" , is(attributes.getEmployee_id())))
//                .andExpect(jsonPath("$.employee_name" , is(attributes.getEmployee_name())))
//                .andExpect(jsonPath("$.employee_email" , is(attributes.getEmployee_email())))
//                .andExpect(jsonPath("$.employee_department" , is(attributes.getEmployee_department())));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete_details/{employee_id}",empId);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }
}