package com.example.demo;



import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Employee_info;
import com.example.demo.repository.EmployeeRepository;

	@DataJpaTest
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	public class EmployeeRepositoryTests {

	    @Autowired
	    private EmployeeRepository employeeRepository;

	    // JUnit test for saveEmployee
	    @Test
	    @Order(1)
	    @Rollback(value = false)
	    public void saveEmployeeTest(){

	    	Employee_info employee_info = new Employee_info("aishwarya","ankalagi","aish@gmail.com");
	        employeeRepository.save(employee_info);

	        Assertions.assertThat(employee_info.getId()).isPositive();
	    }

	    @Test
	    @Order(2)
	    public void getEmployeeTest(){

	        Employee_info employee_info = employeeRepository.findById(1L).get();

	        Assertions.assertThat(employee_info.getId()).isEqualTo(1L);

	    }

	    @Test
	    @Order(3)
	    public void getListOfEmployeesTest(){

	        List<Employee_info> employees = employeeRepository.findAll();

	        Assertions.assertThat(employees.size()).isPositive();

	    }
	    @Test
	    @Order(4)
	    @Rollback(value = false)
	    public void updateEmployeeTestid(){

	        Employee_info employee_info = employeeRepository.findById(1L).get();

	        employee_info.setId(1);

	        Employee_info employeeUpdated =  employeeRepository.save(employee_info);

	        Assertions.assertThat(employeeUpdated.getId()).isEqualTo(1);

	    }

	    @Test
	    @Order(5)
	    @Rollback(value = false)
	    public void updateEmployeeTest(){

	        Employee_info employee_info = employeeRepository.findById(1L).get();

	        employee_info.setEmailId("ram@gmail.com");

	        Employee_info employeeUpdated =  employeeRepository.save(employee_info);

	        Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("ram@gmail.com");

	    }
	    @Test
	    @Order(6)
	    @Rollback(value = false)
	    public void updateEmployeeTestFirstName(){

	        Employee_info employee_info = employeeRepository.findById(1L).get();

	        employee_info.setFirstName("swag");

	        Employee_info employeeUpdated =  employeeRepository.save(employee_info);

	        Assertions.assertThat(employeeUpdated.getFirstName()).isEqualTo("swag");

	    }
	    @Test
	    @Order(7)
	    @Rollback(value = false)
	    public void updateEmployeeTestLastName(){

	        Employee_info employee_info = employeeRepository.findById(1L).get();

	        employee_info.setLastName("Joshi");

	        Employee_info employeeUpdated =  employeeRepository.save(employee_info);

	        Assertions.assertThat(employeeUpdated.getLastName()).isEqualTo("Joshi");

	    }
	    @Test
	    @Order(8)
	    @Rollback(value = false)
	    public void deleteEmployeeTest(){

	        Employee_info employee_info = employeeRepository.findById(1L).get();

	        employeeRepository.delete(employee_info);
	    }
	   
}
	       
	    
	   
	 

