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
class EmployeeApplicationTests {
	
	 @Autowired
	    private EmployeeRepository employeeRepository;

	    // JUnit test for saveEmployee
	 @Test
	    @Order(1)
	    @Rollback(value = false)
	    public void CreateEmployeeTest(){

	    	Employee_info employee = new Employee_info("aishwarya","ankalagi","aish@gmail.com");
	        employeeRepository.save(employee);

	        Assertions.assertThat(employee.getId()).isPositive();
	    }
	 @Test
	    @Order(2)
	    @Rollback(value = false)
	    public void saveEmployeeTest(){

	    	Employee_info employee = new Employee_info("aishwarya","ankalagi","aish@gmail.com");
	        employeeRepository.save(employee);

	        Assertions.assertThat(employee.getId()).isPositive();
	    }
	   
	    

	    @Test
	    @Order(3)
	    public void getListOfEmployeesTest(){

	        List<Employee_info> employees = employeeRepository.findAll();

	        Assertions.assertThat(employees.size()).isPositive();

	    }
	   
	    	
	    


}
	