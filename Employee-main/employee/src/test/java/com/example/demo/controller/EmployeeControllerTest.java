package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.demo.exception.ResouceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Employee_info;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {


    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void CreateEmployeeTest() {

        Employee_info employee = new Employee_info("aishwarya", "ankalagi", "aish@gmail.com");
        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isPositive();
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void saveEmployeeTest() {

        Employee_info employee = new Employee_info("aishwarya", "ankalagi", "aish@gmail.com");
        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isPositive();
    }


    @Test
    @Order(3)
    public void getListOfEmployeesTest() {

        List<Employee_info> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isPositive();

    }

    /**
     * Method under test: {@link EmployeeController#createEmployee(Employee_info)}
     */
    @Test
    void testCreateEmployee() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        when(employeeRepository.save((Employee_info) any())).thenReturn(employee_info);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("42");
        employee_info1.setFirstName("Jane");
        employee_info1.setId(123L);
        employee_info1.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee_info1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"emailId\":\"42\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#createEmployee(Employee_info)}
     */
    @Test
    void testCreateEmployee2() throws Exception {
        when(employeeRepository.save((Employee_info) any())).thenThrow(new ResouceNotFoundException("An error occurred"));

        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee_info);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        doNothing().when(employeeRepository).delete((Employee_info) any());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/employees/{id}", 123L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"deleted\":true}"));
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        doThrow(new ResouceNotFoundException("An error occurred")).when(employeeRepository).delete((Employee_info) any());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/employees/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee3() throws Exception {
        doNothing().when(employeeRepository).delete((Employee_info) any());
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/employees/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employees");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees2() throws Exception {
        when(employeeRepository.findAll()).thenThrow(new ResouceNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employees");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees3() throws Exception {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/employees");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employees/{id}", 123L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"emailId\":\"42\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById2() throws Exception {
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employees/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById3() throws Exception {
        when(employeeRepository.findById((Long) any())).thenThrow(new ResouceNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employees/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Long, Employee_info)}
     */
    @Test
    void testUpdateEmployee() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        Optional<Employee_info> ofResult = Optional.of(employee_info);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("42");
        employee_info1.setFirstName("Jane");
        employee_info1.setId(123L);
        employee_info1.setLastName("Doe");
        when(employeeRepository.save((Employee_info) any())).thenReturn(employee_info1);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee_info employee_info2 = new Employee_info();
        employee_info2.setEmailId("42");
        employee_info2.setFirstName("Jane");
        employee_info2.setId(123L);
        employee_info2.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee_info2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/employees/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"emailId\":\"42\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Long, Employee_info)}
     */
    @Test
    void testUpdateEmployee2() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        when(employeeRepository.save((Employee_info) any())).thenThrow(new ResouceNotFoundException("An error occurred"));
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("42");
        employee_info1.setFirstName("Jane");
        employee_info1.setId(123L);
        employee_info1.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee_info1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/employees/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Long, Employee_info)}
     */
    @Test
    void testUpdateEmployee3() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("42");
        employee_info.setFirstName("Jane");
        employee_info.setId(123L);
        employee_info.setLastName("Doe");
        when(employeeRepository.save((Employee_info) any())).thenReturn(employee_info);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("42");
        employee_info1.setFirstName("Jane");
        employee_info1.setId(123L);
        employee_info1.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee_info1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/employees/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}